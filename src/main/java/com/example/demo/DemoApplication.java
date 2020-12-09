package com.example.demo;
import com.example.demo.client.MedicationPlanInterfac;
import com.example.demo.view.Login;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

import javax.swing.*;

@SpringBootApplication
public class DemoApplication extends JFrame {

    @Bean
    public HessianProxyFactoryBean hessianInvoker() {
        HessianProxyFactoryBean invoker = new HessianProxyFactoryBean();
        invoker.setServiceUrl("https://rmi-server-app.herokuapp.com/MedicationPlanInterface");
        //invoker.setServiceUrl("http://localhost:8080/MedicationPlanInterface");
        invoker.setServiceInterface(MedicationPlanInterfac.class);
        return invoker;
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new SpringApplicationBuilder(
                DemoApplication.class).headless(false).run(args);

        Login appFrame = context.getBean(Login.class);
        MedicationPlanInterfac interfac = context.getBean(MedicationPlanInterfac.class);
        appFrame.setInt(interfac);
        appFrame.setVisible(true);

    }
}
