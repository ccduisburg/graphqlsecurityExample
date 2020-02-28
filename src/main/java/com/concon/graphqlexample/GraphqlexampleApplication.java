package com.concon.graphqlexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class GraphqlexampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphqlexampleApplication.class, args);
    }

}


