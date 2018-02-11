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

class GraduatedAdapter (context: Context, data_items: ArrayList<Capsule>, data_layout: Int) : BaseAdapter() {


    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var items = ArrayList<Capsule>()
    private var layout : Int = 0

    init {
        items = data_items
        layout = data_layout
    }

    override fun getView(i: Int, convertview: View?, viewGroup: ViewGroup): View {
        var view:View?=null

        view =  inflater?.inflate(R.layout.friendsitem,viewGroup,false)

        var sendname : TextView
        var receiverName : TextView
        var context : TextView
        var date : TextView

        date = view.findViewById<TextView>(R.id.graduatedData) as TextView
        receiverName = view?.findViewById<TextView>(R.id.graduatedContext) as TextView
        sendname = view?.findViewById<TextView>(R.id.senderName) as TextView
        context = view?.findViewById<TextView>(R.id.receiverName) as TextView



        var capsule : Capsule = getItem(i)!!

        sendname.text = capsule.senderName
        receiverName.text = capsule.receiverName
        context.text = capsule.content
        date.text = capsule.createdDate

        return view!!
    }

    override fun getCount(): Int {
        return items!!.size
    }

    override fun getItem(i: Int): Capsule? {
        return items?.get(i)

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}