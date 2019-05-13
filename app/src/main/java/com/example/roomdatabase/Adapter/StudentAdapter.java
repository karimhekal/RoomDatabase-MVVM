package com.example.roomdatabase.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.roomdatabase.Model.Student;
import com.example.roomdatabase.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentHolder> {

    private List<Student> students = new ArrayList<>();
    private OnStudentClickListener listener;

    @NonNull
    @Override
    public StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new StudentHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentHolder holder, int position) {
        Student currentStudent = students.get(position);
        holder.textViewName.setText(currentStudent.getName());
        holder.textViewCGPA.setText(currentStudent.getCgpa());
        holder.textViewID.setText(String.valueOf(currentStudent.getId()));
        holder.textViewGender.setText(currentStudent.getGender());
        holder.textViewCollege.setText(currentStudent.getCollege());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public void setStudents(List<Student> students) { // this method will be executed when live data are changed and it will be called in the "onChanged" method in ViewStudent and we give it a reference of the new students to change it entirely
        this.students = students;
        notifyDataSetChanged();
    }

    public Student getStudentAt(int position) {
        return students.get(position);
    }

    public void setOnItemClickListener(OnStudentClickListener listener) {  // will call this method in ViewStudent to implement the interface through it
        this.listener = listener;
    }

    public interface OnStudentClickListener {
        void onItemClick(Student student);
    }

    class StudentHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;  // 5 fields
        private TextView textViewCGPA;
        private TextView textViewCollege;
        private TextView textViewGender;
        private TextView textViewID;

        public StudentHolder(@NonNull View itemView) {

            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewCGPA = itemView.findViewById(R.id.text_view_CGPA);
            textViewCollege = itemView.findViewById(R.id.text_view_college);
            textViewGender = itemView.findViewById(R.id.text_view_gender);
            textViewID = itemView.findViewById(R.id.text_view_id);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition(); // getting position of clicked item
                    if (listener != null && position != RecyclerView.NO_POSITION) {  // to make sure we don't click on an invalid position
                        listener.onItemClick(students.get(position));
                    }
                }
            });
        }
    }
}
