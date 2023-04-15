package com.example.fsc;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddCommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //隐藏标题栏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
        Intent intent=getIntent();
        String std_name=intent.getStringExtra("std_name");
        String std_id=intent.getStringExtra("std_id");
        String std_comment=intent.getStringExtra("std_comment");
        String std_class=intent.getStringExtra("std_class");

        TextView et_number_comment=(TextView) findViewById(R.id.tv_comment_name);
        EditText add_comment_et=(EditText)findViewById(R.id.add_comment_et);
        Button btn_add_comment=(Button)findViewById(R.id.btn_add_comment);

        et_number_comment.setText(std_name);
        add_comment_et.setText(std_comment);

        btn_add_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(AddCommentActivity.this,std_name,Toast.LENGTH_SHORT).show();
            }
        });
    }
}