package com.example.distribuiao_frequencia

import adapter.DistribuiçaoFrequencia_Adapter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.distribuiao_frequencia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var listFreq: RecyclerView
    private lateinit var intervaloProvider: IntervaloClasseProvider
    private lateinit var distribuiçaofrequenciaAdapter: DistribuiçaoFrequencia_Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intervaloProvider = IntervaloClasseProvider(binding)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //listDistribuiçaoFreq()
        clickButton()
    }

    fun listDistribuiçaoFreq() {
        listFreq = binding.rvRecycleView
        val dados = intervaloProvider.listaDosIntervalosClasse() // Certifique-se que isso retorna uma lista
        distribuiçaofrequenciaAdapter = DistribuiçaoFrequencia_Adapter(baseContext, dados)

        listFreq.layoutManager = LinearLayoutManager(this)
        listFreq.adapter = distribuiçaofrequenciaAdapter
    }


    fun clickButton() {

        binding.btnCalcular.setOnClickListener { it ->
            listDistribuiçaoFreq()
            return@setOnClickListener
        }
    }

}