package ru.kartyshova.androidtv.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import ru.kartyshova.androidtv.databinding.FragmentAllChannelsBinding
import ru.kartyshova.androidtv.presentation.main.MainFragmentDirections

class AllChannelsFragment : Fragment() {

    private var fragmentAllChannelsBinding: FragmentAllChannelsBinding? = null
    private val viewModel: ChannelsViewModel by viewModel()
    private val navController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentAllChannelsBinding = FragmentAllChannelsBinding.inflate(inflater, container, false)
        return fragmentAllChannelsBinding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.status.observe(viewLifecycleOwner) {
            fragmentAllChannelsBinding?.allChannels?.adapter =
                CardChannelItemAdapter(
                    it,
                    onClickStar = {
                        viewModel.addFavorites(it)
                    },
                    onClickStream = {
                        navController.navigate(MainFragmentDirections.actionAllChannelsFragmentToPlayerFragment(it))

                    }
                )
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentAllChannelsBinding = null
    }
}