package kr.purplebeen.big_future

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_pager.view.*
import kr.purplebeen.big_future.utills.RetrofitUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("ValidFragment")
/**
 * Created by pc on 2018-02-11.
 */

class PagerFragment @SuppressLint("ValidFragment") constructor (id : String) : Fragment(){
    var id = ""
    lateinit var capsuleList : ArrayList<Capsule>
    var fragmentView : View? = null
    init {
        Log.d("id", id)
        this.id = id
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = inflater.inflate(R.layout.fragment_pager, null)
        loadData()
        return fragmentView
    }

    fun loadData() {
        var capsuleService : CapsuleService = RetrofitUtil.getLoginRetrofit(activity!!).create(CapsuleService::class.java)
        var call : Call<CapsuleListGet> = capsuleService.getCapsuleList(id)
        call.enqueue(object : Callback<CapsuleListGet> {
            override fun onResponse(call: Call<CapsuleListGet>?, response: Response<CapsuleListGet>) {
                if(response.body()!!.status.success) {
                    capsuleList = response.body()!!.capsuleList
                    Log.d("size", capsuleList.size.toString())
                    setListView()
                } else {
                    Toast.makeText(activity, response.body()!!.status.message, Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<CapsuleListGet>?, t: Throwable?) {
                Log.e("retrofitError!", t!!.message)
                Toast.makeText(activity, "네트워크를 확인해주세요!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun setListView() {
        var graduatedAdapter : GraduatedAdapter = GraduatedAdapter(activity!!, capsuleList, R.layout.graduated_item)
        fragmentView!!.graduatedlist.adapter = graduatedAdapter
        fragmentView!!.graduatedlist.deferNotifyDataSetChanged()
    }
}