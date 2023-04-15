package com.example.fsc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class GradeAdapter extends ArrayAdapter<Grade> {
    private int resourceId;
    private List<Grade> objects;
    private Context context;

    public GradeAdapter(Context context, int textViewResourceID, List<Grade> objects){
        super(context,textViewResourceID,objects);
        this.objects=objects;
        this.context=context;
        resourceId=textViewResourceID;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Grade grade=getItem(position);  //获取当前Grade实例
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView gradename=(TextView)view.findViewById(R.id.tv_grade_name);
        gradename.setText(grade.getName());
        TextView gradestdid=(TextView)view.findViewById(R.id.tv_grade_stdid);
        gradestdid.setText(grade.getStdid());
        TextView gradeclass=(TextView)view.findViewById(R.id.tv_grade_class);
        gradeclass.setText(grade.getClasses());
        TextView gradegrade=(TextView)view.findViewById(R.id.tv_grade_grade);
        gradegrade.setText(grade.getGrade());
        return view;
    }
}
