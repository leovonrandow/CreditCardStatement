package br.com.cotemig.leonardo.ui.activites

import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.leonardo.R
import br.com.cotemig.leonardo.models.Fatura
import br.com.cotemig.leonardo.services.RetrofitInitializer
import br.com.cotemig.leonardo.ui.adapters.FaturaAdapter
import retrofit2.Call
import retrofit2.Response
import java.text.NumberFormat.getCurrencyInstance


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getFatura()

        //from stackoverflow tirar cor da barra de status
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }

    }

    fun getFatura() {

        var s = RetrofitInitializer().serviceFatura()
        var call = s.getFatura()
        call.enqueue(object : retrofit2.Callback<Fatura> {
            override fun onResponse(call: Call<Fatura>, response: Response<Fatura>) {

                if (response.code() == 200) {

                    response.body()?.let {
                        show(it)
                    }

                }
            }

            override fun onFailure(call: Call<Fatura>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Ops", Toast.LENGTH_LONG).show()
            }
        })

    }

    fun show( fatura: Fatura  ) {

        var limite = findViewById<TextView>(R.id.limite_value)
        limite.text = getCurrencyInstance().format(fatura.limiteDisponivel);

        var valor = findViewById<TextView>(R.id.fatura_value)

        valor.text = getCurrencyInstance().format(fatura.valor);

        var list = findViewById<RecyclerView>(R.id.recyclerViewDespesas)
        list.adapter = FaturaAdapter(this, fatura.despesas)

        list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        list.onFlingListener = null
        //var snapHelper = PagerSnapHelper()
        //snapHelper.attachToRecyclerView(list)*/

    }
}