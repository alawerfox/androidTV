package ru.kartyshova.androidtv.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.google.android.exoplayer2.util.MimeTypes
import ru.kartyshova.androidtv.databinding.FragmentPlayerBinding
import ru.kartyshova.androidtv.db.DomainChannel

class PlayerFragment : Fragment() {

    private var fragmentPlayerBinding: FragmentPlayerBinding? = null
    private var exoPlayer: ExoPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentPlayerBinding = FragmentPlayerBinding.inflate(inflater, container, false)
        return fragmentPlayerBinding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val domainChannel = arguments?.getParcelable<DomainChannel>("arg") ?: return

        initializePlayer(domainChannel.url)
    }

    private fun initializePlayer(stream_url: String) {
        exoPlayer = ExoPlayer.Builder(requireContext())
            .build()


        val mediaItem = MediaItem.Builder()
            .setUri(stream_url)
            .setMimeType(MimeTypes.APPLICATION_MP4)
            .build()


        val mediaSource = ProgressiveMediaSource.Factory(
            DefaultDataSource.Factory(requireContext())
        )
            .createMediaSource(mediaItem)

        exoPlayer!!.apply {
            setMediaSource(mediaSource)
            playWhenReady = true
            seekTo(0, 0L)
            prepare()
        }.also {
            fragmentPlayerBinding?.player?.player = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentPlayerBinding = null
    }
}
