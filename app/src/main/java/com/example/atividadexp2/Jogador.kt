package com.example.atividadexp2

data class Jogador (
    var nome: String,
    var level: Int = 1,
    var equipamento: Int = 0,
    var modificadores: Int = 0
){
    val poder: Int
        get () = level + equipamento + modificadores
}