
package id.co.nds.gadai_2022_07_04.validators;

import id.co.nds.gadai_2022_07_04.exceptions.ClientException;
import id.co.nds.gadai_2022_07_04.globals.GlobalTypeProduct;

public class ProductValidator {

	public void validateProductLTV(Double productLTV) throws ClientException {
		if (productLTV < 0 || productLTV > 100) {
			throw new ClientException("product LTV count %(persen(1-100)) ");
		}
	}

	// public void validateTypeProduct(String ProductType) throws ClientException {
	// 	if (ProductType !="Konsinyasi Cicilan Tetap") {
	// 		// if (ProductType != GlobalTypeProduct.FLEKSIBEL) {
	// 		throw new ClientException("Product Type Not Found");

	// 	}
	// }

	public void validateBABPersen(Double productBuka) throws ClientException {
		if (productBuka < 0 || productBuka > 100) {
			throw new ClientException(
					"Biaya Admin Buka count %(persen(1-100)) ");
		}
	}

	public void validateBATPersen(Double productTutup) throws ClientException {
		if (productTutup < 0 || productTutup > 100) {
			throw new ClientException(
					"Biaya Admin Tutup count %(persen(1-100)) ");
		}
	}

	public void validateJangkaWaktu(Integer JangkaWaktu, Integer PenyPeriode)
			throws ClientException {
		if (JangkaWaktu % PenyPeriode != 0) {
			throw new ClientException(
					"Error Jangka Waktu Tidak Bagi Habis Penyimpanan Jasa Biaya");
		}
	}

	public void nullCheckObject(Object o) throws ClientException {

		if (o == null) {
			throw new ClientException("Product  is not found");
		}
	}

}
