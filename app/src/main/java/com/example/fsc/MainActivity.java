package com.example.fsc;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTabHost;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.UUID;

public class MainActivity extends FragmentActivity {
    private static final String TAG = "MainActivity";

    private FragmentTabHost fragmentTabHost;
    private String texts[]={"资讯","活动","班级","通知","我的"};
    private int imageButton[]={R.drawable.bt_news_selector,R.drawable.bt_activity_selector,R.drawable.bt_class_selector,R.drawable.bt_notice_selector,R.drawable.bt_personal_information_selector};
    private Class fragmentArray[]={NewsFragment.class, ActivityFragment.class, ClassFragment.class, NoticeFragment.class,UserFragment.class};

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //实例化tabhost
        fragmentTabHost =(FragmentTabHost)findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this,getSupportFragmentManager(),R.id.main_content);

        for (int i =0;i<texts.length;i++){
            TabHost.TabSpec spec = fragmentTabHost.newTabSpec(texts[i]).setIndicator(getView(i));
            fragmentTabHost.addTab(spec,fragmentArray[i],null);
        }
//        Intent intent=getIntent();
//        identity=intent.getStringExtra("identity");
//        Log.e(TAG, "onCreate: "+identity );
//        String test=getIdentity();
//        getAccount();
    }

    private View getView(int i){
        View view=View.inflate(MainActivity.this,R.layout.tabcontent,null);

        ImageView imageView=(ImageView)view.findViewById(R.id.image);
//        TextView textView=(TextView)view.findViewById(R.id.text);

        imageView.setImageResource(imageButton[i]);

//        textView.setText(texts[i]);

        return view;
    }

    public void setTab(int tab){
        fragmentTabHost.setCurrentTab(tab);
    }

    public String getIdentity(){
        Intent intent=getIntent();
        String identity=intent.getStringExtra("identity");
        return identity;
    }

    public String getAccount(){
        Intent intent=getIntent();
        String account =intent.getStringExtra("account");
//        Log.e(TAG, "getAccount: "+account);
        return account;
    }

}