package com.one.Exam;

import com.one.listener.ActionDoneListener;

import javax.swing.*;

public class AddExam extends JDialog {

    ActionDoneListener listener = null;

    public AddExam(JFrame jf, String title, boolean isModel, ActionDoneListener listener){
        super(jf,title,isModel);
        this.listener = listener;

    }


    public ActionDoneListener getListener() {
        return listener;
    }

    public void setListener(ActionDoneListener listener) {
        this.listener = listener;
    }
}
