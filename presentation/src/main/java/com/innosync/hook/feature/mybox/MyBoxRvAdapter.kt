package com.innosync.hook.feature.mybox

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.innosync.hook.R
import com.innosync.hook.databinding.ItemMyboxBinding
import com.innosync.hook.feature.mybox.detail.ApplicantsRvModel
import javax.inject.Inject

class MyBoxRvAdapter @Inject constructor(
    private val datalist: List<MyBoxRvData>,
    private val action: (MyBoxRvData, Int) -> Unit,
    private val context: Context
) : RecyclerView.Adapter<MyBoxRvAdapter.MyBoxViewHolder>() {

    //myboxitem을 바인딩
    inner class MyBoxViewHolder(private val binding: ItemMyboxBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(myBoxData: MyBoxRvData, position: Int, context: Context) {
            binding.textJobStatus.text = myBoxData.statusName
            binding.textJobContent.text = myBoxData.content
            binding.textTime.text = myBoxData.date.toString()
            if (myBoxData.statusName == "매칭완료") {
                binding.layoutMybox.setBackgroundColor(if (position%2 == 0) Color.parseColor("#F8FBFF") else Color.parseColor("#FBFBFB"))
                binding.textJobContent.setTextColor(Color.parseColor("#D0D0D0"))
                binding.textJobStatus.background = context.getDrawable(R.drawable.current_status_round2)
                binding.textDone.text = "취소"
            } else {
                binding.layoutMybox.setBackgroundColor(if (position%2 == 0) Color.parseColor("#E9F2FF") else Color.parseColor("#F3F3F3"))
                binding.textDone.text = "완료"
            }
            binding.textDone.setOnClickListener {
                Log.d(TAG, myBoxData.id.toString())
                if ( myBoxData.status == "matching") {
                    action(myBoxData, 0)

                    //완료처리 호출
                } else  {
                    action(myBoxData, 1)
                }
            }

            binding.layoutMybox.setOnClickListener {
                if (myBoxData.type == "hackathon") { // 대회 카테고리인지 판별
                    action(myBoxData, 2)
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBoxViewHolder =
        MyBoxViewHolder(ItemMyboxBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun getItemCount(): Int = datalist.size

    override fun onBindViewHolder(holder: MyBoxRvAdapter.MyBoxViewHolder, position: Int) {
        holder.bind(datalist[position], position, context)
    }


}