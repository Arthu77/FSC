package com.example.fsc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CommentManageActivity extends AppCompatActivity {
    private List<Comment> commentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_manage);
        Intent intent =getIntent();
        String identity= intent.getStringExtra("str_identity");
        initsComment();
        CommentAdapter adapter=new CommentAdapter(CommentManageActivity.this,R.layout.comment_item,commentList);
        ListView listView=(ListView)findViewById(R.id.lv_comment);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Comment comment=commentList.get(position);
                Intent intent=new Intent(CommentManageActivity.this,AddCommentActivity.class);
                intent.putExtra("std_name",comment.getName());
                intent.putExtra("std_id",comment.getStdid());
                intent.putExtra("std_comment",comment.getComment());
                intent.putExtra("std_class",comment.getClasses());
                startActivity(intent);
            }
        });
    }


    private void initsComment(){
        for(int i=0;i<30;i++){
            Comment Jack=new Comment("张三","1601","2019091601001","你是一个好学生。");
            commentList.add(Jack);
            Comment Rose=new Comment("李四","1601","2019091601002",null);
            commentList.add(Rose);
        }
    }



}