package com.example.fsc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CheckInActivity extends AppCompatActivity {

    private AlertDialog alertDialog;
    public Boolean state_is_normal=false;
    String classID;
    TextView check_in_name;
    TextView check_in_state;
    String stdid=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
        Intent intent=getIntent();
        String identity=intent.getStringExtra("identity");
        classID=intent.getStringExtra("classID");
        String account=intent.getStringExtra("account");
        stdid=intent.getStringExtra("stdid");

        check_in_name=(TextView)findViewById(R.id.check_in_name);
        check_in_state=(TextView)findViewById(R.id.check_in_state);
        TextView check_in_data =(TextView)findViewById(R.id.check_in_data);

        Calendar calendars = Calendar.getInstance();
        calendars.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String data=String.valueOf(calendars.get(Calendar.YEAR))+"-"+String.valueOf(calendars.get(Calendar.MONTH)+1)+"-"+String.valueOf(calendars.get(Calendar.DATE));

        Button state_bt=(Button)findViewById(R.id.state_bt);
        state_bt.setVisibility(View.GONE);

        if (identity.equals("教师")){
            state_bt.setVisibility(View.VISIBLE);
            new StudentViewAttendTask().execute(stdid);
        }else if(identity.equals("家长")){
            new ParentViewAttendTask().execute(account);
        }else if(identity.equals("学生"))
            new StudentViewAttendTask().execute(account);

//        Toast.makeText(CheckInActivity.this,classID,Toast.LENGTH_SHORT).show();


        check_in_data.setText(data);

//        if (!state_is_normal){
//            check_in_state.setText("缺勤");
//            check_in_state.setTextColor(Color.RED);
//        }
    }

    public void showSingleAlertDialog(View view){
        final String[] items={"正常","缺勤"};
        AlertDialog.Builder alertBuilder=new AlertDialog.Builder(this);
        alertBuilder.setTitle("请选择学生的状态");
        alertBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("正常")){
                    state_is_normal=true;
//                    refreshState(state_is_normal);
                }
                if(items[i].equals("缺勤")){
                    state_is_normal=false;
//                    refreshState(state_is_normal);
                }
            }
        });

        alertBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                refreshState(state_is_normal);
                alertDialog.dismiss();
            }
        });

        alertDialog=alertBuilder.create();
        alertDialog.show();
    }

    public void refreshState(Boolean is){
        TextView check_in_state=(TextView)findViewById(R.id.check_in_state);
        if (!is){
            check_in_state.setText("缺勤");
            check_in_state.setTextColor(Color.RED);
            new UpdateAttendTask().execute(stdid,"缺勤");
        }
        if (is){
            check_in_state.setText("正常");
            check_in_state.setTextColor(Color.parseColor("#009BE2"));
            new UpdateAttendTask().execute(stdid,"正常");
        }
    }

    public class StudentViewAttendTask extends AsyncTask<String,Integer,String> {
        protected String doInBackground(String... params) {//参数为字符串列表
            String responseMsg=null;
            try {
                OkHttpClient client=new OkHttpClient();
                FormBody formBody=new FormBody.Builder()
                        .add("ID",params[0])
                        .build();
                Request request=new Request.Builder()
                        .url("http://192.168.58.87:8080/Student/checkin")
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
            ParamService paramService=new ParamService();
            check_in_name.setText(paramService.getJsonArrayValue(result,0,"Name"));
            check_in_state.setText(paramService.getJsonArrayValue(result,0,"Conten"));
//            Toast.makeText(CheckInActivity.this,result,Toast.LENGTH_SHORT).show();
        }
    }
    public class ParentViewAttendTask extends AsyncTask<String,Integer,String> {
        protected String doInBackground(String... params) {//参数为字符串列表
            String responseMsg=null;
            try {
                OkHttpClient client=new OkHttpClient();
                FormBody formBody=new FormBody.Builder()
                        .add("ID",params[0])
                        .build();
                Request request=new Request.Builder()
                        .url("http://192.168.58.87:8080/Parent/checkin")
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
            ParamService paramService=new ParamService();
            check_in_name.setText(paramService.getJsonArrayValue(result,0,"Name"));
            check_in_state.setText(paramService.getJsonArrayValue(result,0,"Conten"));
            Toast.makeText(CheckInActivity.this,result,Toast.LENGTH_SHORT).show();
        }
    }

    public class UpdateAttendTask extends AsyncTask<String,Integer,String> {
        protected String doInBackground(String... params) {//参数为字符串列表
            String responseMsg=null;
            try {
                OkHttpClient client=new OkHttpClient();
                FormBody formBody=new FormBody.Builder()
                        .add("ID",params[0])
                        .add("Conten",params[1])
                        .build();
                Request request=new Request.Builder()
                        .url("http://192.168.58.87:8080/Teacher/checkininsert")
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
//            ParamService paramService=new ParamService();
//            check_in_name.setText(paramService.getJsonArrayValue(result,0,"Name"));
//            check_in_state.setText(paramService.getJsonArrayValue(result,0,"Conten"));
            Toast.makeText(CheckInActivity.this,result,Toast.LENGTH_SHORT).show();
        }
    }



}