package ru.kartyshova.androidtv.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.kartyshova.androidtv.databinding.FragmentFavoritesBinding
import ru.kartyshova.androidtv.presentation.main.MainFragmentDirections

class FavoritesFragment : Fragment() {

    private var fragmentFavoritesBinding: FragmentFavoritesBinding? = null
    private val viewModel: FavoriteViewModel by viewModel()
    private val navController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentFavoritesBinding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return fragmentFavoritesBinding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.status.observe(viewLifecycleOwner) {
            fragmentFavoritesBinding?.favorites?.adapter =
                CardChannelItemAdapter(it,
                    onClickStar = {
                        viewModel.updateFavorite(it)
                    },
                    onClickStream = {
                        navController.navigate(
                            MainFragmentDirections.actionAllChannelsFragmentToPlayerFragment(it)
                        )
                    })
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentFavoritesBinding = null
    }
}