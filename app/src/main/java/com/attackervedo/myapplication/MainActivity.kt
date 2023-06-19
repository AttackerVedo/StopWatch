package com.attackervedo.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.Timer
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var startBtn : Button
    private lateinit var resetBtn : Button
    private lateinit var tvF : TextView
    private lateinit var tvS : TextView
    private lateinit var tvT : TextView

    private var isRunning = false
    private var timer : Timer? = null
    private var time = 0
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startBtn = findViewById(R.id.startBtn)
        resetBtn = findViewById(R.id.resetBtn)
        tvF = findViewById(R.id.tvF)
        tvS = findViewById(R.id.tvS)
        tvT = findViewById(R.id.textView3)

        startBtn.setOnClickListener(this)
        resetBtn.setOnClickListener(this)

    }

    private fun start(){
        startBtn.text = getString(R.string.strPause)
        startBtn.setBackgroundResource(R.drawable.round_button_red)
        isRunning = true

        timer = timer(period = 10){
            //1000ms = 1s
            // 0.01 time 1+
            time++

            var tvTValue = time%100 // 초이하 부분
            var tvSValue = (time%6000) / 100 // 초부분
            var minuteValue = time / 6000 // 분부분

            runOnUiThread {
                if(isRunning){

                    tvF.text = "${minuteValue}"
                    tvS.text = if(tvSValue <10) "0${tvSValue}" else "${tvSValue}"
                    tvT.text = if(tvTValue<10) "0${tvTValue}" else "${tvTValue}"
                }
            }
        }
    }
    @SuppressLint("ResourceAsColor")
    private fun pause(){
        startBtn.text = getString(R.string.strStart)
        startBtn.setBackgroundResource(R.drawable.round_button_blue)
        timer?.cancel()
        isRunning = false
    }

    @SuppressLint("ResourceAsColor")
    private fun reset(){
        timer?.cancel()

        startBtn.text = getString(R.string.strStart)
        startBtn.setBackgroundResource(R.drawable.round_button_blue)
        isRunning =false

        time = 0
        tvF.text = "00"
        tvS.text = "00"
        tvT.text = "00"

    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.startBtn -> {
                if(isRunning)
                    pause()
                else
                    start()
            }
            R.id.resetBtn -> {
                reset()
            }
        }

    }
}