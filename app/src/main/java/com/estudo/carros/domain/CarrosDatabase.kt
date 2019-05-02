package com.estudo.carros.domain

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(Carro::class), version = 1)
abstract class CarrosDatabase: RoomDatabase(){
    abstract fun carroDAO(): CarroDAO
}