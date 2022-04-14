package com.project.road2food

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.compose.ui.text.toLowerCase
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_item.view.*
import org.osmdroid.tileprovider.modules.IFilesystemCache

class ItemAdapter(
   var clickedItem: ClickedItem
): RecyclerView.Adapter<ItemAdapter.ItemAdapterVH>(), Filterable {


    var itemModalList= ArrayList<ItemModal>()
    var itemModalListFilter= ArrayList<ItemModal>()

    fun setData(itemModalList: ArrayList<ItemModal>){
        this.itemModalList = itemModalList
        this.itemModalListFilter = itemModalList
        notifyDataSetChanged()

    }
    interface ClickedItem{
        fun ClickedItem(itemModal: ItemModal)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapterVH {
      return ItemAdapterVH(
          LayoutInflater.from(parent.context).inflate(R.layout.row_item,parent,false)
      )
    }

    override fun onBindViewHolder(holder: ItemAdapterVH, position: Int) {

        var itemModal = itemModalList[position]

        holder.imageView.setImageResource(itemModal.image)
        holder.name.text = itemModal.name
        holder.desc.text = itemModal.desc

        holder.itemView.setOnClickListener {
            clickedItem.ClickedItem(itemModal)

        }
    }

    override fun getItemCount(): Int {
        return itemModalList.size
    }
    class ItemAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView){

        var imageView = itemView.imageview
        var name = itemView.tvname
        var desc= itemView.tvdesc

    }

    override fun getFilter(): Filter {

        return object : Filter(){
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                var filterResults = FilterResults()
                if (charSequence == null || charSequence.isEmpty()){
                    filterResults.count = itemModalListFilter.size
                    filterResults.values = itemModalListFilter

                }else{
                    var searchChar: String = charSequence.toString().lowercase()
                    var itemModal = ArrayList<ItemModal>()
                    for (items in itemModalListFilter) {
                        if (items.name.lowercase().contains(searchChar) || items.desc.lowercase().contains(searchChar)
                        ) {
                            itemModal.add(items)
                        }
                    }
                    filterResults.count = itemModal.size
                    filterResults.values = itemModal
                }
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                itemModalList = p1!!.values as ArrayList<ItemModal>
                notifyDataSetChanged()
            }

        }
    }


}