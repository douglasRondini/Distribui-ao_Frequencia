package adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.distribuiao_frequencia.IntervaloClasseProvider
import com.example.distribuiao_frequencia.R
import model.InfoDistribuiçaoFreq

class DistribuiçaoFrequencia_Adapter (
    private val context: Context,
    private val dbFrequencia: List<IntervaloClasseProvider>
): RecyclerView.Adapter<DistribuiçaoFrequencia_Adapter.IntervaloClasseProviderViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IntervaloClasseProviderViewHolder {
        val itemLista = LayoutInflater.from(context).inflate(R.layout.classe_item,parent,false)
        val holder = IntervaloClasseProviderViewHolder(itemLista)
        return holder
    }

    override fun onBindViewHolder(holder: IntervaloClasseProviderViewHolder, position: Int) {
        holder.classe.text = dbFrequencia[position].listaDosIntervalosClasse().toString()
 //       holder.pMedio.text = dbFrequencia[position].calcularPontoMedio
//        holder.freqAbs.text = dbFrequencia[position].freqAbsoluta
//        holder.freqRel.text = dbFrequencia[position].freqRelativa
//        holder.freqAcu.text = dbFrequencia[position].freqAcumulativa
    }

    override fun getItemCount(): Int = dbFrequencia.size



    inner class IntervaloClasseProviderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val classe = itemView.findViewById<TextView>(R.id.txt_classe_valu)
        val pMedio = itemView.findViewById<TextView>(R.id.txt_pMedio_value)
        val freqAbs = itemView.findViewById<TextView>(R.id.txt_freqAbs_value)
        val freqRel = itemView.findViewById<TextView>(R.id.txt_freqRel_value)
        val freqAcu = itemView.findViewById<TextView>(R.id.txt_freAcu_value)

    }



}