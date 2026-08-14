package com.witbit.sherlock

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel

abstract class BaseVActivity<V : ViewDataBinding> : FragmentActivity() {
    protected lateinit var mBinding: V

    protected abstract fun getLayoutRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, getLayoutRes())
        mBinding.lifecycleOwner = this

    }


}