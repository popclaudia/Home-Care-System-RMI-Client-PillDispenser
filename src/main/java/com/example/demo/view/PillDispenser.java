package com.example.demo.view;

import com.example.demo.client.MedicationPlanInterfac;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PillDispenser extends JFrame implements ActionListener {
    JLabel dateTime;
    JLabel medicationPlans;
    MedicationPlanInterfac service;
    Integer user;
    ArrayList<JLabel> meds=new ArrayList<JLabel>();
    ArrayList<JLabel> times=new ArrayList<JLabel>();
    ArrayList<JButton> take=new ArrayList<JButton>();
    ArrayList<Integer> taken=new ArrayList<Integer>();


    public PillDispenser(Integer u, MedicationPlanInterfac service) {

        this.service=service;
        this.user = u;
        dateTime = new JLabel(" ");
        SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyy \nHH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String f = ". " + formatter.format(date) + " .";
        dateTime.setText(f);
        showTime();
        getMedicalPlan();
        getMeds();
        initUI();
    }

    private void initUI() {

        ImageIcon img = new ImageIcon("src/main/resources/pills-icon (1).png");
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(img);
        JLabel plans = new JLabel("Your medication plans:");
        dateTime.setForeground(new Color(88, 0, 17));
        dateTime.setOpaque(true);
        dateTime.setBackground(new Color(251, 253, 202));
        dateTime.setFont(new Font(dateTime.getFont().getName(),dateTime.getFont().getStyle(),20));
        medicationPlans.setForeground(Color.white);
        medicationPlans.setFont(new Font(medicationPlans.getFont().getName(),medicationPlans.getFont().getStyle(),16));
        plans.setForeground(new Color(251, 253, 194));
        plans.setFont(new Font(plans.getFont().getName(),plans.getFont().getStyle(),18));
        setTitle("Pill Dispenser");
        ImageIcon icon = new ImageIcon("src/main/resources/medicine.png");
        setIconImage(icon.getImage());
        setSize(1000, 680);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        createLayout(dateTime, medicationPlans, plans, imageLabel);

    }

    private void createLayout(JComponent... arg) {

        Container pane = getContentPane();
        pane.setBackground(new Color(2, 56, 61));
        GroupLayout gl = new GroupLayout(pane);
        GroupLayout gl = new GroupLayout(pane);
        pane.setBackground(new Color(2, 56, 61));
        pane.setLayout(gl);
        gl.setAutoCreateContainerGaps(true);

        GroupLayout.ParallelGroup pGroup = gl
                .createParallelGroup(GroupLayout.Alignment.CENTER);
        GroupLayout.SequentialGroup sGroup = gl
                .createSequentialGroup();
        for (int i=0; i<meds.size(); i++) {
            GroupLayout.SequentialGroup ssGroup = gl
                    .createSequentialGroup();
            GroupLayout.ParallelGroup ppGroup = gl
                    .createParallelGroup(GroupLayout.Alignment.CENTER);
            ssGroup.addComponent(meds.get(i));
            ssGroup.addGap(130,140,150);
            ssGroup.addComponent(times.get(i));
            ssGroup.addGap(130,140,150);
            ssGroup.addComponent(take.get(i));
            ssGroup.addGap(130,140,150);
            pGroup.addGroup(ssGroup);

            ppGroup.addComponent(meds.get(i));
            ppGroup.addComponent(times.get(i));
            ppGroup.addComponent(take.get(i));
            sGroup.addGroup(ppGroup);
            sGroup.addGap(10,12,14);
        }

        gl.setHorizontalGroup(gl
                .createSequentialGroup()
                .addGap(60,70,80)
                .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(650,670,720)
                        .addComponent(arg[2])
                        .addComponent(arg[1])
                        .addGroup(pGroup))
                .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(arg[0])
                        .addComponent(arg[3])
                )
                .addGap(60,70,80)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(10,20,30)
                .addComponent(arg[0])
                .addGap(5,10,15)
                .addComponent(arg[2])
                .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(arg[1])
                        .addComponent(arg[3]))
                .addGap(10,20,30)
                .addGroup(sGroup)
        );

    }

    public void getMedicalPlan(){
        List<String> list = service.getPlan(user);
        String medicationPlans ="<html>";
        int i = 0;
        for(String s: list) {
            i++;
            if(i%2==1)
                medicationPlans = medicationPlans + "<li>";
            medicationPlans = medicationPlans + s;
            if(i%2==1)
                medicationPlans = medicationPlans + "</li>";
            if(i%2==0)
                medicationPlans = medicationPlans + "<br><br>";
        }
        medicationPlans = medicationPlans + "</html>";
        this.medicationPlans= new JLabel();
        this.medicationPlans.setText(medicationPlans);

    }

    public void getMeds(){
        List<String> list = service.getMedication(user);
        for(String s: list){
            System.out.println(s);
            String[] parts = s.split("@");
            for(int i=1; i< parts.length; i++){
                JLabel m = new JLabel(parts[0]);
                JLabel t = new JLabel(" ⏰ "+parts[i]);
                JButton b = new JButton("  TAKEN  ");
                String[] start_end = parts[i].split("-");
                int start = Integer.parseInt(start_end[0]);
                int end = Integer.parseInt(start_end[1]);
                m.setForeground(new Color(231, 199, 253));
                t.setForeground(new Color(255, 201, 213));
                b.setBackground(new Color(40, 1, 27));
                b.setForeground(Color.white);
                b.setBorder(BorderFactory.createBevelBorder(0));
                m.setFont(new Font(m.getFont().getName(),m.getFont().getStyle(),18));
                t.setFont(new Font(t.getFont().getName(),t.getFont().getStyle(),20));
                b.setFont(new Font(b.getFont().getName(),b.getFont().getStyle(),16));
                b.addActionListener(this);
                b.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        b.setBackground(new Color(102, 242, 255));
                        b.setForeground(Color.black);
                    }

                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        b.setBackground(new Color(40, 1, 27));
                        b.setForeground(Color.white);
                    }
                });
                meds.add(m);
                times.add(t);
                take.add(b);
                taken.add(0);
                this.activateButton(start, end, b);
            }
        }
    }

    private void activateButton(int start, int end, JButton b){

        Runnable runnable =
                () -> {
                    while(true){
                        SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyy \nHH:mm:ss");
                        Date date = new Date(System.currentTimeMillis());
                        int hours = date.getHours();
                        if(start<=hours && end>hours && taken.get(take.indexOf(b))==0){
                            b.setEnabled(true);
                        }else{
                            b.setEnabled(false);
                        }

                        if(end<=hours && taken.get(take.indexOf(b))==0){
                            taken.set(take.indexOf(b), 2);
                            service.saveTakenNotTaken(meds.get(take.indexOf(b)).getText(), date, false);
                            meds.get(take.indexOf(b)).setForeground(Color.red);
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                };

        Thread thread = new Thread(runnable);
        thread.start();

    }
    private void showTime(){


        Runnable runnable =
                () -> {
                    while(true){
                        SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyy \nHH:mm:ss");
                        Date date = new Date(System.currentTimeMillis());
                        String f = " \uD83D\uDCC6 " + formatter.format(date)+ " \n ";
                        dateTime.setText(f);
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                };

        Thread thread = new Thread(runnable);
        thread.start();



    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for(int i=0;i<meds.size();i++){
            if(e.getSource() == take.get(i)){
                String soundName = "src/main/resources/cheerful-2-528.wav";
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                }catch(Exception ee){
                    ee.printStackTrace();
                }
                String med = meds.get(i).getText();
                SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyy \nHH:mm:ss");
                Date date = new Date(System.currentTimeMillis());
                service.saveTakenNotTaken(med, date, true);
                take.get(i).setEnabled(false);
                taken.set(i, 1);
                JLabel l = meds.get(i);
                l.setForeground(new Color(106, 255, 75));
                meds.set(i,l);
            }
        }
    }
}
