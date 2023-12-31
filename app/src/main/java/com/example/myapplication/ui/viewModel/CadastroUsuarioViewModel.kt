package com.example.myapplication.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Usuario
import com.example.myapplication.repository.FirebaseAuthRepository
import com.example.myapplication.repository.Resource

class CadastroUsuarioViewModel(private val repository: FirebaseAuthRepository): ViewModel() {

    fun cadastra(usuario: Usuario): LiveData<Resource<Boolean>>{
        return repository.cadastrarUsuario(usuario)
    }

}