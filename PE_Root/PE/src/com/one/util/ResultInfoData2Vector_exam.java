package com.one.util;


import com.one.domain.ResultInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;


//这个用于data数据中
public class ResultInfoData2Vector_exam {

    //将返回的数据转换称Vector
    public static Vector<Vector> convertResultInfoData2Vector(ResultInfo info){
        List list = (List) info.getData();
        Vector<Vector> vectors = new Vector<>();
        for (int i = 0; i < list.size(); i++) {
            HashMap map = (HashMap) list.get(i);
            Vector vector = new Vector();
            vector.add(0,map.get("examId"));
            vector.add(1,map.get("examType"));
            vector.add(2,map.get("stem"));
            vector.add(3,map.get("optionA"));
            vector.add(4,map.get("optionB"));
            vector.add(5,map.get("optionC"));
            vector.add(6,map.get("optionD"));
            vector.add(7,map.get("answer"));
            vector.add(8,map.get("correctRate"));
            vector.add(9,map.get("numerOfRes"));

            vectors.add(vector);
        }
        return vectors;
    }
}
