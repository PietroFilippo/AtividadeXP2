package com.example.atividadexp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Munchkin()
        }
    }
}

@Composable
fun GerenciadorJogadores(){

    var jogadores = remember { mutableStateListOf<Jogador>()}

    if (jogadores.isEmpty()) {
        for (i in 1..6) {
            jogadores.add(Jogador(nome = "Jogador $i"))
        }
    }

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        items(jogadores) { jogador ->
            CartaJogador(jogador)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartaJogador(jogador: Jogador) {

    var nome by remember { mutableStateOf(jogador.nome) }
    var level by remember { mutableStateOf(jogador.level) }
    var equipamento by remember { mutableStateOf(jogador.equipamento) }
    var modificadores by remember { mutableStateOf(jogador.modificadores) }

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

        TextField(
            value = nome,
            onValueChange = {
                nome = it
                jogador.nome = it
            },
            label = { Text(text = "Nome do Jogador") }
        )

        Text(text = "Level: $level")

        Row {
            Button(onClick = {
                if (level < 10) {
                    level++
                    jogador.level = level
                }
            }) {
                Text(text = "+")
            }

            Button(onClick = {
                if (level > 1) {
                    level--
                    jogador.level = level
                }
            }) {
                Text(text = "-")
            }
        }

        Text(text = "Bônus de equipamento: $equipamento")

        Row {
            Button(onClick = {
                if (equipamento < 99) {
                    equipamento++
                    jogador.equipamento = equipamento
                }
            }) {
                Text(text = "+")
            }

            Button(onClick = {
                if (equipamento > 0) {
                    equipamento--
                    jogador.equipamento = equipamento
                }
            }) {
                Text(text = "-")
            }
        }

        Text(text = "Modificadores: $modificadores")

        Row {
            Button(onClick = {
                if (modificadores < 10) {
                    modificadores++
                    jogador.modificadores = modificadores
                }
            }) {
                Text(text = "+")
            }

            Button(onClick = {
                if (modificadores > -20) {
                    modificadores--
                    jogador.modificadores = modificadores
                }
            }) {
                Text(text = "-")
            }
        }

        Text(text = "Poder total: ${jogador.poder}")
    }
}

@Composable
fun Munchkin(){
    GerenciadorJogadores()
}

@Preview(showBackground = true)
@Composable
fun LayoutPreview(){
    Munchkin()
}