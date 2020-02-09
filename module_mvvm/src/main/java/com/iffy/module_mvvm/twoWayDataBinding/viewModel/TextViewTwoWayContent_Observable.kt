package com.iffy.module_mvvm.twoWayDataBinding.viewModel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData

/**
 * Created on 2/2/2020.
 * @author Iffy Zhang
 */
//kotlin中DataBinding注解不能用
//java代码中的set和get方法，在kotlin中以不同的方式出现，导致@Bindable和notifyPropertyChanged(BR.xxl)不能直接用于get和set中

//解决方法
//用ObservableField

class TextViewTwoWayContent_Observable(var text: ObservableField<String>) {

}

