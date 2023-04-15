package com.example.fsc;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.UUID;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ClassFragment extends Fragment {
    TextView tv_class_id,tv_class_teacher;
    String classID;
    String account;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class, null);
        MainActivity mainActivity = (MainActivity) getActivity();
        String str_identity = mainActivity.getIdentity();
        account = mainActivity.getAccount();


        //动态添加班级编号和班主任名字
        tv_class_id = (TextView) view.findViewById(R.id.tv_class_id);
        tv_class_teacher = (TextView) view.findViewById(R.id.tv_class_teacher);
        tv_class_id.setText("");
        tv_class_teacher.setText("");

        //跳转按钮
        ImageButton btn_checkin = (ImageButton) view.findViewById(R.id.btn_checkin);
        ImageButton btn_homework = (ImageButton) view.findViewById(R.id.btn_homework);
        ImageButton btn_grade = (ImageButton) view.findViewById(R.id.btn_grade);
        ImageButton btn_comment = (ImageButton) view.findViewById(R.id.btn_comment);

        Button add_item_bt = (Button) view.findViewById(R.id.add_item_bt);

        add_item_bt.setVisibility(View.GONE);
        if (str_identity.equals("教师")) {
            add_item_bt.setVisibility(View.VISIBLE);
        }

        new GetClassMsgTask().execute(account);


        add_item_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopupWindow(v);
            }
        });

        //考勤
        btn_checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(str_identity.equals("教师")){
                    Intent intent = new Intent(getActivity(), CheckInManageActivity.class);
                    intent.putExtra("account",account);
                    startActivity(intent);
                }else if(str_identity.equals("学生")) {
                    Intent intent = new Intent(getActivity(),CheckInActivity.class);
                    intent.putExtra("account",account);
                    intent.putExtra("identity","学生");
                    intent.putExtra("classID",classID);
                    startActivity(intent);
                }else if(str_identity.equals("家长")){
                    Intent intent = new Intent(getActivity(),CheckInActivity.class);
                    intent.putExtra("account",account);
                    intent.putExtra("identity","家长");
                    intent.putExtra("classID",classID);
                    startActivity(intent);
                }
            }
        });

        //作业
        btn_homework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(str_identity.equals("教师")){
                    Intent intent = new Intent(getActivity(), HomeworkManageActivity.class);
                    intent.putExtra("classID",classID);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(getActivity(),HomeworkActivity.class);
                    intent.putExtra("account",account);
                    intent.putExtra("classID",classID);
                    startActivity(intent);
                }
            }
        });

        //成绩
        btn_grade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(str_identity.equals("教师")){
                    Intent intent = new Intent(getActivity(), GradeManageActivity.class);
                    intent.putExtra("classID",classID);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent();
                    intent.putExtra("account",account);
                    intent.putExtra("classID",classID);
                    intent = new Intent(getActivity(),GradeActivity.class);
                    startActivity(intent);
                }
            }
        });

        //评语
        btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (str_identity.equals("教师")){
                    Intent intent=new Intent() ;
                    intent.setClass(getActivity(),CommentManageActivity.class);
                    intent.putExtra("classID",classID);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent();
                    intent.putExtra("account",account);
                    intent = new Intent(getActivity(),CommentActivity.class);
                    intent.putExtra("classID",classID);
                    startActivity(intent);
                }
            }
        });
        return view;
    }

    private void initPopupWindow(View v) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.add_item_popup, null, true);
        Button add_activity_bt = (Button) view.findViewById(R.id.add_activity_bt);
        add_activity_bt.setBackgroundColor(Color.parseColor("#F4F5F9"));
        Button add_notice_bt = (Button) view.findViewById(R.id.add_notice_bt);
        add_notice_bt.setBackgroundColor(Color.parseColor("#F4F5F9"));

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

        add_activity_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),AddActivityActivity.class);
                intent.putExtra("classID",classID);
                startActivity(intent);
                popupWindow.dismiss();
            }
        });

        add_notice_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),AddNoticeActivity.class);
                intent.putExtra("classID",classID);
                intent.putExtra("account",account);
                startActivity(intent);
                popupWindow.dismiss();
            }
        });

    }

    public class GetClassMsgTask extends AsyncTask<String,Integer,String> {
        protected String doInBackground(String... params) {//参数为字符串列表
            String responseMsg=null;
            try {
                OkHttpClient client=new OkHttpClient();
                FormBody formBody=new FormBody.Builder()
                        .add("ID",params[0])
                        .build();
                Request request=new Request.Builder()
                        .url("http://192.168.45.38:8080/Message/class")
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
                classID=jsonObject.getString("Class_ID");
                tv_class_id.setText(classID);
                tv_class_teacher.setText(jsonObject.getString("Teacher_Name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
//            Toast.makeText(getActivity(),result,Toast.LENGTH_SHORT).show();
        }
    }

}