package com.freetalk.freetalk_backend.controller.freetalk_backend.serviceimpl;

import com.freetalk.freetalk_backend.serviceimpl.UserClassification;
import org.junit.jupiter.api.Assertions;

class UserClassificationTest {

    @org.junit.jupiter.api.Test
    void classifyUser() {
        UserClassification userClassification= new UserClassification();
        Assertions.assertEquals(3,userClassification.classifyUser(200,300,3,400));
        Assertions.assertEquals(3,userClassification.classifyUser(50,200,2,300));
        Assertions.assertEquals(2,userClassification.classifyUser(50,50,2,100));
        Assertions.assertEquals(2,userClassification.classifyUser(100,50,1,250));
        Assertions.assertEquals(1,userClassification.classifyUser(10,10,1,200));
    }
}
