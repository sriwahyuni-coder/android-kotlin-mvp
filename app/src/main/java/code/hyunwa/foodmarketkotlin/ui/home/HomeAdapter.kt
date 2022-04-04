package code.hyunwa.foodmarketkotlin.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.recyclerview.widget.RecyclerView
import code.hyunwa.foodmarketkotlin.R
import code.hyunwa.foodmarketkotlin.model.dummy.HomeModel
import code.hyunwa.foodmarketkotlin.model.response.home.Data
import code.hyunwa.foodmarketkotlin.model.response.home.HomeResponse
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_home.view.tvTitle
import kotlinx.android.synthetic.main.item_home_horizontal.view.*

class HomeAdapter (
    private var listData : List<Data>,
    private var itemAdapterCallback : ItemAdapterCallback
    ) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_home_horizontal,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(data: Data, itemAdapterCallback: ItemAdapterCallback){
            itemView.apply {
                tvTitle.text = data.name
                rbFood.rating = data.rate?.toFloat() ?: 0f

                Glide.with(context)
                    .load(data.picturePath)
                    .into(ivPoster)

                itemView.setOnClickListener { itemAdapterCallback.onClick(it, data) }

            }
        }

    }

    interface ItemAdapterCallback{
        fun onClick(v: View, data: Data)
    }


}