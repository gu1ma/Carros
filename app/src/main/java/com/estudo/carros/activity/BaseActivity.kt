package com.estudo.carros.activity

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.app.AppCompatActivity

@SuppressLint("Registered")
open class BaseActivity() : AppCompatActivity(){
    //propriedade para acessar o contexto
    protected val context: Context get() = this;

}