package com.estudo.carros

import android.app.Application
import android.util.Log
import java.lang.IllegalStateException

class CarrosApplication : Application(){
    //pra debug
    private val TAG = "Carros Application";

    override fun onCreate() {
        super.onCreate();

        //salvamos a instancia do app aqui
        appInstance = this;


    }

    companion object {
        //singleton
        private var appInstance: CarrosApplication? = null;

        fun getInstance(): CarrosApplication?{

            if(appInstance == null){
                throw IllegalStateException("Configure a classe Application no manifest");
            }

            return this.appInstance!!;
        }
    }


    /*override fun onTerminate() {
        super.onTeminate();
        Log.d(TAG, "CarrosApplication.onTerminate()");
    }*/
}