package com.iffy.mianshi.binder.app2system;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

public interface IServiceManager extends IInterface {
    String descriptor = "android.os.IServiceManager";
    int GET_SERVICE_TRANSACTION = 1;
    int CHECK_SERVICE_TRANSACTION = 2;
    int ADD_SERVICE_TRANSACTION = 3;
    int LIST_SERVICES_TRANSACTION = 4;
    int CHECK_SERVICES_TRANSACTION = 5;
    int SET_PERMISSION_CONTROLLER_TRANSACTION = 6;
    int DUMP_FLAG_PRIORITY_CRITICAL = 1;
    int DUMP_FLAG_PRIORITY_HIGH = 2;
    int DUMP_FLAG_PRIORITY_NORMAL = 4;
    int DUMP_FLAG_PRIORITY_DEFAULT = 8;
    int DUMP_FLAG_PRIORITY_ALL = 15;
    int DUMP_FLAG_PROTO = 16;


    IBinder getService(String var1) throws RemoteException;


    IBinder checkService(String var1) throws RemoteException;

    void addService(String var1, IBinder var2, boolean var3, int var4) throws RemoteException;

    String[] listServices(int var1) throws RemoteException;


}
