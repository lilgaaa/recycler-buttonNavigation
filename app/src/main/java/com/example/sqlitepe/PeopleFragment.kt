package com.example.sqlitepe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class PeopleFragment : Fragment() {

    private lateinit var db: NoteDataBaseHelper
    private lateinit var linkEditText: EditText
    private lateinit var contentEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_people, container, false)

        // Inicializar la base de datos
        db = NoteDataBaseHelper(requireContext())

        // Obtener referencias a las vistas
        linkEditText = view.findViewById(R.id.editTextLink)
        contentEditText = view.findViewById(R.id.EditTextDescripcion)
        saveButton = view.findViewById(R.id.buttonSubir)

        // Configurar el evento de clic en el botón
        saveButton.setOnClickListener {
            val link = linkEditText.text.toString()
            val content = contentEditText.text.toString()
            val note = Nota(0, link, content)
            db.insertNotes(note)

            Toast.makeText(requireContext(), "Note saved", Toast.LENGTH_SHORT).show()


        }

        return view
    }
}
