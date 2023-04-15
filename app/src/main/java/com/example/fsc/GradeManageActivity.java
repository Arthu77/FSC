package com.example.fsc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GradeManageActivity extends AppCompatActivity {
    private List<Grade> gradeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_manage);
        Intent intent=getIntent();
        String classID=intent.getStringExtra("classID");
//        initsGrade();
        GradeAdapter adapter=new GradeAdapter(GradeManageActivity.this,R.layout.grade_item,gradeList);
        ListView listView=(ListView)findViewById(R.id.lv_grade);
        listView.setAdapter(adapter);
        new TeacherViewGradeTask().execute(classID);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Grade grade=gradeList.get(position);
                Intent intent=new Intent(GradeManageActivity.this,AddGradeActivity.class);
                intent.putExtra("std_name",grade.getName());
                intent.putExtra("std_id",grade.getStdid());
                intent.putExtra("std_grade",grade.getGrade());
                intent.putExtra("std_class",grade.getClasses());
                startActivity(intent);
            }
        });
    }

//    private void initsGrade(){
//        for(int i=0;i<30;i++){
//            Grade Jack=new Grade("张三","1601","2019091601001",null);
//            gradeList.add(Jack);
//            Grade Rose=new Grade("李四","1601","2019091601002","语文65,数学84,英语80,物理86,化学68,生物63");
//            gradeList.add(Rose);
//        }
//    }


    public class TeacherViewGradeTask extends AsyncTask<String,Integer,String> {
        protected String doInBackground(String... params) {//参数为字符串列表
            String responseMsg=null;
            try {
                OkHttpClient client=new OkHttpClient();
                FormBody formBody=new FormBody.Builder()
                        .add("ID",params[0])
                        .build();
                Request request=new Request.Builder()
                        .url("http://192.168.58.87:8080/Teacher/gradeselect")
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

                Grade Jack=new Grade(paramService.getJsonArrayValue(result,i,"Name"),paramService.getJsonArrayValue(result,i,"ClassID"),paramService.getJsonArrayValue(result,i,"ID"),paramService.getJsonArrayValue(result,i,"Grade"));
                gradeList.add(Jack);
            }
//            Toast.makeText(CheckInManageActivity.this,result,Toast.LENGTH_SHORT).show();
        }
    }


}