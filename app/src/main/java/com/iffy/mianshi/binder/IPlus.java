package com.iffy.mianshi.binder;

import android.os.IInterface;

// 步骤2：创建 IInterface 接口类 的匿名类
// 创建前，需要预先定义 继承了IInterface 接口的接口 -->分析3
public interface IPlus extends IInterface {
    // 继承自IInterface接口->>分析4
    // 定义需要实现的接口方法，即Client进程需要调用的方法
    int add(int a, int b);
}
