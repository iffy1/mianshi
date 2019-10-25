package com.iffy.mianshi.binder;

import android.os.*;
import android.os.Binder;

//服务端
public class Stub extends Binder implements IPlus {
    final static int add = 1000;
    final static String name ="ppppllllluuuusssss";

    public Stub() {
        // 1. 将（add two int，IPlus）作为（key,value）对存入到Binder对象中的一个Map<String,IInterface>对象中
        // 2. 之后，Binder对象 可根据add two int通过queryLocalIInterface（）获得对应IInterface对象（即plus）的引用，可依靠该引用完成对请求方法的调用
        attachInterface(this, "add two int");
    }

    // 复写onTransact（）
    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case Stub.add: {
                // 客户端先发送的是服务描述，所以这里先接收服务描述并判断是否和自己一致
                data.enforceInterface("add two int");

                int arg0 = data.readInt();
                int arg1 = data.readInt();
                //调用自己的add方法
                int result = add(arg0, arg1);

                //c将计算结果写入到reply
                reply.writeInt(result);
                return true;
            }
        }
        return super.onTransact(code, data, reply, flags);
    }

    // 确定Client进程需要调用的方法
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    // 实现IInterface(IPlus)接口中唯一的方法
    @Override
    public IBinder asBinder() {
        return null;
    }
}
