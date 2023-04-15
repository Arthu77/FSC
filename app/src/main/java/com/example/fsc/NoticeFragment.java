package com.example.fsc;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NoticeFragment extends Fragment {
    private static final String TAG = "NoticeFragment";

//    String str_identity = "老师";

//    LinearLayout notice_ll1;
//    LinearLayout notice_ll2;

    TextView notice_time1;
    TextView notice_time2;

    TextView notice_text1;
    TextView notice_text2;

    String str_time1 = "2021-7-31 23:18";
    String str_time2 = "2021-7-30 22:07";

    String str_text1 = "动态添加的通知内容一";
    String str_text2 = "动态添加的通知内容二";

    Button edit_notice1;
    Button edit_notice2;
    Button last_page_notice_bt;
    Button next_page_notice_bt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice, null);
        MainActivity mainActivity = (MainActivity) getActivity();
        String str_identity = mainActivity.getIdentity();
        String account=mainActivity.getAccount();
        Log.e(TAG, "onCreateView: " + str_identity);

//        notice_ll1 = (LinearLayout) view.findViewById(R.id.notice_ll1);
//        notice_ll2 = (LinearLayout) view.findViewById(R.id.notice_ll2);

//        notice_ll1.setClickable(true);
//        notice_ll2.setClickable(true);

        notice_time1 = (TextView) view.findViewById(R.id.notice_time1);
        notice_time2 = (TextView) view.findViewById(R.id.notice_time2);

        notice_text1 = (TextView) view.findViewById(R.id.notice_text1);
        notice_text2 = (TextView) view.findViewById(R.id.notice_text2);

//        notice_time1.setText(Html.fromHtml(str_time1));
//        notice_time2.setText(Html.fromHtml(str_time2));
        notice_time1.setText("");
        notice_time2.setText("");

        notice_time1.setTextSize(15);
        notice_time2.setTextSize(15);

//        notice_text1.setText(Html.fromHtml(str_text1));
//        notice_text2.setText(Html.fromHtml(str_text2));
        notice_text1.setText("");
        notice_text2.setText("");

        notice_text1.setTextSize(20);
        notice_text2.setTextSize(20);

        edit_notice1 = (Button) view.findViewById(R.id.edit_notice1);
        edit_notice2 = (Button) view.findViewById(R.id.edit_notice2);
        last_page_notice_bt = (Button) view.findViewById(R.id.last_page_notice_bt);
        next_page_notice_bt = (Button) view.findViewById(R.id.next_page_notice_bt);


        edit_notice1.setVisibility(View.GONE);
        edit_notice2.setVisibility(View.GONE);

        if (str_identity.equals("教师")) {
            //设置可见性
            edit_notice1.setVisibility(View.VISIBLE);
            edit_notice2.setVisibility(View.VISIBLE);
        }

        new ViewNoticeTask().execute(account);




        edit_notice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopupWindowOne(v);
            }
        });

        edit_notice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopupWindowTwo(v);
            }
        });

        last_page_notice_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "点击了上一页按钮", Toast.LENGTH_SHORT).show();
            }
        });

        next_page_notice_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "点击了下一页按钮", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void initPopupWindowOne(View v) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_popup, null, true);
        Button edit_bt_activity=(Button)view.findViewById(R.id.edit_bt_activity);
        edit_bt_activity.setBackgroundColor(Color.parseColor("#F4F5F9"));
        Button delete_bt_activity=(Button)view.findViewById(R.id.delete_bt_activity);
        delete_bt_activity.setBackgroundColor(Color.parseColor("#F4F5F9"));

        final PopupWindow popupWindow=new PopupWindow(view,ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);

        popupWindow.setAnimationStyle(R.anim.anim_popup);

        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });

        popupWindow.showAsDropDown(v,0,0);

        edit_bt_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"点击了编辑按钮",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),AddNoticeActivity.class);
                startActivity(intent);
                popupWindow.dismiss();
            }
        });

        delete_bt_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"点击了删除按钮",Toast.LENGTH_SHORT).show();
                String text= notice_text1.getText().toString();
                new DeleteNoticeTask().execute(text);
                popupWindow.dismiss();
            }
        });
    }

    private void initPopupWindowTwo(View v) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_popup, null, true);
        Button edit_bt_activity=(Button)view.findViewById(R.id.edit_bt_activity);
        edit_bt_activity.setBackgroundColor(Color.parseColor("#F4F5F9"));
        Button delete_bt_activity=(Button)view.findViewById(R.id.delete_bt_activity);
        delete_bt_activity.setBackgroundColor(Color.parseColor("#F4F5F9"));

        final PopupWindow popupWindow=new PopupWindow(view,ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);

        popupWindow.setAnimationStyle(R.anim.anim_popup);

        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });

        popupWindow.showAsDropDown(v,0,0);

        edit_bt_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"点击了编辑按钮",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),AddNoticeActivity.class);
                startActivity(intent);
                popupWindow.dismiss();
            }
        });

        delete_bt_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"点击了删除按钮",Toast.LENGTH_SHORT).show();
                String text= notice_text2.getText().toString();
                new DeleteNoticeTask().execute(text);
                popupWindow.dismiss();
            }
        });
    }


    public class ViewNoticeTask extends AsyncTask<String,Integer,String> {
        protected String doInBackground(String... params) {//参数为字符串列表
            String responseMsg=null;
            try {
                OkHttpClient client=new OkHttpClient();
                FormBody formBody=new FormBody.Builder()
                        .add("ID",params[0])
                        .build();
                Request request=new Request.Builder()
                        .url("http://192.168.45.38:8080/Message/communicate")
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
            String string1=result;

            String string2=string1.substring(string1.indexOf(':')+2);
            String string1_1=string2.substring(0,string2.indexOf('"'));
            String string3=string2.substring(string2.indexOf(':')+2);
            String string1_2=string3.substring(0,string3.indexOf('"'));
            notice_text1.setText(string1_1);
            notice_time1.setText(string1_2);


            String string4=string3.substring(string3.indexOf(':')+2);
            String string2_1=string4.substring(0,string4.indexOf('"'));
            String string5=string4.substring(string4.indexOf(':')+2);
            String string2_2=string5.substring(0,string5.indexOf('"'));
            notice_text2.setText(string2_1);
            notice_time2.setText(string2_2);



            Toast.makeText(getActivity(),result,Toast.LENGTH_SHORT).show();
        }
    }

    public class DeleteNoticeTask extends AsyncTask<String,Integer,String> {
        protected String doInBackground(String... params) {//参数为字符串列表
            String responseMsg=null;
            try {
                OkHttpClient client=new OkHttpClient();
                FormBody formBody=new FormBody.Builder()
                        .add("Conten",params[0])
                        .build();
                Request request=new Request.Builder()
                        .url("http://192.168.58.87:8080/Teacher/communicatedelete")
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

            Toast.makeText(getActivity(),result,Toast.LENGTH_SHORT).show();
        }
    }

}