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
import id.co.nds.gadai_2022_07_04.services.ProductService;

@RestController
@Validated
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping(value = "/doinsertproduct")
	public ResponseEntity<ResponseModel> postProductController(@Validated(PostingNew.class)
			@RequestBody ProductModel productModel) throws ClientException {

		ProductEntity productEntity = productService.add(productModel);

		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
		response.setResponseMessage("Produk Baru Berhasil Disimpan");
		response.setResponseDescription("Menambahkan Product Baru");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(productEntity);

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/dogetdetailproduct/{id}")
	public ResponseEntity<ResponseModel> getProductByIdProduct(
			@PathVariable("id") String id)
			throws ClientException, NotFoundException {

		ProductEntity productEntity = productService.findById(id);

		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
		response.setResponseMessage("Sukses Mencari Data User");
		response.setResponseDescription("mencari data Detail User");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(productEntity);

		return ResponseEntity.ok(response);

	}

	@GetMapping(value = "/dosearchproduct")
	public ResponseEntity<ResponseModel> searchProductController(
			@RequestBody ProductModel productModel) throws ClientException {

		List<ProductEntity> products = productService
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

	@PutMapping(value = "/doupdateproduct")
	public ResponseEntity<ResponseModel> putProductController(
			@Validated(UpdatingById.class) @RequestBody ProductModel productModel)
			throws ClientException, NotFoundException {

		ProductEntity product = productService.edit(productModel);

		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
		response.setResponseMessage("Sukses Mengubah Data Product" + product.getProductId());
		response.setResponseDescription("mengubah data Product");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(product);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "/dodeleteproduct")
	public ResponseEntity<ResponseModel> deleteProductController(
			@Validated(DeletingById.class) @RequestBody ProductModel productModel)
			throws ClientException, NotFoundException {

		ProductEntity product = productService.delete(productModel);

		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
		response.setResponseMessage("Sukses Hapus Data Product");
		response.setResponseDescription("Menghapus data Product");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(product);

		return ResponseEntity.ok(response);

	}

}
