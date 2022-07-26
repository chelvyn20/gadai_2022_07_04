package id.co.nds.gadai_2022_07_04.validators;

import id.co.nds.gadai_2022_07_04.exceptions.ClientException;
import id.co.nds.gadai_2022_07_04.exceptions.NotFoundException;

public class CicilanTetapValidator {
    public void nullCheckObject(Object o) throws NotFoundException {
        if(o == null) {
            throw new NotFoundException("Transaksi tidak ditemukan");
        }
    }

    public void validateNilaiPencairanPelanggan(Double nilai) throws ClientException{
        if(nilai<1000000.00){
            throw new ClientException( "Nilai Pencairan pelanggan harus lebih besar atau sama dengan Rp.1.000.000,00");
        }
    }

    public void validateDiskonAdminBuka(Double diskon) throws ClientException{
        if(diskon<0|| diskon>100){
            throw new ClientException( "Diskon admin buka Pencairan pelanggan tidak valid (0-100%)");
        }
    }

    public void validateTotalNilaiTaksiran(Double nilaiTaksiran) throws ClientException{
        if(nilaiTaksiran<=0){
            throw new ClientException( "Nilai Pencairan pelanggan tidak valid");
        }
    }

}
