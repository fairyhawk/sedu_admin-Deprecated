package com.shangde.common.vo;

/**
 * 简单属性类
 * @author guoqiang.liu
 */
public class SimpleProperty {
    private float floatValue;
    private String stringDesc;
    private int value;
    private int desc;

    public SimpleProperty(int value, int desc) {
        this.value = value;
        this.desc = desc;
    }

    public SimpleProperty(float floatValue, String stringDesc) {
        this.floatValue = floatValue;
        this.stringDesc = stringDesc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getDesc() {
        return desc;
    }

    public void setDesc(int desc) {
        this.desc = desc;
    }

    public float getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(float floatValue) {
        this.floatValue = floatValue;
    }

    public String getStringDesc() {
        return stringDesc;
    }

    public void setStringDesc(String stringDesc) {
        this.stringDesc = stringDesc;
    }
}
