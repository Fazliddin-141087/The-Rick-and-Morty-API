package uz.mobiler.therickmorty.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.mobiler.therickmorty.databinding.ItemRickAndMortyBinding
import uz.mobiler.therickmorty.models.Result

class RvAdapters(var list: ArrayList<Result>,var myOnClickListener: MyOnClickListener) : RecyclerView.Adapter<RvAdapters.Vh>() {
    inner class Vh(var itemRickAndMortyBinding: ItemRickAndMortyBinding) :
        RecyclerView.ViewHolder(itemRickAndMortyBinding.root) {
        fun onBind(result: Result) {
            Picasso.get().load(result.image).into(itemRickAndMortyBinding.images)
            itemRickAndMortyBinding.name.text = result.name
            itemRickAndMortyBinding.desc.text = result.gender

            itemRickAndMortyBinding.root.setOnClickListener {
                myOnClickListener.onMyItemClick(result.id!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRickAndMortyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface MyOnClickListener{
        fun onMyItemClick(id: Int)
    }

}