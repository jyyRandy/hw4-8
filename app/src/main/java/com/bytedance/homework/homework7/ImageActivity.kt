package com.bytedance.homework.homework7

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.bytedance.homework.R

class ImageActivity : AppCompatActivity() {

    private val imgViewPager2: ViewPager2 by lazy { findViewById(R.id.vp_img) }
    private val imgList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        initImgList()
        imgViewPager2.adapter = ImageAdapter(this, imgList)
        imgViewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            private var isScrolling = false
            private var currentPosition: Int = 0

            override fun onPageSelected(position: Int) {
                currentPosition = position

            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                if (state == 1) {
                    isScrolling = false
                } else if (state == 2) {
                    isScrolling = true
                } else {
                    if (!isScrolling) {
                        if (currentPosition == 0) {
                            imgViewPager2.currentItem = 3
                        } else {
                            imgViewPager2.currentItem = 0
                        }
                    }
                }
            }
        })
    }

    private fun initImgList() : MutableList<String> {
        imgList.add("https://img0.baidu.com/it/u=3541579432,993171261&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=561")
        imgList.add("https://img1.baidu.com/it/u=1906853179,2481380790&fm=253&fmt=auto&app=138&f=JPEG?w=200&h=200")
        imgList.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg3.doubanio.com%2Fview%2Fgroup_topic%2Fraw%2Fpublic%2Fp297361430.jpg&refer=http%3A%2F%2Fimg3.doubanio.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654498663&t=51e07ce748e7f1b4429ecb4e91a8bd07")
        return imgList
    }

}