package com.iffy.mianshi.binder;

import android.os.IBinder;
import android.os.Parcel;

public class Client {
    public Client() {
        //获取服务
        //IBinder b = ServiceManager.getService(Stub.name);
        // 1. Client进程 将需要传送的数据写入到Parcel对象中
        // data = 数据 = 目标方法的参数（Client进程传进来的，此处就是整数a和b） + IInterface接口对象的标识符descriptor
        Parcel data = Parcel.obtain();
        data.writeInt(1);
        data.writeInt(2);

        // 方法对象标识符让Server进程在Binder对象中根据"add two int"通过queryLocalIInterface（）查找相应的IInterface对象
        // （即Server创建的plus），Client进程需要调用的相加方法就在该对象中
        data.writeInterfaceToken("add two int");

        Parcel reply = Parcel.obtain();
        // reply：目标方法执行后的结果（此处是相加后的结果）
        // 2. 通过 调用代理对象的transact（） 将 上述数据发送到Binder驱动
        // 参数说明：
        // 1. Stub.add：目标方法的标识符（Client进程 和 Server进程 自身约定，可为任意）
        // 2. data ：上述的Parcel对象
        // 3. reply：返回结果
        // 0：可不管
        try {
            //发送请求后阻塞直到reply包含的结果返回
            // 注：在发送数据后，Client进程的该线程会暂时被挂起
            // 所以，若Server进程执行的耗时操作，请不要使用主线程，以防止ANR
           // b.transact(Stub.add, data, reply, 0);

            // 1. Binder驱动根据 代理对象 沿原路 将结果返回 并通知Client进程获取返回结果
            // 2. 通过代理对象 接收结果（之前被挂起的线程被唤醒）
            reply.readException();
            int result = reply.readInt();
        } catch (Exception e) {

        }

    }
}
