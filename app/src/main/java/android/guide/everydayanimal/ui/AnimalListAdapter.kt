package android.guide.everydayanimal.ui

import android.guide.everydayanimal.databinding.ListViewItemBinding
import android.guide.everydayanimal.network.Animal
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class AnimalListAdapter(val clickListener: AnimalListener):
    ListAdapter<Animal, AnimalListAdapter.AnimalViewHolder>(DiffCallback){


    class AnimalViewHolder(var binding: ListViewItemBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(clickListener: AnimalListener, animal: Animal){
            binding.animal = animal
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    /*     For manging the items, so if some items changes, the exacts items will be reloading,
            the rest of items won't changing and reloading
            so no need to update the whole recyclerView     */
    companion object DiffCallback: DiffUtil.ItemCallback<Animal>(){
        override fun areItemsTheSame(oldItem: Animal, newItem: Animal): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Animal, newItem: Animal): Boolean {
            return oldItem.image_link == newItem.image_link
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AnimalViewHolder(
            ListViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = getItem(position)
        holder.bind(clickListener, animal)
    }
}

class AnimalListener(val clickListener: (animal: Animal) -> Unit){
    fun onClick(animal: Animal) = clickListener(animal)
}