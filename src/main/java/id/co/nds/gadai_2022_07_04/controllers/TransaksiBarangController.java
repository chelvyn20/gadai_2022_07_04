
package id.co.nds.gadai_2022_07_04.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties.RSocket.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.nds.gadai_2022_07_04.entities.TransaksiBarangEntity;
import id.co.nds.gadai_2022_07_04.exceptions.ClientException;
import id.co.nds.gadai_2022_07_04.exceptions.NotFoundException;
import id.co.nds.gadai_2022_07_04.models.ResponseModel;
import id.co.nds.gadai_2022_07_04.models.TransaksiBarangModel;
import id.co.nds.gadai_2022_07_04.services.TransaksiBarangService;

@RestController
@RequestMapping("/TransaksiBarang")
public class TransaksiBarangController {

	@Autowired
	private TransaksiBarangService transaksiBarangService;

	@GetMapping(value = "/get")
	public ResponseEntity<ResponseModel> GetTransaksiBarangController()
			throws ClientException {

		List<TransaksiBarangEntity> transaksi = transaksiBarangService
				.findAll();

		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
		response.setResponseMessage("Sukses Menampilkan Data Transaksi Barang");
		response.setResponseDescription("Menampilkan Data Transaksi Barang");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(transaksi);
		response.setResponseData(transaksi);

		return ResponseEntity.ok(response);
	}

	// @PostMapping(value = "/post")
	// public ResponseEntity<ResponseModel> PostTransaksiBarangController(
	// 		@RequestBody TransaksiBarangModel transaksiBarangModel)
	// 		throws ClientException, NotFoundException {

	// 	TransaksiBarangEntity transaksi = transaksiBarangService
	// 			.add(transaksiBarangModel);
	// 	DateTimeFormatter dtf = DateTimeFormatter
	// 			.ofPattern("uuuu/MM/dd HH:mm:ss");
	// 	LocalDateTime now = LocalDateTime.now();
	// 	ResponseModel response = new ResponseModel();
	// 	response.setResponseCode("200");
	// 	response.setResponseMessage("Sukses Tambah Data Transaksi Barang");
	// 	response.setResponseDescription("Menambahkan Data Transaksi Barang");
	// 	response.setResponseTime(dtf.format(now));
	// 	response.setResponseData(transaksi);

	// 	return ResponseEntity.ok(response);
	// }

	@GetMapping(value = "/get/{id}/{noUrut}")
	public ResponseEntity<ResponseModel> getTransaksiBarangController(
			@PathVariable("id") String id, @PathVariable("noUrut") Integer noUrut )
			throws ClientException, NotFoundException {

		TransaksiBarangEntity transaksi = transaksiBarangService.findById(id,
				noUrut);

		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
		response.setResponseMessage("Sukses Detail Data Transaksi Barang");
		response.setResponseDescription("MenDetailkan Data Transaksi Barang");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(transaksi);

		return ResponseEntity.ok(response);
	}

	
}
