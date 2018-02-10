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


class MainActivity : AppCompatActivity() {
    var tag : String = ""
    var friend : User? = null
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
    }

    fun setORM() {
        val appPath : String = applicationContext.filesDir.absolutePath
        pultusORM = PultusORM("bigfuture.db", appPath)
    }

    fun setListners() {
        fab.setOnClickListener{
            var userList : List<Any> = pultusORM.find(User())
            var me : User = userList[userList.size - 1] as User
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
