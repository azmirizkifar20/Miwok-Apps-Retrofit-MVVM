package org.d3if4055.miwokapps.ui.miwok

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import org.d3if4055.miwokapps.MainActivity

import org.d3if4055.miwokapps.R
import org.d3if4055.miwokapps.data.Miwok
import org.d3if4055.miwokapps.databinding.FragmentMiwokBinding
import org.d3if4055.miwokapps.utils.RecyclerViewClickListener

@Suppress("SpellCheckingInspection")
class MiwokFragment : Fragment(),
    RecyclerViewClickListener {

    private lateinit var binding: FragmentMiwokBinding
    private lateinit var viewModel: MiwokViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        judul()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_miwok, container, false)
        binding.lifecycleOwner = this

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = MiwokViewModel.Factory(activity!!.application)
        viewModel = ViewModelProvider(this, factory).get(MiwokViewModel::class.java)
        binding.miwokVm = viewModel

        viewModel.listMiwok.observe(viewLifecycleOwner, Observer {
            val dataFix = it.distinctBy { miwok -> miwok.category }
            val adapter = MiwokAdapter(dataFix)
            val recyclerview = binding.rvMiwok
            recyclerview.adapter = adapter
            recyclerview.layoutManager = LinearLayoutManager(this.requireContext())

            // set listener
            adapter.listener = this
        })

//        viewModel.response.observe(viewLifecycleOwner, Observer {
//            if (it.isNotEmpty()) {
//                Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
//            }
//        })
    }

    override fun onRecyclerViewItemMiwokClicked(view: View, miwok: Miwok) {
        val bundle = Bundle()
        bundle.putString("category", miwok.category)
        view.findNavController().navigate(R.id.action_miwokFragment_to_wordListFragment, bundle)
    }

    private fun judul() {
        val getActivity = activity!! as MainActivity
        getActivity.supportActionBar?.title = "Miwok (6706184055)"
    }

}
