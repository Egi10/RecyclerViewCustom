package id.buaja.myapplication

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.buaja.myapplication.adapter.MainAdapter
import id.buaja.myapplication.adapter.MainCaraKeduaAdapter
import id.buaja.myapplication.entity.Goods
import id.buaja.myapplication.entity.MappingGoods
import kotlinx.android.synthetic.main.item_list_goods.*

class MainActivity : AppCompatActivity() {
    private var listAsal: MutableList<Goods> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val response = listOf(
            Goods(
                date = "Sabtu, 29 Agustus 2020",
                title = "Aku Sayang Kamu",
                status = "Selesai",
                alamatDatang = "08:00",
                alamatTujuan = "09:00"
            ),
            Goods(
                date = "Sabtu, 29 Agustus 2020",
                title = "Sayang Kamu Bangat",
                status = "Selesai",
                alamatDatang = "10:00",
                alamatTujuan = "11:00"
            ),
            Goods(
                date = "Sabtu, 30 Agustus 2020",
                title = "Kamu Sayang Aku ?",
                status = "Selesai",
                alamatDatang = "09:00",
                alamatTujuan = "10:00"
            )
        )
        listAsal.addAll(response)

        oldGame()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.new_game -> {
                newGame()
                true
            }

            R.id.old_game -> {
                oldGame()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun oldGame() {
        val listGrouping = listAsal.groupBy {
            it.date
        }

        val listMapping: MutableList<MappingGoods> = mutableListOf()
        listGrouping.map {
            listMapping.add(
                MappingGoods(
                    date = it.key,
                    list = it.value
                )
            )
        }

        val adapter = MainAdapter(listMapping) { mappingGoods, goods ->
            Toast.makeText(this, "${mappingGoods.date} - ${goods.title}", Toast.LENGTH_SHORT).show()
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun newGame() {
        var visibleData = ""
        listAsal.forEach {
            if (visibleData != it.date) {
                visibleData = it.date.toString()
                it.grouping = visibleData
            }
        }
        val adapter = MainCaraKeduaAdapter(listAsal) {
            Toast.makeText(this, "${it.date} - ${it.title}", Toast.LENGTH_SHORT).show()
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}