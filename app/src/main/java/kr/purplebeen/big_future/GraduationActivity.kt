package kr.purplebeen.big_future

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import kotlinx.android.synthetic.main.activity_graduation.*
import kr.purplebeen.big_future.utills.RetrofitUtil
import ninja.sakib.pultusorm.core.PultusORM
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar

class GraduationActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    lateinit var pultusORM : PultusORM
    var strDate : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graduation)

        val appPath : String = applicationContext.filesDir.absolutePath
        pultusORM = PultusORM("bigfuture.db", appPath)

        setListener()
    }

    fun setListener() {
        dateText.setOnClickListener {
            val now = Calendar.getInstance()
            val dpd = DatePickerDialog.newInstance(
                    this@GraduationActivity,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            )
            dpd.show(fragmentManager, "Datepickerdialog")
        }

        nextButton.setOnClickListener {
            val userService : UserService = RetrofitUtil.getLoginRetrofit(applicationContext).create(UserService::class.java)
            val userList : List<Any> = pultusORM.find(User())
            var user : User = userList[userList.size - 1] as User
            user.graduationDate = strDate!!
            val call : Call<UserGet> = userService.updateUser(user)
            if(strDate != null) {
                call.enqueue(object : Callback<UserGet> {

                    override fun onResponse(call: Call<UserGet>?, response: Response<UserGet>) {
                        if (response.body()!!.status.success) {
                            pultusORM.delete(User::class.java)
                            pultusORM.save(response.body()!!.user)
                            Log.d("graduateDate", response.body()!!.user.graduationDate)
                            Toast.makeText(applicationContext, "졸업 예정일이 성공적으로 설정되었습니다!", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this@GraduationActivity, MainActivity::class.java))
                        } else {
                            Toast.makeText(applicationContext, response.body()!!.status.message, Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<UserGet>?, t: Throwable?) {
                        Log.e("retrofit error", t!!.message)
                        Toast.makeText(applicationContext, "네트워크를 확인해주세요!", Toast.LENGTH_LONG).show()
                    }

                })
            } else {
                Toast.makeText(applicationContext, "날짜를 입력해주세요!", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {

        var calendar = Calendar.getInstance()
        calendar.set(year, monthOfYear, dayOfMonth)
        var format : SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        strDate = format.format(calendar.getTime())
        dateText.text = strDate
    }
}
