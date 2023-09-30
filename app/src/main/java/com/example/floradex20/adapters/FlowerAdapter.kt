package com.example.floradex20.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.floradex20.R
import com.example.floradex20.models.Flower
import com.squareup.picasso.Picasso

class FlowerAdapter(val flowers: List<Flower>, private val listener: OnItemClickListener): RecyclerView.Adapter<FlowerAdapter.PlantViewHolder>() {

//    var listen: onItemClickListener
//    this.listen = listener
//    val listen:OnItemClickListener = listener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_flower, parent, false)
        return PlantViewHolder(view)
    }

    override fun getItemCount(): Int {
        return flowers.size
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {

        return holder.bind(flowers[position], holder)
    }


    inner class PlantViewHolder(itemView : View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val photo: ImageView = itemView.findViewById(R.id.flower_photo)
        private val title: TextView = itemView.findViewById(R.id.flower_name)

        init {
            photo.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }

        }

        fun bind(plant: Flower, plantviewer: PlantViewHolder) {
            //Pone foto y nombre de la planta a la tarjeta
            val imageurl: String = plant.image_url
            Picasso.get().load(imageurl).into(photo)
            //Nombre de la planta
            title.text = "Name: " + plant.common_name

        }


    }

    interface OnItemClickListener {

        //Metodo que va a usar el activity pa hacer cosas
        fun onItemClick(position: Int)
    }
}


