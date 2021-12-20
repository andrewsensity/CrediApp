package com.sistecredito.creditapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sistecredito.creditapp.R
import com.sistecredito.creditapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var creditValue: String
    private lateinit var cc: String
    private lateinit var fee: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        binding.goToHistory.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_historyFragment)
        }

        binding.calculateBtn.setOnClickListener {
            creditValue = binding.etCredit.text.toString()
            cc = binding.etCc.text.toString()
            fee = binding.etFee.text.toString()

            var result = 0
            result = creditValue.toInt()/fee.toInt()
            binding.resultValueFee.text = "El valor de las cuotas es de: $$result"

            MainFragmentDirections.actionMainFragmentToHistoryFragment(cc)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}