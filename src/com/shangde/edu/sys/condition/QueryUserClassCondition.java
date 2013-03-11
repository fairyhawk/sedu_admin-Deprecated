package com.shangde.edu.sys.condition;

public class QueryUserClassCondition {
    private int userId;
    private int classId;
        
    public int getUserId(){
        return userId;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }
    public int getClassId(){
        return classId;
    }

    public void setClassId(int classId){
        this.classId = classId;
    }
}