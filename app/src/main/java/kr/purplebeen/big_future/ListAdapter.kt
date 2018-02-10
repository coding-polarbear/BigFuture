package kr.purplebeen.big_future

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

/**
 * Created by pc on 2018-02-11.
 */
class ListAdapter(context: Context, data_items: ArrayList<User>, data_layout: Int) : BaseAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var items = ArrayList<User>()
    private var layout : Int = 0

    init {
        items = data_items
        layout = data_layout
    }

    override fun getView(i: Int, convertview: View?, viewGroup: ViewGroup): View {
        var view:View?=null

        view =  inflater?.inflate(R.layout.friendsitem,viewGroup,false)

        var name : TextView

        name = view?.findViewById<TextView>(R.id.friendsname) as TextView

        var user : User = getItem(i)!!

        name.text = user.userName

        return view!!
    }

    override fun getCount(): Int {
        return items!!.size
    }

    override fun getItem(i: Int): User? {
        return items?.get(i)
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }


}