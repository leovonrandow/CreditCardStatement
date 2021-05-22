package br.com.cotemig.leonardo.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.leonardo.R
import br.com.cotemig.leonardo.models.Despesa
import coil.load
import java.text.NumberFormat
import java.text.NumberFormat.getCurrencyInstance

class FaturaAdapter(var context: Context, var despesas: List<Despesa>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_despesa, parent, false)
        return ExpenseHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ExpenseHolder).bind(context, despesas[position])
    }

    override fun getItemCount(): Int {
        return despesas.size
    }

    class ExpenseHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(context: Context, despesa: Despesa) {

            var icone = itemView.findViewById<ImageView>(R.id.typebill_icon)
            icone.load(despesa.imagem)


            var desc = itemView.findViewById<TextView>(R.id.cobrador_name)
            desc.text = despesa.descricao

            var valor = itemView.findViewById<TextView>(R.id.valor_gasto)
            valor.text = getCurrencyInstance().format(despesa.valor);

            var tipo_gasto = itemView.findViewById<TextView>(R.id.cobrador_type)
            tipo_gasto.text = despesa.tipo

            var hora = itemView.findViewById<TextView>(R.id.hora)
            hora.text = despesa.data




        }
    }
}