package kr.purplebeen.big_future

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.friendsitem.view.*
import kr.purplebeen.big_future.utills.loadUrl

/**
 * Created by pc on 2018-02-11.
 */
class ListAdapter(context: Context, data_items: ArrayList<GeneralUser>, data_layout: Int) : BaseAdapter() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private var items = ArrayList<GeneralUser>()
    private var layout : Int = 0

    init {
        items = data_items
        layout = data_layout
    }

    override fun getView(i: Int, convertview: View?, parent: ViewGroup): View {
        var listView:View?=null

        listView =  inflater.inflate(R.layout.friendsitem, parent!!, false)

        var user : GeneralUser = items[i]

        listView.friendsname.text = user.name
        Log.d("userTest", user.name)
        listView.circle_image_view.loadUrl(user.picture)


        return listView!!
    }

    override fun getCount(): Int {
        return items!!.size
    }

    override fun getItem(i: Int): GeneralUser? {
        return items?.get(i)
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }


}