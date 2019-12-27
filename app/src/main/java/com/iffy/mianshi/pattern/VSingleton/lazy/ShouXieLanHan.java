package com.iffy.mianshi.pattern.VSingleton.lazy;

public class ShouXieLanHan {
    //关闭构造函数
    private ShouXieLanHan(){

    }
    final static ShouXieLanHan instance = new ShouXieLanHan();
    static public ShouXieLanHan getInstance() {
        return instance;
    }

    public String who(){
        return "iffy";
    }
}

class  Test{
    public static void main(String[] args) {
        //new ShouXieLanHan();
        ShouXieLanHan.getInstance().who();
    }
}


