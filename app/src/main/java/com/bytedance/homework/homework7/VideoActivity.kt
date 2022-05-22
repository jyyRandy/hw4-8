package com.bytedance.homework.homework7

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import com.bytedance.homework.R


class VideoActivity : AppCompatActivity() {

    private val videoView: VideoView by lazy { findViewById(R.id.videoView) }
    private val layPlayPause: FrameLayout by lazy { findViewById(R.id.lay_play_pause) }
    private val imgPlay: ImageView by lazy { findViewById(R.id.img_play) }
    private val imgPause: ImageView by lazy { findViewById(R.id.img_pause) }
    private val currentTime: TextView by lazy { findViewById(R.id.tv_current_time) }
    private val seekBar: SeekBar by lazy { findViewById(R.id.seekBar) }
    private val totalTime: TextView by lazy { findViewById(R.id.tv_total_time) }
    private val rePlay: LinearLayout by lazy { findViewById(R.id.lay_replay) }
    private var seekBarIsTracking = false
    private var handler: Handler = Handler(Looper.getMainLooper())
    private var runnable: Runnable = Runnable {
        run {
            if (videoView.isPlaying) {
                currentTime.text = getTimeString(videoView.currentPosition / 1000)
                seekBar.progress = (videoView.currentPosition * 100.0 / videoView.duration).toInt()
            }
            handler.postDelayed(runnable, 300)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        videoView.setVideoPath(getVideoPath(R.raw.operation))
        videoView.keepScreenOn = true
        videoView.setOnInfoListener { mp, what, extra -> true }
        videoView.setOnErrorListener { p0, p1, p2 -> true }
        videoView.setOnPreparedListener {
            videoView.seekTo(1)
            totalTime.text = getTimeString(videoView.duration / 1000)
            handler.post(runnable)
        }
        videoView.setOnCompletionListener {
            rePlay.visibility = View.VISIBLE
            window.decorView.systemUiVisibility = View.VISIBLE
            pauseAnimator()
        }

        rePlay.setOnClickListener {
            playAnimator()
            videoView.start()
            rePlay.visibility = View.INVISIBLE
        }

        layPlayPause.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.pause()
                pauseAnimator()
            } else {
                videoView.start()
                rePlay.visibility = View.INVISIBLE
                playAnimator()
            }
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (seekBarIsTracking) {
                    videoView.seekTo((videoView.duration * progress * 1.0 / 100 ).toInt())
                }
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
                seekBarIsTracking = true
                pauseAnimator()
                rePlay.visibility = View.INVISIBLE
                videoView.pause()
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {
                seekBarIsTracking = false
                playAnimator()
                videoView.start()
            }
        })

    }

    private fun getVideoPath(resId: Int): String {
        return "android.resource://" + this.packageName + "/" + resId
    }

    @SuppressLint("SetTextI18n")
    private fun getTimeString(totalSec: Int) : String {
        val s = totalSec % 60
        val sec = if (s < 10) "0$s" else "$s"
        val m = (totalSec % 3600 - s) / 60
        val min = if (m < 10) "0$m" else "$m"
        val h = totalSec / 3600
        val hr = if (h < 10)  "0$h" else "$h"
        return if (h == 0) "$min:$sec" else "$hr:$min:$sec"
    }


    private fun pauseAnimator() {
        val animator1 = ObjectAnimator.ofFloat(imgPlay, "rotation", -90f, 0f)
        val animator2 = ObjectAnimator.ofFloat(imgPlay, "alpha", 0f, 1f)
        val animator3 = ObjectAnimator.ofFloat(imgPause, "rotation", 0f, 90f)
        val animator4 = ObjectAnimator.ofFloat(imgPause, "alpha", 1f, 0f)
        val animSet = AnimatorSet()
        animSet.duration = 300
        animSet.play(animator1).with(animator2).with(animator3).with(animator4)
        animSet.start()
    }

    private fun playAnimator() {
        val animator1 = ObjectAnimator.ofFloat(imgPlay, "rotation", 0f, -90f)
        val animator2 = ObjectAnimator.ofFloat(imgPlay, "alpha", 1f, 0f)
        val animator3 = ObjectAnimator.ofFloat(imgPause, "rotation", 90f, 0f)
        val animator4 = ObjectAnimator.ofFloat(imgPause, "alpha", 0f, 1f)
        val animSet = AnimatorSet()
        animSet.duration = 300
        animSet.play(animator1).with(animator2).with(animator3).with(animator4)
        animSet.start()
    }


    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}