package com.example.fsc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button register_btn2=(Button)findViewById(R.id.register_btn2);

//        EditText phone_et=(EditText)findViewById(R.id.phone_et);//获取输入的账号
//        EditText password_register_et= (EditText)findViewById(R.id.password_register_et);//获取密码
//        EditText password_register_et2=(EditText)findViewById(R.id.password_register_et2);//获取确认密码
//
//        String phone = phone_et.getText().toString();//手机号码转字符串
//        Log.e(TAG, "onCreate: "+phone+" 电话");
//        String password_register=password_register_et.getText().toString();//密码转字符串
//        Log.e(TAG, "onCreate: "+password_register+" 密码");
//        String password_register2=password_register_et2.getText().toString();//确认密码转字符串
//        Log.e(TAG, "onCreate: "+password_register2+" 确认密码");

        register_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText phone_et=(EditText)findViewById(R.id.phone_et);//获取输入的账号
                EditText password_register_et= (EditText)findViewById(R.id.password_register_et);//获取密码
                EditText password_register_et2=(EditText)findViewById(R.id.password_register_et2);//获取确认密码
                EditText name_register_et=(EditText)findViewById(R.id.name_register_et);
                EditText student_id_register_et=(EditText)findViewById(R.id.student_id_register_et);

                String phone = phone_et.getText().toString();//手机号码转字符串
//                Log.e(TAG, "onCreate: "+phone+" 电话");
                String password_register=password_register_et.getText().toString();//密码转字符串
//                Log.e(TAG, "onCreate: "+password_register+" 密码");
                String password_register2=password_register_et2.getText().toString();//确认密码转字符串
//                Log.e(TAG, "onCreate: "+password_register2+" 确认密码");
                String name_register=name_register_et.getText().toString();
                String student_id_register=student_id_register_et.getText().toString();

                //手机号未填
                if (TextUtils.isEmpty(phone_et.getText().toString())){
                    Toast.makeText(RegisterActivity.this,"请填写手机号",Toast.LENGTH_LONG).show();
                    return;
                }
                //密码未填
                if (TextUtils.isEmpty(password_register_et.getText().toString())){
                    Toast.makeText(RegisterActivity.this,"请填写密码",Toast.LENGTH_LONG).show();
                    return;
                }
                //确认密码未填
                if (TextUtils.isEmpty(password_register_et2.getText().toString())){
                    Toast.makeText(RegisterActivity.this,"请确认密码",Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(name_register_et.getText().toString())){
                    Toast.makeText(RegisterActivity.this,"请填写姓名",Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(student_id_register_et.getText().toString())){
                    Toast.makeText(RegisterActivity.this,"请填写学生账号",Toast.LENGTH_LONG).show();
                    return;
                }
                //密码和确认密码不一致
                if (!password_register.equals(password_register2)){
                    Toast.makeText(RegisterActivity.this,"两次输入密码不一致，请重新输入",Toast.LENGTH_LONG).show();
                }
                new ParentRegisterTask().execute(phone,student_id_register,name_register,password_register);
            }
        });
    }

    public class ParentRegisterTask extends AsyncTask<String,Integer,String> {
        protected String doInBackground(String... params) {//参数为字符串列表
            String responseMsg=null;
            try {
                OkHttpClient client=new OkHttpClient();
                FormBody formBody=new FormBody.Builder()
                        .add("ParentID",params[0])
                        .add("StdID",params[1])
                        .add("ParentName",params[2])
                        .add("ParentPwd",params[3])
                        .build();
                Request request=new Request.Builder()
                        .url("http://192.168.58.87:8080/Parent/parentregister")
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
//        try {
//            JSONObject jsonObject=new JSONObject(result);
//            String string=jsonObject.getString("");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
            if(result.equals("success")){
                finish();
            }
            else {
                Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(RegisterActivity.this,result,Toast.LENGTH_SHORT).show();
        }
    }
}