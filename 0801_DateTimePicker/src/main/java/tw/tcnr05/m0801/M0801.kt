package tw.tcnr05.m0801

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity

class M0801 : AppCompatActivity(), View.OnClickListener {
    private var ans: TextView? = null
    private var date: DatePicker? = null
    private var time: TimePicker? = null
    private var btn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.m0801)
        setupViewCompoent()
    }

    private fun setupViewCompoent() {
        ans = findViewById<View>(R.id.m0801_t001) as TextView
        date = findViewById<View>(R.id.m0801_date01) as DatePicker
        time = findViewById<View>(R.id.m0801_time01) as TimePicker
        btn = findViewById<View>(R.id.m0801_b001) as Button
        btn!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val s = getString(R.string.m0801_t001)
        ans!!.text = s + date!!.year + getString(R.string.yy) +
                (date!!.month + 1) + getString(R.string.mm) +
                date!!.dayOfMonth + getString(R.string.dd) + time!!.hour + getString(R.string.hh) +
                time!!.minute + getString(R.string.ss)
    }
}