package tw.tcnr05.m0802

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class M0802 : AppCompatActivity(), View.OnClickListener {
    private lateinit var cg: Calendar
    private var ans01: TextView? = null
    private var date01: DatePicker? = null
    private var time01: TimePicker? = null
    private var btn: Button? = null
//    private var cg Calendar? = null
    private var years01 = 0
    private var months01 = 0
    private var dates01 = 0
    private var hours01 = 0
    private var minius01 = 0
    private val handler = Handler()
    private var endTime: Long = 0
    private var spentTime: Long = 0
    private var hours: Long = 0
    private var minius: Long = 0
    private var seconds: Long = 0
    private var time: TextView? = null
    private var text01: TextView? = null
    private var startmusic: MediaPlayer? = null
    private var btn02: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.m0802)
        setupCompoent()
    }

    private fun setupCompoent() {
        ans01 = findViewById<View>(R.id.m0802_ans01) as TextView
        text01 = findViewById<View>(R.id.m0802_t001) as TextView
        date01 = findViewById<View>(R.id.m0802_date01) as DatePicker
        time01 = findViewById<View>(R.id.m0802_time01) as TimePicker
        time = findViewById<View>(R.id.m0802_timer) as TextView
        btn = findViewById<View>(R.id.m0802_b001) as Button
        btn02 = findViewById<View>(R.id.m0802_b002) as Button
        btn!!.setOnClickListener(this)
        btn02!!.setOnClickListener(this)
        startmusic = MediaPlayer.create(this, R.raw.s01)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.m0802_b001 -> {
                btn!!.visibility = View.INVISIBLE
                btn02!!.visibility = View.VISIBLE
                val s = getString(R.string.m0802_ans01)
                years01 = date01!!.year //取得畫面的"年"
                months01 = date01!!.month //取得畫面的"月"
                dates01 = date01!!.dayOfMonth //取得畫面的"日"
                hours01 = time01!!.hour // 取得畫面的"小時"
                minius01 = time01!!.minute // 取得畫面的"分鐘"
                ans01!!.text =
                    (s + years01 + getString(R.string.n_yy) + (months01 + 1) + getString(R.string.n_mm)
                            + dates01 + getString(R.string.m_dd) + hours01 + getString(R.string.d_hh) + minius01
                            + getString(R.string.d_mm))
                cg = Calendar.getInstance()
                cg.set(years01, months01, dates01, hours01, minius01)
                endTime = cg.getTimeInMillis() //取得時間毫秒資料
                handler.postDelayed(updateTimes, 1000)
            }
            R.id.m0802_b002 -> {
                btn02!!.visibility = View.INVISIBLE
                btn!!.visibility = View.VISIBLE
                Toast.makeText(applicationContext, getString(R.string.btnNG), Toast.LENGTH_LONG)
                    .show()
                music_set()
                time!!.text = getString(R.string.timer)
                text01!!.text = getString(R.string.m0802_play)
                setnowtime()
                handler.removeCallbacks(updateTimes)
            }
        }
    }

    private fun setnowtime() {
        val c = Calendar.getInstance()
        val years02 = c[Calendar.YEAR]
        val months02 = c[Calendar.MONTH]
        val dates02 = c[Calendar.DAY_OF_MONTH]
        val hours02 = c[Calendar.HOUR_OF_DAY]
        val minius02 = c[Calendar.MINUTE]
        date01!!.updateDate(years02, months02, dates02)
        time01!!.hour = hours02
        time01!!.minute = minius02
        ans01!!.setText(R.string.m0802_ans01)
    }

    private val updateTimes: Runnable = object : Runnable {
        override fun run() {
            spentTime = endTime - System.currentTimeMillis() - 55000 //-秒
            hours = spentTime / 1000 / 60 / 60
            minius = spentTime / 1000 / 60 % 60
            seconds = spentTime / 1000 % 60
            if (spentTime < 0 || hours > 999) {
                Toast.makeText(this@M0802, R.string.msg, Toast.LENGTH_SHORT).show()
                time!!.text = getString(R.string.timer)
                ans01!!.text = getString(R.string.txtResult)
                handler.removeCallbacks(this)
            } else {
                text01!!.text = getString(R.string.m0802_alerm)
                music_set()
                time!!.text =
                    String.format("%02d", hours) + ":" + String.format(
                        "%02d",
                        minius
                    ) + ":" + String.format("%02d", seconds)
                handler.postDelayed(this, 1000)
                if (hours == 0L && minius == 0L && seconds == 0L) {
                    startmusic!!.start()
                    text01!!.text = getString(R.string.m0802_play)
                    handler.removeCallbacks(this)
                }
            }
        }
    }

    private fun music_set() {
        if (startmusic != null && startmusic!!.isPlaying) startmusic!!.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(updateTimes)
        finish()
    }

    override fun onBackPressed() {
//        super.onBackPressed();
    }
}