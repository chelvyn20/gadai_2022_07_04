package id.co.nds.gadai_2022_07_04.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.nds.gadai_2022_07_04.controllers.GroupController.PostingNew;
import id.co.nds.gadai_2022_07_04.entities.CicilanTetapEntity;
import id.co.nds.gadai_2022_07_04.entities.CustomerEntity;
import id.co.nds.gadai_2022_07_04.entities.ProductEntity;
import id.co.nds.gadai_2022_07_04.exceptions.ClientException;
import id.co.nds.gadai_2022_07_04.exceptions.NotFoundException;
import id.co.nds.gadai_2022_07_04.models.CicilanTetapModel;
import id.co.nds.gadai_2022_07_04.models.CustomerModel;
import id.co.nds.gadai_2022_07_04.models.ResponseModel;
import id.co.nds.gadai_2022_07_04.services.TrxCicilanTetapService;

@RestController
@RequestMapping("/cicilan")
public class CicilanTetapController {
    @Autowired
    private TrxCicilanTetapService cicilanService;
    
    @GetMapping(value = "/dosearchcicilantetap")
	public ResponseEntity<ResponseModel> doSearchTransCicTetap(
			@RequestBody CicilanTetapModel cicilanTetapModel) throws ClientException {

		List<CicilanTetapEntity> cicilanTetap =  cicilanService.doSearchTransCicTetap(cicilanTetapModel);

		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
        response.setResponseMessage("Sukses Search Data Cicilan Tetap");
        response.setResponseDescription("Search data Transaksi Cicilan Tetap");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(cicilanTetap);

		return ResponseEntity.ok(response);
	}
    @GetMapping(value = "/dogetdetailcictetap/{id}")
    public ResponseEntity<ResponseModel> doGetDetailCicTetap(@PathVariable String id) throws ClientException, NotFoundException{
       
        CicilanTetapEntity cicilan = cicilanService.doGetDetailCicTetap(id);

        DateTimeFormatter dtf = DateTimeFormatter
                .ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        ResponseModel response = new ResponseModel();
        response.setResponseCode("200");
        response.setResponseMessage("Sukses Search Data Cicilan Tetap");
        response.setResponseDescription("Search data Cicilan Tetap");
        response.setResponseTime(dtf.format(now));
        response.setResponseData(cicilan);

        return ResponseEntity.ok(response);
        
    }
    
    @GetMapping(value = "/dosearchpelanggan")
	public ResponseEntity<ResponseModel> dosearchpelanggan(
			@RequestBody CustomerModel customerModel) throws ClientException {

		List<CustomerEntity> customer =  cicilanService.doSearchPelanggan(customerModel);

		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
        response.setResponseMessage("Sukses Search Data Pelanggan");
        response.setResponseDescription("Search data Pelanggan");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(customer);

		return ResponseEntity.ok(response);
	}

    @GetMapping(value = "/dogetlistproduct")
	public ResponseEntity<ResponseModel> doGetListProduct() {

		List<ProductEntity> products =  cicilanService.doGetListProduct();

		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
        response.setResponseMessage("Sukses Search Data Produk");
        response.setResponseDescription("Search data Produk");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(products);

		return ResponseEntity.ok(response);
	}


    @GetMapping(value = "/dohitung")
	public ResponseEntity<ResponseModel> dohitung(
			@RequestBody CicilanTetapModel cicilanTetapModel) throws ClientException {

		CicilanTetapEntity cicilanTetapEntity =  cicilanService.dohitung(cicilanTetapModel);

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

    @PostMapping(value = "/dosave")
	public ResponseEntity<ResponseModel> dosave(@Validated(PostingNew.class)
			@RequestBody CicilanTetapModel cicilanTetapModel) throws ClientException {

		CicilanTetapEntity cicilanTetapEntity =  cicilanService.dosave(cicilanTetapModel);

		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("uuuu/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		ResponseModel response = new ResponseModel();
		response.setResponseCode("200");
		response.setResponseMessage("Cicilan Tetap Berhasil Disimpan");
		response.setResponseDescription("Menambahkan Transaksi Cicilan Tetap");
		response.setResponseTime(dtf.format(now));
		response.setResponseData(cicilanTetapEntity);

		return ResponseEntity.ok(response);
	}
}
