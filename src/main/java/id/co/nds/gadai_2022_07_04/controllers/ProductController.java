
package id.co.nds.gadai_2022_07_04.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
import id.co.nds.gadai_2022_07_04.controllers.GroupController.PostingNew;
import id.co.nds.gadai_2022_07_04.controllers.GroupController.UpdatingById;
import id.co.nds.gadai_2022_07_04.entities.ProductEntity;
import id.co.nds.gadai_2022_07_04.exceptions.ClientException;
import id.co.nds.gadai_2022_07_04.exceptions.NotFoundException;
import id.co.nds.gadai_2022_07_04.models.ProductModel;
import id.co.nds.gadai_2022_07_04.models.ResponseModel;
import id.co.nds.gadai_2022_07_04.services.ProductServices;

@RestController
@Validated
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductServices productServices;

	@PostMapping(value = "/doInsertProduct")
	public ResponseEntity<ResponseModel> postProductController(@Validated(PostingNew.class)
			@RequestBody ProductModel productModel) throws ClientException {

		ProductEntity productEntity = productServices.add(productModel);

		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
		response.setResponseMessage("Sukses Insert Data User Baru");
		response.setResponseDescription("menambah data User Baru");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(productEntity);

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/doGetDetailProduct/{id}")
	public ResponseEntity<ResponseModel> getProductByIdProduct(
			@PathVariable("id") String id)
			throws ClientException, NotFoundException {

		ProductEntity productEntity = productServices.findById(id);

		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
		response.setResponseMessage("Sukses Detail Data User");
		response.setResponseDescription("mencari data Detail User");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(productEntity);

		return ResponseEntity.ok(response);

	}

	@GetMapping(value = "/doSearchProduct")
	public ResponseEntity<ResponseModel> searchProductController(
			@RequestBody ProductModel productModel) throws ClientException {

		List<ProductEntity> products = productServices
				.findAllByCriteria(productModel);

		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
		response.setResponseMessage("Sukses Mencari Data Product");
		response.setResponseDescription("mencari data Product");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(products);

		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "/doUpdateProduct")
	public ResponseEntity<ResponseModel> putProductController(
			@Validated(UpdatingById.class) @RequestBody ProductModel productModel)
			throws ClientException, NotFoundException {

		ProductEntity product = productServices.edit(productModel);

		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
		response.setResponseMessage("Sukses Update Data Product");
		response.setResponseDescription("mengUpdate data Product");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(product);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "/doDeleteProduct")
	public ResponseEntity<ResponseModel> deleteProductController(
			@Validated(DeletingById.class) @RequestBody ProductModel productModel)
			throws ClientException, NotFoundException {

		ProductEntity product = productServices.delete(productModel);

		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
		response.setResponseMessage("Sukses Delete Data Product");
		response.setResponseDescription("mengDelete data Product");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(product);

		return ResponseEntity.ok(response);

	}

}
