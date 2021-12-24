package com.one.util;


import com.one.domain.ResultInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class ResultInfoData2Vector_post {

    //将返回的数据转换称Vector
    public static Vector<Vector> convertResultInfoData2Vector(ResultInfo info){
        List list = (List) info.getData();
        Vector<Vector> vectors = new Vector<>();
        for (int i = 0; i < list.size(); i++) {
            HashMap map = (HashMap) list.get(i);
            Vector vector = new Vector();
            vector.add(0,map.get("postId"));
            vector.add(1,map.get("userId"));
            vector.add(2,map.get("content"));
            vector.add(3,map.get("numberOfLikes"));
            vector.add(4,map.get("userName"));
            vectors.add(vector);
        }
        return vectors;
    }

    public static Vector<Vector> convertResultInfoData2Vector(ResultInfo info,int bool){
        List list = (List) info.getData();
        Vector<Vector> vectors = new Vector<>();
        for (int i = 0; i < list.size(); i++) {
            HashMap map = (HashMap) list.get(i);
            Vector vector = new Vector();
            vector.add(0,map.get("postId"));
            vector.add(1,map.get("content"));
            vector.add(2,map.get("numberOfLikes"));
            vectors.add(vector);
        }
        return vectors;
    }
}
