// MessageReceiver.aidl
// Declare any non-default types here with import statements
//接下来我们还需要将服务端接收到的远程服务器消息，传递到客户端。有同学估计会说：“这不就是一个回调接口的事情嘛”，
//设置回调接口思路是对的，但是在这里使用的回调接口有点不一样，在AIDL中传递的接口，不能是普通的接口，只能是AIDL接口，
//所以我们需要新建一个AIDL接口传到服务端，作为回调接口。

package com.iffy.mianshi.multiProcess;
import com.iffy.mianshi.multiProcess.data.MessageModel;

interface MessageReceiver {
    void onMessageReceived(in MessageModel receivedMessage);
}
