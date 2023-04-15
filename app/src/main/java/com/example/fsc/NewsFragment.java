package com.example.fsc;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsFragment extends Fragment {

    TextView new_button1;
    TextView new_button2;
    TextView new_button3;
    TextView new_button4;
    TextView new_button5;
    TextView new_button6;

//    String str1="动态添加的第一条新闻标题";
//    String str2="动态添加的第二条新闻标题";
//    String str3="动态添加的第三条新闻标题";
//    String str4="动态添加的第四条新闻标题";
//    String str5="动态添加的第五条新闻标题";
//    String str6="动态添加的第六条新闻标题";

    Button last_page_news_bt;
    Button next_page_news_bt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_news, null);

        new_button1=(TextView)view.findViewById(R.id.new_button1);
        new_button2=(TextView)view.findViewById(R.id.new_button2);
        new_button3=(TextView)view.findViewById(R.id.new_button3);
        new_button4=(TextView)view.findViewById(R.id.new_button4);
        new_button5=(TextView)view.findViewById(R.id.new_button5);
        new_button6=(TextView)view.findViewById(R.id.new_button6);

        last_page_news_bt=(Button)view.findViewById(R.id.last_page_news_bt);
        next_page_news_bt=(Button)view.findViewById(R.id.next_page_news_bt);

//        new_button1.setText(Html.fromHtml(str1));
//        new_button2.setText(Html.fromHtml(str2));
//        new_button3.setText(Html.fromHtml(str3));
//        new_button4.setText(Html.fromHtml(str4));
//        new_button5.setText(Html.fromHtml(str5));
//        new_button6.setText(Html.fromHtml(str6));
//        new_button1.setText(str1);
//        new_button2.setText(str2);
//        new_button3.setText(str3);
//        new_button4.setText(str4);
//        new_button5.setText(str5);
//        new_button6.setText(str6);


        new_button1.setTextSize(30);
        new_button2.setTextSize(30);
        new_button3.setTextSize(30);
        new_button4.setTextSize(30);
        new_button5.setTextSize(30);
        new_button6.setTextSize(30);

        new GetNewsTask().execute();


        new_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),DetailOfTheNewsActivity.class);
                intent.putExtra("title",new_button1.getText().toString());
                startActivity(intent);
            }
        });

        new_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),DetailOfTheNewsActivity.class);
                intent.putExtra("title",new_button2.getText().toString());
                startActivity(intent);
            }
        });

        new_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),DetailOfTheNewsActivity.class);
                intent.putExtra("title",new_button3.getText().toString());
                startActivity(intent);
            }
        });

        new_button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),DetailOfTheNewsActivity.class);
                intent.putExtra("title",new_button4.getText().toString());
                startActivity(intent);
            }
        });

        new_button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),DetailOfTheNewsActivity.class);
                intent.putExtra("title",new_button5.getText().toString());
                startActivity(intent);
            }
        });

        new_button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),DetailOfTheNewsActivity.class);
                intent.putExtra("title",new_button6.getText().toString());
                startActivity(intent);
            }
        });

        last_page_news_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"点击了上一页按钮",Toast.LENGTH_SHORT).show();
            }
        });

        next_page_news_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"点击了下一页按钮",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public class GetNewsTask extends AsyncTask<String,Integer,String> {
        protected String doInBackground(String... params) {//参数为字符串列表
            String responseMsg=null;
            try {
                OkHttpClient client=new OkHttpClient();
                FormBody formBody=new FormBody.Builder()
                        .build();
                Request request=new Request.Builder()
//                        .url("http://192.168.58.87:8080/Message/select")
                        .url("http://192.168.45.38:8080/Message/select")
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
//            try {
//                JSONObject jsonObject=new JSONObject(result);
//                new_button1.setText(jsonObject.getString("Msg_Title"));
//                new_button2.setText("dsakfjjf");
////                new_button3.setText(jsonObject.getString("Msg_Title"));
////                new_button4.setText(jsonObject.getString("Msg_Title"));
////                new_button5.setText(jsonObject.getString("Msg_Title"));
////                new_button6.setText(jsonObject.getString("Msg_Title"));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            ParamService paramService=new ParamService();
//            Map<String,String> map=null;
//            try {
//                map=paramService.getStringToMap(result);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            while(it.hasNext()) {
//
//                new_button1.setText(map.get(key));
//                new_button2.setText(map.get(key));
//                new_button3.setText(map.get(key));
//                new_button4.setText(map.get(key));
//                new_button5.setText(map.get(key));
//                new_button6.setText(map.get(key));
//
//            }
            String[] array=result.substring(1,result.indexOf("]")).split(",");
            int i=1;
            try {
                JSONObject jsonObject=new JSONObject(array[0]);
                new_button1.setText(jsonObject.getString("Msg_Title"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            i++;
            if(i>array.length)return;
            try {
                JSONObject jsonObject=new JSONObject(array[1]);
                new_button2.setText(jsonObject.getString("Msg_Title"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            i++;
            if(i>array.length)return;
            try {
                JSONObject jsonObject=new JSONObject(array[2]);
                new_button3.setText(jsonObject.getString("Msg_Title"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            i++;
            if(i>array.length)return;
            try {
                JSONObject jsonObject=new JSONObject(array[3]);
                new_button4.setText(jsonObject.getString("Msg_Title"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            i++;
            if(i>array.length)return;
            try {
                JSONObject jsonObject=new JSONObject(array[4]);
                new_button5.setText(jsonObject.getString("Msg_Title"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            i++;
            if(i>array.length)return;
            try {
                JSONObject jsonObject=new JSONObject(array[5]);
                new_button6.setText(jsonObject.getString("Msg_Title"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            i++;
            if(i>array.length)return;
//            Toast.makeText(getActivity(),result,Toast.LENGTH_SHORT).show();
//            Toast.makeText(getActivity(),map.toString(),Toast.LENGTH_SHORT).show();

//            if(result.equals("success")){
//            }
//            else {
//                Toast.makeText(getActivity(),"登陆失败",Toast.LENGTH_SHORT).show();
//            }
//            Toast.makeText(getActivity(),result,Toast.LENGTH_SHORT).show();
        }
    }
}