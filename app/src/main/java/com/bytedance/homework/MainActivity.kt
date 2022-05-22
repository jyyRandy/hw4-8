package com.bytedance.homework

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bytedance.homework.homework4.ClockActivity
import com.bytedance.homework.homework5.TranslateActivity
import com.bytedance.homework.homework6.TodoListActivity
import com.bytedance.homework.homework7.ImgVidActivity
import com.bytedance.homework.homework8.CameraActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        skipActivity(R.id.btn_4, ClockActivity::class.java)
        skipActivity(R.id.btn_5, TranslateActivity::class.java)
        skipActivity(R.id.btn_6, TodoListActivity::class.java)
        skipActivity(R.id.btn_7, ImgVidActivity::class.java)
        skipActivity(R.id.btn_8, CameraActivity::class.java)
    }

    private fun skipActivity(btnId: Int, activityClass: Class<*>) {
        findViewById<View>(btnId).setOnClickListener {
            startActivity(Intent(this, activityClass))
        }
    }
}