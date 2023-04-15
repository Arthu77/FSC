package com.example.fsc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        TextView comment_student_name = (TextView)findViewById(R.id.comment_student_name);
        TextView comment_text=(TextView)findViewById(R.id.comment_text);



        comment_student_name.setText("动添加的学生的名字");
        comment_text.setText("动态添加的内容\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n动态添加的内容");
        
    }
}