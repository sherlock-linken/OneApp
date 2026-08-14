package com.witbit.sherlock.single_picker

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.witbit.sherlock.BaseVActivity
import com.witbit.sherlock.oneapp.R
import com.witbit.sherlock.oneapp.databinding.ActivitySinglePickerBinding

class SinglePickerActivity : BaseVActivity<ActivitySinglePickerBinding>() {

    override fun getLayoutRes() = R.layout.activity_single_picker


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.btnShowDialog.setOnClickListener{

        }

    }
}