package com.example.demo.client;
        import org.springframework.beans.factory.annotation.Value;
        import org.springframework.boot.SpringApplication;
        import org.springframework.context.annotation.Bean;
        import org.springframework.remoting.caucho.HessianProxyFactoryBean;
        import org.springframework.remoting.rmi.RmiProxyFactoryBean;
        import java.util.List;

public class RMIClient {

    @Value("${server.port}")
    private Integer userBucketPath;
    //https://rmi-server-app.herokuapp.com/
    @Bean
//    RmiProxyFactoryBean service() {
//        RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
//        rmiProxyFactory.setServiceUrl("rmi://localhost:1089/MedicationPlanInterface");
//        //rmiProxyFactory.setServiceUrl("rmi://rmi-server-app.herokuapp.com/MedicationPlanInterface");
//        rmiProxyFactory.setServiceInterface(MedicationPlanInterfac.class);
//        return rmiProxyFactory;
//    }

    public HessianProxyFactoryBean hessianInvoker() {
        HessianProxyFactoryBean invoker = new HessianProxyFactoryBean();
        invoker.setServiceUrl("https://rmi-server-app.herokuapp.com/MedicationPlanInterface");
        //invoker.setServiceUrl("http://localhost:8080/MedicationPlanInterface");
        invoker.setServiceInterface(MedicationPlanInterfac.class);
        return invoker;
    }

}