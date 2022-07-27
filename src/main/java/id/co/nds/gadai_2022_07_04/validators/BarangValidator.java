package id.co.nds.gadai_2022_07_04.validators;

import id.co.nds.gadai_2022_07_04.exceptions.ClientException;

public class BarangValidator {

    public void notNullChekNoUrut(Integer noBrg) throws ClientException{
        if(noBrg==null){
            throw new ClientException( "Nomor barang tidak boleh kosong");

        }
    }
    
    public void notNullChekNoTransaksi(String nomor ) throws ClientException {
        if(nomor!=null){
            throw new ClientException( "Nomor Transaksi dibuat secara otomatis, jangan masukkan!");

        }
    }

    public void nullCheckNamaBarang(String barangName) throws ClientException {
        if(barangName == null) {
            throw new ClientException("Nama barang tidak boleh kosong");
        }
    }

    public void nullCheckKondisiBarang(String kondisi) throws ClientException {
        if(kondisi == null) {
            throw new ClientException("Kondisi barang tidak boleh kosong");
        }
    }

    public void nullCheckJumlahBarang(Integer qty) throws ClientException {
        if(qty == null) {
            throw new ClientException("Jumlah barang Barang tidak boleh kosong");
        }
    }

    public void nullCheckHargaBarang(Double harga) throws ClientException {
        if(harga == null) {
            throw new ClientException("Harga Barang tidak boleh kosong");
        }
    }
    
    public void validateNoBarang (Integer no) throws ClientException{
        if (no <= 0){
            throw new ClientException("No Barang tidak valid");
        }
    }

    public void validateNamaBarang(String nama) throws ClientException {
        if(nama.length() > 30) {
            throw new ClientException("Nama barang tidak boleh melebihi 30 huruf.");
        }
    }


    public void validateDescBarang(String desc) throws ClientException {
        if(desc.length() > 150) {
            throw new ClientException("Deskripsi barang tidak boleh melebihi 150 huruf.");
        }
    }

    public void validateJumlahBarang(Integer jumlah) throws ClientException {
        if(jumlah < 1) {
            throw new ClientException("Jumlah barang tidak valid");
        }
    }

    public void validateHargaBarang(Double harga) throws ClientException {
        if(harga < 1) {
            throw new ClientException("Harga barang tidak valid");
        }
    }
}
