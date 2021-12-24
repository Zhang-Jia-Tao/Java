package com.sdkj.boot.domain;
import lombok.ToString;

@ToString
public class Message {

    private String mid;
    private String content;
    private String send_id;
    private String receive_id;
    private String send_time;



    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSend_id() {
        return send_id;
    }

    public void setSend_id(String send_id) {
        this.send_id = send_id;
    }

    public String getReceive_id() {
        return receive_id;
    }

    public void setReceive_id(String receive_id) {
        this.receive_id = receive_id;
    }

    public String getSend_time() {
        return send_time;
    }

    public void setSend_time(String send_time) {
        this.send_time = send_time;
    }
}
