package kr.purplebeen.big_future

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import kotlinx.android.synthetic.main.content_main.*
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kr.purplebeen.big_future.utills.loadUrl
import ninja.sakib.pultusorm.core.PultusORM
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    var tag : String = ""
    var friend : User? = null
    lateinit var me : User
    lateinit var pultusORM : PultusORM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputLayout.isCounterEnabled = true
        inputLayout.counterMaxLength = 50

        val adapter = ArrayAdapter.createFromResource(this, R.array.select, android.R.layout.simple_spinner_item)
        mainSpinner.adapter = adapter
        mainSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var select = mainSpinner.adapter.getItem(mainSpinner.selectedItemPosition) as String
                tag = select
            }
        }

        setORM()
        getDDay()
        setListners()
    }

    fun getDifferenceDays(d1: Date, d2: Date): Long {
        val diff = d2.time - d1.time
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)
    }

    fun setORM() {
        val appPath : String = applicationContext.filesDir.absolutePath
        pultusORM = PultusORM("bigfuture.db", appPath)
        var userList : List<Any> = pultusORM.find(User())
        me = userList[userList.size - 1] as User
    }

    fun getDDay() {
        var simpleDataFormat : SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        var date1 : Date = simpleDataFormat.parse(me.graduationDate)
        var date2 : Date = Date(System.currentTimeMillis())
        var dDay : Long = getDifferenceDays(date1, date2)
        toolbar_layout.title = "D " + dDay.toString() + "日"
    }

    fun setListners() {
        fab.setOnClickListener{
            if (friend != null && tag != null) {
                var capsule: Capsule = Capsule(me.userID, me.userName, friend!!.userID, friend!!.userID, editContent.text.toString(), tag)
            } else {
                Toast.makeText(applicationContext, "받을 대상과 태그를 지정해주세요", Toast.LENGTH_LONG).show()
            }
        }

        circle_image_view.setOnClickListener {

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 3000) {
            friend = data!!.extras.get("friend") as User
            circle_image_view.loadUrl(friend!!.picture)
        }
    }
}
