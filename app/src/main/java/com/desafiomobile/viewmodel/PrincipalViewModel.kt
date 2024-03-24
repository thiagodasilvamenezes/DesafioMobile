package com.desafiomobile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PrincipalViewModel : ViewModel() {

    private val _apelidoValido = MutableLiveData<Boolean>()
    val apelidoValido: LiveData<Boolean> = _apelidoValido

    fun validarApelido(apelido: String) {
        _apelidoValido.value = apelido.matches("^[a-zA-Z0-9]{3,20}$".toRegex())
    }
}