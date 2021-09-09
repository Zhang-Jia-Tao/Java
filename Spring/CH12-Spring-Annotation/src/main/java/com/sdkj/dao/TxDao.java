package com.sdkj.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class TxDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void insert(){
        String sql = "insert into Student (SId,Sname,Sage,Ssex) values (?,?,?,?)";

        String username = UUID.randomUUID().toString().substring(0,5);
        jdbcTemplate.batchUpdate(sql,"1",username,null,"n");
    }

}
