package id.buaja.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.buaja.myapplication.R
import id.buaja.myapplication.entity.Goods
import kotlinx.android.synthetic.main.item_list_gods.view.*

class ItemMainAdapter(private val item: List<Goods>, private val listener: (Goods) -> Unit) :
    RecyclerView.Adapter<ItemMainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_gods, parent, false)
        )

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(item[position], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(item: Goods, listener: (Goods) -> Unit) {
            with(itemView) {
                tvTitle.text = item.title
                tvStatus.text = item.status
                tvAlamatAwal.text = item.alamatDatang
                tvAlamatAkhir.text = item.alamatTujuan

                setOnClickListener {
                    listener(item)
                }
            }
        }
    }
}