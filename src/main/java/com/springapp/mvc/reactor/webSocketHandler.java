package com.springapp.mvc.reactor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import reactor.core.Reactor;
import reactor.event.Event;
import reactor.spring.annotation.Selector;

/**
 * Created by zhangjie on 2014/11/25.
 */
@Component
public class webSocketHandler {

    @Autowired
    private SimpMessagingTemplate template;//忽悠此错误

    @Autowired
    @Qualifier("rootReactor")
    private Reactor reactor;

    @Selector(value = "helloWebSocket", reactor = "@rootReactor")
    public void handleWebSocket(Event<String> event) throws Exception{
        System.out.println("------web socket");
    }

    @Selector(value = "hiReactor", reactor = "@rootReactor")
    public void handleWebSocketMessage(Event<String> event) throws Exception{
        System.out.println("------hi reactor");
        this.template.convertAndSend("/reactor/hiReactor", "zhangjie");
    }
}
