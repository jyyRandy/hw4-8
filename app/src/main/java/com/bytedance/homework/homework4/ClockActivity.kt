package com.bytedance.homework.homework4

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.bytedance.homework.R

class ClockActivity : AppCompatActivity(), View.OnClickListener {

    var handler: Handler = Handler(Looper.getMainLooper())
    var runnable: Runnable = Runnable {
        run {
            findViewById<ClockView>(R.id.clock).setAngle()
            handler.postDelayed(runnable, 1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clock)
        findViewById<Button>(R.id.clock_button).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        handler.post(runnable)
    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        return super.dispatchKeyEvent(event)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }

}