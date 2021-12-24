package com.sdkj.boot.domain;


import lombok.ToString;

//网盘资料
@ToString
public class Return_Data {

    //资料类型
    private String DataType;

    //网盘地址
    private String DataAddress;
    private int NumberOfDown;

    //网盘验证码
    private String VerCode;

    //简介
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getDataType() {
        return DataType;
    }

    public void setDataType(String dataType) {
        DataType = dataType;
    }

    public String getDataAddress() {
        return DataAddress;
    }

    public void setDataAddress(String dataAddress) {
        DataAddress = dataAddress;
    }

    public int getNumberOfDown() {
        return NumberOfDown;
    }

    public void setNumberOfDown(int numberOfDown) {
        NumberOfDown = numberOfDown;
    }

    public String getVerCode() {
        return VerCode;
    }

    public void setVerCode(String verCode) {
        VerCode = verCode;
    }

}
