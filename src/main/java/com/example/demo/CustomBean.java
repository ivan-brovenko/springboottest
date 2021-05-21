package com.example.demo;

public class CustomBean {
    private String value;

    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "CustomBean{" +
                "value='" + value + '\'' +
                '}';
    }
}
