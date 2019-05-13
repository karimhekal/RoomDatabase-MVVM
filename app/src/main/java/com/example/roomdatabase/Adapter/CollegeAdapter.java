package com.example.roomdatabase.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.roomdatabase.Model.College;
import com.example.roomdatabase.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CollegeAdapter extends RecyclerView.Adapter<CollegeAdapter.CollegeHolder> {
    private CollegeAdapter.OnCollegeClickListener listener;
    private List<College> colleges = new ArrayList<>();

    @NonNull
    @Override
    public CollegeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.college_item, parent, false);
        return new CollegeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CollegeHolder holder, int position) {
        College currentCollege = colleges.get(position);
        //   holder.textViewID.setText(String.valueOf(currentCollege.getCollegeID()));

        holder.textViewName.setText(currentCollege.getCollegeName());
    }


    @Override
    public int getItemCount() {
        return colleges.size();
    }

    public void setColleges(List<College> colleges) { // this method will be executed when live data are changed and it will be called in the "onChanged" method in ViewStudent and we give it a reference of the new colleges to change it entirely
        this.colleges = colleges;
        notifyDataSetChanged();
    }

    public College getCollegeAt(int position) {
        return colleges.get(position);
    }

    public void setOnItemClickListener(OnCollegeClickListener listener) {  // will call this method in ViewStudent to implement the interface through it
        this.listener = listener;
    }


    public interface OnCollegeClickListener {
        void onItemClick(College college);
    }

    class CollegeHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;  // 1 field
        //  private TextView textViewID;

        public CollegeHolder(@NonNull View itemView) {

            super(itemView);
            textViewName = itemView.findViewById(R.id.college_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition(); // getting position of clicked item
                    if (listener != null && position != RecyclerView.NO_POSITION) {  // to make sure we don't click on an invalid position
                        listener.onItemClick(colleges.get(position));
                    }
                }
            });
        }
    }

}
