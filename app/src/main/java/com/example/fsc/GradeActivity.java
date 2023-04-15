package com.example.fsc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class GradeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);

        Grade grade=new Grade("张三","1601","2019091601001","语文79，数学89，英语82，物理89，化学53，生物76");

        TextView grade_content=(TextView)findViewById(R.id.grade_text);
        TextView grade_name=(TextView)findViewById(R.id.grade_name);
        TextView grade_stdid=(TextView)findViewById(R.id.grade_std_id);
        grade_content.setText(grade.getGrade());
        grade_name.setText(grade.getName());
        grade_stdid.setText(grade.getStdid());

    }
}