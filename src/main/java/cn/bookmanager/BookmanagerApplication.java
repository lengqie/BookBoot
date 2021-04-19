package cn.bookmanager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
public class BookmanagerApplication {


    public static void main(String[] args){
        SpringApplication.run(BookmanagerApplication.class, args);

        // QuickStart();

    }
    public static void  QuickStart(){
        Runtime run = Runtime.getRuntime();
        try {
            Process process = run.exec("cmd /k start http://localhost:8081");
            System.out.println("http://localhost:8081");
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

}
