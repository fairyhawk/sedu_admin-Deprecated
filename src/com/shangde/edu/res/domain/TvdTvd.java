package com.shangde.edu.res.domain;

import java.io.Serializable;

public class TvdTvd implements Serializable{
    private int id;
    private int vdid;
    private int tjvdid;
        
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public int getVdid(){
        return vdid;
    }

    public void setVdid(int vdid){
        this.vdid = vdid; 
    }
        
    public int getTjvdid(){
        return tjvdid;
    }

    public void setTjvdid(int tjvdid){
        this.tjvdid = tjvdid; 
    }
}
