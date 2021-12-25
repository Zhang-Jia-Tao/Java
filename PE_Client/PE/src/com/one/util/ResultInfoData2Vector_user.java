package com.one.util;


import com.one.domain.ResultInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class ResultInfoData2Vector_user {

    //将返回的数据转换称Vector
    public static Vector<Vector> convertResultInfoData2Vector(ResultInfo info){
        List list = (List) info.getData();
        Vector<Vector> vectors = new Vector<>();
        for (int i = 0; i < list.size(); i++) {
            HashMap map = (HashMap) list.get(i);
            Vector vector = new Vector();
            vector.add(map.get("userId"));
            vectors.add(vector);
        }
        return vectors;
    }

    //获取较为完整的用户信息
    public static Vector<Vector> convertResultInfoData2Vector(ResultInfo info,int flag){
        List list = (List) info.getData();
        Vector<Vector> vectors = new Vector<>();
        for (int i = 0; i < list.size(); i++) {
            HashMap map = (HashMap) list.get(i);
            Vector vector = new Vector();
            vector.add(0,map.get("userName"));
            vector.add(1,map.get("sex"));
            vector.add(2,map.get("phone"));
            vectors.add(vector);
        }
        return vectors;
    }
}
