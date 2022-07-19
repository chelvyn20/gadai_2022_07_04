
package id.co.nds.gadai_2022_07_04.validators;

import id.co.nds.gadai_2022_07_04.exceptions.ClientException;

public class ProductValidator {

	public void validateProductLTV(Double productLTV) throws ClientException {
		if (productLTV < 1 || productLTV > 100) {
			throw new ClientException("product LTV count %(persen(1-100)) ");
		}
	}

	public void validateBABPersen(Double productBuka) throws ClientException {
		if (productBuka < 1 || productBuka > 100) {
			throw new ClientException(
					"Biaya Admin Buka count %(persen(1-100)) ");
		}
	}

	public void validateBATPersen(Double productTutup) throws ClientException {
		if (productTutup < 1 || productTutup > 100) {
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
