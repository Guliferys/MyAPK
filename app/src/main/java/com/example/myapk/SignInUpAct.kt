package com.example.myapk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.example.myapk.constance.Constance
import com.example.myapk.databinding.ActivitySignInUpBinding

class SignInUpAct : AppCompatActivity() {
    lateinit var bindingClass: ActivitySignInUpBinding
    private var signState = "empty"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivitySignInUpBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        signState = intent.getStringExtra(Constance.SIGN_STATE)!!
        if(signState == Constance.SIGN_IN_STATE){
            bindingClass.edName1.visibility = View.GONE
            bindingClass.edName2.visibility = View.GONE
            bindingClass.edName3.visibility = View.GONE
            bindingClass.bAvatar.visibility = View.INVISIBLE
        }else if(signState == Constance.SIGN_UP_STATE){
            bindingClass.edName1.visibility = View.VISIBLE
            bindingClass.edName2.visibility = View.VISIBLE
            bindingClass.edName3.visibility = View.VISIBLE
            bindingClass.bAvatar.visibility = View.VISIBLE
        }
    }

    fun onClickDone(view: View){
        if(signState == Constance.SIGN_UP_STATE) {
            val intent = Intent()
            intent.putExtra(Constance.LOGIN, bindingClass.edLogin.text.toString())
            intent.putExtra(Constance.PASSWORD, bindingClass.edPassword.text.toString())
            intent.putExtra(Constance.NAME1, bindingClass.edName1.text.toString())
            intent.putExtra(Constance.NAME2, bindingClass.edName2.text.toString())
            intent.putExtra(Constance.NAME3, bindingClass.edName3.text.toString())
            if(bindingClass.imgAvatar.isVisible)intent.putExtra(Constance.AVATAR_ID, R.drawable.img1)
            setResult(RESULT_OK, intent)
            finish()
        } else if(signState == Constance.SIGN_IN_STATE){
            intent.putExtra(Constance.LOGIN, bindingClass.edLogin.text.toString())
            intent.putExtra(Constance.PASSWORD, bindingClass.edPassword.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    fun onClickAvatar(view: View){
        bindingClass.imgAvatar.visibility = View.VISIBLE
    }

}