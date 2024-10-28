
package com.example.ejercicio_git


interface CursoView {
    fun mostrarCurso(cursos: List<Model>)
    fun mostrarMensaje(mensaje: String)
}

class CursoPresenter(private val view: CursoView, private val dbHelper: DatabaseCurso) {

    fun agregarCurso(curso: Model) {
        val result = dbHelper.agregarCurso(curso)
        if (result > 0) {
            view.mostrarMensaje("Curso agregado correctamente")
        } else {
            view.mostrarMensaje("Error al agregar curso")
        }
    }

    fun editarCurso(curso: Model) {
        val result = dbHelper.editarCurso(curso)
        if (result > 0) {
            view.mostrarMensaje("Curso editado correctamente")
        } else {
            view.mostrarMensaje("Error al editar curso")
        }
    }

    fun eliminarCurso(codigo: String) {
        val result = dbHelper.eliminarCurso(codigo)
        if (result > 0) {
            view.mostrarMensaje("Curso eliminado correctamente")
        } else {
            view.mostrarMensaje("Error al eliminar curso")
        }
    }

    fun mostarCursos() {
        val cursos = dbHelper.mostarCursos()
        view.mostrarCurso(cursos)
    }
}
