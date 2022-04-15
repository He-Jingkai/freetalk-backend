package com.freetalk.freetalk_backend.serviceimpl;

//初级用户：1
//活跃用户：2
//中级活跃用户：3

class UserClassification{
    int classifyUser(int post, int reply, int prevType, int time){
        int type = 1;
        if (prevType==3) {
            type = 3;
        } else if (prevType==2){
            if (post+reply>200){
                type=3;
            } else type=2;
        } else {
            if (post+reply>100 && time>200){
                type=2;
            }
        }
        return type;
    }

    public static void main(String[] args){
        UserClassification userClassification = new UserClassification();
        System.out.println(userClassification.classifyUser(1,2,3,4));
    }
}

