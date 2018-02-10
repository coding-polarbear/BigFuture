package kr.purplebeen.big_future

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import kotlinx.android.synthetic.main.content_main.*
import android.widget.ArrayAdapter





class MainActivity : AppCompatActivity() {

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

                when (select) {
                    "행복" ->  Log.e("1","1")

                    "화남" -> Log.e("2","2")

                    "슬픔" -> Log.e("3","3")

                    "멋짐" -> Log.e("4","4")

                    "웃김" -> Log.e("5","5")

                    "기념일" -> Log.e("6","6")


                }
            }

        }


    }
}
