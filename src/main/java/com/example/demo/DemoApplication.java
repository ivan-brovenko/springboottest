package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("a", "value1");
        dataMap.put("b", "value2");
        dataMap.put("c", "value3");

        ConfigurableApplicationContext configContext = (ConfigurableApplicationContext) applicationContext;
        SingletonBeanRegistry beanRegistry = configContext.getBeanFactory();

        // expect false
        System.out.println(configContext.containsBean("a"));
        System.out.println(configContext.containsBean("b"));
        System.out.println(configContext.containsBean("c"));
//        error
//        Object c = configContext.getBean("c");
//        System.out.println(c);

        dataMap.entrySet().forEach(entry -> {
            CustomBean customBean = new CustomBean();
            customBean.setValue(entry.getValue());
            beanRegistry.registerSingleton(entry.getKey(), customBean);
        });

        // expect true
        System.out.println(configContext.containsBean("a"));
        System.out.println(configContext.containsBean("b"));
        System.out.println(configContext.containsBean("c"));

        CustomBean beanC = (CustomBean) configContext.getBean("c");
        System.out.println(beanC);
    }
}
