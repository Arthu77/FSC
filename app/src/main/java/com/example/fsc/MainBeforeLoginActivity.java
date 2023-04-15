package com.example.fsc;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTabHost;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainBeforeLoginActivity extends FragmentActivity {

    private FragmentTabHost fragmentTabHost;
    private String texts[]={"资讯","活动","班级","通知","我的"};
    private int imageButton[]={R.drawable.bt_news_selector,R.drawable.bt_activity_selector,R.drawable.bt_class_selector,R.drawable.bt_notice_selector,R.drawable.bt_personal_information_selector};
    private Class fragmentArray[]={NewsFragment.class, ActivityBeforeLoginFragment.class, ClassBeforeLoginFragment.class, NoticeBeforeLoginFragment.class,LoginFragment.class};

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_before_login);

        //实例化tabhost
        fragmentTabHost =(FragmentTabHost)findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this,getSupportFragmentManager(),R.id.main_content_before_login);

        for (int i =0;i<texts.length;i++){
            TabHost.TabSpec spec = fragmentTabHost.newTabSpec(texts[i]).setIndicator(getView(i));
            fragmentTabHost.addTab(spec,fragmentArray[i],null);
        }
    }

    private View getView(int i){
        View view=View.inflate(MainBeforeLoginActivity.this,R.layout.tabcontent,null);

        ImageView imageView=(ImageView)view.findViewById(R.id.image);
        TextView textView=(TextView)view.findViewById(R.id.text);

        imageView.setImageResource(imageButton[i]);

//        textView.setText(texts[i]);

        return view;
    }

    public void setTab(int tab){
        fragmentTabHost.setCurrentTab(tab);
    }


}