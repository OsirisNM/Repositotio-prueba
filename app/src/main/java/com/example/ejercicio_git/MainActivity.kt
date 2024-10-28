package com.example.ejercicio_git

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ejercicio_git.ui.theme.EjercicioGitTheme

class MainActivity : ComponentActivity() {
    private lateinit var presenter: CursoPresenter
    private lateinit var dbHelper: DatabaseCurso

    private lateinit var editCodigo: TextView
    private lateinit var editCurso: TextView
    private lateinit var editCarrera: TextView
    private lateinit var btnAgregar: Button
    private lateinit var btnEditar: Button
    private lateinit var btnEliminar: Button
    private lateinit var btnMostrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view)

        // Inicializa vistas
        editCodigo = findViewById(R.id.editCodigo)
        editCurso = findViewById(R.id.editCurso)
        editCarrera = findViewById(R.id.editCarrera)
        btnAgregar = findViewById(R.id.btnAgregar)
        btnEditar = findViewById(R.id.btnEditar)
        btnEliminar = findViewById(R.id.btnEliminar)
        btnMostrar = findViewById(R.id.btnMostrar)

        dbHelper = DatabaseCurso(this)
        presenter = CursoPresenter(this, dbHelper)

        btnAgregar.setOnClickListener {
            val curso = Model(editCodigo.text.toString(), editCurso.text.toString(), editCarrera.text.toString())
            presenter.agregarCurso(curso)
        }

        btnEditar.setOnClickListener {
            val curso = Model(editCodigo.text.toString(), editCurso.text.toString(), editCarrera.text.toString())
            presenter.editarCurso(curso)
        }

        btnEliminar.setOnClickListener {
            presenter.eliminarCurso(editCodigo.text.toString())
        }

        btnMostrar.setOnClickListener {
            presenter.mostarCursos()
        }
    }

    override fun mostrarCurso(cursos: List<Model>) {
        val cursosStr = cursos.joinToString(separator = "\n") { "Código: ${it.codigo}, Curso: ${it.curso}, Carrera: ${it.carrera}" }
        Toast.makeText(this, cursosStr, Toast.LENGTH_LONG).show()
    }

    override fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}