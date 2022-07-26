
package id.co.nds.gadai_2022_07_04.validators;

import org.springframework.boot.autoconfigure.integration.IntegrationProperties.RSocket.Client;

import id.co.nds.gadai_2022_07_04.exceptions.ClientException;

public class TransaksiBarangValidator {
	
	public void nullCheckObject(Object o) throws ClientException {

		if (o == null) {
			throw new ClientException("Product  is not found");
		}
	}

	public void validateNamaBarang(String namaBarang) throws ClientException{
		if (namaBarang.length() > 30) {
			throw new ClientException("Nama Barang length max 30");
		}
	}

	public void validateDesc(String descripsi) throws ClientException {
		if (descripsi.length() > 150) {
			throw new ClientException("Description length max 150");
		}
	}
	
	public void validatejumlah(Integer Jumlah) throws ClientException {
		if (Jumlah > 999) {
			throw new ClientException("Jumlah max 999");
		}
	}

	public void nullNamabarang(String namaBarang) throws ClientException {
		if (namaBarang.length() == 0) {
			throw new ClientException("Nama Barang is Required");
		}
	}
	
	public void nullDesc(String descripsi) throws ClientException {
		if (descripsi.length() == 0) {
			throw new ClientException("Description is Required");
		}
	}

	public void nulljumlah(Integer jumlah) throws ClientException {
		if (jumlah == null) {
			throw new ClientException("Jumlah is Required");
		}
	}

	public void nullTotal(Double hargasatuan) throws ClientException {
		if(hargasatuan == null){
			throw new ClientException("Harga Satuan is Required");
		}
	}
	
}
