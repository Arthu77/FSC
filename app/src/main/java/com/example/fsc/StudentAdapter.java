package com.example.fsc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

public class StudentAdapter extends ArrayAdapter<CheckIn> {
    private int resourceId;
    private List<CheckIn> objects;
    private Context context;

    public StudentAdapter(Context context, int textViewResourceID, List<CheckIn> objects){
        super(context,textViewResourceID,objects);
        this.objects=objects;
        this.context=context;
        resourceId=textViewResourceID;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        CheckIn checkIn =getItem(position);  //获取当前Student实例
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView checkinname=(TextView)view.findViewById(R.id.tv_checkin_name);
        checkinname.setText(checkIn.getName());
        TextView checkinstdid=(TextView)view.findViewById(R.id.tv_checkin_stdid);
        checkinstdid.setText(checkIn.getStdid());
        TextView checkinclass=(TextView)view.findViewById(R.id.tv_checkin_class);
        checkinclass.setText(checkIn.getClasses());
        TextView checkincontent=(TextView)view.findViewById(R.id.tv_checkin_content);
        checkincontent.setText(checkIn.getState());
        return view;
    }
}
