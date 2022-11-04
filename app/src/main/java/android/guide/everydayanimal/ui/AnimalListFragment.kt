package android.guide.everydayanimal.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.guide.everydayanimal.R
import android.guide.everydayanimal.databinding.FragmentAnimalListBinding
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController


class AnimalListFragment : Fragment() {

    //    so the viewModel lifecycle will be match the activity host
    private val viewModel: AnimalViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentAnimalListBinding.inflate(inflater)
        binding.lifecycleOwner = this // for live data
        binding.viewModel = viewModel // match this viewModel with one of the xml
        binding.recyclerView.adapter = AnimalListAdapter(AnimalListener {
                animal -> viewModel.onAnimalClicked(animal) // if one card(item) is clicked, it will sends to detail fragment with proper object
            findNavController() // start navigate
                .navigate(R.id.action_animalListFragment_to_animalDetailFragment)
        })

        // setting swipe
        binding.swiperefresh.setOnRefreshListener {
//            set the swipe to false after refresh, so load icon won't be displayed all the time
            binding.swiperefresh.isRefreshing = false
//            reGenerate animal data
            viewModel.getAnimalsList()
        }
        return binding.root
    }
}