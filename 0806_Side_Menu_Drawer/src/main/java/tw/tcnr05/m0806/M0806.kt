package tw.tcnr05.m0806

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.navdrawer.SimpleSideDrawer

class M0806 : AppCompatActivity() {
    private lateinit var mNav: SimpleSideDrawer

    //    private var mNav: SimpleSideDrawer? = null
    private var lmb001: Button? = null
    private var rmb001: Button? = null
    private val t001: TextView? = null
    private var startTime: Long? = null
    private var spenttime: Long? = null
    private var lt001: TextView? = null
    private var lt002: TextView? = null
    private var lt003: TextView? = null
    private var rt001: TextView? = null
    private var rt002: TextView? = null
    private var rt003: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.m0806)
        // 取得目前時間
        startTime = System.currentTimeMillis()
        // -------------------------------------------
        mNav = SimpleSideDrawer(this)
        // 設定開啟左側選單
        findViewById<View>(R.id.m0806_b001).setOnClickListener { mNav.toggleLeftDrawer() }
        // 設定開啟右側選單
        findViewById<View>(R.id.m0806_b002).setOnClickListener { mNav.toggleRightDrawer() }
        mNav.setLeftBehindContentView(R.layout.leftmenu)

        // 設定開啟左側選單 點兩下
        findViewById<View>(R.id.rl01).setOnClickListener {
            spenttime = System.currentTimeMillis() - startTime!!
            if (spenttime!! < 1000) {
                mNav.toggleLeftDrawer()
            } else {
                startTime = System.currentTimeMillis()
            }
        }


// 設定開啟右邊欄 長按
        mNav.setRightBehindContentView(R.layout.rightmenu)
        findViewById<View>(R.id.rl01).setOnLongClickListener {
            mNav.toggleRightDrawer()
            false
        }


        // -------------------------------------------
        setupViewComponent()
    }

    private fun setupViewComponent() {
        lt001 = findViewById<View>(R.id.lt001) as TextView
        lt002 = findViewById<View>(R.id.lt002) as TextView
        lt003 = findViewById<View>(R.id.lt003) as TextView
        lmb001 = findViewById<View>(R.id.lmb001) as Button
        rt001 = findViewById<View>(R.id.rt001) as TextView
        rt002 = findViewById<View>(R.id.rt002) as TextView
        rt003 = findViewById<View>(R.id.rt003) as TextView
        rmb001 = findViewById<View>(R.id.rmb001) as Button
        // 設定 button 按鍵的事件
        lt001!!.setOnClickListener(OnClick)
        lt002!!.setOnClickListener(OnClick)
        lt003!!.setOnClickListener(OnClick)
        lmb001!!.setOnClickListener(OnClick) // 左側邊欄按鈕監聽
        rt001!!.setOnClickListener(OnClick)
        rt002!!.setOnClickListener(OnClick)
        rt003!!.setOnClickListener(OnClick)
        rmb001!!.setOnClickListener(OnClick) // 右側邊欄按鈕監聽
    }

    private val OnClick = View.OnClickListener { v ->
        when (v.id) {
            R.id.lt001 -> {
                // 左側textview1 按下 執行工作
                mNav.closeLeftSide()
                Toast.makeText(applicationContext, getString(R.string.lt001), Toast.LENGTH_LONG)
                    .show()
            }
            R.id.lt002 -> {
                // 左側textview2 按下 執行工作
                mNav.closeLeftSide()
                Toast.makeText(applicationContext, getString(R.string.lt002), Toast.LENGTH_LONG)
                    .show()
            }
            R.id.lt003 -> {
                // 左側textview3 按下 執行工作
                mNav.closeLeftSide()
                Toast.makeText(applicationContext, getString(R.string.lt003), Toast.LENGTH_LONG)
                    .show()
            }
            R.id.lmb001 -> {
                // 左側button 按下 執行工作
                val uri = Uri.parse("http://developer.android.com/")
                val it = Intent(Intent.ACTION_VIEW, uri)
                startActivity(it)
                mNav.closeLeftSide()
                Toast.makeText(applicationContext, getString(R.string.lb001), Toast.LENGTH_LONG)
                    .show()
            }
            R.id.rt001 -> {
                // 右側textview1 按下 執行工作
                mNav.closeRightSide()
                Toast.makeText(applicationContext, getString(R.string.rt001), Toast.LENGTH_LONG)
                    .show()
            }
            R.id.rt002 -> {
                // 右側textview2 按下 執行工作
                mNav.closeRightSide()
                Toast.makeText(applicationContext, getString(R.string.rt002), Toast.LENGTH_LONG)
                    .show()
            }
            R.id.rt003 -> {
                // 右側textview3 按下 執行工作
                mNav.closeRightSide()
                Toast.makeText(applicationContext, getString(R.string.rt003), Toast.LENGTH_LONG)
                    .show()
            }
            R.id.rmb001 -> {
                // 右側button 按下 執行工作
                mNav.closeLeftSide()
                Toast.makeText(applicationContext, getString(R.string.rb001), Toast.LENGTH_LONG)
                    .show()
            }
            else -> {
            }
        }
    }
}