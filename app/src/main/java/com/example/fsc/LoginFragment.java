package com.example.fsc;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginFragment extends Fragment {
    private static final String TAG = "LoginFragment";

    String spinner_identity;
    String account;

    private TextView identity_spinner_text;
    private Spinner identity_spinner;
    private ArrayAdapter identity_spinner_adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_login, null);

        //选择身份
        identity_spinner=(Spinner)view.findViewById(R.id.identity_spinner);
        identity_spinner_text=(TextView)view.findViewById(R.id.identity_spinner_text);
        //将可选内容如ArrayAdapter连接起来
        identity_spinner_adapter=ArrayAdapter.createFromResource(getActivity(),R.array.identity, android.R.layout.simple_spinner_item);
        //设置下拉列表风格
        identity_spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将identity_spinner_adapter添加到spinner中
        identity_spinner.setAdapter(identity_spinner_adapter);
        //添加spinner事件监听
//        identity_spinner.setOnItemSelectedListener(new SpinnerXMLSelectedListener);
        identity_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner_identity=identity_spinner_adapter.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //设置默认值
        identity_spinner.setVisibility(View.VISIBLE);

        //选择登录或者注册
        Button login_btn = (Button) view.findViewById(R.id.login_btn);
        Button register_btn = (Button) view.findViewById(R.id.register_btn);
//        EditText account_et=(EditText) view.findViewById(R.id.account_et);
//        String account=account_et.getText().toString();
//        EditText password_et=(EditText) view.findViewById(R.id.password_et);
//        String password=password_et.getText().toString();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText account_et=(EditText) view.findViewById(R.id.account_et);
                account=account_et.getText().toString().trim();
//                Log.e(TAG, "onClick: "+account+" 账号");
                EditText password_et=(EditText) view.findViewById(R.id.password_et);
                String password=password_et.getText().toString().trim();
//                Log.e(TAG, "onClick: "+password+" 账号");

                if (TextUtils.isEmpty(account_et.getText())||TextUtils.isEmpty(password_et.getText())){
                    Toast.makeText(getActivity(),"账号或密码为空",Toast.LENGTH_LONG).show();
                    return;
                }

//                Toast.makeText(getActivity(),spinner_identity+"登录",Toast.LENGTH_LONG).show();

//                Intent intent =new Intent();
//                intent.putExtra("identity",spinner_identity);
//                intent.putExtra("account",account);
//                intent.setClass(getActivity(),MainActivity.class);
//                startActivity(intent);
//                getActivity().finish();
                if(spinner_identity.equals("教师"))
                    new TeacherLoginTask().execute(account,password);
                if(spinner_identity.equals("家长"))
                    new ParentLoginTask().execute(account,password);
                if(spinner_identity.equals("学生"))
                    new StudentLoginTask().execute(account,password);
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),RegisterActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public class TeacherLoginTask extends AsyncTask<String,Integer,String> {
        protected String doInBackground(String... params) {//参数为字符串列表
            String responseMsg=null;
            try {
                OkHttpClient client=new OkHttpClient();
                FormBody formBody=new FormBody.Builder()
                        .add("TeacherID",params[0])
                        .add("TeacherPwd",params[1])
                        .build();
                Request request=new Request.Builder()
                        .url("http://192.168.45.38:8080/Teacher/teacherlogin")
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
            if(result.equals("success")){
                Intent intent =new Intent();
                intent.putExtra("identity",spinner_identity);
                intent.putExtra("account",account);
                intent.setClass(getActivity(),MainActivity.class);
                startActivity(intent);
                getActivity().finish();
                Toast.makeText(getActivity(),"登录成功",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getActivity(),"登陆失败",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class ParentLoginTask extends AsyncTask<String,Integer,String> {
        protected String doInBackground(String... params) {//参数为字符串列表
            String responseMsg=null;
            try {
                OkHttpClient client=new OkHttpClient();
                FormBody formBody=new FormBody.Builder()
                        .add("ParentID",params[0])
                        .add("ParentPwd",params[1])
                        .build();
                Request request=new Request.Builder()
                        .url("http://192.168.45.38:8080/Parent/parentlogin")
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
            if(result.equals("success")){
                Intent intent =new Intent();
                intent.putExtra("identity",spinner_identity);
                intent.putExtra("account",account);
                intent.setClass(getActivity(),MainActivity.class);
                startActivity(intent);
                getActivity().finish();
                Toast.makeText(getActivity(),"登录成功",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getActivity(),"登陆失败",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class StudentLoginTask extends AsyncTask<String,Integer,String> {
        protected String doInBackground(String... params) {//参数为字符串列表
            String responseMsg=null;
            try {
                OkHttpClient client=new OkHttpClient();
                FormBody formBody=new FormBody.Builder()
                        .add("StudentID",params[0])
                        .add("StudentPwd",params[1])
                        .build();
                Request request=new Request.Builder()
                        .url("http://192.168.45.38:8080/Student/studentlogin")
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
            if(result.equals("success")){
                Intent intent =new Intent();
                intent.putExtra("identity",spinner_identity);
                intent.putExtra("account",account);
                intent.setClass(getActivity(),MainActivity.class);
                startActivity(intent);
                getActivity().finish();
                Toast.makeText(getActivity(),"登录成功",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getActivity(),"登陆失败",Toast.LENGTH_SHORT).show();
            }
        }
    }



}