package id.co.nds.gadai_2022_07_04.validators;

import id.co.nds.gadai_2022_07_04.exceptions.ClientException;
import id.co.nds.gadai_2022_07_04.exceptions.NotFoundException;
import id.co.nds.gadai_2022_07_04.globals.GlobalConstant;

public class ProductValidator {
    public void notNullCheckProductId(String productId) throws ClientException{
        if(productId != null) {
            throw new ClientException("Id dibuat secara otomatis, jangan masukan id");
        }
    }

    public void nullCheckProductId(String productId) throws ClientException {
        if(productId == null) {
            throw new ClientException("Produk id perlu diisi");
        }
    }

    public void nullCheckProductName(String productName) throws ClientException {
        if(productName == null) {
            throw new ClientException("Nama produk perlu diisi");
        }
    }

    public void nullCheckProductTipe(String productTipe) throws ClientException {
        if(productTipe == null) {
            throw new ClientException("Tipe produk perlu diisi");
        } 
    }

    public void nullCheckTenor(Integer productTenor) throws ClientException {
        if(productTenor == null) {
            throw new ClientException("Jangka waktu perlu diisi");
        }
    }

    public void nullCheckLtv(Double productLtv) throws ClientException {
        if(productLtv == null) {
            throw new ClientException("Ltv perlu diisi");
        }
    }

    public void nullCheckProductaBiayaAdminBukaTipe(String biayaAdminBukaTipe) throws ClientException {
        if(biayaAdminBukaTipe == null) {
            throw new ClientException("Jenis biaya admin buka perlu diisi");
        }
    }

    public void nullCheckProductBiayaAdminTutupTipe(String biayaAdminTutupTipe) throws ClientException {
        if(biayaAdminTutupTipe == null) {
            throw new ClientException("Jenis biaya admin tutup perlu diisi");
        }
    }

    public void nullCheckProductAdminOpeningFee(Double openingFee) throws ClientException {
        if(openingFee == null) {
            throw new ClientException("Biaya admin buka perlu diisi");
        }
    }

    public void nullCheckProductAdminClosingFee(Double closingFee) throws ClientException {
        if(closingFee == null) {
            throw new ClientException("Biaya admin tutup perlu diisi");
        }
    }
    
    public void nullCheckProductBiayaPenyimpanan(Double biayaPenyimpanan) throws ClientException {
        if(biayaPenyimpanan == null) {
            throw new ClientException("Biaya penyimpanan perlu diisi");
        }
    }

    public void nullCheckProductBiayaPenyimpananPeriode(Integer periodePeny) throws ClientException {
        if(periodePeny == null) {
            throw new ClientException("Periode penyimpanan perlu diisi");
        }
    }

    public void nullCheckProductBiayaDenda(Double productBiayaDenda) throws ClientException {
        if(productBiayaDenda == null) {
            throw new ClientException("Biaya denda perlu diisi");
        }
    }

    public void nullCheckProductBiayaDendaPeriode(Integer productBiayaDendaPeriode) throws ClientException {
        if(productBiayaDendaPeriode == null) {
            throw new ClientException("Periode denda perlu diisi");
        }
    }

    public void nullCheckObject(Object o) throws NotFoundException {
        if(o == null) {
            throw new NotFoundException("Produk tidak ditemukan");
        }
    }



    public void validateProductTipe(String tipe) throws ClientException{
        if(!tipe.trim().equalsIgnoreCase("Konsinyasi Cicilan Tetap")  && !tipe.trim().equalsIgnoreCase("Konsinyasi Cicilan Fleksibel")  ){
            throw new ClientException( "Tipe produk harus diisi dengan 'Konsinyasi Cicilan Tetap' atau 'Konsinyasi Cicilan Fleksibel'");
        }
    }


    public void validateName (String name) throws ClientException{
        if (name.trim().equalsIgnoreCase(" ") || name.length() > 50) {
            throw new ClientException("Nama produk tidak boleh kosong dan tidak boleh melebihi 50 huruf");
        }
    }

    public void validateProductJangkaWaktu(Integer waktu ) throws ClientException {
        if(waktu <= 1 ){
            throw new ClientException( "Jangka waktu produk harus lebih besar dari 0");

        }
    }
    public void validateProductDesc(String desc) throws ClientException {
        if(desc.length() > 255) {
            throw new ClientException("Keterangan Produk tidak boleh lebih dari 255 karakter");
        }
    }

    public void validateProductLtv(Double ltv ) throws ClientException {
        if(ltv > 100 ){
            throw new ClientException( "Ltv produk harus diisi dalam bentuk persen (minimum 0 dan maksimum 100%)");

        }
    }

    public void validateBiayaAdminBuka(String tipeBuka, Double biayaBuka ) throws ClientException {
        
        if(biayaBuka <0){
            throw new ClientException( "Biaya buka produk tidak valid");
        }
        if(tipeBuka.trim().equalsIgnoreCase("PERSEN")&& biayaBuka >100){
            throw new ClientException( "Biaya buka produk dalam bentuk persen (max 100%) ");
        }
        
        
    }

    public void validateBiayaAdminBukaTipe(String tipeBuka ) throws ClientException {
        if(!tipeBuka.trim().equalsIgnoreCase("PERSEN") && !tipeBuka.trim().equalsIgnoreCase("NOMINAL")){
            throw new ClientException( "Tipe buka produk tidak valid, silahkan masukkan persen atau nominal");

        }
    }

    public void validateBiayaAdminTutup(String tipeTutup, Double biayaTutup ) throws ClientException {
        if(biayaTutup <0){
            throw new ClientException( "Biaya tutup produk tidak valid");
        }
        if(tipeTutup.trim().equalsIgnoreCase("PERSEN")&& biayaTutup >100){
            throw new ClientException( "Biaya tutup produk dalam bentuk persen (max 100%)");
        }
    }

    public void validateBiayaAdminTutupTipe(String tipeTutup ) throws ClientException {
        if(!tipeTutup.trim().equalsIgnoreCase("PERSEN") && !tipeTutup.trim().equalsIgnoreCase("NOMINAL")){
            throw new ClientException( "Tipe tutup produk tidak valid, silahkan masukkan persen atau nominal");

        }
    }

    public void validateBiayaAdminPeny(Double biayaPeny ) throws ClientException {
        if(biayaPeny<0 ){
            throw new ClientException( "Biaya penyimpanan produk tidak valid");

        }
    }

    public void validateBiayaAdminPenyPeriode(Integer waktu, Integer periodePeny ) throws ClientException {
        if( (periodePeny > waktu) || waktu%periodePeny !=0 ){
            throw new ClientException( "Periode biaya penyimpanan produk tidak boleh lebih besar dari jangka waktu dan periode penyimpanan harus kelipatan dari jangka waktu");
        }
        if(periodePeny<0){
            throw new ClientException( "Periode biaya penyimpanan produk tidak valid");

        }
    }

    public void validateBiayaAdminDenda(Double biayaDenda ) throws ClientException {
        if(biayaDenda<0){
            throw new ClientException( "Biaya denda produk tidak valid");

        }
    }

    public void validateBiayaAdminDendaPeriode(Integer periodeDenda ) throws ClientException {
        if(periodeDenda<0 ){
            throw new ClientException( "Biaya penyimpanan produk tidak valid");

        }
    }    

    public void validateProductStatus (String id, String status) throws ClientException{
        if (status.equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE)){
            throw new ClientException("Pelanggan dengan id = " + id+" telah dihapus");
        }
    }
}
