package com.example.asteroidradar

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.asteroidradar.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivInfo.setOnClickListener{
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder
                .setMessage("The astronomical unit (au) is a unit of length, roughly the distance from the Earth to the Sun and equal to about 150 million kilometers (93 million miles)")
                .setNegativeButton("Accept") { dialog, which ->
                    dialog.dismiss()
                }
            builder.create().show()
        }
        binding.tvDate.text = args.asteroid.closeApproachDate
        binding.tvMagnitude.text = "${args.asteroid.absoluteMagnitude} au"
        binding.tvDiameter.text = "${args.asteroid.diameter.toString()} km"
        binding.tvVelocity.text = "${args.asteroid.relativeVelocity.toString()} km/s"
        binding.tvDistance.text = "${args.asteroid.distance.toString()} au"
        if (args.asteroid.isPotentiallyHazardous) {
            binding.tvHazardous.text =getString(R.string.potentially_hazardous)
            binding.tvHazardous.setTextColor(resources.getColor(R.color.red))
        } else {
            binding.tvHazardous.text =getString(R.string.not_hazardous)
            binding.tvHazardous.setTextColor(resources.getColor(R.color.white))
        }

    }
}