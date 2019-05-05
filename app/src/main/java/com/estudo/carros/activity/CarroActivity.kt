package com.estudo.carros.activity

import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.startActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.estudo.carros.R;
import com.estudo.carros.domain.*
import com.estudo.carros.utils.RefreshListEvent
import com.estudo.carros.utils.loadUrl
import kotlinx.android.synthetic.main.activity_carro.*
import kotlinx.android.synthetic.main.activity_carro_contents.*
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.*

class CarroActivity : BaseActivity(){
    val carro by lazy { intent.getSerializableExtra("carro") as Carro }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_carro)

        setUpToolbar(R.id.toolbar, carro.nome, true)

        //atualizando img do player
        img.loadUrl(carro.urlFoto);

        //listener para dar play no video
        imgPlayVideo.setOnClickListener {
            val url = carro.urlVideo;
            val intent = Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse(url), "video/*");
            startActivity(intent);
        }

        //Atualiza descrição
        tDesc.text = carro.desc
        //Mostra foto do carro
        appBarImg.loadUrl(carro.urlFoto)

        fab.setOnClickListener( View.OnClickListener{ onClickFavoritar(carro) } )

        checkIfIsFavorite()
    }

    private fun onClickFavoritar(carro: Carro){
        taskFavoritar(carro)
    }

    private fun taskFavoritar(carro: Carro){
        doAsync {
            val favoritado = FavoritosService.favoritar(carro)
            uiThread {
                //Dispara evento para atualizar a lista
                EventBus.getDefault().post(RefreshListEvent())
                //Alerta de sucesso
                setFavoriteColor(favoritado);
                toast(if(favoritado) R.string.msg_carro_favoritado else R.string.msg_carro_desfavoritado)
            }
        }
    }

    private fun checkIfIsFavorite() {

        doAsync {
            val dao = DatabaseManager.getCarroDAO();
            val isFavorite = dao.getById(carro.id);
            uiThread {
                if(isFavorite == null)
                {
                    setFavoriteColor(false);
                }
                else
                {
                    setFavoriteColor(true);
                }
            }
        }
    }

    private fun setFavoriteColor(favoritado:Boolean)
    {
        val background = ContextCompat.getColor(this, if(favoritado) R.color.favorito_on else R.color.favorito_off);
        val color = ContextCompat.getColor(this, if(favoritado) R.color.yellow else R.color.favorito_on);

        fab.backgroundTintList = ColorStateList(arrayOf(intArrayOf(0)), intArrayOf(background));
        fab.setColorFilter(color);
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_carro, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_editar -> {
                //abrimos a tela para edicao
                startActivity<CarroFormActivity>("carro" to carro)
                finish()
            }
            R.id.action_deletar -> {
                //toast("deletar carro")
                alert("Tem certeza que deseja excluir este carro?"){
                    title = "Atenção"
                    positiveButton(R.string.sim) { taskDeletar() }
                    negativeButton(R.string.nao) {}
                }.show()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    //task para deletar carro
    private fun taskDeletar(){
        doAsync {
            val response = CarroService.delete(carro)

            uiThread {
                //chamada do event bus para indicar que houve alguma alteração na lista
                EventBus.getDefault().post(RefreshListEvent())
                toast(response.msg)
                finish()
            }
        }
    }
}