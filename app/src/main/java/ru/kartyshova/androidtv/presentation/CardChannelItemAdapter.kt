package ru.kartyshova.androidtv.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.kartyshova.androidtv.R
import ru.kartyshova.androidtv.data.ChannelInfo
import ru.kartyshova.androidtv.databinding.CardChannelItemBinding
import ru.kartyshova.androidtv.db.DomainChannel


class CardChannelItemAdapter(
    private val image: List<DomainChannel>,
    private val onClickStar: (DomainChannel) -> Unit,
    private val onClickStream:(DomainChannel) -> Unit
) : RecyclerView.Adapter<CardChannelItemAdapter.CardChannelItemHolder>() {

    inner class CardChannelItemHolder(private val binding: CardChannelItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(channelInfo: DomainChannel) = with(binding) {
            nameChannel.text = channelInfo.name
            nameBroadcast.text = channelInfo.currentTvShow
            Glide.with(logo).load(channelInfo.imageUrl).into(logo)
            star.setOnClickListener {
                onClickStar(channelInfo)
            }
            root.setOnClickListener { onClickStream(channelInfo) }
            if (channelInfo.favorite) star.setImageResource(R.drawable.star)
            else star.setImageResource(R.drawable.star_default)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardChannelItemHolder {
        val binding = CardChannelItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CardChannelItemHolder(binding)
    }

    override fun onBindViewHolder(holder: CardChannelItemHolder, position: Int) {
        val item = image[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return image.size
    }

}
