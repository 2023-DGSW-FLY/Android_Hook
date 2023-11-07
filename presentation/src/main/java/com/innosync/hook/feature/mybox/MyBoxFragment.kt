package com.innosync.hook.feature.mybox

import android.content.ContentValues.TAG
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

    override fun onResume() {
        super.onResume()
        viewModel.loadEat()
        mBinding.myboxRecyclerview.layoutManager = LinearLayoutManager(requireContext())
//        mBinding.myboxRecyclerview.removed(ItemSpacingDecoration(3))
        mBinding.myboxRecyclerview.addItemDecoration(ItemSpacingDecoration(3))

        mBinding.tvMeal.setOnClickListener {
            mBinding.tvMeal.isSelected = mBinding.tvMeal?.isSelected != true
            viewModel.loadEat()
        }
        mBinding.tvExercise.setOnClickListener {
            mBinding.tvExercise?.isSelected = mBinding.tvExercise?.isSelected != true
            viewModel.loadExercise()
        }
        mBinding.tvCollectPeople.setOnClickListener {
            mBinding.tvCollectPeople?.isSelected = mBinding.tvCollectPeople?.isSelected != true
            viewModel.loadJobSearch()
        }
        mBinding.tvContest.setOnClickListener {
            mBinding.tvContest?.isSelected = mBinding.tvContest?.isSelected != true
            viewModel.loadHackathon()
        }
        mBinding.icSetting.setOnClickListener {
            //설정으로
        }
    }

    override fun observerViewModel() {
        initObserver()
        //livedata인 rvdata의 변경사항을 관찰.
        viewModel.addstatus()

        //text color 변경
        bindingViewEvent { event ->
            when (event) {
                MyBoxViewModel.ON_CLICK_HACKATHON -> {
                    setTextColor("대회")
                    Log.d(TAG, "MyBoxFragment - 대회누름")
                }

                MyBoxViewModel.ON_CLICK_EAT -> {
                    setTextColor("식사")
                    Log.d(TAG, "MyBoxFragment - 식사누름")
                }

                MyBoxViewModel.ON_CLICK_EXERCISE -> {
                    setTextColor("운동")
                    Log.d(TAG, "MyBoxFragment - 운동누름")
                }

                MyBoxViewModel.ON_CLICK_COLLECT_PEOPLE -> {
                    Log.d(TAG, "구직 누름")
                    setTextColor("구직")
                    Log.d(TAG, "MyBoxFragment - 구직누름")
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
                            val navigate =  MyBoxFragmentDirections.actionNavItemMyBoxToMyBoxDetailFragment(item.id)
                            findNavController().navigate(navigate)
                        }
                    }
                }
            )
            mBinding.myboxRecyclerview.adapter = adaptor
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
}


