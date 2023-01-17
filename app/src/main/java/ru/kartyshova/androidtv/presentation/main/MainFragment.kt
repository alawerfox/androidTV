package ru.kartyshova.androidtv.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.kartyshova.androidtv.R
import ru.kartyshova.androidtv.databinding.FragmentMainBinding
import ru.kartyshova.androidtv.presentation.AllChannelsFragment
import ru.kartyshova.androidtv.presentation.FavoritesFragment

class MainFragment : Fragment() {
    private var fragmentMainBinding: FragmentMainBinding? = null

    private val pageAdapter: PageAdapter by lazy {
        val pageList = listOf(
            getString(R.string.all) to AllChannelsFragment(),
            getString(R.string.favorites) to FavoritesFragment(),
        )

        PageAdapter(childFragmentManager, pageList)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false)
        return fragmentMainBinding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentMainBinding?.pager?.adapter = pageAdapter
        fragmentMainBinding?.tabLayout?.setupWithViewPager(fragmentMainBinding?.pager)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentMainBinding = null
    }
}

