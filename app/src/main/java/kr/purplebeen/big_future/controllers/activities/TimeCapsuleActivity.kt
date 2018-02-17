package kr.purplebeen.big_future.controllers.activities

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_graduated.*
import kotlinx.android.synthetic.main.content_graduated.*
import kr.purplebeen.big_future.controllers.adapters.PagerAdapter
import kr.purplebeen.big_future.R

class TimeCapsuleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graduated)

        GraduatedTab.addTab(GraduatedTab.newTab().setText("행복"))
        GraduatedTab.addTab(GraduatedTab.newTab().setText("화남"))
        GraduatedTab.addTab(GraduatedTab.newTab().setText("슬픔"))
        GraduatedTab.addTab(GraduatedTab.newTab().setText("멋짐"))
        GraduatedTab.addTab(GraduatedTab.newTab().setText("웃김"))
        GraduatedTab.addTab(GraduatedTab.newTab().setText("기념일"))
        GraduatedTab.tabGravity = TabLayout.GRAVITY_FILL

        val pageradapter = PagerAdapter(supportFragmentManager, GraduatedTab.tabCount)
        graduatedpager.adapter = pageradapter

        graduatedpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(GraduatedTab))

        GraduatedTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                graduatedpager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
}
