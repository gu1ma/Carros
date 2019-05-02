package com.estudo.carros.domain

import android.arch.persistence.room.Room
import com.estudo.carros.CarrosApplication

object DatabaseManager {
    private var dbInstance: CarrosDatabase

    init {
        val context = CarrosApplication.getInstance()!!.applicationContext

        //Configura o room
        dbInstance = Room.databaseBuilder(
            context,
            CarrosDatabase::class.java,
            "carros.sqlite"
        ).build()
    }

    fun getCarroDAO(): CarroDAO{
        return dbInstance.carroDAO()
    }
}