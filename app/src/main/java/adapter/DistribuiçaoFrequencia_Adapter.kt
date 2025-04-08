package com.example.distribuiao_frequencia.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.distribuiao_frequencia.R
import model.InfoDistribuiçaoFreq

class DistribuiçaoFrequencia_Adapter(
    private val context: Context,
    private val frequenciaList: List<InfoDistribuiçaoFreq>
) : RecyclerView.Adapter<DistribuiçaoFrequencia_Adapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtClasseValue: TextView = view.findViewById(R.id.txt_classe_valu)
        val txtPMedioValue: TextView = view.findViewById(R.id.txt_pMedio_value)
        val txtFreqAbsValue: TextView = view.findViewById(R.id.txt_freqAbs_value)
        val txtFreqRelValue: TextView = view.findViewById(R.id.txt_freqRel_value)
        val txtFreqAcuValue: TextView = view.findViewById(R.id.txt_freAcu_value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.classe_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val frequencia = frequenciaList[position]
        holder.txtClasseValue.text = frequencia.classe
        holder.txtPMedioValue.text = String.format("%.2f", frequencia.pontoMedio)
        holder.txtFreqAbsValue.text = frequencia.frequenciaAbsoluta.toString()
        holder.txtFreqRelValue.text = String.format("%.2f%%", frequencia.frequenciaRelativa * 100)
        holder.txtFreqAcuValue.text = String.format("%.2f%%", frequencia.frequenciaAcumulativa * 100)
    }

    override fun getItemCount(): Int = frequenciaList.size
}