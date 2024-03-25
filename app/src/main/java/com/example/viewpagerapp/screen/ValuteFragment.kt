package com.example.viewpagerapp.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viewpagerapp.adapter.ValuteAdapter
import com.example.viewpagerapp.api.valute.ApiRequests
import com.example.viewpagerapp.databinding.FragmentValuteBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ValuteFragment : Fragment() {

    lateinit var binding: FragmentValuteBinding
    private val scope = CoroutineScope(Dispatchers.IO)
    private val apiRequests = ApiRequests(scope)
    private lateinit var adapter: ValuteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentValuteBinding.inflate(layoutInflater)
        adapter = ValuteAdapter()
        scope.launch(Dispatchers.Main) {
            var task = apiRequests.getMoneyAsync().await()
            binding.rcv.layoutManager = LinearLayoutManager(requireContext())
            binding.rcv.adapter = adapter
            task.Valute.values.forEach {
                adapter.addOper(it)
            }
            task.Valute.keys.forEach {
                println(it)
            }
        }
        return binding.root
    }
}