package com.sistecredito.creditapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.sistecredito.creditapp.data.model.Users
import com.sistecredito.creditapp.databinding.FragmentHistoryBinding
import com.sistecredito.creditapp.ui.adapter.HistoryAdapter

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var listCC: MutableList<Users>
    private var listMutable: MutableList<Users> = mutableListOf()
    private val args: HistoryFragmentArgs by navArgs()
    private val args2: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

//        val cc = args.cc
//        val credit = args.credit
        listCC = args.listData.toMutableList()

        val adapter = HistoryAdapter(listCC)
        binding.recyclerviewHistory.adapter = adapter
        binding.recyclerviewHistory.layoutManager = LinearLayoutManager(binding.root.context)
        adapter.setOnItemClickListener(object : HistoryAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val list: Array<Users> = listMutable.toTypedArray()
                val action = HistoryFragmentDirections.actionHistoryFragmentToDetailFragment(list)
                navController.navigate(action)
            }
        })

//        binding.goToDetail.setOnClickListener {
//            val list: Array<Users> = listMutable.toTypedArray()
//            val action = HistoryFragmentDirections.actionHistoryFragmentToDetailFragment(list)
//            navController.navigate(action)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}