package com.example.sqlitepe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.sqlitepe.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Reemplaza el fragmento por defecto al inicio
        if (savedInstanceState == null) {
            replaceFragment(SecondFragment())
        }

        // Configura el BottomNavigationView
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    replaceFragment(SecondFragment())
                    true
                }
                R.id.people -> {
                    replaceFragment(PeopleFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment) // Asegúrate de que este ID sea correcto
            .commit()
        return true
    }
}
