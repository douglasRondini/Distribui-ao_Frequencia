package com.example.distribuiao_frequencia

import com.example.distribuiao_frequencia.databinding.ActivityMainBinding

class IntervaloClasseProvider (private val binding: ActivityMainBinding) {
    fun listaDosIntervalosClasse(): List<String> {
        val list1 = limiteInferior()
        val list2 = limiteSuperior()
        val intervlo = "--"
        val resultado = list1.zip(list2) {a, b ->"$a$intervlo$b"}
        return resultado

    }


    fun calcularPontoMedio(intervalos: List<String>): List<Double> {
        return intervalos.map { intervalo ->
            val limites = intervalo.split("--").map { it.toDouble() }
            (limites[0] + limites[1]) / 2
        }
    }


    fun incementoLimiteSuperior(lista: List<Double>, incremento: Int, quantidade: Int?): List<Double> {
        val limiteSuperior = mutableListOf<Double>()
        val minValue = lista[1] ?: return limiteSuperior
        quantidade?.let {
            for (i in 0 until it) {
                limiteSuperior.add(minValue-1 + (i*incremento))

            }
        }
        return limiteSuperior

    }

    fun limiteSuperior(): List<Double> {
        val list = limiteInferior()
        val classe = binding.edtNumClasse.text.toString()
        val result = incementoLimiteSuperior(
            list,
            incremento = intervaloClass(),
            quantidade = classe.toInt()
        )
        return result
    }



    fun incementoLimiteInferior(lista: List<Double>, incremento: Int, quantidade: Int?): List<Double> {
        val limiteInferior = mutableListOf<Double>()
        val minValue =lista.minOrNull() ?: return limiteInferior
        quantidade?.let {
            for (i in 0 until it) {
                limiteInferior.add(minValue + (i*incremento))
            }

        }
        return limiteInferior

    }


    fun limiteInferior(): List<Double> {
        val list = listDados()
        val classe = binding.edtNumClasse.text.toString()
        val result = incementoLimiteInferior(
            list,
            incremento = intervaloClass(),
            quantidade = classe.toInt()
        )
        return result
    }


    fun intervaloClass(): Int {
        val numberClass = binding.edtNumClasse.text.toString().toDouble()
        val intervalo = amplitude()/numberClass
        return intervalo.toInt()

    }

    fun amplitude(): Double {
        val maxValue = listDados().maxOrNull().toString().toDouble()
        val minValue = listDados().minOrNull().toString().toDouble()
        val amplitude = maxValue - minValue
        return amplitude
    }


    fun listDados(): List<Double> {
        val dados = binding.edtValues.text.toString()
        return dados.split(",").map { it.trim().toDouble() }



    }
}
