package com.valeriaukat.daftarmatkul.data

import com.valeriaukat.daftarmatkul.R
import com.valeriaukat.daftarmatkul.model.Daftar

class Datasource {
    fun loadMataKuliah(): List<Daftar> {
        return listOf(
            Daftar(1, R.string.english3, R.drawable.english, "I41919K", "13:00 - 14:40", "Siska Lidya Revianti,S.Pd., M.Hum."),
            Daftar(2, R.string.datamining, R.drawable.mining, "IF1924T", "07:30 - 10:00", "Dini Fakta Sari,S.T., M.T."),
            Daftar(3, R.string.gkv, R.drawable.grafika, "IF1925T", "10:00 - 11:40", "Basuki Heri Winarno,S.Pd., M.Kom."),
            Daftar(4, R.string.infbigdata, R.drawable.infrastruktur, "IF1923T", "15:00 - 17:30", "S.Kom.Bagas Triaji, M.Kom."),
            Daftar(5, R.string.manpro, R.drawable.manpro, "IF1920T", "13:00 - 14:40", "Sari Iswanti, S.Si., M.Kom."),
            Daftar(6, R.string.pbm, R.drawable.mobile, "IF1922T", "15:00 - 17:30", "Pius Dian Widi Anggoro, S.Si, M.Cs."),
            Daftar(7, R.string.bindo, R.drawable.bindo, "I41904T", "08:00 - 19:40", "Agung Budi Prasetyo, S.Kom., M.Kom."),
            Daftar(8, R.string.prakgkv, R.drawable.prakgrafika, "IF1925P", "10:00 - 12:00", "Basuki heri Winarno, S.Pd., M.Kom."),
            Daftar(9, R.string.prakinfbig, R.drawable.prakinf, "IF1923P", "13:00 - 15:00", "S.Kom.Bagas Triaji, M.Kom."),
            Daftar(10, R.string.prakpbm, R.drawable.prakmobile, "IF1922P", "10:00 - 12:00", "Pius Dian Widi Anggoro, S.Si., M.Cs."),
            Daftar(11, R.string.ppaw, R.drawable.ppaw, "IF1921T", "07:30 - 10:00", "Ir.M. Guntara, M.T."),
            Daftar(12, R.string.spk, R.drawable.spk, "If1926G", "10:00 - 12:30", "Femi Dwi Astuti, S.Kom., M.Cs."),
        )
    }
}