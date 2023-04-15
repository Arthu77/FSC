package com.example.fsc;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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

public class ActivityFragment extends Fragment {

    LinearLayout activity_ll1;
    LinearLayout activity_ll2;
    LinearLayout activity_ll3;
    LinearLayout activity_ll4;

    TextView activity_time1;
    TextView activity_time2;
    TextView activity_time3;
    TextView activity_time4;

    TextView activity_title1;
    TextView activity_title2;
    TextView activity_title3;
    TextView activity_title4;


    TextView activity_text1;
    TextView activity_text2;
    TextView activity_text3;
    TextView activity_text4;

    String str_time1 = "2021-07-31 16:54";
    String str_time2 = "2021-07-30 15:43";
    String str_time3 = "2021-07-29 14:32";
    String str_time4 = "2021-07-28 13:21";

    String str_title1 = "动态添加的活动标题1";
    String str_title2 = "动态添加的活动标题1";
    String str_title3 = "动态添加的活动标题1";
    String str_title4 = "动态添加的活动标题1";

    String str_text1 = "动态添加的活动内容1动态添加的活动内容1动态添加的活动内容1动态添加的活动内容1动态添加的活动内容1动态添加的活动内容1动态添加的活动内容1动态添加的活动内容1动态添加的活动内容1动态添加的活动内容1动态添加的活动内容1动态添加的活动内容1动态添加的活动内容1";
    String str_text2 = "动态添加的活动内容2动态添加的活动内容2动态添加的活动内容2动态添加的活动内容2动态添加的活动内容2动态添加的活动内容2动态添加的活动内容2动态添加的活动内容2动态添加的活动内容2动态添加的活动内容2动态添加的活动内容2动态添加的活动内容2动态添加的活动内容2";
    String str_text3 = "动态添加的活动内容3动态添加的活动内容3动态添加的活动内容3动态添加的活动内容3动态添加的活动内容3动态添加的活动内容3动态添加的活动内容3动态添加的活动内容3动态添加的活动内容3动态添加的活动内容3动态添加的活动内容3动态添加的活动内容3动态添加的活动内容3";
    String str_text4 = "动态添加的活动内容4动态添加的活动内容4动态添加的活动内容4动态添加的活动内容4动态添加的活动内容4动态添加的活动内容4动态添加的活动内容4动态添加的活动内容4动态添加的活动内容4动态添加的活动内容4动态添加的活动内容4动态添加的活动内容4动态添加的活动内容4";

    Button last_page_activity_bt;
    Button next_page_activity_bt;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_activity, null);
        MainActivity mainActivity = (MainActivity) getActivity();
        String str_identity = mainActivity.getIdentity();
        String account=mainActivity.getAccount();

        activity_ll1=(LinearLayout)view.findViewById(R.id.activity_ll1);
        activity_ll2=(LinearLayout)view.findViewById(R.id.activity_ll2);
        activity_ll3=(LinearLayout)view.findViewById(R.id.activity_ll3);
        activity_ll4=(LinearLayout)view.findViewById(R.id.activity_ll4);

        activity_ll1.setClickable(true);
        activity_ll2.setClickable(true);
        activity_ll3.setClickable(true);
        activity_ll4.setClickable(true);

        activity_time1=(TextView)view.findViewById(R.id.activity_time1);
        activity_time2=(TextView)view.findViewById(R.id.activity_time2);
        activity_time3=(TextView)view.findViewById(R.id.activity_time3);
        activity_time4=(TextView)view.findViewById(R.id.activity_time4);

        activity_title1=(TextView)view.findViewById(R.id.activity_title1);
        activity_title2=(TextView)view.findViewById(R.id.activity_title2);
        activity_title3=(TextView)view.findViewById(R.id.activity_title3);
        activity_title4=(TextView)view.findViewById(R.id.activity_title4);

        activity_text1=(TextView)view.findViewById(R.id.activity_text1);
        activity_text2=(TextView)view.findViewById(R.id.activity_text2);
        activity_text3=(TextView)view.findViewById(R.id.activity_text3);
        activity_text4=(TextView)view.findViewById(R.id.activity_text4);

