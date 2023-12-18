package com.example.myapplication

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.fragment.AboutFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Infla o layout da atividade principal a partir do arquivo XML
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configura a barra de ação (ActionBar) com a barra de ferramentas (Toolbar)
        setSupportActionBar(binding.appBarMain.toolbar)

        // Obtém referências para o DrawerLayout e NavigationView
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        // Configura a navegação usando o NavController
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        // Define as configurações da AppBar, especificando os destinos de menu e o DrawerLayout
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_favorites , R.id.nav_assessments,  R.id.nav_profile
            ), drawerLayout
        )

        // Configura a ActionBar para trabalhar com a navegação
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Configura o NavigationView para trabalhar com a navegação
        navView.setupWithNavController(navController)

//        Firebase.auth.signInWithEmailAndPassword("suzanateste@tuamaeaquelaursa.com", "teste123")
//            .addOnSuccessListener {
//                Log.i(TAG, "onCrate: usuário está logado" )
//            }
//            .addOnFailureListener {
//                Log.e(TAG,"onCrate: Usuário não logado")
//            }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Infla o menu na barra de ação, se estiver presente
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                // Crie uma instância do fragmento AboutFragment
                val aboutFragment = AboutFragment()

                // Use o FragmentManager para substituir o conteúdo principal pelo fragmento AboutFragment
                supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_content_main, aboutFragment)
                    .addToBackStack(null) // Adicione à pilha de retorno se desejar
                    .commit()

                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        // Configura a navegação de retorno na ActionBar
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    companion object {
        // Visibilidade do botão hamburguer do layout drawer



    }

}
