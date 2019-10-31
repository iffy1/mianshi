// MessageSender.aidl
//https://cjw-blog.net/2017/02/26/AIDL/
//创建一个AIDL接口，接口中提供发送消息的方法
//MessageSender.aidl -> 定义了发送消息的方法，会自动生成名为MessageSender.Stub的Binder类，在服务端实现(MessageIPCService)，
//返回给客户端(IPCActivity)调用

//一个比较尴尬的事情，看了很多文章，从来没有一篇能说清楚in、out、inout这三个参数方向的意义，
//后来在stackoverflow上找到比较能理解的答案（stackoverflow原文链接），我翻译一下大概意思：
//被“in”标记的参数，就是接收实际数据的参数，这个跟我们普通参数传递一样的含义。在AIDL中，“out” 指定了一个仅用于输出的参数，
//换而言之，这个参数不关心调用方传递了什么数据过来，但是这个参数的值可以在方法被调用后填充（无论调用方传递了什么值过来，
//在方法执行的时候，这个参数的初始值总是空的），这就是“out”的含义，仅用于输出。而“inout”显然就是“in”和“out”的合体了，
//输入和输出的参数。区分“in”、“out”有什么用？这是非常重要的，因为每个参数的内容必须编组（序列化，传输，接收和反序列化）。
//in/out标签允许Binder跳过编组步骤以获得更好的性能。

//注释不能写在 Import和interface之间 不然报错
package com.iffy.mianshi.multiProcess;
import com.iffy.mianshi.multiProcess.data.MessageModel;
import com.iffy.mianshi.multiProcess.messageReceiver;

interface MessageSender {
 void sendMessage(in MessageModel messageModel);
 void registerReceiveListener(MessageReceiver messageReceiver);
 void unregisterReceiveListener(MessageReceiver messageReceiver);
}