//        activity_time1.setText(Html.fromHtml(str_time1));
//        activity_time2.setText(Html.fromHtml(str_time2));
//        activity_time3.setText(Html.fromHtml(str_time3));
//        activity_time4.setText(Html.fromHtml(str_time4));
        activity_time1.setText("");
        activity_time2.setText("");
        activity_time3.setText("");
        activity_time4.setText("");

        activity_time1.setTextSize(15);
        activity_time2.setTextSize(15);
        activity_time3.setTextSize(15);
        activity_time4.setTextSize(15);

//        activity_title1.setText(Html.fromHtml(str_title1));
//        activity_title2.setText(Html.fromHtml(str_title2));
//        activity_title3.setText(Html.fromHtml(str_title3));
//        activity_title4.setText(Html.fromHtml(str_title4));

        activity_title1.setText("");
        activity_title2.setText("");
        activity_title3.setText("");
        activity_title4.setText("");

        activity_title1.setTextSize(20);
        activity_title2.setTextSize(20);
        activity_title3.setTextSize(20);
        activity_title4.setTextSize(20);


//        if (str_text1.length()>70){
//            str_text1=str_text1.substring(0,67)+"...";
//        }
//        if (str_text2.length()>70){
//            str_text2=str_text2.substring(0,67)+"...";
//        }
//        if (str_text3.length()>70){
//            str_text3=str_text3.substring(0,67)+"...";
//        }
//        if (str_text4.length()>70){
//            str_text4=str_text4.substring(0,67)+"...";
//        }
//        activity_text1.setText(Html.fromHtml(str_text1));
//        activity_text2.setText(Html.fromHtml(str_text2));
//        activity_text3.setText(Html.fromHtml(str_text3));
//        activity_text4.setText(Html.fromHtml(str_text4));

        activity_text1.setText("");
        activity_text2.setText("");
        activity_text3.setText("");
        activity_text4.setText("");

        activity_text1.setTextSize(15);
        activity_text2.setTextSize(15);
        activity_text3.setTextSize(15);
        activity_text4.setTextSize(15);




        last_page_activity_bt=(Button)view.findViewById(R.id.last_page_activity_bt);
        next_page_activity_bt=(Button)view.findViewById(R.id.next_page_activity_bt);

        new ViewActivityTask().execute(account);

        activity_ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=activity_title1.getText().toString();
                String time=activity_time1.getText().toString();
                String text=activity_text1.getText().toString();
                Intent intent=new Intent(getActivity(),DetailOfTheActivityActivity.class);
                intent.putExtra("activity_id","1"+str_identity);
                intent.putExtra("title",title);
                intent.putExtra("time",time);
                intent.putExtra("text",text);
                startActivity(intent);
            }
        });

        activity_ll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=activity_title2.getText().toString();
                String time=activity_time2.getText().toString();
                String text=activity_text2.getText().toString();
                Intent intent=new Intent(getActivity(),DetailOfTheActivityActivity.class);
                intent.putExtra("activity_id","2"+str_identity);
                intent.putExtra("title",title);
                intent.putExtra("time",time);
                intent.putExtra("text",text);
                startActivity(intent);
            }
        });

        activity_ll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=activity_title3.getText().toString();
                String time=activity_time3.getText().toString();
                String text=activity_text3.getText().toString();
                Intent intent=new Intent(getActivity(),DetailOfTheActivityActivity.class);
                intent.putExtra("activity_id","3"+str_identity);
                intent.putExtra("title",title);
                intent.putExtra("time",time);
                intent.putExtra("text",text);
                startActivity(intent);
            }
        });

        activity_ll4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=activity_title4.getText().toString();
                String time=activity_time4.getText().toString();
                String text=activity_text4.getText().toString();
                Intent intent=new Intent(getActivity(),DetailOfTheActivityActivity.class);
                intent.putExtra("activity_id","4"+str_identity);
                intent.putExtra("title",title);
                intent.putExtra("time",time);
                intent.putExtra("text",text);
                startActivity(intent);
            }
        });

        last_page_activity_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"点击了上一页按钮",Toast.LENGTH_SHORT).show();
            }
        });

        next_page_activity_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"点击了下一页按钮",Toast.LENGTH_SHORT).show();
            }
        });



        return view;
    }

    public class ViewActivityTask extends AsyncTask<String,Integer,String> {
        protected String doInBackground(String... params) {//参数为字符串列表
            String responseMsg=null;
            try {
                OkHttpClient client=new OkHttpClient();
                FormBody formBody=new FormBody.Builder()
                        .add("ID",params[0])
                        .build();
                Request request=new Request.Builder()
                        .url("http://192.168.58.87:8080/Message/activity")
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
            Toast.makeText(getActivity(),string1,Toast.LENGTH_SHORT).show();



            String string2=string1.substring(string1.indexOf(':')+2);
            String string1_1=string2.substring(0,string2.indexOf('"'));
            String string3=string2.substring(string2.indexOf(':')+2);
            String string1_2=string3.substring(0,string3.indexOf('"'));
            String string4=string3.substring(string3.indexOf(':')+2);
            String string1_3=string4.substring(0,string4.indexOf('"'));
            activity_title1.setText(string1_1);
            activity_text1.setText(string1_2);
            activity_time1.setText(string1_3);
            if((string4.indexOf("]")-string4.indexOf("}"))<3)return;

            String string5=string4.substring(string4.indexOf(':')+2);
            String string2_1=string5.substring(0,string5.indexOf('"'));
            String string6=string5.substring(string5.indexOf(':')+2);
            String string2_2=string6.substring(0,string6.indexOf('"'));
            String string7=string6.substring(string6.indexOf(':')+2);
            String string2_3=string7.substring(0,string7.indexOf('"'));
            activity_title2.setText(string2_1);
            activity_text2.setText(string2_2);
            activity_time2.setText(string2_3);
            if((string7.indexOf("]")-string7.indexOf("}"))<3)return;

            String string8=string7.substring(string7.indexOf(':')+2);
            String string3_1=string8.substring(0,string8.indexOf('"'));
            String string9=string8.substring(string8.indexOf(':')+2);
            String string3_2=string9.substring(0,string9.indexOf('"'));
            String string10=string9.substring(string9.indexOf(':')+2);
            String string3_3=string10.substring(0,string10.indexOf('"'));
            activity_title3.setText(string3_1);
            activity_text3.setText(string3_2);
            activity_time3.setText(string3_3);
            if((string10.indexOf("]")-string10.indexOf("}"))<3)return;

            String string11=string10.substring(string10.indexOf(':')+2);
            String string4_1=string11.substring(0,string11.indexOf('"'));
            String string12=string11.substring(string11.indexOf(':')+2);
            String string4_2=string12.substring(0,string12.indexOf('"'));
            String string13=string12.substring(string12.indexOf(':')+2);
            String string4_3=string13.substring(0,string13.indexOf('"'));
            activity_title4.setText(string4_1);
            activity_text4.setText(string4_2);
            activity_time4.setText(string4_3);
            return;


//            i++;
//            if(i>array.length)return;
//            try {
//                JSONObject jsonObject=new JSONObject(array[1]);
//                activity_title2.setText(jsonObject.getString("Activity_Title"));
//                activity_text2.setText(jsonObject.getString("Activity_Details"));
//                activity_time2.setText(jsonObject.getString("Activity_Time"));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            i++;
//            if(i>array.length)return;
//            try {
//                JSONObject jsonObject=new JSONObject(array[2]);
//                activity_title3.setText(jsonObject.getString("Activity_Title"));
//                activity_text3.setText(jsonObject.getString("Activity_Details"));
//                activity_time3.setText(jsonObject.getString("Activity_Time"));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            i++;
//            if(i>array.length)return;
//            try {
//                JSONObject jsonObject=new JSONObject(array[3]);
//                activity_title4.setText(jsonObject.getString("Activity_Title"));
//                activity_text4.setText(jsonObject.getString("Activity_Details"));
//                activity_time4.setText(jsonObject.getString("Activity_Time"));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            i++;
//            if(i>array.length)return;
//            Toast.makeText(getActivity(),result,Toast.LENGTH_SHORT).show();
        }
    }
}