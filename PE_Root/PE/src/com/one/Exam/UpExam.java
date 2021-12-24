package com.one.Exam;

import com.one.listener.ActionDoneListener;

import javax.swing.*;

public class UpExam extends JDialog {

    private ActionDoneListener listener = null;
    private String id;
    public UpExam(JFrame jf,String title,boolean isModel,ActionDoneListener listener,String id){
        super(jf,title,isModel);
        this.listener = listener;
        this.id = id;




    }




    public ActionDoneListener getListener() {
        return listener;
    }

    public void setListener(ActionDoneListener listener) {
        this.listener = listener;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
