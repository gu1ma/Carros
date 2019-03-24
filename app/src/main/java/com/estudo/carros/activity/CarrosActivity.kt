package com.estudo.carros.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import com.estudo.carros.R
import com.estudo.carros.adapter.CarroAdapter
import com.estudo.carros.domain.Carro
import com.estudo.carros.domain.CarroService
import com.estudo.carros.utils.TipoCarro
import kotlinx.android.synthetic.main.fragment_carros.*
import org.jetbrains.anko.startActivity

class CarrosActivity : BaseActivity(){
    private var tipo: TipoCarro = TipoCarro.Classicos;
    private var carros = listOf<Carro>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //layout padrão
        setContentView(R.layout.activity_carros);

        //pegamos o tipo do argumento
        this.tipo = intent.getSerializableExtra("tipo") as TipoCarro;
        val nomeCarro = context.getString(tipo.string);

        //Log.d("Log", nomeCarro)

        //vamos mostrar a toolbar
        //setUpToolbar(R.id.toolbar, nomeCarro, true);

        setUpToolbar(R.id.toolbar);

        supportActionBar?.title = nomeCarro;
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        //setTitle(nomeCarro);
        //setDisplayHomeAsUpEnabled(true)

        //mostramos o carro na tela
 //       val textView = findViewById<TextView>(R.id.text);
//        textView.text = "Carros $nomeCarro";

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)

    }

    override fun onResume() {
        super.onResume()
        taskCarros()
    }

    private fun taskCarros(){
        //Buscamos os carros
        this.carros = CarroService.getCarros(tipo)
        //Atualiza lista
        recyclerView.adapter = CarroAdapter(carros, {
            startActivity<CarroActivity>("carro" to it)
        })
    }
}