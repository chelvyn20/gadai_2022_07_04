
package id.co.nds.gadai_2022_07_04.validators;

import id.co.nds.gadai_2022_07_04.exceptions.ClientException;

public class PembayaranValidator {

	public void ValidateMetodePembayaran(String MetodeBayar)
			throws ClientException {

		
			throw new ClientException("Metode Pembayaran Not Found");
		
	}
}
