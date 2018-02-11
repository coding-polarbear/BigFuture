package kr.purplebeen.big_future

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

@SuppressLint("ValidFragment")
/**
 * Created by pc on 2018-02-11.
 */

class PagerFragment @SuppressLint("ValidFragment") constructor
(id : String) : Fragment(){
    var id = ""

    init {
        this.id = id
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = super.onCreateView(inflater, container, savedInstanceState)

        when(id){

        }


        return view
    }
}