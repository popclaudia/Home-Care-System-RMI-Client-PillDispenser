package com.example.demo.view;

import com.example.demo.client.MedicationPlanInterfac;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

@Component
public class Login extends JFrame {
    MedicationPlanInterfac service;
    private Image backgroundImage;

    public Login() {
        //service = SpringApplication.run(RMIClient.class).getBean(MedicationPlanInterfac.class);

        try {

            Image i = ImageIO.read(new File("src/main/resources/capsules-pills-medicine.jpg"));
            backgroundImage = i;
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, null);
            }
        });
        initUI();
    }

    public void setInt(MedicationPlanInterfac interfac){
        service = interfac;
    }

    private void initUI() {

        JButton quitButton = new JButton("LogIn");
        JTextField user =new JTextField();
        JLabel message = new JLabel(" ");

        quitButton.addActionListener((ActionEvent event) -> {
            service.hello("Hello from client!");
            try {
                Integer u = Integer.parseInt(user.getText());
                message.setText("");
                PillDispenser pill = new PillDispenser(u, service);
                pill.setVisible(true);
                this.setVisible(false);
            }
            catch(Exception e){
                message.setText("Your Id must be a number");
                message.setForeground(Color.RED);
                e.printStackTrace();
            }


        });
        user.setPreferredSize(new Dimension(200,35));
        quitButton.setPreferredSize(new Dimension(100,35));
        quitButton.setBackground(new Color(0, 71, 95));
        quitButton.setForeground(Color.white);
        quitButton.setFont(new Font(quitButton.getFont().getName(),quitButton.getFont().getStyle(),18));
        createLayout(quitButton, user, message);
        setTitle("Pill Dispenser");
        ImageIcon icon = new ImageIcon("src/main/resources/medicine.png");
        setIconImage(icon.getImage());
        setSize(1000, 680);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    private void createLayout(JComponent... arg) {

        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl
                .createSequentialGroup()
                .addGap(200,210,220)
                .addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(arg[1])
                .addComponent(arg[2]))
                .addGap(10,20,100)
                .addComponent(arg[0])
                .addGap(200,210,220)

        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(40,60,100)
                .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(arg[1])
                .addComponent(arg[0]))
                .addComponent(arg[2])
        );

    }

}
