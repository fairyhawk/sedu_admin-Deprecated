package com.shangde.edu.tk.condition;

public class QueryTaskCusCondition {
    private int id;
    private int cusId;
    private int taskId;
    private String keyWord;
        
    public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
}