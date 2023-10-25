package com.innosync.hook.feature.mybox

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.innosync.hook.databinding.ItemJobSearchBinding
import com.innosync.hook.databinding.ItemMyboxBinding
import com.innosync.hook.feature.home.HomeRvAdaptor

class MyBoxRvAdapter constructor(
    private val datalist: List<MyBoxRvData>,
    private val action: (MyBoxRvData) -> Unit
) : RecyclerView.Adapter<MyBoxRvAdapter.MyBoxViewHolder>() {

    //myboxitem을 바인딩
    inner class MyBoxViewHolder(private val binding: ItemMyboxBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(MyBoxData: MyBoxRvData) {
            binding.textJobStatus.text = MyBoxData.status
            binding.textJobContent.text = MyBoxData.content
            binding.textTime.text = MyBoxData.date.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBoxViewHolder =
        MyBoxViewHolder(ItemMyboxBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            itemView.setOnClickListener {
                action(datalist[adapterPosition])
            }
        }


    override fun getItemCount(): Int = datalist.size

    override fun onBindViewHolder(holder: MyBoxRvAdapter.MyBoxViewHolder, position: Int) {
        holder.bind(datalist[position])
    }
//    fun setData(newDataList: List<MyBoxRvData>) {
//        datalist.clear()
//        datalist.addAll(newDataList)
//        notifyDataSetChanged()
//        //새로운 데이터를 업데이트한 후에 리사이클러뷰에 변경된 내용을 반영.
//    }

}