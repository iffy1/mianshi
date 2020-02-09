package com.iffy.module_mvvm.oneWayDataBinding.viewModel

import androidx.lifecycle.ViewModel


/**
 * Created on 2/2/2020.
 * @author Iffy Zhang
 */

//text不是LiveData所以 activity中改变TextViewOneWayContentVM UI不会改变
class TextViewOneWayContentVM(var text: String):ViewModel() {

}