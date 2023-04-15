package com.example.fsc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeworkActivity extends AppCompatActivity {
    TextView homework_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);
        Intent intent=getIntent();
        String classID= intent.getStringExtra("classID");


//        Homework homework=new Homework("1601","默写《出师表》100遍\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        homework_content=(TextView)findViewById(R.id.homework_text);
        new ViewHomeworkTask().execute(classID);
//        homework_content.setText(homework.getHomework());
    }

    public class ViewHomeworkTask extends AsyncTask<String,Integer,String> {
        protected String doInBackground(String... params) {//参数为字符串列表
            String responseMsg=null;
            try {
                OkHttpClient client=new OkHttpClient();
                FormBody formBody=new FormBody.Builder()
                        .add("ID",params[0])
                        .build();
                Request request=new Request.Builder()
                        .url("http://192.168.58.87:8080/Message/homework")
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
            ParamService paramService=new ParamService();
            homework_content.setText(paramService.getJsonArrayValue(result,0,"Conten"));
            Toast.makeText(HomeworkActivity.this,result,Toast.LENGTH_SHORT).show();
        }
    }

}