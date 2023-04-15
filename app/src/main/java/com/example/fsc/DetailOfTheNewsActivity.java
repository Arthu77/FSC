package com.example.fsc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DetailOfTheNewsActivity extends AppCompatActivity {

    String str_identity = "教师";

    TextView news_title;
    TextView news_text;

//    String str_title = "动态添加的资讯标题";
//    String str_text = "动态添加的内容\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n动态添加的内容";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_of_the_news);

        news_title = (TextView) findViewById(R.id.news_title);
        news_text = (TextView) findViewById(R.id.news_text);
        Intent intent=getIntent();
        String title= intent.getStringExtra("title");


//        news_title.setText(Html.fromHtml(str_title));
//        news_text.setText(Html.fromHtml(str_text));
        news_title.setText(title);
        new GetNewsDetailTask().execute(title);

    }

    public class GetNewsDetailTask extends AsyncTask<String,Integer,String> {
        protected String doInBackground(String... params) {//参数为字符串列表
            String responseMsg=null;
            try {
                OkHttpClient client=new OkHttpClient();
                FormBody formBody=new FormBody.Builder()
                        .add("Title",params[0])
                        .build();
                Request request=new Request.Builder()
                        .url("http://192.168.58.87:8080/Message/messageinfor")
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
            try {
                JSONObject jsonObject=new JSONObject(result);
                news_text.setText(jsonObject.getString("Conten"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Toast.makeText(DetailOfTheNewsActivity.this,result,Toast.LENGTH_SHORT).show();
        }
    }

}