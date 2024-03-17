package com.uyuanx.randompicker;

import android.app.Fragment;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PickerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PickerFragment extends Fragment {

    boolean isOnAnimation = false;

    public PickerFragment() {
    }

    public static PickerFragment newInstance(String param1, String param2) {
        PickerFragment fragment = new PickerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_picker, container, false);

        RecyclerView recyclerView = mainView.findViewById(R.id.picker_list);
        EditText editText = mainView.findViewById(R.id.picker_add_text);
        ImageButton addBtn = mainView.findViewById(R.id.picker_add_btn);
        ImageButton pickBtn = mainView.findViewById(R.id.picker_btn);

        TextView pickedTxt = mainView.findViewById(R.id.picker_text);

        List<String> list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(mainView.getContext()));
        MyAdapter adapter = new MyAdapter(list);

        Animation pickAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.pick_anim);
        pickAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isOnAnimation = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isOnAnimation = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = editText.getText().toString();
                if (!str.isEmpty()) {
                    list.add(str);
                    adapter.notifyDataSetChanged();
                    editText.setText("");
                }
            }
        });

        pickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isOnAnimation && !list.isEmpty()) {
                    int max = list.size();
                    int ran = (int) (Math.random() * max);
                    pickedTxt.setText(list.get(ran));
                    pickedTxt.startAnimation(pickAnim);
                }
            }
        });


        recyclerView.setAdapter(adapter);

        return mainView;
    }


    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private List<String> list;

        public MyAdapter(List<String> list) {
            this.list = list;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.list_item, viewGroup, false);
            return new MyViewHolder(view);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView title;
            public ImageView deleteBtn;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.picker_item);
                deleteBtn = itemView.findViewById(R.id.picker_delete);
            }
        }

        @Override
        public void onBindViewHolder(MyViewHolder viewHolder, int position) {
            String text = list.get(position);
            viewHolder.title.setText(text);
            viewHolder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = viewHolder.getAdapterPosition();
                    list.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, list.size());
                }
            });
        }

    }
}