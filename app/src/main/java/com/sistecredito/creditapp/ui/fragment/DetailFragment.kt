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
import com.sistecredito.creditapp.R
import com.sistecredito.creditapp.data.model.Users
import com.sistecredito.creditapp.databinding.FragmentDetailBinding
import com.sistecredito.creditapp.ui.adapter.DetailsAdapter
import java.text.DecimalFormat

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var listValues: MutableList<Users>
    private var interest = 1.1f
    private val args: DetailFragmentArgs by navArgs()
    private var creditValue = 0
    private var fee = 0

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

        navController = Navigation.findNavController(view)

        listValues = args.listDetails.toMutableList()
        val posVal = args.posicion
        val dec = DecimalFormat("#,###")

        binding.cc.text = listValues[posVal].cc.toString()
        binding.credit.text = "$${dec.format(listValues[posVal].credit)}"
        creditValue = listValues[posVal].credit
        fee = listValues[posVal].fee


        binding.goToHistory.setOnClickListener {
            navController.popBackStack()
        }
        calculateInterest()
    }

    private fun calculateInterest() {

        val listFee = mutableListOf<Float>()

        for (i in 0..fee - 1) {
            var feeWOI = creditValue.toFloat() / fee.toFloat()
            feeWOI *= interest
            interest -= 0.01f

            listFee.add(feeWOI)
        }

        val adapter = DetailsAdapter(listFee)
        binding.recyclerDetails.adapter = adapter
        binding.recyclerDetails.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}