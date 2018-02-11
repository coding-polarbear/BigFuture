package kr.purplebeen.big_future

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by pc on 2018-02-11.
 */
class PagerAdapter(fm: FragmentManager?, tabCount : Int) : FragmentStatePagerAdapter(fm) {

    private var tabCount : Int = 0

    init {
        this.tabCount = tabCount
    }

    override fun getItem(position: Int): Fragment? {
        return when(position){
            0 -> PagerFragment("행복")
            1 -> PagerFragment("화남")
            2 -> PagerFragment("슬픔")
            3 -> PagerFragment("멋짐")
            4 -> PagerFragment("웃김")
            5 -> PagerFragment("기념일")
            else -> null
        }
    }

    override fun getCount(): Int {
        return tabCount
    }

}