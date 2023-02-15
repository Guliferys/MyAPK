package com.example.myapk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.example.myapk.constance.Constance
import com.example.myapk.constance.Constance.REQUEST_CODE_SIGN_IN
import com.example.myapk.databinding.ActivityMainBinding

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMainBinding
    private var login: String = "empty"
    private var password: String = "empty"
    private var name1: String = "empty"
    private var name2: String = "empty"
    private var name3: String = "empty"
    private var avatarImgId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Constance.REQUEST_CODE_SIGN_IN) {
            val l = data?.getStringExtra(Constance.LOGIN)
            val p = data?.getStringExtra(Constance.PASSWORD)
            if (login == l && password == p) {
                bindingClass.imAvatar.visibility = View.VISIBLE
                bindingClass.imAvatar.setImageResource(avatarImgId)
                bindingClass.tv.text = "$name1 $name2 $name3"
                bindingClass.bHide.visibility = View.GONE
                bindingClass.bExit.text = getString(R.string.exit)
            } else bindingClass.tv.text = getString(R.string.acc_no_exist)
        }

        else if (requestCode == Constance.REQUEST_CODE_SIGN_UP) {
            login = data?.getStringExtra(Constance.LOGIN)!!
            password = data.getStringExtra(Constance.PASSWORD)!!
            name1 = data.getStringExtra(Constance.NAME1)!!
            name2 = data.getStringExtra(Constance.NAME2)!!
            name3 = data.getStringExtra(Constance.NAME3)!!
            avatarImgId = data.getIntExtra(Constance.AVATAR_ID, 0)

            bindingClass.imAvatar.visibility = View.VISIBLE
            bindingClass.imAvatar.setImageResource(avatarImgId)
            bindingClass.tv.text = "$name1 $name2 $name3"
            bindingClass.bHide.visibility = View.GONE
            bindingClass.bExit.text = getString(R.string.exit)
        }
    }

    fun onClickSignIn(view: View){
        if(bindingClass.imAvatar.isVisible && bindingClass.tv.text.toString() != getString(R.string.acc_no_exist)){
            bindingClass.imAvatar.visibility = View.INVISIBLE
            bindingClass.tv.text = getString(R.string.hello)
            bindingClass.bExit.text = getString(R.string.sign_in)
            bindingClass.bHide.visibility = View.VISIBLE
        }else {
            val intent = Intent(this, SignInUpAct::class.java)
            intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_IN_STATE)
            startActivityForResult(intent, Constance.REQUEST_CODE_SIGN_IN)
        }
    }

    fun onClickSignUp(view: View){
        val intent = Intent(this, SignInUpAct::class.java)
        intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_UP_STATE)
        startActivityForResult(intent, Constance.REQUEST_CODE_SIGN_UP)
    }
}