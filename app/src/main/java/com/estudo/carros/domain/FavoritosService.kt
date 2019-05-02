package com.estudo.carros.domain

object FavoritosService{
    fun getCarros(): List<Carro>{
        val dao = DatabaseManager.getCarroDAO()
        val carros = dao.findAll()

        return carros
    }

    fun isFavorito(carro: Carro): Boolean{
        val dao = DatabaseManager.getCarroDAO()
        return dao.getById(carro.id) != null
    }

    fun favoritar(carro: Carro): Boolean{
        val favorito = isFavorito(carro)
        val dao = DatabaseManager.getCarroDAO()

        if(favorito){
            //Removendo dos favoritos
            dao.delete(carro)
            return false
        }

        dao.insert(carro)
        return true

    }

}