
package id.co.nds.gadai_2022_07_04.controllers;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.nds.gadai_2022_07_04.controllers.GroupController.DeletingById;
import id.co.nds.gadai_2022_07_04.controllers.GroupController.GettingAllByCriteria;
import id.co.nds.gadai_2022_07_04.controllers.GroupController.PostingNew;
import id.co.nds.gadai_2022_07_04.controllers.GroupController.UpdatingById;
import id.co.nds.gadai_2022_07_04.entities.UserEntity;
import id.co.nds.gadai_2022_07_04.exceptions.ClientException;
import id.co.nds.gadai_2022_07_04.exceptions.NotFoundException;
import id.co.nds.gadai_2022_07_04.models.ResponseModel;
import id.co.nds.gadai_2022_07_04.models.UserModel;
import id.co.nds.gadai_2022_07_04.services.UserServices;


@RestController
@Validated
@RequestMapping("/user")
public class userController {

	@Autowired
	private UserServices userServices;

	@PostMapping(value = "/doInsert")
	public ResponseEntity<ResponseModel> postUserController(
			@Validated(PostingNew.class) @RequestBody UserModel userModel)
			throws ClientException, ParseException {

		UserEntity user = userServices.add(userModel);

		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
		response.setResponseMessage("Sukses Input Data User");
		response.setResponseDescription("menginput data User");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(user);

		return ResponseEntity.ok(response);

	}

	@GetMapping(value = "/doSearchUser")
	public ResponseEntity<ResponseModel> searchUserController(
			@Validated(GettingAllByCriteria.class) @RequestBody UserModel userModel) {

		List<UserEntity> users = userServices.findAllByCriteria(userModel);

		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
		response.setResponseMessage("Sukses Pencarian Data User");
		response.setResponseDescription("mencari data User");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(users);

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "doGetDetailuser/{id}")
	public ResponseEntity<ResponseModel> getUserByIdController(
			@NotNull @PositiveOrZero @PathVariable String id)
			throws ClientException, NotFoundException {

		UserEntity user = userServices.findById(id);

		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
		response.setResponseMessage("Sukses Detail Data User Baru");
		response.setResponseDescription("mencari data Detail User");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(user);

		return ResponseEntity.ok(response);

	}

	@PutMapping(value = "/doUpdate")
	public ResponseEntity<ResponseModel> putUsetController(
			@Validated(UpdatingById.class) @RequestBody UserModel userModel)
			throws ClientException, NotFoundException, ParseException {

		UserEntity user = userServices.edit(userModel);

		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
		response.setResponseMessage("Sukses Update Data User Baru");
		response.setResponseDescription("Mengupdate data User");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(user);

		return ResponseEntity.ok(response);

	}

	@DeleteMapping(value = "/doDelete")
	public ResponseEntity<ResponseModel> deleteUserController(
			@Validated(DeletingById.class) @RequestBody UserModel userModel)
			throws ClientException, NotFoundException {

		UserEntity user = userServices.delete(userModel);

		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
		response.setResponseMessage(
				"Sukses Hapus Data user id : " + userModel.getUserId());
		response.setResponseDescription("Mengdelete data User User");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(user);

		return ResponseEntity.ok(response);

	}

}
