package com.springapp.mvc;

import com.springapp.mvc.model.Greeting;
import com.springapp.mvc.model.HelloMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reactor.core.Reactor;
import reactor.event.Event;

@Controller
@RequestMapping("/")
public class HelloController {

    @Autowired
    private SimpMessagingTemplate template;//忽悠此错误

    @Autowired
    @Qualifier("rootReactor")
    private Reactor r;

    @RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
        r.notify("helloWebSocket", Event.wrap("你好"));
		return "hello";
	}

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String printWelcomeHi(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        r.notify("helloWebSocket", Event.wrap("你好"));
        return "hi";
    }

    @RequestMapping(value = "/hiReactor",method = RequestMethod.GET)
    public String printWelcomeHiReactor(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        r.notify("helloWebSocket", Event.wrap("你好"));
        return "reactor";
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(5000); // simulated delay
        this.template.convertAndSend("/topic/greetings", "Hello World");
        return new Greeting("Hello1, " + message.getName() + "!");
    }

    @MessageMapping("/hi")
    @SendTo("/topic/greetings")
    public void greeting2(HelloMessage message) throws Exception {
//        Thread.sleep(5000); // simulated delay
        this.template.convertAndSend("/topic/greetings", new Greeting("Hello1, " + message.getName() + "!"));
//        return new Greeting("Hello1, " + message.getName() + "!");
    }

    @MessageMapping("/hiReactor")
    @SendTo("/reactor/hiReactor")
    public void hiReactor(HelloMessage message) throws Exception{
        r.notify("hiReactor", Event.wrap("你好"));
    }

}