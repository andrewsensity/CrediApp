package com.sistecredito.creditapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.sistecredito.creditapp.data.model.Users
import com.sistecredito.creditapp.databinding.FragmentDetailBinding
import com.sistecredito.creditapp.ui.adapter.FeeAdapter

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var listValues: MutableList<Users>
    private var interest = 1.1f
    private val args: HistoryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //listValues = args.listData.toMutableList()

//        for (i in 0 until listValues.size) {
//            val creditValue = listValues[i].credit
//            val fee = listValues[i].fee
//            val listFee = mutableListOf<Float>()
//
//            for (i in 0..fee) {
//                var feeWOI = creditValue.toFloat() / fee.toFloat()
//                feeWOI *= interest
//                interest -= 0.01f
//
//                listFee.add(feeWOI)
//
//                val adapter = FeeAdapter(listFee)
//                binding.recyclerDetails.adapter = adapter
//                binding.recyclerDetails.layoutManager = LinearLayoutManager(context)
//            }
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}