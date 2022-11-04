package android.guide.everydayanimal.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.guide.everydayanimal.R
import android.guide.everydayanimal.databinding.FragmentAnimalDetailBinding
import androidx.fragment.app.activityViewModels


class AnimalDetailFragment : Fragment() {
    private val viewModel : AnimalViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAnimalDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // Inflate the layout for this fragment
        return binding.root
    }

}