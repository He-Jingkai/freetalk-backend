package com.freetalk.freetalk_backend.serviceimpl;
// return 0 最新回答
// return 1 一般回答
// return 2 高点赞
// return 3 高回复
// return 5 高赞高回复
// return -1 表示普通回答

public class ReplyClassification {
    public static int Judge(int likes,int replies,int time) {
        int type;
        if (time >= 10 || likes < 20 || replies < 3)
            type = -1;
        else if(time < 1)
            type = 0;
        else {
            if (replies > 100 && likes > 500) type = 5;
            else {
                if (likes >= 1000) type = 2;
                else if (replies > 200) type = 3;
                else type = 1;
            }
        }
        return type;
    }
}
