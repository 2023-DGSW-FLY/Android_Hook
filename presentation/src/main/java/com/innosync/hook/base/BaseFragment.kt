package com.innosync.hook.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.innosync.hook.base.BaseViewModel
import androidx.databinding.library.baseAdapters.BR
import com.innosync.hook.R
import java.lang.reflect.ParameterizedType
import java.util.Locale
import java.util.Objects

abstract class BaseFragment<VB: ViewDataBinding, VM: BaseViewModel> : Fragment() {
    protected lateinit var mBinding: VB
    protected lateinit var mViewModel: VM

    protected abstract val viewModel: VM

    private var isLoad = false
    private var isLoad2 = false

    protected abstract fun observerViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(
            inflater,
            layoutRes(),
            container, false
        )
        performDataBinding()
        if (!isLoad2) {
            isLoad2 = true
            observerViewModel()
        }
        return mBinding.root
    }

    private fun performDataBinding() {
        mViewModel = if (::mViewModel.isInitialized) mViewModel else viewModel
        mBinding.setVariable(BR.vm, mViewModel)
        mBinding.lifecycleOwner = this
    }

    protected fun bindingViewEvent(action: (event: Any) -> Unit) {
        if (!isLoad) {
            isLoad = true
            viewModel.viewEvent.observe(this) { event ->
                action.invoke(event)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::mBinding.isInitialized) mBinding.unbind()
    }
    @LayoutRes
    private fun layoutRes(): Int {
        val split = (
                (Objects.requireNonNull(javaClass.genericSuperclass) as ParameterizedType)
                    .actualTypeArguments[0] as Class<*>
                )
            .simpleName.replace("Binding$".toRegex(), "")
            .split("(?<=.)(?=\\p{Upper})".toRegex())
            .dropLastWhile { it.isEmpty() }.toTypedArray()

        val name = StringBuilder()

        for (i in split.indices) {
            name.append(split[i].lowercase(Locale.ROOT))
            if (i != split.size - 1)
                name.append("_")
        }

        try {
            return R.layout::class.java.getField(name.toString()).getInt(R.layout::class.java)
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        }

        return 0
    }
}