package org.d3if4055.miwokapps.ui.wordlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import org.d3if4055.miwokapps.MainActivity

import org.d3if4055.miwokapps.R
import org.d3if4055.miwokapps.data.Miwok
import org.d3if4055.miwokapps.databinding.FragmentWordListBinding
import org.d3if4055.miwokapps.utils.RecyclerViewClickListener
import org.d3if4055.miwokapps.ui.miwok.MiwokViewModel

@Suppress("SpellCheckingInspection")
class WordListFragment : Fragment(),
    RecyclerViewClickListener {

    private lateinit var binding: FragmentWordListBinding
    private lateinit var viewModel: MiwokViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        judul()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_word_list, container, false)
        viewModel = ViewModelProvider(this).get(MiwokViewModel::class.java)
        binding.lifecycleOwner = this
        binding.miwokVm = viewModel

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            val listData = mutableListOf<Miwok>()
            val category = arguments?.getString("category")

            viewModel.data.observe(viewLifecycleOwner, Observer {
                it.map {  miwok ->
                    if (miwok.category == category) {
                        listData.add(miwok)
                    }
                }

                val adapter =
                    WordListAdapter(listData)
                val recyclerview = binding.rvWordList
                recyclerview.adapter = adapter
                recyclerview.layoutManager = LinearLayoutManager(this.requireContext())
                adapter.listener = this
            })

            viewModel.response.observe(viewLifecycleOwner, Observer {
                if (it.isNotEmpty()) {
                    Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
                }
            })

        }

    }

    override fun onRecyclerViewItemWordListClicked(view: View, miwok: Miwok) {
        super.onRecyclerViewItemWordListClicked(view, miwok)
        Snackbar.make(requireView(), miwok.miwokWord, Snackbar.LENGTH_SHORT).show()
    }

    private fun judul() {
        val getActivity = activity!! as MainActivity
        getActivity.supportActionBar?.title = arguments!!.getString("category")
    }

}
