package com.example.convertor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.convertor.ui.theme.ConvertorTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConvertUI(
    vm : MyViewModel = viewModel()
) {
    val uiState = vm.uiState.collectAsState()
    Column(
        verticalArrangement =Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "Give your Input",
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier)

        Spacer(modifier = Modifier.padding(30.dp))

        OutlinedTextField(
            value = uiState.value.userData,
            onValueChange = {vm.onTextChange(it)}
        )

        Spacer(modifier = Modifier.padding(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Switch(
                checked = uiState.value.isFahrenheit,
                onCheckedChange = { vm.updateSwitchState(it)}
            )
            Spacer(modifier = Modifier.padding(50.dp))
            Text(text = if (uiState.value.isFahrenheit) "Fahrenheit" else "Celsius",
                style = MaterialTheme.typography.bodyMedium)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(onClick = { vm.onUserSubmit() }) {
                Text(text = "Convert")
            }
        }
        Spacer(modifier = Modifier.padding(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = uiState.value.result,
                style = MaterialTheme.typography.headlineMedium)
            Text(text = if (uiState.value.isFahrenheit) "°C" else "°F")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConvertorUIPreview() {
    ConvertorTheme {
        ConvertUI()
    }
}