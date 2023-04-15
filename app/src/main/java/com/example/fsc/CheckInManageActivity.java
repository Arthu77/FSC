package com.example.fsc;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CheckInManageActivity extends AppCompatActivity {
    private static final String TAG = "CheckInManageActivity";
        private List<CheckIn> checkInList = new ArrayList<>();
        @Override
        protected void onCreate(Bundle savedInstanceState){
            //隐藏标题栏
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.hide();
            }
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_checkin_manage);


//            initStudents();
//            MainActivity mainActivity = (MainActivity)CheckInManageActivity.this;
//            Log.e(TAG, "onCreate: "+classID);
            Intent intent=getIntent();

//            Toast.makeText(CheckInManageActivity.this,intent.getStringExtra("account"),Toast.LENGTH_SHORT).show();
            new TeacherViewAttendanceTask().execute(intent.getStringExtra("account"));

            StudentAdapter adapter =new StudentAdapter(CheckInManageActivity.this,R.layout.checkin_item, checkInList);
            ListView listView=(ListView) findViewById(R.id.lv_checkin);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    CheckIn checkIn = checkInList.get(position);
                    Intent intent=new Intent();
                    intent.putExtra("identity","教师");
                    intent.putExtra("stdid",checkIn.getStdid());
                    intent.setClass(CheckInManageActivity.this,CheckInActivity.class);
                    startActivity(intent);
                }
            });
        }

    public class TeacherViewAttendanceTask extends AsyncTask<String,Integer,String> {
        protected String doInBackground(String... params) {//参数为字符串列表
            String responseMsg=null;
            try {
                OkHttpClient client=new OkHttpClient();
                FormBody formBody=new FormBody.Builder()
                        .add("ID",params[0])
                        .build();
                Request request=new Request.Builder()
                        .url("http://192.168.45.38:8080/Teacher/checkin")
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
            int count=0;
//            String[] array = result.substring(1, result.indexOf("]")).split("},");
//            CheckIn Jack=new CheckIn(paramService.getJsonArrayValue(result,1,"Name"),paramService.getJsonArrayValue(result,1,"Conten"),paramService.getJsonArrayValue(result,1,"ClassID"),paramService.getJsonArrayValue(result,1,"StdID"));
//            checkInList.add(Jack);
            try {
                JSONArray jsonArr=new JSONArray(result);
                count=jsonArr.length();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < count; i++) {

                CheckIn Jack=new CheckIn(paramService.getJsonArrayValue(result,i,"Name"),paramService.getJsonArrayValue(result,i,"Conten"),paramService.getJsonArrayValue(result,i,"ClassID"),paramService.getJsonArrayValue(result,i,"StdID"));
                checkInList.add(Jack);
            }
//            Toast.makeText(CheckInManageActivity.this,result,Toast.LENGTH_SHORT).show();
        }
    }


//    private void initStudents(){
//            for(int i=0;i<30;i++){
//                CheckIn Rose=new CheckIn("李四","到校","1601","2019091601001");
//                checkInList.add(Rose);
//                CheckIn Jack=new CheckIn("张三","到校","1601","2019091601002");
//                checkInList.add(Jack);
//            }
//
//        }
}
