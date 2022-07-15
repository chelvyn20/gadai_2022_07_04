
package id.co.nds.gadai_2022_07_04.validators;

import id.co.nds.gadai_2022_07_04.exceptions.ClientException;

public class UserValidator {

	public void validatemaxLimit(Double maxLimit) throws ClientException {
		if (maxLimit < 500000 || maxLimit > 1000000000) {
			throw new ClientException(
					"Transaction must be min 500000 or max 1000000000");
		}
	}

	public void ValidateUserNameLength(String userName) throws ClientException {
		if (userName.length() > 30) {
			throw new ClientException("UserName max Length 30");
		}
	}

	public void ValidateUserDescLength(String userDesc) throws ClientException {
		if (userDesc.length() > 50) {
			throw new ClientException("User Description max length 50");
		}
	}

	public void ValidateUserIdLength(String userId) throws ClientException {
		if (userId.length() > 15) {
			throw new ClientException("User Id max length 15");
		}
	}

	public void ValidateUserMaxLimitLength(String maxlimit)
			throws ClientException {
		if (maxlimit.length() > 17.2) {
			throw new ClientException("User Transaction max length 17");
		}
	}

	public void nullCheckObject(Object o) throws ClientException {

		if (o == null) {
			throw new ClientException("User is not found");
		}
	}

}
