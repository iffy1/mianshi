// MessageModel.aidl
package com.iffy.mianshi.multiProcess.data;


//在AIDL中传递的对象，必须实现Parcelable序列化接口；
//在AIDL中传递的对象，需要在类文件相同路径下，创建同名、但是后缀为.aidl的文件，并在文件中使用parcelable关键字声明这个类；
//跟普通接口的区别：只能声明方法，不能声明变量；
//所有[非]基础数据类型参数都需要标出数据走向的方向标记。可以是 in、out 或 inout，基础数据类型默认只能是 in，不能是其他方向。

//MessageModel.aidl -> 声明了MessageModel可在AIDL中传递，放在跟MessageModel.kt相同的包路径下
parcelable MessageModel;