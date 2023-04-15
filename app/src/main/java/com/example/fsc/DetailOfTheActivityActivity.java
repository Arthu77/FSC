package com.example.fsc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DetailOfTheActivityActivity extends AppCompatActivity {
    private static final String TAG = "DetailOfTheActivity";

//    String str_identity="教师";

    ImageView edit_activity;

    TextView activity_title;
    TextView activity_text;
    TextView activity_time;
    String title;
    String activityId;

//    String str_title="动态添加的活动标题";
//    String str_text="动态添加的内容\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n动态添加的内容";
//    String str_time="2021-08-01";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_of_the_activity);
        Intent intent =getIntent();
        title= intent.getStringExtra("title");
        String time=intent.getStringExtra("time");
        String text=intent.getStringExtra("text");
        String str_identity=intent.getStringExtra("activity_id");
//        str_text=str_identity;
        String activity_id=str_identity.substring(0,1);
//        Log.e(TAG, "onCreate: "+str_identity+" "+activity_id);

        edit_activity=(ImageView)findViewById(R.id.edit_activity);
        edit_activity.setClickable(true);

        activity_title=(TextView)findViewById(R.id.activity_title);
        activity_text=(TextView)findViewById(R.id.activity_text);
        activity_time=(TextView)findViewById(R.id.activity_time);

//        activity_title.setText(Html.fromHtml(str_title));
//        activity_text.setText(Html.fromHtml(str_text));
//        activity_time.setText(Html.fromHtml(str_time));
        activity_title.setText(title);
        activity_text.setText(text);
        activity_time.setText(time);

        edit_activity.setVisibility(View.GONE);

        if (str_identity.equals("1教师")||str_identity.equals("2教师")||str_identity.equals("3教师")||str_identity.equals("4教师")){
            edit_activity.setVisibility(View.VISIBLE);
        }

        edit_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailOfTheActivityActivity.this,"点击了编辑按钮",Toast.LENGTH_SHORT).show();
                initPopupWindow(view);
            }
        });
    }

    private void initPopupWindow(View v){
        View view= LayoutInflater.from(DetailOfTheActivityActivity.this).inflate(R.layout.activity_popup,null,false);
        Button edit_bt_activity=(Button)view.findViewById(R.id.edit_bt_activity);
        edit_bt_activity.setBackgroundColor(Color.parseColor("#F4F5F9"));
        Button delete_bt_activity=(Button)view.findViewById(R.id.delete_bt_activity);
        delete_bt_activity.setBackgroundColor(Color.parseColor("#F4F5F9"));

        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);

        popupWindow.setAnimationStyle(R.anim.anim_popup);

        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });

        popupWindow.showAsDropDown(v,-80,0);

        edit_bt_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(DetailOfTheActivityActivity.this,"点击了编辑按钮",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(DetailOfTheActivityActivity.this,AddActivityActivity.class);
                intent.putExtra("activityId",activityId);
                startActivity(intent);
                popupWindow.dismiss();
            }
        });

        delete_bt_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DeleteActivityTask().execute(title);
//                Toast.makeText(DetailOfTheActivityActivity.this,"点击了删除按钮",Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });
    }

    public class DeleteActivityTask extends AsyncTask<String,Integer,String> {
        protected String doInBackground(String... params) {//参数为字符串列表
            String responseMsg=null;
            try {
                OkHttpClient client=new OkHttpClient();
                FormBody formBody=new FormBody.Builder()
                        .add("Title",params[0])
                        .build();
                Request request=new Request.Builder()
                        .url("http://192.168.58.87:8080/Teacher/activitydelete")
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
            Toast.makeText(DetailOfTheActivityActivity.this,result,Toast.LENGTH_SHORT).show();
        }
    }



}