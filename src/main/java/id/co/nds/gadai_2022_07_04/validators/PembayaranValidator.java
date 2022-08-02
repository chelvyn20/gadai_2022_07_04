package id.co.nds.gadai_2022_07_04.validators;

import id.co.nds.gadai_2022_07_04.exceptions.ClientException;

public class PembayaranValidator {
    
   
    
    public void validateJumlahPembayaran(Double bayar , Double total ) throws ClientException {
        if(bayar < total){
            throw new ClientException( "jumlah pembayaran tidak boleh lebih kecil dari yang ditagihkan");

        }
    }

    public void validateMetodePembayaran(String metode ) throws ClientException {
        if(!metode.trim().equalsIgnoreCase("CASH")  && !metode.trim().equalsIgnoreCase("TRANSFER")  && !metode.trim().equalsIgnoreCase("DEBIT")  && !metode.trim().equalsIgnoreCase("CREDIT")){
            throw new ClientException( "metode pembayaran yang tersedia:  CASH / TRANSFER / DEBIT / CREDIT");

        }
    }

    public void validateDiskon(Double diskon, Double bJasaPeny, Double totalDenda) throws ClientException {
        if(diskon > (bJasaPeny + totalDenda)){
            throw new ClientException( "Diskon tidak boleh melebihi biaya jasa penyimpanan + denda ");
        }
    }

}
