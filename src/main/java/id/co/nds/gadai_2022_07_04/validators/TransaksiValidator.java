package id.co.nds.gadai_2022_07_04.validators;

import id.co.nds.gadai_2022_07_04.exceptions.ClientException;

public class TransaksiValidator {
	
	public void validatePersenAdmBuka(Double persenAdmbuka) throws ClientException {
		if (persenAdmbuka < 1 || persenAdmbuka > 100) {
			throw new ClientException("product Diskon Admin Buka count %(persen(1-100)) ");
		}
	}
}
