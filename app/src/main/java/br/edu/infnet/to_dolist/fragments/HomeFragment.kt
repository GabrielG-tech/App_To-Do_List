package br.edu.infnet.to_dolist.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.infnet.to_dolist.R
import br.edu.infnet.to_dolist.TarefasViewModel
import br.edu.infnet.to_dolist.databinding.FragmentHomeBinding
import br.edu.infnet.to_dolist.modelo.TarefaAdapter

class HomeFragment : Fragment() {

    val viewModel: TarefasViewModel by activityViewModels()

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        setup()
        return view
    }

    private fun setup() {
        setupRecyclerView()
        setupObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    val adapter = TarefaAdapter()

    private fun setupRecyclerView() {
        binding.rvHome.adapter = adapter
        binding.rvHome.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    fun setupObservers(){
        viewModel.listaTarefa.observe(viewLifecycleOwner){
            adapter.submitList(
                viewModel.getListaCompleta()
            )
            binding.rvHome.adapter  = adapter
        }
    }

}