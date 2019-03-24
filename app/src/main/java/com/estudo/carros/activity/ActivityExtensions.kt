package com.estudo.carros.activity

import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast


    fun AppCompatActivity.onClick(@IdRes viewId: Int, onClick: (v: android.view.View?) -> Unit) {
        val view = findViewById<View>(viewId)
        view.setOnClickListener { onClick(it) }

    }
    //mostra um toast
    fun AppCompatActivity.toast(message: CharSequence, lenght: Int = Toast.LENGTH_SHORT){
        Toast.makeText(this, message, lenght).show();
    }

    fun AppCompatActivity.toast(@StringRes message: Int, lenght: Int = Toast.LENGTH_SHORT){
        Toast.makeText(this, message, lenght).show();
    }

    //configuracoes da toolbar
    fun AppCompatActivity.setUpToolbar(@IdRes id: Int, title: String? = null, upNavigaton : (Boolean) = false) : ActionBar{
        val toolbar : Toolbar = findViewById<Toolbar>(id);

        setSupportActionBar(toolbar);

        if(title != null){
            supportActionBar?.title = title;
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(upNavigaton);

        return supportActionBar!!;

    }

    fun AppCompatActivity.addFragment(@IdRes layoutId: Int, fragment: Fragment){
        fragment.arguments = intent.extras;

        val ft = supportFragmentManager.beginTransaction();
        ft.add(layoutId, fragment);

        ft.commit();
    }

