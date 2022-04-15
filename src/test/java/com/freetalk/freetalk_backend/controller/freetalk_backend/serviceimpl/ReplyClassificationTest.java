package com.freetalk.freetalk_backend.controller.freetalk_backend.serviceimpl;

import com.freetalk.freetalk_backend.serviceimpl.ReplyClassification;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class ReplyClassificationTest {
    @Test
    void judge(){
        MatcherAssert.assertThat(ReplyClassification.Judge(15,10,1),equalTo(-1));
        assertThat(ReplyClassification.Judge(0,25,4),equalTo(0));
        assertThat(ReplyClassification.Judge(3,600,150),equalTo(5));
        assertThat(ReplyClassification.Judge(3,1500,90),equalTo(2));
        assertThat(ReplyClassification.Judge(3,800,90),equalTo(3));
        assertThat(ReplyClassification.Judge(3,800,18),equalTo(1));
    }
}
