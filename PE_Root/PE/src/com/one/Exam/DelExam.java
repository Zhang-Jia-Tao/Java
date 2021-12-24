package com.one.Exam;

import com.one.listener.ActionDoneListener;

import javax.swing.*;

public class DelExam {

    private ActionDoneListener listener = null;
    private String id = null;
    private JFrame jf = null;

    public DelExam(JFrame jf,String id,ActionDoneListener listener){
        this.listener = listener;
        this.id = id;
        this.jf = jf;


    }


    public ActionDoneListener getListener() {
        return listener;
    }

    public void setListener(ActionDoneListener listener) {
        this.listener = listener;
    }
}
