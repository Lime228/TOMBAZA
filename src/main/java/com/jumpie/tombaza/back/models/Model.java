package com.jumpie.tombaza.back.models;

public abstract class Model<T> {
    private T id;
    void error() {
        System.out.println("The value is higher than it should be. Try again.");
    }
    public T getId(){
        return id;
    }
    public void setId(T id){
        this.id = id;
    }
}
