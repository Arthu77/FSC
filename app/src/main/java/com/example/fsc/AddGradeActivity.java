package com.example.fsc;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AddGradeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grade);
        Intent intent=getIntent();
        String std_name=intent.getStringExtra("std_name");
        String std_id=intent.getStringExtra("std_id");
        String std_grade=intent.getStringExtra("std_grade");
        String std_class=intent.getStringExtra("std_class");

        TextView tv_grade_name=(TextView) findViewById(R.id.tv_grade_name);
        EditText add_grade_et=(EditText)findViewById(R.id.add_grade_et);

        tv_grade_name.setText(std_name);
        add_grade_et.setText(std_grade);
        Button btn_add_grade=(Button)findViewById(R.id.btn_add_grade);

        btn_add_grade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(AddGradeActivity.this,std_name,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class TeacherGradeTask extends AsyncTask<String,Integer,String> {
        protected String doInBackground(String... params) {//参数为字符串列表
            String responseMsg=null;
            try {
                OkHttpClient client=new OkHttpClient();
                FormBody formBody=new FormBody.Builder()
                        .add("ID",params[0])
                        .build();
                Request request=new Request.Builder()
                        .url("http://192.168.58.87:8080/Teacher/gradeupdate")
                        .post(formBody)
                        .build();
                Response response=client.newCall(request).execute();
                responseMsg=response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return responseMsg;
        }

        protected void onProgressUpdate(Integer... progress) { //更新进度。这里的参数是Integer对象

        }

        protected void onPostExecute(String result) { //执行结果回调
            Toast.makeText(AddGradeActivity.this,result,Toast.LENGTH_SHORT).show();
        }
    }

}