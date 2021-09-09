package com.sdkj.service;

import com.sdkj.dao.TxDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TxService {

    @Autowired
    private TxDao txDao;

    public void insert(){
        txDao.insert();
        System.out.println("插入完成....");

    }
}
