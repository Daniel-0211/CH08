package tw.tcnr05.m0802

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class M0802 : AppCompatActivity() {
    private var t001: TextView? = null
    private var startTime: Long = 0
    private val handler = Handler()
    private var spentTime: Long = 0
    private var minius: Long = 0
    private var seconds: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.m0802)
        setupViewCompoent()
    }

    private fun setupViewCompoent() {
        t001 = findViewById<View>(R.id.m0802_t001) as TextView
        // 取得手機目前時間(毫秒)
        startTime = System.currentTimeMillis()
        //        int a = 0;
        handler.postDelayed(updateTimes, 1000)
    }

    //自己手動生方法(Runnable)
    private val updateTimes: Runnable = object : Runnable {
        override fun run() {
            spentTime = System.currentTimeMillis() - startTime
            //            int a = 0;
            // 計算目前已過分鐘數
            minius = spentTime / 1000 / 60
            // 計算目前已過秒數
            seconds = spentTime / 1000 % 60
            t001!!.text =
                String.format("%02d", minius) + ":" + String.format("%02d", seconds)
            //真正設定延遲時間,更新1000(毫秒)一次
            handler.postDelayed(this, 1000)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(updateTimes)
        finish()
        //        int a = 0;    //中斷點測試用
    }

    override fun onBackPressed() {
//        super.onBackPressed(); //註記後,禁用返回鍵
    }
}