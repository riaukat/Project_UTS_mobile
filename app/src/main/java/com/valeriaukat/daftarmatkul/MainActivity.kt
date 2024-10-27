package com.valeriaukat.daftarmatkul

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import com.valeriaukat.daftarmatkul.data.Datasource
import com.valeriaukat.daftarmatkul.model.Daftar
import com.valeriaukat.daftarmatkul.ui.theme.DaftarMatkulTheme

class MainActivity : ComponentActivity() { // Kelas utama yang mewarisi ComponentActivity
    @OptIn(ExperimentalMaterial3Api::class) // Menggunakan API eksperimental dari Material3
    override fun onCreate(savedInstanceState: Bundle?) { // Metode yang dipanggil saat aktivitas dibuat
        super.onCreate(savedInstanceState)
        setContent { // Mengatur konten tampilan menggunakan Jetpack Compose
            DaftarMatkulTheme { // Menerapkan tema aplikasi
                val navController = rememberNavController() // Inisialisasi NavController untuk navigasi
                Scaffold( // Membuat dasar layout dengan App Bar dan konten
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = { Text("Daftar Mata Kuliah") } // Judul App Bar
                        )
                    }
                ) { paddingValues -> // Menangani padding untuk konten
                    Surface(
                        modifier = Modifier
                            .fillMaxSize() // Mengisi seluruh ukuran tampilan
                            .padding(paddingValues), // Menambahkan padding
                        color = MaterialTheme.colorScheme.background // Warna latar belakang
                    ) {
                        NavHost(navController = navController, startDestination = "main") { // Mengatur host navigasi
                            composable("main") {
                                MataKuliahApp(navController) // Menampilkan daftar mata kuliah
                            }
                            composable("detail/{id}") { backStackEntry -> // Menavigasi ke detail mata kuliah
                                val mataKuliahId = backStackEntry.arguments?.getString("id") // Mengambil ID mata kuliah dari argumen
                                DetailScreen(mataKuliahId) // Menampilkan layar detail
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MataKuliahApp(navController: NavController) { // Fungsi untuk menampilkan daftar mata kuliah
    MataKuliahList(
        mataKuliahList = Datasource().loadMataKuliah(), // Memuat daftar mata kuliah dari datasource
        navController = navController // Mengoper navController untuk navigasi
    )
}

@Composable
fun MataKuliahList(mataKuliahList: List<Daftar>, navController: NavController, modifier: Modifier = Modifier) { // Fungsi untuk menampilkan daftar mata kuliah
    LazyColumn(modifier = modifier.padding(8.dp)) { // Menggunakan LazyColumn untuk daftar yang dapat digulir
        items(mataKuliahList) { mataKuliah -> // Iterasi melalui daftar mata kuliah
            MataKuliahCard(
                mataKuliah = mataKuliah, // Mengoper mata kuliah saat ini
                navController = navController, // Mengoper navController
                modifier = Modifier.padding(8.dp) // Menambahkan padding pada kartu
            )
        }
    }
}

@Composable
fun DetailScreen(mataKuliahId: String?) { // Fungsi untuk menampilkan detail mata kuliah
    val mataKuliah = mataKuliahId?.let { id -> // Mencari mata kuliah berdasarkan ID
        Datasource().loadMataKuliah().find { it.id.toString() == id }
    }

    Column(modifier = Modifier.padding(16.dp)) { // Mengatur layout kolom untuk detail
        Text(
            text = mataKuliah?.let { LocalContext.current.getString(it.stringResourceId) } ?: "Unknown", // Menampilkan nama mata kuliah
            style = MaterialTheme.typography.headlineMedium // Mengatur gaya teks
        )
        Spacer(modifier = Modifier.height(8.dp)) // Menambahkan spasi antara elemen
        mataKuliah?.let { // Jika mata kuliah ditemukan
            Text(text = "Kode: ${it.kode}", style = MaterialTheme.typography.bodyMedium) // Menampilkan kode mata kuliah
            Text(text = "Jam: ${it.jam}", style = MaterialTheme.typography.bodyMedium) // Menampilkan jam mata kuliah
            Text(text = "Dosen: ${it.dosen}", style = MaterialTheme.typography.bodyMedium) // Menampilkan dosen pengampu
        } ?: run {
            Text(text = "No details available", style = MaterialTheme.typography.bodyMedium) // Pesan jika tidak ada detail
        }
    }
}

@Composable
fun MataKuliahCard(mataKuliah: Daftar, navController: NavController, modifier: Modifier = Modifier) { // Fungsi untuk menampilkan kartu mata kuliah
    Card(modifier = modifier.fillMaxWidth(), onClick = { // Menggunakan Card untuk menampilkan informasi mata kuliah
        navController.navigate("detail/${mataKuliah.id}") // Navigasi ke detail mata kuliah saat kartu diklik
    }) {
        Column {
            Image(
                painter = painterResource(mataKuliah.imageResourceId), // Mengambil gambar dari resource
                contentDescription = stringResource(mataKuliah.stringResourceId), // Deskripsi konten gambar
                modifier = Modifier
                    .fillMaxWidth() // Mengisi lebar kartu
                    .height(194.dp), // Mengatur tinggi gambar
                contentScale = ContentScale.Crop // Mengatur skala konten gambar
            )
            Text(
                text = LocalContext.current.getString(mataKuliah.stringResourceId), // Menampilkan nama mata kuliah
                modifier = Modifier.padding(16.dp), // Menambahkan padding
                style = TextStyle(fontSize = 18.sp) // Mengatur ukuran teks
            )
        }
    }
}

@Preview
@Composable
private fun MataKuliahCardPreview() { // Fungsi untuk menampilkan preview kartu mata kuliah
    val dummyMataKuliah = Daftar(1, R.string.english3, R.drawable.english, "I41919K", "13:00 - 14:40", "Siska Lidya Revianti, S.Pd., M.Hum.") // Contoh mata kuliah dummy
    MataKuliahCard(mataKuliah = dummyMataKuliah, navController = rememberNavController(), modifier = Modifier.padding(8.dp)) // Menampilkan kartu mata kuliah dengan navController dummy
}