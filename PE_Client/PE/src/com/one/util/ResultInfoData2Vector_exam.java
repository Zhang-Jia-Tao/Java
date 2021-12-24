package com.one.util;


import com.one.domain.ResultInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class ResultInfoData2Vector_exam {

    //将返回的数据转换称Vector
    public static Vector<Vector> convertResultInfoData2Vector(ResultInfo info){
        List list = (List) info.getData();
        Vector<Vector> vectors = new Vector<>();
        for (int i = 0; i < list.size(); i++) {
            HashMap map = (HashMap) list.get(i);
            Vector vector = new Vector();
            vector.add(map.get("examId"));
            vector.add(map.get("examType"));
            vector.add(map.get("stem"));
            vector.add(map.get("optionA"));
            vector.add(map.get("optionB"));
            vector.add(map.get("optionC"));
            vector.add(map.get("optionD"));
            vector.add(map.get("answer"));
            vector.add(map.get("correctRate"));
            vector.add(map.get("numerOfRes"));
            vectors.add(vector);
        }
        return vectors;
    }
}
