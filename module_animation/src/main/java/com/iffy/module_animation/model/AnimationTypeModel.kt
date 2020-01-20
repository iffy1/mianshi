package com.iffy.module_animation.model


//把数据模块(Model )与 视图(View)控件 进行绑定
//用来显示content内容 和 每个Button的内容
class AnimationTypeModel(
    var content: String,
    var objectAnimator: String,
    var valueAnimator: String,
    var interpolator: String,
    var viewAnimation: String,
    var sceneAnimation: String
)