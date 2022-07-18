package id.co.nds.gadai_2022_07_04.validators;

import id.co.nds.gadai_2022_07_04.exceptions.ClientException;
import id.co.nds.gadai_2022_07_04.exceptions.NotFoundException;
import id.co.nds.gadai_2022_07_04.globals.GlobalConstant;

public class CustomerValidator {
    public void nullChekcCustId(String id) throws ClientException {
        if (id == null) {
            throw new ClientException("Id pelanggan tidak boleh kosong");

        }
    }

    public void notnullChekcCustId(String id) throws ClientException {
        if (id != null) {
            throw new ClientException("Id pelanggan dibuat secara otomatis, jangan masukkan id!");

        }
    }

    public void nullChekcCustName(String custName) throws ClientException {
        if (custName == null) {
            throw new ClientException("Nama pelanggan tidak boleh kosong");

        }
    }

    public void nullChekcCustKtp(String custKtp) throws ClientException {
        if (custKtp == null) {
            throw new ClientException("Nomor KTP pelanggan tidak boleh kosong");

        }
    }

    public void nullChekcCallNumber(String custHp) throws ClientException {
        if (custHp == null) {
            throw new ClientException("Nomor HP pelanggan tidak boleh kosong");

        }
    }

    public void nullChekcCustJk(String custJk) throws ClientException {
        if (custJk == null) {
            throw new ClientException("Jenis kelamin pelanggan tidak boleh kosong");

        }
    }

    public void nullChekcCustJenisUsaha(String custJenisUsaha) throws ClientException {
        if (custJenisUsaha == null) {
            throw new ClientException("Jenis usaha pelanggan tidak boleh kosong");

        }
    }

    public void nullChekcCustLimitTxn(Double custLimitTxn) throws ClientException {
        if (custLimitTxn == null) {
            throw new ClientException("Batas transaksi pelanggan tidak boleh kosong");

        }
    }

    public void nullChekcObject(Object o) throws NotFoundException {
        if (o == null) {
            throw new NotFoundException("Id pelanggan tidak ditemukan");

        }
    }

    public void validateCustId(String id) throws ClientException {
        if (id.length() != 10) {
            throw new ClientException("Id pelanggan harus 10 digit ");
        }
    }

    public void validateName(String custName) throws ClientException {
        if (custName.trim().equalsIgnoreCase(" ")) {
            throw new ClientException("Nama pelanggan tidak boleh kosong");
        }
    }

    public void validateCustKtp(String custKtp) throws ClientException {
        if (custKtp.length() != 16) {
            throw new ClientException("Nomor KTP tidak valid, harus 16 digit");
        }

       
    }

    public void validateCallNumber(String custHp) throws ClientException {
        if (!(custHp.length() <= 12) || !(custHp.length() >= 9)) {
            throw new ClientException("Nomor HP pelanggan harus 9-12 digit, masukkan hanya angka ");
        }

    }

    public void validateCustJk(String jk) throws ClientException {

        if (!"P".equals(jk) && !"W".equals(jk)) {
            throw new ClientException(" Masukan Salah, Masukkan 'P' atau 'W'");
        }

    }

    public void validateJenisUsaha(String usaha) throws ClientException {
        if (usaha.trim().equalsIgnoreCase(" ")) {
            throw new ClientException("Jenis Usaha pelanggan tidak boleh kosong");
        }
    }

    public void validatetLimitTxn(Double limit) throws ClientException {
        if (!(limit >= 1000000.00) || !(limit <= 3000000.00)) {

            throw new ClientException("Batas transaksi pelanggan adalah 1.000.000,00 - 3.000.000,00");
        }

    }

    public void validateCustStatus(String id, String status) throws ClientException {
        if (status.equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE)) {
            throw new ClientException("Pelanggan dengan id = " + id + " telah dihapus");
        }
    }
}
