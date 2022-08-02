package id.co.nds.gadai_2022_07_04.services;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import id.co.nds.gadai_2022_07_04.entities.CicilanEntity;
import id.co.nds.gadai_2022_07_04.entities.CicilanTetapEntity;
import id.co.nds.gadai_2022_07_04.entities.CicilanTetapInfoEntity;
import id.co.nds.gadai_2022_07_04.entities.DendaKeterlambatanEntity;
import id.co.nds.gadai_2022_07_04.entities.PembayaranEntity;
import id.co.nds.gadai_2022_07_04.exceptions.ClientException;
import id.co.nds.gadai_2022_07_04.exceptions.NotFoundException;
import id.co.nds.gadai_2022_07_04.models.CicilanTetapModel;
import id.co.nds.gadai_2022_07_04.models.PembayaranModel;
import id.co.nds.gadai_2022_07_04.repos.CicilanRepo;
import id.co.nds.gadai_2022_07_04.repos.CicilanTetapInfoRepo;
import id.co.nds.gadai_2022_07_04.repos.CicilanTetapRepo;
import id.co.nds.gadai_2022_07_04.repos.CustomerRepo;
import id.co.nds.gadai_2022_07_04.repos.DendaKeterlambatanRepo;
import id.co.nds.gadai_2022_07_04.repos.PembayaranRepo;
import id.co.nds.gadai_2022_07_04.repos.ProductRepo;
import id.co.nds.gadai_2022_07_04.repos.specs.CicilanTetapSpec;
import id.co.nds.gadai_2022_07_04.validators.PembayaranValidator;

@Service
public class PembayaranService implements Serializable{
    
    @Autowired
    CicilanRepo cicilanRepo;

    @Autowired
    PembayaranRepo pembayaranRepo;

    @Autowired
    CicilanTetapRepo cicilanTetapRepo;

    @Autowired
    CicilanTetapInfoRepo cicilanTetapInfoRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    ProductRepo productRepo;
    @Autowired
    DendaKeterlambatanRepo dendaRepo;

    PembayaranValidator pembayaranValidator = new PembayaranValidator();
    
    public List<CicilanTetapEntity> doSearchBayarCicTetap(CicilanTetapModel cicilanTetapModel) {
        List<CicilanTetapEntity> cicilanTetap = new ArrayList<>();
        CicilanTetapSpec cicilanTetapSpec = new CicilanTetapSpec(cicilanTetapModel);
        cicilanTetapRepo.findAll(cicilanTetapSpec).forEach(cicilanTetap::add);
        return cicilanTetap;
    }

    public CicilanTetapInfoEntity doGetDetailTagihanCic(CicilanTetapModel cicilanTetapModel) {
        CicilanTetapInfoEntity cicilanTetaps = cicilanTetapInfoRepo.find(cicilanTetapModel.getNoTransaksi());
        return cicilanTetaps;
    }
    

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public CicilanTetapInfoEntity doUpdatePembayaran(PembayaranModel pembayaranModel) throws ClientException, NotFoundException {
        CicilanTetapInfoEntity cicilanTetap = cicilanTetapInfoRepo.find(pembayaranModel.getNoTransaksi());
        List<CicilanEntity> cicilan = cicilanRepo.getPembayaranByNoTrans(pembayaranModel.getNoTransaksi());
        
        
        Double totalCicilan = 0.00;
        Double totalBunga = 0.00;
        Double totalDenda = 0.00;
        Double adminTutup = 0.00;
        for (int i=0 ; i<pembayaranModel.getSelectedNoCic().size(); i++){
            if(cicilan.get(pembayaranModel.getSelectedNoCic().get(i)-1).getNoPembayaran() != null){
                throw new ClientException("Cicilan ke - " + pembayaranModel.getSelectedNoCic().get(i) + " sudah dibayar");
            }

            totalCicilan += (cicilan.get(pembayaranModel.getSelectedNoCic().get(i)-1).getTxPokok())+(cicilanTetap.getTotalBiayaJasaPeny());
            totalBunga += cicilan.get(pembayaranModel.getSelectedNoCic().get(i)-1).getTxBunga();
            
            DendaKeterlambatanEntity denda = dendaRepo.findByNoTransaksiCicilanKe(pembayaranModel.getNoTransaksi(), pembayaranModel.getSelectedNoCic().get(i));
            
            if (denda==null){
                totalDenda += 0.00;
            } else{
                totalDenda += denda.getBiayaDenda();
            }
            
            if (pembayaranModel.getSelectedNoCic().get(i) == cicilan.size()){
                adminTutup += cicilanTetap.getTxBiayaAdmTutup();
            }

        }
        
        
        Double diskon = pembayaranModel.getDiskon();
      
        pembayaranValidator.validateDiskon(diskon, cicilanTetap.getTotalBiayaJasaPeny(), totalDenda);

        Double totalTagihan = totalCicilan+totalBunga+totalDenda-diskon+adminTutup;
        
        pembayaranValidator.validateJumlahPembayaran(pembayaranModel.getJumlahPembayaran(), totalTagihan);
        pembayaranValidator.validateMetodePembayaran(pembayaranModel.getMetodeBayar());
        
        PembayaranEntity pembayaran = new PembayaranEntity();
        pembayaran.setNoTransaksi(pembayaranModel.getNoTransaksi());
        pembayaran.setTotalTagihanCicilan(totalCicilan);
        pembayaran.setTotalTagihanDenda(totalDenda);
        pembayaran.setBiayaAdmTutup(adminTutup);
        pembayaran.setTotalTagihan(totalTagihan);
        pembayaran.setPembulatan(totalTagihan%10000);
        pembayaran.setJumlahPembayaran(pembayaranModel.getJumlahPembayaran());
        pembayaran.setMetodeBayar(pembayaranModel.getMetodeBayar());
        pembayaranRepo.save(pembayaran);
        
        for(Integer i = 0; i < pembayaranModel.getSelectedNoCic().size(); i++) {
            cicilan.get(pembayaranModel.getSelectedNoCic().get(i)-1).setStatusTransaksi("DIBAYAR");
            cicilan.get(pembayaranModel.getSelectedNoCic().get(i)-1).setTglBayar(LocalDateTime.now());
            cicilan.get(pembayaranModel.getSelectedNoCic().get(i)-1).setNoPembayaran(pembayaran.getNoPembayaran());

            DendaKeterlambatanEntity denda = dendaRepo.findByNoTransaksiCicilanKe(pembayaranModel.getNoTransaksi(), pembayaranModel.getSelectedNoCic().get(i));
            if(denda!=null){
                denda.setNoPembayaran(pembayaran.getNoPembayaran());
                denda.setTglByrDenda(LocalDate.now());
            }
            
        }

        Integer count = 0;
        for(Integer i = 0; i < cicilan.size(); i++) {
            if(cicilan.get(i).getNoPembayaran() != null) {
                count++;
                System.out.println(count);
            }
        }

        System.out.println(cicilan.size());
        if(count == cicilan.size()) {
            cicilanTetap.setStatus("LUNAS");
        }
        
        return cicilanTetap;
    }
}