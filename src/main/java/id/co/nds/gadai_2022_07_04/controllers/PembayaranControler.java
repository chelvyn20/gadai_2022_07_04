package id.co.nds.gadai_2022_07_04.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.nds.gadai_2022_07_04.entities.CicilanTetapEntity;
import id.co.nds.gadai_2022_07_04.entities.CicilanTetapInfoEntity;
import id.co.nds.gadai_2022_07_04.exceptions.ClientException;
import id.co.nds.gadai_2022_07_04.exceptions.NotFoundException;
import id.co.nds.gadai_2022_07_04.models.CicilanTetapModel;
import id.co.nds.gadai_2022_07_04.models.PembayaranModel;
import id.co.nds.gadai_2022_07_04.models.ResponseModel;
import id.co.nds.gadai_2022_07_04.services.PembayaranService;

@RestController
@RequestMapping("/pembayaran")
public class PembayaranControler {
    @Autowired
    PembayaranService pembayaranService;


    @GetMapping(value = "/get/bayar")
    public ResponseEntity<ResponseModel> doSearchBayarCicTetap ( @RequestBody CicilanTetapModel cicilanTetapModel) throws ClientException, NotFoundException {
        List<CicilanTetapEntity> cicilanTetap = pembayaranService.doSearchBayarCicTetap(cicilanTetapModel);

        DateTimeFormatter dtf = DateTimeFormatter
                .ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        ResponseModel response = new ResponseModel();
        response.setResponseCode("200");
        response.setResponseMessage("Sukses Search Data Customer");
        response.setResponseDescription("Search data Customer");
        response.setResponseTime(dtf.format(now));
        response.setResponseData(cicilanTetap);

        return ResponseEntity.ok(response);
        
    }

    @GetMapping(value = "/get/detailtagihan")
    public ResponseEntity<ResponseModel> doGetDetailTagihanCic ( @RequestBody CicilanTetapModel cicilanTetapModel) throws ClientException, NotFoundException {
        CicilanTetapInfoEntity cicilanTetap = pembayaranService.doGetDetailTagihanCic(cicilanTetapModel);

        DateTimeFormatter dtf = DateTimeFormatter
                .ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        ResponseModel response = new ResponseModel();
        response.setResponseCode("200");
        response.setResponseMessage("Sukses Search Data Customer");
        response.setResponseDescription("Search data Customer");
        response.setResponseTime(dtf.format(now));
        response.setResponseData(cicilanTetap);

        return ResponseEntity.ok(response);
        
    }

    @PostMapping(value = "/bayar")
	public ResponseEntity<ResponseModel> dohitung(
			@RequestBody PembayaranModel pembayaranModel) throws ClientException, NotFoundException {

		CicilanTetapInfoEntity cicilanTetapEntity =  pembayaranService.doUpdatePembayaran(pembayaranModel);

		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
		response.setResponseMessage("Perhitungan berhasil dilakukan");
		response.setResponseDescription("Melakukan Perhitungan Transaksi Cicilan Tetap");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(cicilanTetapEntity);

		return ResponseEntity.ok(response);
	}

}
