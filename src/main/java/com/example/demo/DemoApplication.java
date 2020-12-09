package com.example.demo;
import com.example.demo.view.Login;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class DemoApplication extends JFrame {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new SpringApplicationBuilder(
                DemoApplication.class).headless(false).run(args);

        Login appFrame = context.getBean(Login.class);
        appFrame.setVisible(true);

    }
}
