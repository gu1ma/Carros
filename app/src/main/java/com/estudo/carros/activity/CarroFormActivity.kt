package com.estudo.carros.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import com.estudo.carros.R
import com.estudo.carros.domain.Carro
import com.estudo.carros.domain.CarroService
import com.estudo.carros.utils.TipoCarro
import kotlinx.android.synthetic.main.activity_carro_form_contents.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class CarroFormActivity: BaseActivity(){
    val carro: Carro? by lazy { intent.getSerializableExtra("carro") as? Carro }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_carro_form)
        //configuracao da toolbar
        val title = carro?.nome  ?: getString(R.string.novo_carro)
        setUpToolbar(R.id.toolbar, title, true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_form_carro, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_salvar -> {
                taskSalvar()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun taskSalvar(){
        doAsync {
            val c = getCarroForm()
            //
            val response = CarroService.save(c)
            uiThread {
                toast("OK")
                finish()
            }
        }
    }

    private fun getCarroForm(): Carro {
        val c =  carro?: Carro()
        c.tipo = getTipo()
        c.nome = tNome.getText().toString()
        c.desc = tDesc.getText().toString()

        return c
    }

    private fun getTipo(): String {
        when(radioTipo.getCheckedRadioButtonId()){
            R.id.tipoEsportivo -> return getString(R.string.esportivos)
            R.id.tipoClassico -> return getString(R.string.classicos)
        }
        return getString(R.string.luxo)
    }
}