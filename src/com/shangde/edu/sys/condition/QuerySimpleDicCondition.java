package com.shangde.edu.sys.condition;

public class QuerySimpleDicCondition {
    private int dicId;
    private String tableName;
    private String columnName;
        
    public int getDicId(){
        return dicId;
    }

    public void setDicId(int dicId){
        this.dicId = dicId;
    }
    public String getTableName(){
        return tableName;
    }

    public void setTableName(String tableName){
        this.tableName = tableName;
    }
    public String getColumnName(){
        return columnName;
    }

    public void setColumnName(String columnName){
        this.columnName = columnName;
    }
}