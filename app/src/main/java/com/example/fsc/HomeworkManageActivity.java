package com.example.fsc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeworkManageActivity extends AppCompatActivity {
    EditText add_homework_et;
    String classID=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework_manage);

        add_homework_et=(EditText)findViewById(R.id.add_homework_et);
        EditText et_homework_class=(EditText)findViewById(R.id.et_homework_class);
        Button btn_add_homework=(Button)findViewById(R.id.btn_add_homework);
//        Homework day1=new Homework("1601","akjsdhfjkhasd");
        Intent intent=getIntent();
//        add_homework_et.setText(day1.getHomework());
        et_homework_class.setText(intent.getStringExtra("classID"));
        classID=intent.getStringExtra("classID");
        new TViewHomeworkTask().execute(classID);

        btn_add_homework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_add_homework=add_homework_et.getText().toString();
                new UpdateHomeworkTask().execute(classID,str_add_homework);
            }
        });
    }

    public class UpdateHomeworkTask extends AsyncTask<String,Integer,String> {
        protected String doInBackground(String... params) {//参数为字符串列表
            String responseMsg=null;
            try {
                OkHttpClient client=new OkHttpClient();
                FormBody formBody=new FormBody.Builder()
                        .add("ID",params[0])
                        .add("Conten",params[1])
                        .build();
                Request request=new Request.Builder()
                        .url("http://192.168.58.87:8080/Teacher/homeworkinsert")
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
//            ParamService paramService=new ParamService();
//            add_homework_et.setText(paramService.getJsonArrayValue(result,0,"Text"));
            Toast.makeText(HomeworkManageActivity.this,result,Toast.LENGTH_SHORT).show();
        }
    }

    public class TViewHomeworkTask extends AsyncTask<String,Integer,String> {
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
            add_homework_et.setText(paramService.getJsonArrayValue(result,0,"Conten"));
//            Toast.makeText(HomeworkManageActivity.this,result,Toast.LENGTH_SHORT).show();
        }
    }

}