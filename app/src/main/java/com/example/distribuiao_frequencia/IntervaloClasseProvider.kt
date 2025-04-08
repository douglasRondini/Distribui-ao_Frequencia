package com.example.distribuiao_frequencia

import com.example.distribuiao_frequencia.databinding.ActivityMainBinding
import model.InfoDistribuiçaoFreq

class IntervaloClasseProvider(private val binding: ActivityMainBinding) {
    fun listaDosIntervalosClasse(): List<InfoDistribuiçaoFreq> {
        val intervalos = gerarIntervalos()
        val pontosMedios = calcularPontoMedio(intervalos)
        val frequenciasAbsolutas = calcularFrequenciasAbsolutas(intervalos)
        val frequenciasRelativas = calcularFrequenciasRelativas(frequenciasAbsolutas)
        val frequenciasAcumulativas = calcularFrequenciasAcumulativas(frequenciasRelativas)

        return intervalos.mapIndexed { index, intervalo ->
            InfoDistribuiçaoFreq(
                classe = intervalo,
                pontoMedio = pontosMedios[index],
                frequenciaAbsoluta = frequenciasAbsolutas[index],
                frequenciaRelativa = frequenciasRelativas[index],
                frequenciaAcumulativa = frequenciasAcumulativas[index]
            )
        }
    }

    private fun gerarIntervalos(): List<String> {
        val list1 = limiteInferior()
        val list2 = limiteSuperior()
        val intervalo = "--"
        return list1.zip(list2) { a, b -> "$a$intervalo$b" }
    }

    private fun calcularPontoMedio(intervalos: List<String>): List<Double> {
        return intervalos.map { intervalo ->
            val limites = intervalo.split("--").map { it.toDouble() }
            (limites[0] + limites[1]) / 2
        }
    }

    private fun calcularFrequenciasAbsolutas(intervalos: List<String>): List<Int> {
        val dados = listDados()
        return intervalos.map { intervalo ->
            val (inferior, superior) = intervalo.split("--").map { it.toDouble() }
            dados.count { it >= inferior && it <= superior }
        }
    }

    private fun calcularFrequenciasRelativas(frequenciasAbsolutas: List<Int>): List<Double> {
        val total = frequenciasAbsolutas.sum()
        return frequenciasAbsolutas.map { it.toDouble() / total }
    }

    private fun calcularFrequenciasAcumulativas(frequenciasRelativas: List<Double>): List<Double> {
        val acumulativas = mutableListOf<Double>()
        var acumulada = 0.0
        frequenciasRelativas.forEach { frequencia ->
            acumulada += frequencia
            acumulativas.add(acumulada)
        }
        return acumulativas
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
        val minValue = lista.minOrNull() ?: return limiteInferior
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
