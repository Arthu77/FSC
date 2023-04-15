package com.example.fsc;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AddActivityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activity);
        EditText activity_title=(EditText)findViewById(R.id.et_activity_title);
        EditText activity_content=(EditText)findViewById(R.id.et_activity_content);
        Button btn_add_activity=(Button)findViewById(R.id.btn_add_activity);
        Intent intent=getIntent();
        String classID=intent.getStringExtra("classID");
        String activityId=intent.getStringExtra("activityId");
//        Activity activity=new Activity("1601","班级足球赛","今天举行了班级足球赛！");

//        activity_title.setText(activity.getTitle());
//        activity_content.setText(activity.getActivity_content());

        btn_add_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=activity_title.getText().toString();
                String content=activity_content.getText().toString();
                new AddActivityTask().execute(classID,content,title);
            }
        });

    }

    public class AddActivityTask extends AsyncTask<String,Integer,String> {
        protected String doInBackground(String... params) {//参数为字符串列表
            String responseMsg=null;
            try {
                OkHttpClient client=new OkHttpClient();
                FormBody formBody=new FormBody.Builder()
                        .add("ID",params[0])
                        .add("Conten",params[1])
                        .add("Title",params[2])
                        .build();
                Request request=new Request.Builder()
                        .url("http://192.168.58.87:8080/Teacher/activityinsert")
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
            Toast.makeText(AddActivityActivity.this,result,Toast.LENGTH_SHORT).show();
        }
    }

}
