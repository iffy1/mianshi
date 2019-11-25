package com.iffy.mianshi.pattern.facade

import com.iffy.mianshi.pattern.facade.system.Facade

// 优点
//
//    降低了客户类与子系统类的耦合度，实现了子系统与客户之间的松耦合关系
//
//        只是提供了一个访问子系统的统一入口，并不影响用户直接使用子系统类
//        减少了与子系统的关联对象，实现了子系统与客户之间
//        的松耦合关系，松耦合使得子系统的组件变化不会影响到它的客户。
//
//    外观模式对客户屏蔽了子系统组件，从而简化了接口，减少了客户处理的对象数目并使子系统的使用更加简单。
//
//        引入外观角色之后，用户只需要与外观角色交互；
//        用户与子系统之间的复杂逻辑关系由外观角色来实现
//


fun main() {
    val facade = Facade()
    println("回家了")
    facade.backHome()

    println("离家了")
    facade.leaveHome()
}