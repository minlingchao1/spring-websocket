package com.springapp.mvc.reactor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;
import reactor.spring.context.config.EnableReactor;

/**
 * Created by zhangjie on 2014/11/25.
 */
@Configuration
@EnableReactor
@ComponentScan
public class ReactorConfig {


    @Bean(name = "rootReactor")
    public Reactor rootReactor(Environment env){
        return Reactors.reactor().env(env).get();


//        Reactor reactor = Reactors.reactor(env);
//        System.out.println("env=" + env);
//        System.out.println("reactor=" + reactor);
//        return reactor;
    }

    @Bean(name = "reportReactor")
    public Reactor reportReactor(Environment env){
        return Reactors.reactor().env(env).get();
    }
}
