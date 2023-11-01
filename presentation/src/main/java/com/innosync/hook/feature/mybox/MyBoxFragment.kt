package com.innosync.hook.feature.mybox

import android.content.ContentValues.TAG
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.innosync.hook.R
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentMyBoxBinding
import com.innosync.hook.databinding.ItemMyboxBinding
import com.innosync.hook.util.ItemSpacingDecoration
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.internal.processedrootsentinel.codegen._com_innosync_hook_util_HookApplication


@AndroidEntryPoint
class MyBoxFragment : BaseFragment<FragmentMyBoxBinding, MyBoxViewModel>() {
    val MyBoxDataList = mutableListOf<MyBoxRvData>()
    private lateinit var myBoxRvAdapter: MyBoxRvAdapter
    private lateinit var sbinding: ItemMyboxBinding

    override val viewModel: MyBoxViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sbinding = ItemMyboxBinding.inflate(inflater, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    override fun observerViewModel() {



        initObserver()
        //livedata인 rvdata의 변경사항을 관찰.
        viewModel.addstatus()

        mBinding.myboxRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        mBinding.myboxRecyclerview.addItemDecoration(ItemSpacingDecoration(3))
        //text color 변경
        bindingViewEvent { event ->
            when (event) {
                MyBoxViewModel.ON_CLICK_HACKATHON -> {
                    setTextColor("대회")
                }

                MyBoxViewModel.ON_CLICK_EAT -> {
                    setTextColor("식사")
                }

                MyBoxViewModel.ON_CLICK_EXERCISE -> {
                    setTextColor("운동")
                }

                MyBoxViewModel.ON_CLICK_COLLECT_PEOPLE -> {
                    Log.d(TAG, "구직 누름")
                    setTextColor("구직")

                }
            }
        }


    }

    private fun initObserver() {
        viewModel.rvData.observe(this@MyBoxFragment) {
            val adaptor = MyBoxRvAdapter(
                datalist = it,
                context = requireContext(),
                action = { item, type ->
                // 매칭중 클릭
                    when (type) {
                        0 -> {
                            viewModel.complete(
                                item.id,
                                item.type
                            )
                        }
                        1 -> {
                            viewModel.match(
                                item.id,
                                item.type
                            )
                        }
                        // 레이아웃 클릭
                        else -> {
                            //네비게이션 연결하세요
                            findNavController().navigate(
                                R.id.action_nav_item_myBox_to_myBoxDetailFragment
                            )
                        }
                    }
                }
            )

            mBinding.myboxRecyclerview.adapter = adaptor
        }
        viewModel.loadEat()

        mBinding.tvMeal.setOnClickListener{
            viewModel.loadEat()
        }
        mBinding.tvContest.setOnClickListener{
            mBinding.tvMeal.setTextColor(Color.BLACK)
            viewModel.loadHackathon()
        }
        mBinding.tvExercise.setOnClickListener{
            mBinding.tvExercise?.isSelected = mBinding.tvExercise?.isSelected != true
            viewModel.loadExercise()
        }
        mBinding.tvCollectPeople.setOnClickListener{
            mBinding.tvCollectPeople?.isSelected = mBinding.tvCollectPeople?.isSelected != true
            viewModel.loadJobSearch()
        }
        mBinding.icSetting.setOnClickListener {
            //설정으로
        }

    }

    private fun setTextColor(text: String) {
        val grayText = context?.getColor(R.color.darkGray)!!
        val darkText = context?.getColor(R.color.black)!!

        fun getTextColor(mText: String): Int =
            if (text == mText) darkText else grayText

        with(mBinding) {
            tvExercise.setTextColor(getTextColor("운동"))
            tvContest.setTextColor(getTextColor("대회"))
            tvMeal.setTextColor(getTextColor("식사"))
            tvCollectPeople.setTextColor(getTextColor("구직"))
        }
    }

    private fun observeData() {

    }
}


