package com.example.fsc;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParamService {
    public String getJsonArrayValue(String jsonStr,int index,String key){
        String jsonValue="";
        if(jsonStr==null || jsonStr.trim().length()==0){
            return null;
        }
        try {
            JSONArray jsonArr=new JSONArray(jsonStr);
            JSONObject jsonObj=jsonArr.getJSONObject(index);
            jsonValue=jsonObj.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonValue;
    }

}
