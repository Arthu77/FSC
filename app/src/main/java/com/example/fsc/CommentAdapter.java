package com.example.fsc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CommentAdapter extends ArrayAdapter<Comment> {
    private int resourceId;
    private List<Comment> objects;
    private Context context;
    public CommentAdapter(Context context, int textViewResourceID, List<Comment> objects){
        super(context,textViewResourceID,objects);
        this.objects=objects;
        this.context=context;
        resourceId=textViewResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Comment comment=getItem(position);  //获取当前Grade实例
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView gradename=(TextView)view.findViewById(R.id.tv_comment_name);
        gradename.setText(comment.getName());
        TextView gradestdid=(TextView)view.findViewById(R.id.tv_comment_stdid);
        gradestdid.setText(comment.getStdid());
        TextView gradeclass=(TextView)view.findViewById(R.id.tv_comment_class);
        gradeclass.setText(comment.getClasses());
        TextView gradegrade=(TextView)view.findViewById(R.id.tv_comment_comment);
        gradegrade.setText(comment.getComment());
        return view;
    }
}