package com.freetalk.freetalk_backend.serviceimpl;

//高级用户(type = 4)
//活跃用户(type = 1)
//基本用户(type = 2)
//初级用户(type = 3)
class UserClassification{
    int classifyUser(int post, int reply, int prevType, int time){
        int type;
        if(prevType == 4)
            type = 4;
        else if(prevType == 3){
            if(time >= 30 &&(post >10 || reply > 50))
                type = 4;
            else if(post < 5 || reply <25)
                    type = 2;
            else
                type = 3;
        }
        else if (prevType == 2){
            if(time >= 30 && reply > 50)
                type = 3;
            else if(time < 30)
                type = 2;
            else{
                if(reply < 10)
                    type = 1;
                else
                    type = 2;
            }
        }
        else{
            if(time >= 30)
                type = 2;
            else
                type = 1;
        }
        return type;
    }
}
