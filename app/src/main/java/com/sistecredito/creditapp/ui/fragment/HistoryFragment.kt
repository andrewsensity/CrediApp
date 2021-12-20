package com.sistecredito.creditapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.sistecredito.creditapp.R
import com.sistecredito.creditapp.databinding.FragmentHistoryBinding
import com.sistecredito.creditapp.ui.adapter.HistotryAdapter

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var listCC: MutableList<String>
    private val args: HistoryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val view = binding.root

        val cc = args.cc
        listCC.add(cc)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        val adapter = HistotryAdapter(listCC)
        binding.recyclerviewHistory.adapter = adapter
        binding.recyclerviewHistory.layoutManager = LinearLayoutManager(context)

            binding.goToDetail.setOnClickListener {
            navController.navigate(
                R.id.action_historyFragment_to_detailFragment
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}