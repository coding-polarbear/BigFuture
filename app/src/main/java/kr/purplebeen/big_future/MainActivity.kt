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
import kr.purplebeen.big_future.utills.RetrofitUtil
import kr.purplebeen.big_future.utills.loadUrl
import ninja.sakib.pultusorm.core.PultusORM
import ninja.sakib.pultusorm.core.isAndroidPlatform
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    var tag : String = ""
    var friendName : String? = null
    var friendID : String? = null
    var friendPicture : String? = null
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
            if (friendName != null && tag != null) {
                var capsule: Capsule = Capsule(me.userID, me.userName, friendID!!,  friendName!!, editContent.text.toString(), "#" + tag)
                var capsuleService : CapsuleService = RetrofitUtil.getLoginRetrofit(applicationContext).create(CapsuleService::class.java)
                var call : Call<Success> = capsuleService.sendCapsule(capsule)
                call.enqueue(object : Callback<Success> {
                    override fun onResponse(call: Call<Success>?, response: Response<Success>) {
                        if(response.body()!!.status.success) {
                            Toast.makeText(applicationContext, "성공적으로 타임캡슐이 저장되었습니다!", Toast.LENGTH_LONG).show()
                            editContent.setText("")
                        } else {
                            Toast.makeText(applicationContext, response.body()!!.status.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                    override fun onFailure(call: Call<Success>?, t: Throwable?) {
                        Log.e("retrofit Error", t!!.message)
                    }



                })
            } else {
                Toast.makeText(applicationContext, "받을 대상과 태그를 지정해주세요", Toast.LENGTH_LONG).show()
            }
        }

        circle_image_view.setOnClickListener {
            startActivityForResult(Intent(this@MainActivity, activity_friendslist::class.java), 3000)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == 3000) {
            friendName = data!!.getStringExtra("friendName")
            friendID = data!!.getStringExtra("friendID")
            friendPicture = data!!.getStringExtra("friendPicture")
            receiverText.text =  friendName!! + "님에게"

            receiverName.text = friendName!!
            senderName.text = me.userName

            circle_image_view.loadUrl(friendPicture!!)
        }
    }
}
