package com.one.UserTec;

import com.one.domain.ResultInfo;
import com.one.net_client.FailListener;
import com.one.net_client.GetUtils;
import com.one.net_client.SuccessListener;
import com.one.util.JsonUtils;
import com.one.util.ResultInfoData2Vector_confirm;

import java.util.Vector;

//查询Confim表中的申请信息,如果已经同意或者拒绝 就不会弹出窗口
public class QueryConfirm {
    Vector<Vector> vectors= null;

    public Vector<Vector> query(){

        GetUtils.get("http://localhost:8080/confirm/compare", new SuccessListener() {
            @Override
            public Object success(String result) {
                ResultInfo info = JsonUtils.parseResult(result);
                if(info.isFlag()){
                   vectors = ResultInfoData2Vector_confirm.convertResultInfoData2Vector(info);
                }
                return null;
            }
        }, new FailListener() {
            @Override
            public void fail() {

            }
        });

        return vectors;
    }
}
