package com.example.fsc;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UserFragment extends Fragment {
    TextView user_name_text;
    TextView user_class_text;
    TextView user_class_text2;
    String str_identity;
    String account;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_user, null);

        Button about_btn=(Button)view.findViewById(R.id.about_btn);
        Button logout_btn=(Button)view.findViewById(R.id.logout_btn) ;
        user_name_text=(TextView) view.findViewById(R.id.user_name_text);
        user_class_text=(TextView) view.findViewById(R.id.user_class_text);
        user_class_text2=(TextView) view.findViewById(R.id.user_name_text2);

        user_class_text2.setText(null);
        user_name_text.setText("动态添加的姓名");
        user_class_text.setText("动态添加的班级");

        MainActivity mainActivity=(MainActivity)getActivity();
        str_identity=mainActivity.getIdentity();
        account=mainActivity.getAccount();
        user_class_text2.setVisibility(View.GONE);
        if(str_identity.equals("教师"))
            new GetTeacherMsgTask().execute(account);
        if(str_identity.equals("家长"))
            new GetParentMsgTask().execute(account);
        if(str_identity.equals("学生"))
            new GetStudentMsgTask().execute(account);

        about_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent =new Intent(getActivity(),AboutActivity.class);
                startActivity(intent);
            }
        });

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getActivity(),MainBeforeLoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }

    public class GetTeacherMsgTask extends AsyncTask<String,Integer,String> {
        protected String doInBackground(String... params) {//参数为字符串列表
            String responseMsg=null;
            try {
                OkHttpClient client=new OkHttpClient();
                FormBody formBody=new FormBody.Builder()
                        .add("TeacherID",params[0])
                        .build();
                Request request=new Request.Builder()
                        .url("http://192.168.58.87:8080/Teacher/mine")
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
                user_class_text.setText(jsonObject.getString("Class_ID"));
                user_name_text.setText(jsonObject.getString("Teacher_Name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
//                Toast.makeText(getActivity(),result,Toast.LENGTH_SHORT).show();
        }
    }

    public class GetParentMsgTask extends AsyncTask<String,Integer,String> {
        protected String doInBackground(String... params) {//参数为字符串列表
            String responseMsg=null;
            try {
                OkHttpClient client=new OkHttpClient();
                FormBody formBody=new FormBody.Builder()
                        .add("ParentID",params[0])
                        .build();
                Request request=new Request.Builder()
                        .url("http://192.168.58.87:8080/Parent/mine")
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
            user_class_text2.setVisibility(View.VISIBLE);
            user_name_text.setText(jsonObject.getString("Std_Name"));
            user_class_text2.setText(jsonObject.getString("Parent_Name"));
            user_class_text.setText(jsonObject.getString("Class_ID"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
//        Toast.makeText(getActivity(),result,Toast.LENGTH_SHORT).show();
        }
    }

    public class GetStudentMsgTask extends AsyncTask<String,Integer,String> {
        protected String doInBackground(String... params) {//参数为字符串列表
            String responseMsg=null;
            try {
                OkHttpClient client=new OkHttpClient();
                FormBody formBody=new FormBody.Builder()
                        .add("StdID",params[0])
                        .build();
                Request request=new Request.Builder()
                        .url("http://192.168.58.87:8080/Student/mine")
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
                user_class_text.setText(jsonObject.getString("Class_ID"));
                user_name_text.setText(jsonObject.getString("Std_Name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
//            Toast.makeText(getActivity(),result,Toast.LENGTH_SHORT).show();
        }
    }
}