package com.example.littletoothwallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class CadastroEtapa1Fragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflando o layout XML da etapa 1 do cadastro
        return inflater.inflate(R.layout.fragment_cadastro_etapa1, container, false)
    }
}