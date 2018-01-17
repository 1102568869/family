package tech.washmore.family;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Washmore
 * @version V1.0
 * @summary Family程序入口
 * @Copyright (c) 2017, www.washmore.tech
 * @since 2017/6/13
 */
@SpringBootApplication
public class FamilyStarter {

    public static void main(String[] args) {
        try {
            SpringApplication.run(FamilyStarter.class, args);
            System.out.println("---------------正常启动--^_^------------#####################################");
        } catch (Exception e) {
            System.out.println("---------------启动挂了--T_T------------#####################################");
        }

    }
}
