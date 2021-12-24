package com.one.domain;

//试题
public class Exam {
    private int ExamId;
    private String ExamType;

    //试题题干
    private String Stem;
    private String OptionA;
    private String OptionB;
    private String OptionC;
    private String OptionD;
    private String Answer;

    //正确率
    private String CorrectRate;

    //答题数量
    private Integer NumerOfRes;

    public Exam(int examId, String examType, String stem, String optionA, String optionB,
                String optionC, String optionD, String answer, String correctRate, Integer numerOfRes) {
        ExamId = examId;
        ExamType = examType;
        Stem = stem;
        OptionA = optionA;
        OptionB = optionB;
        OptionC = optionC;
        OptionD = optionD;
        Answer = answer;
        CorrectRate = correctRate;
        NumerOfRes = numerOfRes;
    }



    @Override
    public String toString() {
        return "Exam{" +
                "ExamId=" + ExamId +
                ", ExamType='" + ExamType + '\'' +
                ", Stem='" + Stem + '\'' +
                ", OptionA='" + OptionA + '\'' +
                ", OptionB='" + OptionB + '\'' +
                ", OptionC='" + OptionC + '\'' +
                ", OptionD='" + OptionD + '\'' +
                ", Answer='" + Answer + '\'' +
                ", CorrectRate='" + CorrectRate + '\'' +
                ", NumerOfRes=" + NumerOfRes +
                '}';
    }

    public int getExamId() {
        return ExamId;
    }

    public void setExamId(int examId) {
        ExamId = examId;
    }

    public String getExamType() {
        return ExamType;
    }

    public void setExamType(String examType) {
        ExamType = examType;
    }

    public String getStem() {
        return Stem;
    }

    public void setStem(String stem) {
        Stem = stem;
    }

    public String getOptionA() {
        return OptionA;
    }

    public void setOptionA(String optionA) {
        OptionA = optionA;
    }

    public String getOptionB() {
        return OptionB;
    }

    public void setOptionB(String optionB) {
        OptionB = optionB;
    }

    public String getOptionC() {
        return OptionC;
    }

    public void setOptionC(String optionC) {
        OptionC = optionC;
    }

    public String getOptionD() {
        return OptionD;
    }

    public void setOptionD(String optionD) {
        OptionD = optionD;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public String getCorrectRate() {
        return CorrectRate;
    }

    public void setCorrectRate(String correctRate) {
        CorrectRate = correctRate;
    }

    public Integer getNumerOfRes() {
        return NumerOfRes;
    }

    public void setNumerOfRes(int numerOfRes) {
        NumerOfRes = numerOfRes;
    }
}
