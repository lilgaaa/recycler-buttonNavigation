package com.example.sqlitepe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqlitepe.databinding.FragmentSecondBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {

    private lateinit var db: NoteDataBaseHelper
    private lateinit var notesAdapter: NotasAdapter
    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño para este fragmento
        binding = FragmentSecondBinding.inflate(inflater, container, false)

        // Inicializar la base de datos
        db = NoteDataBaseHelper(requireContext())

        // Configurar el RecyclerView
        binding.notesRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Crear el adaptador con una lista vacía inicialmente
        notesAdapter = NotasAdapter(emptyList(), requireContext())
        binding.notesRecyclerView.adapter = notesAdapter

        // Cargar los datos de la base de datos y actualiza el adaptador
        updateNotes()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        // Actualizar los datos cuando el fragmento se reanuda
        updateNotes()
    }

    private fun updateNotes() {
        val notes = db.getAllNotes() // Obtener las notas de la base de datos
        notesAdapter.refreshData(notes) // Actualizar los datos en el adaptador
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}