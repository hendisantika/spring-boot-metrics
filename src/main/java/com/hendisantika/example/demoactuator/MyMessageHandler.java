package com.hendisantika.example.demoactuator;

import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : demo-actuator
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 10/10/17
 * Time: 08.54
 * To change this template use File | Settings | File Templates.
 */

@Service
public class MyMessageHandler implements MessageHandler {
    public void handleMessage(org.springframework.messaging.Message<?> message) throws org.springframework.messaging.MessagingException {
        for (String headerKey : message.getHeaders().keySet()) {
            System.out.println("Header: "+headerKey+"="+message.getHeaders().get(headerKey));
        }
        System.out.println("Payload: "+message.getPayload());
    }
}
