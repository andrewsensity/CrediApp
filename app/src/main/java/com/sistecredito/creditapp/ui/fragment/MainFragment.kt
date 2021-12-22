package com.sistecredito.creditapp.ui.fragment

import android.content.Context
import android.os.Bundle
import android.os.TokenWatcher
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.marginTop
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.sistecredito.creditapp.CreditApplication.Companion.prefs
import com.sistecredito.creditapp.R
import com.sistecredito.creditapp.data.model.Users
import com.sistecredito.creditapp.databinding.FragmentMainBinding
import com.sistecredito.creditapp.ui.adapter.FeeAdapter
import java.lang.reflect.Parameter

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var listMutable: MutableList<Users> = mutableListOf()
    private var interest = 1.1f
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //initUI() // SharedPreferences
    }

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

//        binding.calculateBtn.setOnClickListener {
//            calculateInterest()
//            accessToDetail() // SharedPreferences
//            addListRecycler()
//            view.hideKeyboard()
//        }

        binding.clearBtn.setOnClickListener {
            clearFields()
        }

        binding.goToHistory.setOnClickListener {
            val list: Array<Users> = listMutable.toTypedArray()
            val action = MainFragmentDirections.actionMainFragmentToHistoryFragment(list)
            navController.navigate(action)
        }
        binding.etFee.setOnClickListener {
            openOptionMenu(binding.etFee)
            view.hideKeyboard()
        }
        initUI()
    }

    private fun initUI() { // SharedPreferences
        binding.calculateBtn.setOnClickListener {
            accessToDetail()
            calculateInterest()
            addListRecycler()
            view?.hideKeyboard()
        }
    }

    private fun accessToDetail() { // SharedPreferences
        if (binding.etCredit.text.toString().isNotEmpty() &&
            binding.etCc.text.toString().isNotEmpty() &&
            binding.etFee.text.toString().isNotEmpty()
        ) {
            prefs.credit = binding.etCredit.text.toString().toInt()
            prefs.cc = binding.etCc.text.toString().toInt()
            prefs.fee = binding.etFee.text.toString().toInt()
        } else {
            Toast.makeText(context, "Todos los campos deben contener datos", Toast.LENGTH_LONG).show()
        }
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun calculateInterest() {
//        val creditValue = binding.etCredit.text.toString()
//        val fee = binding.etFee.text.toString()
        val creditValue = prefs.credit // SharedPreferences
        val fee = prefs.fee // SharedPreferences

        val listFee = mutableListOf<Float>()

        for (i in 0..fee-1) {
            var feeWOI = creditValue.toFloat() / fee.toFloat()
            feeWOI *= interest
            interest -= 0.01f

            listFee.add(feeWOI)
        }

        val adapter = FeeAdapter(listFee)
        binding.recyclerFee.adapter = adapter
        binding.recyclerFee.layoutManager = LinearLayoutManager(context)

        binding.message.text = "Tu crédito sera diferido a $fee cuotas"

        with(binding) {
            mainImage.visibility    = View.GONE
            message.visibility      = View.VISIBLE
            cardview.visibility     = View.VISIBLE
            spacer.layoutParams     = ViewGroup.LayoutParams(0, 200)
            spacer2.layoutParams    = ViewGroup.LayoutParams(0, 1060)
        }
    }

    private fun addListRecycler() {
//        val creditValue = binding.etCredit.text.toString()
//        val cc = binding.etCc.text.toString()
//        val fee = binding.etFee.text.toString()
        val creditValue = prefs.credit // SharedPreferences
        val cc = prefs.cc // SharedPreferences
        val fee = prefs.fee // SharedPreferences

//        listMutable.add(Users(cc.toInt(), creditValue.toInt(), fee.toInt()))
        listMutable.add(Users(cc, creditValue, fee)) // SharedPreferences
    }

    private fun clearFields() {
        with(binding) {
            etCredit.setText("")
            etCc.setText("")
            etFee.setText("")
        }
    }

    private fun openOptionMenu(it: View?) {
        val popup = PopupMenu(it!!.context, it)
        popup.menuInflater.inflate(R.menu.menu_fee, popup.menu)
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.one -> binding.etFee.setText("1")
                R.id.two -> binding.etFee.setText("2")
                R.id.three -> binding.etFee.setText("3")
                R.id.four -> binding.etFee.setText("4")
                R.id.five -> binding.etFee.setText("5")
                R.id.six -> binding.etFee.setText("6")
                R.id.seven -> binding.etFee.setText("7")
                R.id.eigth -> binding.etFee.setText("8")
                R.id.nine -> binding.etFee.setText("9")
                R.id.ten -> binding.etFee.setText("10")
            }
            true
        }
        popup.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}