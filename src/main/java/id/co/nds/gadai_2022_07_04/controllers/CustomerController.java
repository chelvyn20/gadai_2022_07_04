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

import id.co.nds.gadai_2022_07_04.controllers.ControllerGrup.DeletingById;
import id.co.nds.gadai_2022_07_04.controllers.ControllerGrup.GettingAllByCriteria;
import id.co.nds.gadai_2022_07_04.controllers.ControllerGrup.PostingNew;
import id.co.nds.gadai_2022_07_04.controllers.ControllerGrup.UpdatingById;
import id.co.nds.gadai_2022_07_04.entities.CustomerEntity;
import id.co.nds.gadai_2022_07_04.entities.JenisUsahaEntity;
import id.co.nds.gadai_2022_07_04.exceptions.ClientException;
import id.co.nds.gadai_2022_07_04.exceptions.NotFoundException;
import id.co.nds.gadai_2022_07_04.models.CustomerModel;
import id.co.nds.gadai_2022_07_04.models.ResponseModel;
import id.co.nds.gadai_2022_07_04.services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/doInsertPelanggan")
    public ResponseEntity<ResponseModel> doInsertPelanggan(@Validated(PostingNew.class) @RequestBody CustomerModel customerModel) throws ClientException {
       
        CustomerEntity customer = customerService.add(customerModel);

        DateTimeFormatter dtf = DateTimeFormatter
                .ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        ResponseModel response = new ResponseModel();
        response.setResponseCode("200");
        response.setResponseMessage("Sukses Input Data Customer");
        response.setResponseDescription("menginput data Customer");
        response.setResponseTime(dtf.format(now));
        response.setResponseData(customer);

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<ResponseModel> doDisplayAllPelanggan() {
        List<CustomerEntity> customer = customerService.findAll();

        DateTimeFormatter dtf = DateTimeFormatter
                .ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        ResponseModel response = new ResponseModel();
        response.setResponseCode("200");
        response.setResponseMessage("Sukses Input Data Customer");
        response.setResponseDescription("menginput data Customer");
        response.setResponseTime(dtf.format(now));
        response.setResponseData(customer);

        return ResponseEntity.ok(response);
    }

    

    @GetMapping(value = "/doSearchPelanggan")
    public ResponseEntity<ResponseModel> doSearchPelanggan(@Validated(GettingAllByCriteria.class)  @RequestBody CustomerModel customerModel) throws ClientException, NotFoundException {
        List<CustomerEntity> customer = customerService.findAllByCriteria(customerModel);

        DateTimeFormatter dtf = DateTimeFormatter
                .ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        ResponseModel response = new ResponseModel();
        response.setResponseCode("200");
        response.setResponseMessage("Sukses Search Data Customer");
        response.setResponseDescription("Search data Customer");
        response.setResponseTime(dtf.format(now));
        response.setResponseData(customer);

        return ResponseEntity.ok(response);
        
    }

    // doGetDetailPelanggan
    @GetMapping(value = "/doGetDetailPelanggan/{id}")
    public ResponseEntity<ResponseModel> doGetDetailPelanggan(@PathVariable String id) throws ClientException, NotFoundException{
       
        CustomerEntity customer = customerService.findById(id);

        DateTimeFormatter dtf = DateTimeFormatter
                .ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        ResponseModel response = new ResponseModel();
        response.setResponseCode("200");
        response.setResponseMessage("Sukses Search Data Customer");
        response.setResponseDescription("Search data Customer");
        response.setResponseTime(dtf.format(now));
        response.setResponseData(customer);

        return ResponseEntity.ok(response);
        
    }

    @PutMapping(value = "/doUpdatePelanggan")
    public ResponseEntity<ResponseModel> doUpdatePelanggan(@Validated(UpdatingById.class) @RequestBody CustomerModel customerModel) throws ClientException, NotFoundException {

        CustomerEntity customer = customerService.update(customerModel);

        DateTimeFormatter dtf = DateTimeFormatter
                .ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        ResponseModel response = new ResponseModel();
        response.setResponseCode("200");
        response.setResponseMessage("Sukses Ubah Data Customer");
        response.setResponseDescription("Ubah data Customer");
        response.setResponseTime(dtf.format(now));
        response.setResponseData(customer);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/doDeletePelanggan")
    public ResponseEntity<ResponseModel> doDeletePelanggan(@Validated(DeletingById .class) @RequestBody CustomerModel customerModel) throws ClientException, NotFoundException {
        CustomerEntity customer = customerService.delete(customerModel);

        DateTimeFormatter dtf = DateTimeFormatter
                .ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        ResponseModel response = new ResponseModel();
        response.setResponseCode("200");
        response.setResponseMessage("Sukses Hapus Data Customer");
        response.setResponseDescription("Hapus data Customer");
        response.setResponseTime(dtf.format(now));
        response.setResponseData(customer);

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/dogetjenisusaha")
    public ResponseEntity<ResponseModel> doGetListJenisUsaha() {
        List<JenisUsahaEntity> jenisUsaha = customerService.findAllJenisUsaha();

        DateTimeFormatter dtf = DateTimeFormatter
                .ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        ResponseModel response = new ResponseModel();
        response.setResponseCode("200");
        response.setResponseMessage("Sukses Search Data Jenis Usaha");
        response.setResponseDescription("Search data Jenis Usaha");
        response.setResponseTime(dtf.format(now));
        response.setResponseData(jenisUsaha);

        return ResponseEntity.ok(response);
    }

}
