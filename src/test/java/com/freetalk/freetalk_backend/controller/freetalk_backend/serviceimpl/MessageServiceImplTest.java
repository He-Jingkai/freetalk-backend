package com.freetalk.freetalk_backend.controller.freetalk_backend.serviceimpl;

import com.freetalk.freetalk_backend.FreeTalkBackendApplication;
import com.freetalk.freetalk_backend.dto.MessageView;
import com.freetalk.freetalk_backend.entity.Message;
import com.freetalk.freetalk_backend.service.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FreeTalkBackendApplication.class)
@Transactional
class MessageServiceImplTest {
    @Autowired
    private MessageService messageService;

    @Test
    void findMessagesBySendUser_UserIdInAndAcceptUser_UserIdIn() {
        List<Integer> integers=new ArrayList<>();
        integers.add(2);
        integers.add(3);
        List<MessageView> messages= messageService.findMessagesBySendUser_UserIdInAndAcceptUser_UserIdIn(integers,integers);
        assertThat(messages.size(),equalTo(1));
    }

    @Test
    void findMessagesByAcceptUser_UserIdAndSendUser_UserIdAndIsRead() {
        List<Message> messages= messageService.findMessagesByAcceptUser_UserIdAndSendUser_UserIdAndIsRead(1,2,1);
        assertThat(messages.size(),equalTo(1));
    }

    @Test
    void postAMessage() {
        Map<String,Object> map1=new HashMap<>();
        map1.put("sendUserId", 0);
        map1.put("receiveUserId",7);
        map1.put("messageContent","test");
        messageService.postAMessage(map1);
        List<Message> messages1=messageService.findMessagesByAcceptUser_UserIdAndSendUser_UserIdAndIsRead(0,7,0);
        assertThat(messages1.size(),equalTo(0));
        Map<String,Object> map2=new HashMap<>();
        map2.put("sendUserId", 1);
        map2.put("receiveUserId",6);
        map2.put("messageContent","test");
        messageService.postAMessage(map2);
        List<Message> messages2=messageService.findMessagesByAcceptUser_UserIdAndSendUser_UserIdAndIsRead(1,6,0);
        assertThat(messages2.size(),equalTo(1));
        Map<String,Object> map3=new HashMap<>();
        map3.put("sendUserId", 2);
        map3.put("receiveUserId",5);
        map3.put("messageContent","test");
        messageService.postAMessage(map3);
        List<Message> messages3=messageService.findMessagesByAcceptUser_UserIdAndSendUser_UserIdAndIsRead(2,5,0);
        assertThat(messages3.size(),equalTo(1));
        Map<String,Object> map4=new HashMap<>();
        map4.put("sendUserId", 3);
        map4.put("receiveUserId",4);
        map4.put("messageContent","Hello world");
        messageService.postAMessage(map4);
        List<Message> messages4=messageService.findMessagesByAcceptUser_UserIdAndSendUser_UserIdAndIsRead(3,4,0);
        assertThat(messages4.size(),equalTo(1));
        Map<String,Object> map5=new HashMap<>();
        map5.put("sendUserId", 4);
        map5.put("receiveUserId",3);
        map5.put("messageContent","Hello world");
        messageService.postAMessage(map5);
        List<Message> messages5=messageService.findMessagesByAcceptUser_UserIdAndSendUser_UserIdAndIsRead(4,3,0);
        assertThat(messages5.size(),equalTo(1));
        Map<String,Object> map6=new HashMap<>();
        map6.put("sendUserId", 5);
        map6.put("receiveUserId",2);
        map6.put("messageContent","Hello world");
        messageService.postAMessage(map6);
        List<Message> messages6=messageService.findMessagesByAcceptUser_UserIdAndSendUser_UserIdAndIsRead(5,4,0);
        assertThat(messages6.size(),equalTo(1));
        Map<String,Object> map7=new HashMap<>();
        map7.put("sendUserId", 6);
        map7.put("receiveUserId",1);
        map7.put("messageContent","Hello world");
        messageService.postAMessage(map7);
        List<Message> messages7=messageService.findMessagesByAcceptUser_UserIdAndSendUser_UserIdAndIsRead(6,1,0);
        assertThat(messages7.size(),equalTo(1));
    }

    @Test
    void setAllMessageWithAUserHaveRead() {
        messageService.setAllMessageWithAUserHaveRead("2","3");
        List<Message> messages=messageService.findMessagesByAcceptUser_UserIdAndSendUser_UserIdAndIsRead(2,3,0);
        assertThat(messages.size(),equalTo(0));

    }

    @Test
    void numberOfMessagesUnread() {
        Integer ret=messageService.numberOfMessagesUnread("3");
        assertThat(ret,equalTo(11));
    }

    @Test
    void numberOfMessagesUnreadWithAUser() {
        Integer ret=messageService.numberOfMessagesUnreadWithAUser("3","2");
        assertThat(ret,equalTo(1));
    }
}
