package com.example.myapplication.model

import android.os.Parcel
import android.os.Parcelable

data class Estado(
    val selecionar: String,
    val acao: String

) : Parcelable {

    // Construtor primário (necessário para Parcelable)
    constructor(parcel: Parcel) : this(
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty()
    )
    // Escreva os campos da sua classe para o Parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(selecionar)
        parcel.writeString(acao)
    }

    // Descreva a classe para o Parcel
    override fun describeContents(): Int {
        return 0
    }

    // Companion object para criar instâncias da classe a partir do Parcel
    companion object CREATOR : Parcelable.Creator<Estado> {
        override fun createFromParcel(parcel: Parcel): Estado {
            return Estado(parcel)
        }

        override fun newArray(size: Int): Array<Estado?> {
            return arrayOfNulls(size)
        }
    }
}
