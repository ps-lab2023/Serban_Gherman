package com.book.projectps.controller;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class WebSocketController {

    @MessageMapping("/send/message")
    @SendTo("/topic/message")
    public String sendMessage(String message){
        return message;
    }
}
