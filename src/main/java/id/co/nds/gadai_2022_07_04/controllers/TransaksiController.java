
package id.co.nds.gadai_2022_07_04.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.co.nds.gadai_2022_07_04.controllers.ControllerGrup.PostingNew;
import id.co.nds.gadai_2022_07_04.entities.CicilanEntity;
import id.co.nds.gadai_2022_07_04.entities.CustomerEntity;
import id.co.nds.gadai_2022_07_04.entities.ProductEntity;
import id.co.nds.gadai_2022_07_04.entities.ProductGetListEntity;
import id.co.nds.gadai_2022_07_04.entities.TransaksiCicilanTetapEntity;
import id.co.nds.gadai_2022_07_04.entities.TransaksiGetDetailEntity;
import id.co.nds.gadai_2022_07_04.entities.TransaksiInfoEntity;
import id.co.nds.gadai_2022_07_04.exceptions.ClientException;
import id.co.nds.gadai_2022_07_04.exceptions.NotFoundException;
import id.co.nds.gadai_2022_07_04.models.CustomerModel;
import id.co.nds.gadai_2022_07_04.models.ProductModel;
import id.co.nds.gadai_2022_07_04.models.ResponseModel;
import id.co.nds.gadai_2022_07_04.models.TransaksiModel;
import id.co.nds.gadai_2022_07_04.repos.GetDetailTransaksiRepo;
import id.co.nds.gadai_2022_07_04.services.CustomerService;
import id.co.nds.gadai_2022_07_04.services.ProductServices;
import id.co.nds.gadai_2022_07_04.services.TransaksiService;
import id.co.nds.gadai_2022_07_04.services.UserServices;

@RestController
@Validated
@RequestMapping("/Transaksi")
public class TransaksiController {

	@Autowired
	private TransaksiService transaksiService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private UserServices userServices;

	@Autowired
	private ProductServices productServices;


	@GetMapping(value = "/doSearchPelanggan")
	public ResponseEntity<ResponseModel> searchPelangganController(
			@RequestBody CustomerModel customerModel) {

		List<CustomerEntity> pelanggan = customerService
				.findAllByCriteria(customerModel);
		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
		response.setResponseMessage("Sukses Mencari Data Transaksi");
		response.setResponseDescription("Mengcari data Transaksi");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(pelanggan);

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/doGetListProduct/{id}")
	public ResponseEntity<ResponseModel> SearchListProductController(
			@PathVariable("id") String id) throws ClientException, NotFoundException {

		ProductGetListEntity productEntity = transaksiService.findById(id);

		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
		response.setResponseMessage("Sukses Detail Data Product");
		response.setResponseDescription("mencari data Detail Product");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(productEntity);

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/doSearchTransCipTetap")
	public ResponseEntity<ResponseModel> searchTransaksiCicilanTetapController(
			@RequestParam String noTransaksi, @RequestParam String productId,
			@RequestParam String custId) {

		List<TransaksiInfoEntity> transaksi = transaksiService
				.findAllByCriteria(noTransaksi, productId, custId);
		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
		response.setResponseMessage("Sukses Mencari Data Transaksi");
		response.setResponseDescription("Mengcari data Transaksi");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(transaksi);

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/doGetDetailCicTetap")
	public ResponseEntity<ResponseModel> doGetDetailTetapController(
			@RequestParam String noTransaksi) {

		List<TransaksiGetDetailEntity> transaksi = transaksiService
				.GetDetailTransaksiCicilanTetap(noTransaksi);
		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
		response.setResponseMessage("Sukses Mencari Detail Data Transaksi");
		response.setResponseDescription("Mengcari data Transaksi Detail");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(transaksi);

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/Hitung")
	public ResponseEntity<ResponseModel> HitungTransaksiController(
			@RequestBody TransaksiModel transaksiModel)
			throws ClientException, NotFoundException {

		TransaksiCicilanTetapEntity transaksi = transaksiService
				.Hitung(transaksiModel);
		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
		response.setResponseMessage("Sukses Hitung Data Transaksi");
		response.setResponseDescription("Menghitung Data Transaksi");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(transaksi);

		return ResponseEntity.ok(response);
	}

	@PostMapping(value = "/doSave")
	public ResponseEntity<ResponseModel> DoSaveTransaksiController(@Validated(PostingNew.class)
			@RequestBody TransaksiModel transaksiModel)
			throws ClientException, NotFoundException {

		// List<TransaksiCicilanTetapEntity> findTransaksi = transaksiService
		// .findAll(transaksiModel);
		TransaksiCicilanTetapEntity transaksi = transaksiService
				.doSave(transaksiModel);

		// transaksiService.doSaveCicilan(transaksi);
		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
		response.setResponseMessage("Sukses Save Data Transaksi");
		response.setResponseDescription("Mengnambah Data Transaksi");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(transaksi);

		return ResponseEntity.ok(response);

	}

	@GetMapping(value = "/get")
	public ResponseEntity<ResponseModel> getAllTransaksiController() throws ClientException {
		List<TransaksiCicilanTetapEntity> transaksi = transaksiService
				.findAllTransaksi();

		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
		response.setResponseMessage("Sukses Save Data Transaksi");
		response.setResponseDescription("Mengnambah Data Transaksi");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(transaksi);

		return ResponseEntity.ok(response);
	}
}
