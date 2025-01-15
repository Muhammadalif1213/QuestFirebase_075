package com.example.Firebase.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.Firebase.model.Mahasiswa
import com.example.Firebase.ui.ViewModel.DetailUiState
import com.example.Firebase.ui.ViewModel.DetailViewModel
import com.example.Firebase.ui.ViewModel.PenyediaViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailView(
    modifier: Modifier = Modifier,
    detailViewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onEditClick: (String) -> Unit,
    navigateBack: () -> Unit,
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text("Detail Mahasiswa")},
            )
        },
    ){
            innerPadding ->
        StatusDetail(
            detailUiState = detailViewModel.detailMhsUiState,
            retryAction = {detailViewModel.getMhsById()},
            modifier = Modifier.padding(innerPadding),
            onEditClick = onEditClick,
        )
    }
}

@Composable
fun StatusDetail(
    detailUiState: DetailUiState,
    modifier: Modifier = Modifier,
    onEditClick: (String) -> Unit = {},
    retryAction: () -> Unit,
    onDeleteClick: (Mahasiswa) -> Unit = {}
){
    when(detailUiState) {
        is DetailUiState.Success ->{
            DetailMhsLayout(
                mahasiswa = detailUiState.mahasiswa,
                onEditClick = {onEditClick(it)},
                modifier = modifier
            )
        }
        is DetailUiState.Loading -> OnLoading(modifier = Modifier)
        is DetailUiState.Error -> onErr(message = "", retryAction = retryAction, modifier = Modifier)
    }
}


@Composable
fun DetailMhsLayout(
    modifier: Modifier = Modifier,
    mahasiswa: Mahasiswa,
    onEditClick: (String) -> Unit = {},
) {
    Column (
        modifier = Modifier
            .padding(16.dp)
            .padding(top = 70.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        ItemDetailMhs(
            mahasiswa = mahasiswa,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Button(
            onClick = {
                onEditClick(mahasiswa.nim)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding()
        ) {
            Text(text = "Update")
        }
    }
}


@Composable
fun ItemDetailMhs(
    modifier: Modifier = Modifier,
    mahasiswa: Mahasiswa,
){
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column (
            modifier = Modifier.padding(16.dp)
        ) {
            ComponentDetailMhs(judul = "NIM", isinya = mahasiswa.nim)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailMhs(judul = "Nama Mahasiswa", isinya = mahasiswa.nama)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailMhs(judul = "Jenis Kelamin", isinya = mahasiswa.jenis_kelamin)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailMhs(judul = "Alamat", isinya = mahasiswa.alamat)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailMhs(judul = "kelas", isinya = mahasiswa.kelas)
            Spacer(modifier = Modifier.padding(4.dp))

            ComponentDetailMhs(judul = "Angkatan", isinya = mahasiswa.angkatan)
            Spacer(modifier = Modifier.padding(4.dp))

            Spacer(modifier = Modifier.padding(4.dp))

        }
    }
}

@Composable
fun ComponentDetailMhs(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String,
){
    Column (
        modifier = modifier.fillMaxWidth(),

        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul: ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}