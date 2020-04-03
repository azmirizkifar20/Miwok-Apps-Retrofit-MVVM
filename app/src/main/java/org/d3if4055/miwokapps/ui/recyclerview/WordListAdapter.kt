package org.d3if4055.miwokapps.ui.recyclerview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if4055.miwokapps.R
import org.d3if4055.miwokapps.data.WordList
import org.d3if4055.miwokapps.databinding.RecyclerviewWordlistBinding

@Suppress("SpellCheckingInspection")
class WordListAdapter(
    private val wordList: List<WordList>,
    private val background: String
) : RecyclerView.Adapter<WordListAdapter.WordListViewHolder>() {

    var listener: RecyclerViewClickListener? = null

    inner class WordListViewHolder(
        val recyclerviewWordlistBinding: RecyclerviewWordlistBinding
    ) : RecyclerView.ViewHolder(recyclerviewWordlistBinding.root)

    override fun getItemCount() = wordList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = WordListViewHolder(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.recyclerview_wordlist, parent, false)
    )

    override fun onBindViewHolder(holder: WordListViewHolder, position: Int) {
        holder.recyclerviewWordlistBinding.listWordList.setBackgroundColor(Color.parseColor(background))
        holder.recyclerviewWordlistBinding.tvInggris.text = wordList[position].defaultWord
        holder.recyclerviewWordlistBinding.tvMiwok.text = wordList[position].miwokWord

        // image
        if (wordList[position].image == null) {
            Glide.with(holder.itemView.context).clear(holder.recyclerviewWordlistBinding.image)
            holder.recyclerviewWordlistBinding.image.setImageDrawable(null)
            holder.recyclerviewWordlistBinding.image.visibility = View.GONE
        } else {
            Glide.with(holder.itemView.context)
                .load("http://dif.indraazimi.com/miwok/${wordList[position].image}")
                .placeholder(R.drawable.ic_launcher_foreground)
                .dontAnimate()
                .into(holder.recyclerviewWordlistBinding.image)
        }

        // onclick
        holder.recyclerviewWordlistBinding.listWordList.setOnClickListener {
            listener?.onRecyclerViewItemWordListClicked(it, wordList[position])
        }
    }
}