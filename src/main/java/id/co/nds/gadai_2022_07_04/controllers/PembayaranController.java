
package id.co.nds.gadai_2022_07_04.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hibernate.validator.cfg.defs.NotEmptyDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.co.nds.gadai_2022_07_04.entities.InformasiTransaksiEntity;
import id.co.nds.gadai_2022_07_04.entities.PembayaranEntity;
import id.co.nds.gadai_2022_07_04.entities.TransaksiCicilanTetapEntity;
import id.co.nds.gadai_2022_07_04.entities.TransaksiInfoEntity;
import id.co.nds.gadai_2022_07_04.exceptions.ClientException;
import id.co.nds.gadai_2022_07_04.exceptions.NotFoundException;
import id.co.nds.gadai_2022_07_04.models.PembayaranModel;
import id.co.nds.gadai_2022_07_04.models.ResponseModel;
import id.co.nds.gadai_2022_07_04.services.PembayaranService;
import id.co.nds.gadai_2022_07_04.services.TransaksiService;

@RestController
@RequestMapping("/pembayaran")
public class PembayaranController {

	@Autowired
	private PembayaranService pembayaranService;

	@Autowired
	private TransaksiService transaksiService;

	@GetMapping(value = "/informasiTransaksi")
	public ResponseEntity<ResponseModel> getInformasiTransaksiController(
			@RequestBody PembayaranModel pembayaranModel)
			throws ClientException, NotFoundException {

		InformasiTransaksiEntity informasi = pembayaranService
				.InformasiTransaksi(pembayaranModel);
		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
		response.setResponseMessage("Sukses Menginformasikan Data Transaksi");
		response.setResponseDescription("Informasi Data Transaksi");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(informasi);

		return ResponseEntity.ok(response);

	}

	@PostMapping(value = "/hitungpembayaran")
	public ResponseEntity<ResponseModel> getHitungPembayaranController(
			@RequestBody PembayaranModel pembayaranModel)
			throws ClientException, NotFoundException {

		PembayaranEntity pembayaran = pembayaranService
				.HitungTagihanPembayaran(pembayaranModel);

		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
		response.setResponseMessage(
				"Sukses Menghitung Pembayaran Data Transaksi");
		response.setResponseDescription("hitung Pembayaran Data Transaksi");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(pembayaran);

		return ResponseEntity.ok(response);
	}

	@PostMapping(value = "/doUpdatePembayaran")
	public ResponseEntity<ResponseModel> doUpdatePembayaranController(
			@RequestBody PembayaranModel pembayaranModel)
			throws ClientException, NotFoundException {

		PembayaranEntity pembayaran = pembayaranService
				.DoSavePembayaran(pembayaranModel);
		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
		response.setResponseMessage("Sukses Update Data Transaksi");
		response.setResponseDescription("Mengupdate Data Transaksi");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(pembayaran);

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/doGetDetailBayarCicTetap/{id}")
	public ResponseEntity<ResponseModel> doSearchBayarCicTetap(
			@PathVariable("id") String id)
			throws ClientException, NotFoundException {

		TransaksiCicilanTetapEntity transaksi = transaksiService.findByIds(id);

		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
		response.setResponseMessage("Sukses Detail Data Transaksi");
		response.setResponseDescription("Mengdetail Data Transaksi");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(transaksi);

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/doSearchBayarCicTetap")
	public ResponseEntity<ResponseModel> doSearchCicTetapController(
			@RequestParam String noTransaksi, @RequestParam String custId) {
		List<TransaksiInfoEntity> transaksi = transaksiService
				.findAllByTransaksiCust(noTransaksi, custId);
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

}
