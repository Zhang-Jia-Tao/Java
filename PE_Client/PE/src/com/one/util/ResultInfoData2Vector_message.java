package com.one.util;


import com.one.domain.ResultInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class ResultInfoData2Vector_message {

    //将返回的数据转换称Vector
    public static Vector<Vector> convertResultInfoData2Vector(ResultInfo info){
        List list = (List) info.getData();
        Vector<Vector> vectors = new Vector<>();
        for (int i = 0; i < list.size(); i++) {
            HashMap map = (HashMap) list.get(i);
            Vector vector = new Vector();
            vector.add(0,map.get("receive_id"));
            vector.add(1,map.get("content"));
            vector.add(2,map.get("send_time"));
            vectors.add(vector);
        }
        return vectors;
    }
}
