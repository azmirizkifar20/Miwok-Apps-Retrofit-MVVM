package org.d3if4055.miwokapps.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import org.d3if4055.miwokapps.MainActivity

import org.d3if4055.miwokapps.R
import org.d3if4055.miwokapps.data.WordList
import org.d3if4055.miwokapps.databinding.FragmentWordListBinding
import org.d3if4055.miwokapps.ui.recyclerview.RecyclerViewClickListener
import org.d3if4055.miwokapps.ui.recyclerview.WordListAdapter

@Suppress("SpellCheckingInspection")
class WordListFragment : Fragment(),
    RecyclerViewClickListener {

    private lateinit var binding: FragmentWordListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        judul()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_word_list, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            val wordList = arguments?.getParcelableArrayList<WordList>("wordlist")
            val background = arguments?.getString("background")

            val adapter = WordListAdapter(
                wordList!!,
                background!!
            )
            val recyclerview = binding.rvWordList
            recyclerview.adapter = adapter
            recyclerview.layoutManager = LinearLayoutManager(this.requireContext())

            adapter.listener = this
        }

    }

    override fun onRecyclerViewItemWordListClicked(view: View, wordList: WordList) {
        super.onRecyclerViewItemWordListClicked(view, wordList)
        Snackbar.make(requireView(), wordList.miwokWord.toString(), Snackbar.LENGTH_SHORT).show()
    }

    private fun judul() {
        val getActivity = activity!! as MainActivity
        getActivity.supportActionBar?.title = arguments!!.getString("category")
    }

}
