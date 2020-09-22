package id.buaja.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.buaja.myapplication.R
import id.buaja.myapplication.entity.Goods
import id.buaja.myapplication.entity.MappingGoods
import kotlinx.android.synthetic.main.item_list_goods.view.*


class MainAdapter(
    private val item: List<MappingGoods>,
    private val listener: (MappingGoods, Goods) -> Unit
) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_goods, parent, false)
        )

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(item[position], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(item: MappingGoods, listener: (MappingGoods, Goods) -> Unit) {
            with(itemView) {
                tvDate.text = item.date

                var goods: Goods? = null
                val adapter = item.list?.let {
                    ItemMainAdapter(it) {
                        goods = it
                        goods?.let { it1 -> listener(item, it1) }
                    }
                }
                recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
                recyclerView.adapter = adapter

                setOnClickListener {
                    goods?.let { it1 -> listener(item, it1) }
                }
            }
        }
    }
}