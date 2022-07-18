
package id.co.nds.gadai_2022_07_04.services;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.nds.gadai_2022_07_04.entities.UserEntity;
import id.co.nds.gadai_2022_07_04.exceptions.ClientException;
import id.co.nds.gadai_2022_07_04.exceptions.NotFoundException;
import id.co.nds.gadai_2022_07_04.globals.GlobalConstant;
import id.co.nds.gadai_2022_07_04.models.UserModel;
import id.co.nds.gadai_2022_07_04.repos.UserRepo;
import id.co.nds.gadai_2022_07_04.repos.specs.UserSpec;
import id.co.nds.gadai_2022_07_04.validators.UserValidator;

@Service
public class UserServices {

	@Autowired
	private UserRepo userRepo;

	UserValidator userValidator = new UserValidator();

	public UserEntity add(UserModel userModel)
			throws ClientException, ParseException {

		userValidator.validatemaxLimit(
				Double.parseDouble(userModel.getUserTxnLimit()));
		userValidator.ValidateUserDescLength(userModel.getUserDecs());;
		userValidator.ValidateUserMaxLimitLength(userModel.getUserTxnLimit());
		userValidator.ValidateUserNameLength(userModel.getUserName());

		long count = userRepo.countByPhone(userModel.getUserHp());
		if (count > 0) {
			throw new ClientException("Phone Number is already Exited");
		}

		UserEntity user = new UserEntity();
		user.setUserName(userModel.getUserName());
		user.setMaxLimit(Double.parseDouble(userModel.getUserTxnLimit()));

		if (userModel.getUserHp().startsWith("+62")) {
			String changes = userModel.getUserHp().substring(3);
			String hasil = "0" + changes;

			try {
				user.setUser_phone(hasil);
			} catch (Exception e) {
				// TODO: handle exception
				throw new ClientException("User call number is already Exists");
			}

		} else {
			try {
				user.setUser_phone(userModel.getUserHp());
			} catch (NumberFormatException e) {
				// TODO: handle exception
				throw new ClientException("User call number is already Exists");

			}
		}
		user.setUserNotes(userModel.getUserDecs());

		Date date1 = new SimpleDateFormat("yyyy-MM-dd")
				.parse(userModel.getUserRegDate());
		user.setRegisterDate(date1);
		user.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		user.setCreatedBy(
				userModel.getActorId() == null ? "0" : userModel.getActorId());
		user.setRecStatus(GlobalConstant.REC_STATUS_ACTIVE);

		return userRepo.save(user);

	}

	public List<UserEntity> findAll() {

		List<UserEntity> user = new ArrayList<>();
		userRepo.findAll().forEach(user::add);

		return user;
	}

	public List<UserEntity> findAllByCriteria(UserModel userModel) {

		List<UserEntity> users = new ArrayList<>();
		UserSpec specs = new UserSpec(userModel);
		userRepo.findAll(specs).forEach(users::add);

		return users;
	}

	public UserEntity findById(String id)
			throws ClientException, NotFoundException {

		UserEntity user = userRepo.findById(id).orElse(null);
		userValidator.nullCheckObject(user);

		return user;

	}

	public UserEntity edit(UserModel userModel)
			throws ClientException, NotFoundException, ParseException {

				userValidator.validatemaxLimit(
					Double.parseDouble(userModel.getUserTxnLimit()));
			userValidator.ValidateUserDescLength(userModel.getUserDecs());
			userValidator.ValidateUserIdLength(userModel.getUserId());
			userValidator.ValidateUserMaxLimitLength(userModel.getUserTxnLimit());
			userValidator.ValidateUserNameLength(userModel.getUserName());

		if (!userRepo.existsById(userModel.getUserId())) {
			throw new NotFoundException(
					"Cannot find Product with id :" + userModel.getUserId());
		}

		UserEntity user = new UserEntity();
		user = findById(userModel.getUserId());

		if (userModel.getUserName() != null) {
			user.setUserName(userModel.getUserName());
		}
		if (userModel.getUserHp() != null) {
			user.setUser_phone(userModel.getUserHp());
		}
		if (userModel.getUserDecs() != null) {
			user.setUserNotes(userModel.getUserDecs());
		}
		if (userModel.getUserTxnLimit() != null) {
			user.setMaxLimit(Double.parseDouble(userModel.getUserTxnLimit()));
		}
		if (userModel.getUserRegDate() != null) {
			Date date1 = new SimpleDateFormat("yyyy-MM-dd")
					.parse(userModel.getUserRegDate());
			user.setRegisterDate(date1);
		}

		user.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
		user.setUpdateBy(userModel.getActorId() == null
				? userModel.getUserId() + userModel.getUserName()
				: userModel.getActorId());

		return userRepo.save(user);
	}

	public UserEntity delete(UserModel userModel)
			throws ClientException, NotFoundException {

		if (!userRepo.existsById(userModel.getUserId())) {
			throw new NotFoundException(
					"Cannot find User with id" + userModel.getUserId());
		}

		UserEntity user = new UserEntity();
		user = findById(userModel.getUserId());

		if (user.getRecStatus()
				.equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE)) {
			throw new ClientException("User id (" + user.getUserId()
					+ ") is Already been deleted");
		}

		user.setRecStatus(GlobalConstant.REC_STATUS_NON_ACTIVE);
		user.setDeletedDate(new Timestamp(System.currentTimeMillis()));
		user.setDeletedBy(
				userModel.getActorId() == null ? "0" : userModel.getActorId());

		return userRepo.save(user);

	}

}
