package ru.kartyshova.androidtv.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class PageAdapter(
    fragmentManager: FragmentManager,
    private val pageList: List<Pair<String, Fragment>>
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int = pageList.size

    override fun getItem(position: Int): Fragment = pageList[position].second

    override fun getPageTitle(position: Int): CharSequence = pageList[position].first
}