package com.example.asteroidradar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil3.load
import com.example.asteroidradar.databinding.FragmentMainBinding
import com.example.asteroidradar.models.Asteroid
import com.example.asteroidradar.network.AsteroidApiService
import com.example.asteroidradar.network.AsteroidProperty
import dagger.hilt.android.AndroidEntryPoint


class MainFragment : Fragment() {
    private val viewModel: AsteroidListViwModel by activityViewModels()
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.asteroids.observe(viewLifecycleOwner) {
            binding.rvRecyclerView.adapter = Adapter(it, onItemClicked = { asteroid ->
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToDetailFragment(Asteroid(
                        name = asteroid.name,
                        closeApproachDate = asteroid.closeApproachData.first().closeApproachData,
                        absoluteMagnitude = asteroid.absoluteMagnitude,
                        diameter = asteroid.diameter.kilometers.estimatedDiameterMax,
                        relativeVelocity = asteroid.closeApproachData.first().relativeVelocity?.kilometersPerSecond.toString(),
                        distance = asteroid.closeApproachData.first().distance?.kilometers.orEmpty(),
                        isPotentiallyHazardous = asteroid.isPotentiallyHazardous,
                        image = viewModel.image.value.orEmpty()
                    ))
                )
            })
        }
        viewModel.image.observe(viewLifecycleOwner) {
            binding.ivImageOfTheDay.load(it)
        }
        viewModel.getAsteroids()
        }
    }
