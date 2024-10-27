package com.valeriaukat.daftarmatkul.model

data class Daftar(
    val id: Int,                // ID unik untuk mata kuliah
    val stringResourceId: Int, // Resource ID untuk nama mata kuliah
    val imageResourceId: Int,   // Resource ID untuk gambar mata kuliah
    val kode: String,           // Kode mata kuliah
    val jam: String,            // Jam mata kuliah
    val dosen: String           // Dosen pengampu
)