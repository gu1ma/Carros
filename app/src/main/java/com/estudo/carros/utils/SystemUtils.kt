package com.estudo.carros.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object SystemUtils {

    //Função que verifica se o app tem conexão ativa
    fun isNetworkAvaiable(context: Context): Boolean {
        val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networks = connectivity.allNetworks

        return networks.map { connectivity.getNetworkInfo(it) }
                       .any{ it.state == NetworkInfo.State.CONNECTED }
    }
}