package kr.purplebeen.big_future.controllers.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_friendslist.*
import kr.purplebeen.big_future.*
import kr.purplebeen.big_future.utills.RetrofitUtil
import kr.purplebeen.big_future.controllers.adapters.ListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FriendListActivity : AppCompatActivity() {
    lateinit var userList : ArrayList<GeneralUser>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friendslist)

        loadFriendData()
    }

    fun loadFriendData() {
        var userService : UserService = RetrofitUtil.getLoginRetrofit(applicationContext).create(UserService::class.java)
        var call : Call<UserListGet> = userService.getUserList()
        call.enqueue(object : Callback<UserListGet> {
            override fun onResponse(call: Call<UserListGet>?, response: Response<UserListGet>) {
                if(response.body()!!.status.success) {
                    userList = response.body()!!.userList
                    setListView()
                } else {
                    Toast.makeText(applicationContext, response.body()!!.status.message, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<UserListGet>?, t: Throwable?) {
                Log.e("error", t!!.message)
            }

        })
    }

    fun setListView() {
        var listAdapter : ListAdapter = ListAdapter(applicationContext, userList, R.layout.friendsitem)
        friendslist.adapter = listAdapter
        friendslist.deferNotifyDataSetChanged()
        friendslist.setOnItemClickListener { parent, view, position, id ->
            var newIntent : Intent = Intent()
            var bundle : Bundle = Bundle()
            newIntent.putExtra("friendName", userList[position].name)
            newIntent.putExtra("friendID", userList[position].id)
            newIntent.putExtra("friendPicture", userList[position].picture)
            setResult(3000, newIntent)
            finish()
        }
    }
}
