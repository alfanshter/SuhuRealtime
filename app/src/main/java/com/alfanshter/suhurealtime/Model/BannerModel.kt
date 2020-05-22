package com.alfanshter.suhurealtime.Model

class BannerModel {

    var deskripsi: String? = null
    var gambar: String? = null
    var nama: String? = null
    var id: String? = null


    constructor() {}

    constructor(deskripsi: String?, gambar: String?, nama: String?, id: String?) {
        this.deskripsi = deskripsi
        this.gambar = gambar
        this.nama = nama
        this.id = id
    }


}


