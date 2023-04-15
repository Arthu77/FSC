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

public class AddNoticeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice);
        Intent intent=getIntent();
        String classID=intent.getStringExtra("classID");
        String account=intent.getStringExtra("account");

        EditText notice_content=(EditText)findViewById(R.id.et_notice_content);
        Button btn_add_notice=(Button)findViewById(R.id.btn_add_notice);

//        Notice notice=new Notice("1601","周五开家长会");

//        notice_content.setText(notice.getNotice_content());

        btn_add_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String conten= notice_content.getText().toString();
                new AddNoticeTask().execute(classID,account,conten);
//                Toast.makeText(AddNoticeActivity.this,notice.getNotice_content(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    public class AddNoticeTask extends AsyncTask<String,Integer,String> {
        protected String doInBackground(String... params) {//参数为字符串列表
            String responseMsg=null;
            try {
                OkHttpClient client=new OkHttpClient();
                FormBody formBody=new FormBody.Builder()
                        .add("ClassID",params[0])
                        .add("TeacherID",params[1])
                        .add("Conten",params[2])
                        .build();
                Request request=new Request.Builder()
                        .url("http://192.168.58.87:8080/Teacher/communicateinsert")
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
            Toast.makeText(AddNoticeActivity.this,result,Toast.LENGTH_SHORT).show();
        }
    }

}
