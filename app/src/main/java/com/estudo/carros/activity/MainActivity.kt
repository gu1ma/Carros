package com.estudo.carros.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.estudo.carros.R
import com.estudo.carros.adapter.TabsAdapter
import com.estudo.carros.utils.TipoCarro
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbar)


        //setando toolbar
        setUpToolbar(R.id.toolbar);

        fab.setOnClickListener {
            Snackbar.make(it, "Adicionar novo carro", Snackbar.LENGTH_LONG)
                .show()
        }

        //setando drawer
        setUpNavDrawer()

        //setando view pager
        setUpViewPageTabs()


    }

    private fun setUpViewPageTabs(){
        //Configuração do View Pager + Tabs
        viewPager.offscreenPageLimit = 2
        viewPager.adapter = TabsAdapter(context, supportFragmentManager)

        tabLayout.setupWithViewPager(viewPager)

        //Cor branca no texto
        val cor = ContextCompat.getColor(context, R.color.white)
        tabLayout.setTabTextColors(cor, cor)

    }

    private fun setUpNavDrawer(){
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this);
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_item_carros_todos -> {
                // Handle the camera action
                toast("Clique em todos");
            }
            R.id.nav_item_carros_classicos -> {
                //toast("Clique em classicos");
                //startActivity<CarrosActivity>("tipo" to TipoCarro.Classicos)
                val intent = Intent(context, CarrosActivity::class.java);
                intent.putExtra("tipo", TipoCarro.Classicos);
                startActivity(intent);
            }
            R.id.nav_item_carros_esportivos -> {
                //toast("Clique em esportivos");
                startActivity<CarrosActivity>("tipo" to TipoCarro.Esportivos);
            }
            R.id.nav_item_carros_luxo -> {
                //toast("Clique em luxo");
                startActivity<CarrosActivity>("tipo" to TipoCarro.Luxo);
            }
            R.id.nav_item_settings -> {
                toast("Clique em configurações");
            }
            R.id.nav_item_site_livro -> {
                //toast("Clique em site do livro");
                startActivity<SiteLivroActivity>()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
