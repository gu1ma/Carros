package com.estudo.carros.activity

import android.os.Bundle
import android.os.PersistableBundle
import com.estudo.carros.domain.Carro
import com.estudo.carros.R;
import com.estudo.carros.utils.loadUrl
import kotlinx.android.synthetic.main.activity_carro.*
import kotlinx.android.synthetic.main.activity_carro_contents.*

class CarroActivity : BaseActivity(){
    val carro by lazy { intent.getSerializableExtra("carro") as Carro }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_carro)

        setUpToolbar(R.id.toolbar, carro.nome, true)

        //Atualiza descrição
        tDesc.text = carro.desc
        //Mostra foto do carro
        appBarImg.loadUrl(carro.urlFoto)


    }
}