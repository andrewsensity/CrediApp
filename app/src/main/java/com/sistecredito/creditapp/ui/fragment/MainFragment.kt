package com.sistecredito.creditapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sistecredito.creditapp.data.model.Users
import com.sistecredito.creditapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private var listMutable: MutableList<Users> = mutableListOf()
    private var interest = 1.1
    private val numberFee = 10

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

        binding.calculateBtn.setOnClickListener {
            calculateInterest()
            addListRecycler()
        }

        binding.clearBtn.setOnClickListener {
            clearFields()
        }

        binding.goToHistory.setOnClickListener {
            val list: Array<Users> = listMutable.toTypedArray()
            val action = MainFragmentDirections.actionMainFragmentToHistoryFragment(list)
            navController.navigate(action)
        }
    }

    private fun calculateInterest() {
        val creditValue = binding.etCredit.text.toString()
        val fee = binding.etFee.text.toString()
        val listFee = mutableListOf<Double>()

        for (i in 0..numberFee) {
            var feeWI = creditValue.toDouble() / fee.toDouble()
            feeWI *= interest
            interest -= 0.01

            listFee.add(feeWI)
        }
//        for (i in listFee) {
//            binding.resultValueFee.text = "$i ---------- $listFee"
//        }

        binding.resultValueFee.text = "El valor de las cuotas es de: $$listFee"

        with(binding) {
            ilCredit.visibility = View.GONE
            ilCc.visibility = View.GONE
            ilFee.visibility = View.GONE
            clearBtn.visibility = View.GONE
            calculateBtn.visibility = View.GONE
            message.visibility = View.VISIBLE
        }
    }

    private fun addListRecycler() {
        val creditValue = binding.etCredit.text.toString()
        val cc = binding.etCc.text.toString()
        val fee = binding.etFee.text.toString()

        listMutable.add(Users(cc.toInt(), creditValue.toInt(), fee.toInt()))
    }

    private fun clearFields() {
        with(binding) {
            etCredit.setText("")
            etCc.setText("")
            etFee.setText("")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}