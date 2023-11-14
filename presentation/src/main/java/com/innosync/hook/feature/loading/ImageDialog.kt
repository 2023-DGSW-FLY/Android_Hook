package com.innosync.hook.feature.loading

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.bumptech.glide.Glide
import com.innosync.hook.R
import com.innosync.hook.databinding.DialogImageBinding
import com.innosync.hook.feature.jopoffer.model.JobOfferModel

class ImageDialog(
    private val context: Context,
    private val image: String
) : Dialog(context){

    private lateinit var binding: DialogImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Glide.with(context)
            .load(image)
            .placeholder(R.drawable.ic_android_off)
            .into(binding.imageCongress)
        // 배경 투명하게 바꿔줌

        binding.layoutImage.setOnClickListener {
            this.dismiss()
        }
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    }
}