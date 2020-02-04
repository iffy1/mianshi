package com.iffy.module_mvvm.oneWayDataBinding.viewModel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.iffy.module_mvvm.BR;

/**
 * Created on 2/2/2020.
 *
 * @author Iffy Zhang
 */
public class User extends BaseObservable {

    private String name;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.textview);
    }
}
