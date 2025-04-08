package com.example.distribuiao_frequencia

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.set
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.distribuiao_frequencia.adapter.DistribuiçaoFrequencia_Adapter
import com.example.distribuiao_frequencia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var listFreq: RecyclerView
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

        setupRecyclerView()
        clickButton()
    }

    private fun setupRecyclerView() {
        listFreq = binding.rvRecycleView
        listFreq.layoutManager = LinearLayoutManager(this)
    }

    private fun listDistribuiçaoFreq() {
        val dados = intervaloProvider.listaDosIntervalosClasse()
        distribuiçaofrequenciaAdapter = DistribuiçaoFrequencia_Adapter(this, dados)
        listFreq.adapter = distribuiçaofrequenciaAdapter
    }

    private fun clickButton() {
        val dados = binding.edtValues.text
        val classes = binding.edtNumClasse.text
        binding.btnCalcular.setOnClickListener {
            if (dados.isEmpty() || classes.isEmpty()) {
                Toast.makeText(this, "Preencha todos os Campos", Toast.LENGTH_SHORT).show()
            }
            listDistribuiçaoFreq()
            clear()
        }
    }
    fun clear() {
       binding.edtValues.text.clear()
        binding.edtNumClasse.text.clear()
    }
}