package com.example.asteroidradar
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.asteroidradar.databinding.AsteroidItemBinding
import com.example.asteroidradar.models.Asteroid
import com.example.asteroidradar.network.AsteroidProperty

class Adapter(private val asteroids: List<AsteroidProperty>, private val onItemClicked: (AsteroidProperty) -> Unit): RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder(val binding: AsteroidItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(asteroid: AsteroidProperty, onItemClicked: (AsteroidProperty) -> Unit){
            binding.tvName.text = asteroid.name
            binding.tvDate.text = asteroid.closeApproachData.first().closeApproachData
            if (asteroid.isPotentiallyHazardous) {
                binding.ivHazardous.setImageResource(R.drawable.ic_neutral)
                binding.ivHazardous.contentDescription = "Potentially hazardous"
            } else {
                binding.ivHazardous.setImageResource(R.drawable.ic_happy)
                binding.ivHazardous.contentDescription = "Not hazardous"
            }
            binding.root.setOnClickListener{
                onItemClicked(asteroid)
            }
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(AsteroidItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(asteroids[position], onItemClicked)
    }

    override fun getItemCount(): Int = asteroids.size


}