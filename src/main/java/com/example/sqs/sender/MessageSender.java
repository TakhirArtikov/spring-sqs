package com.example.sqs.sender;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sqs1")
public class MessageSender {

//    @Value("${cloud.aws.end-point.uri}")
    private String endpoint="https://sqs.ap-northeast-1.amazonaws.com/836511517717/taskstatesqs";

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;


    @GetMapping("/send/{message}")
    public void send(@PathVariable(value = "message") String message){
       Message payload = MessageBuilder.withPayload(message)
               .setHeader("message", message)
               .build();
       queueMessagingTemplate.send(endpoint, payload);
    }
}
