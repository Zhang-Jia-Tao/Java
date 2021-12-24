package com.sdkj.boot.domain;

//每人的考试信息
public class Examination {
    private int E_Id;
    private int UserId;
    private int NumberOfAnswer;
    private int CorrectRate;


    public Examination(int e_Id, int userId, int numberOfAnswer, int correctRate) {
        E_Id = e_Id;
        UserId = userId;
        NumberOfAnswer = numberOfAnswer;
        CorrectRate = correctRate;
    }

    @Override
    public String toString() {
        return "Examination{" +
                "E_Id=" + E_Id +
                ", UserId=" + UserId +
                ", NumberOfAnswer=" + NumberOfAnswer +
                ", CorrectRate=" + CorrectRate +
                '}';
    }

    public int getE_Id() {
        return E_Id;
    }

    public void setE_Id(int e_Id) {
        E_Id = e_Id;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getNumberOfAnswer() {
        return NumberOfAnswer;
    }

    public void setNumberOfAnswer(int numberOfAnswer) {
        NumberOfAnswer = numberOfAnswer;
    }

    public int getCorrectRate() {
        return CorrectRate;
    }

    public void setCorrectRate(int correctRate) {
        CorrectRate = correctRate;
    }
}
