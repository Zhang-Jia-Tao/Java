package com.sdkj.boot.domain;


//确认表  用于添加好友时
public class Confirm {
    private int ConfirmId;
    private int Send_id;
    private int Receive_id;
    private int Status;


    public int getConfirmId() {
        return ConfirmId;
    }

    public void setConfirmId(int confirmId) {
        ConfirmId = confirmId;
    }

    public int getSend_id() {
        return Send_id;
    }

    public void setSend_id(int send_id) {
        Send_id = send_id;
    }

    public int getReceive_id() {
        return Receive_id;
    }

    public void setReceive_id(int receive_id) {
        Receive_id = receive_id;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "Confirm{" +
                "ConfirmId=" + ConfirmId +
                ", Send_id=" + Send_id +
                ", Receive_id=" + Receive_id +
                ", Status=" + Status +
                '}';
    }
}
