package com.one.component;


import com.one.util.PathUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BackGround extends Box {

    public BackGround(){
        super(1);

        JPanel bgPanel = new JPanel();
        bgPanel.setBackground(new Color(0x87ECEC));

        Calendar now = Calendar.getInstance();

        LocalDate startDate = LocalDate.of(now.get(Calendar.YEAR),now.get(Calendar.MONTH)+1,now.get(Calendar.DAY_OF_MONTH));
        LocalDate endDate = LocalDate.of(2021,12,25);   //2022届
        LocalDate endDate2 = LocalDate.of(2022,12,24);  //2023届

        long Days = ChronoUnit.DAYS.between(startDate,endDate);
        long Days2 = ChronoUnit.DAYS.between(startDate,endDate2);



        JLabel jLabel1 = new JLabel("距离2022考研还剩"+Days+"天");
        JLabel jLabel2 = new JLabel("距离2023考研还剩"+Days2+"天");




        bgPanel.add(jLabel1,BorderLayout.CENTER);
        bgPanel.add(jLabel2,BorderLayout.CENTER);


        this.add(bgPanel);
    }



}
