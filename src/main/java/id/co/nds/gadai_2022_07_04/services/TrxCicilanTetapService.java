package id.co.nds.gadai_2022_07_04.services;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import id.co.nds.gadai_2022_07_04.entities.BarangEntity;
import id.co.nds.gadai_2022_07_04.entities.CicilanEntity;
import id.co.nds.gadai_2022_07_04.entities.CicilanTetapEntity;
import id.co.nds.gadai_2022_07_04.entities.CustomerEntity;
import id.co.nds.gadai_2022_07_04.entities.DendaKeterlambatanEntity;
import id.co.nds.gadai_2022_07_04.entities.ProductEntity;
import id.co.nds.gadai_2022_07_04.exceptions.ClientException;
import id.co.nds.gadai_2022_07_04.exceptions.NotFoundException;
import id.co.nds.gadai_2022_07_04.models.CicilanTetapModel;
import id.co.nds.gadai_2022_07_04.models.CustomerModel;
import id.co.nds.gadai_2022_07_04.repos.BarangRepo;
import id.co.nds.gadai_2022_07_04.repos.CicilanRepo;
import id.co.nds.gadai_2022_07_04.repos.CicilanTetapRepo;
import id.co.nds.gadai_2022_07_04.repos.CustomerRepo;
import id.co.nds.gadai_2022_07_04.repos.DendaKeterlambatanRepo;
import id.co.nds.gadai_2022_07_04.repos.ProductRepo;
import id.co.nds.gadai_2022_07_04.repos.specs.CicilanTetapSpec;
import id.co.nds.gadai_2022_07_04.repos.specs.CustomerSpec;
import id.co.nds.gadai_2022_07_04.validators.BarangValidator;
import id.co.nds.gadai_2022_07_04.validators.CicilanTetapValidator;


@Service
public class TrxCicilanTetapService implements Serializable {

    @Autowired
    private CicilanRepo cicilanRepo;

    @Autowired
    private CicilanTetapRepo cicilanTetapRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private BarangRepo barangRepo;

    @Autowired
    private DendaKeterlambatanRepo dendaRepo;


    CicilanTetapValidator cTetapValidator = new CicilanTetapValidator();
    BarangValidator barangValidator = new BarangValidator();

    
    public List<CicilanTetapEntity> doSearchTransCicTetap(CicilanTetapModel cicilanTetapModel) {
        List<CicilanTetapEntity> cicilanTetap = new ArrayList<>();
        CicilanTetapSpec cicilanTetapSpec = new CicilanTetapSpec(cicilanTetapModel);
        cicilanTetapRepo.findAll(cicilanTetapSpec).forEach(cicilanTetap::add);
        return cicilanTetap;
    }
    
    
    
    public CicilanTetapEntity doGetDetailCicTetap(String id) throws ClientException, NotFoundException {
        CicilanTetapEntity cicilantetap = cicilanTetapRepo.findById(id).orElse(null);
        return cicilantetap;
    }
    
    public List<CustomerEntity> doSearchPelanggan(CustomerModel customerModel) {
        List<CustomerEntity> customers = new ArrayList<>();
        CustomerSpec customerSpec = new CustomerSpec(customerModel);
        customerRepo.findAll(customerSpec).forEach(customers::add);
       
        return customers;
    }

    public List<ProductEntity> doGetListProduct() {
        List<ProductEntity> products = new ArrayList<>();
        productRepo.findActiveProduct().forEach(products::add);
        return products;
    }
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public CicilanTetapEntity dohitung(CicilanTetapModel cicilanTetapModel) throws ClientException{

        Long count = productRepo.countProductId((cicilanTetapModel.getProductId()));
        if(count<1){
            throw new ClientException("Id product tidak ditemukan");
  
        } 
        
        cTetapValidator.validateNilaiPencairanPelanggan(cicilanTetapModel.getNilaiPencairanPelanggan());
        cTetapValidator.validateTotalNilaiTaksiran(cicilanTetapModel.getTotalNilaiTaksiran());

        ProductEntity product = productRepo.findById(cicilanTetapModel.getProductId()).orElse( null);
        CicilanTetapEntity cicilanTetap =new CicilanTetapEntity();

        cicilanTetap.setProductId(cicilanTetapModel.getProductId());
        cicilanTetap.setNilaiPencairanPelanggan(cicilanTetapModel.getNilaiPencairanPelanggan());
        cicilanTetap.setTotalNilaiTaksiran(cicilanTetapModel.getTotalNilaiTaksiran());
        cicilanTetap.setDiskonAdmBuka(cicilanTetapModel.getDiskonAdmBuka());

            

        if (cicilanTetapModel.getDiskonAdmBuka()==null){
            cicilanTetap.setDiskonAdmBuka(0.00);
        }
        else{
            cTetapValidator.validateDiskonAdminBuka(cicilanTetapModel.getDiskonAdmBuka());
        }
        
        cicilanTetap.setTxLtv( product.getProductLtv());
        cicilanTetap.setMaxNilaiPinj(cicilanTetapModel.getTotalNilaiTaksiran() * product.getProductLtv());
        
        Double biayaBuka = 0.00;      
        if(product.getProductBiayaAdminBukaTipe().trim().equalsIgnoreCase("persen")){
            biayaBuka = cicilanTetapModel.getNilaiPencairanPelanggan() * (product.getProductBiayaAdminBuka()/100);
        }
        else if( product.getProductBiayaAdminBukaTipe().trim().equalsIgnoreCase("nominal")){
            biayaBuka = product.getProductBiayaAdminBuka();
        }
        cicilanTetap.setBiayaAdmBuka(biayaBuka);
        
        Double biayaAdmBukaAkhir = biayaBuka - (biayaBuka-cicilanTetapModel.getDiskonAdmBuka()/100);
        cicilanTetap.setBiayaAdmBukaAkhir(biayaAdmBukaAkhir);

        Double totalPinjaman = cicilanTetapModel.getNilaiPencairanPelanggan()+biayaAdmBukaAkhir;
        cicilanTetap.setTotalNilaiPinj(totalPinjaman);

        LocalDateTime tanggalSekarang = LocalDateTime.now();
        cicilanTetap.setTglTx(tanggalSekarang);
        cicilanTetap.setTglJatuhTempo(tanggalSekarang.plusMonths(product.getProductJangkaWaktu())); 
        cicilanTetap.setTxBiayaJasaPeny(product.getProductBiayaJasaPeny());

        Double biayaPenyPer = (product.getProductBiayaJasaPeny()*totalPinjaman)/(product.getProductJangkaWaktu()/product.getProductBiayaJasaPenyPeriode());
        cicilanTetap.setTxBiayaJasaPenyPer(biayaPenyPer);

        Double totalJasaPeny = (product.getProductJangkaWaktu()/product.getProductBiayaJasaPenyPeriode()) * biayaPenyPer;
        cicilanTetap.setTotalBiayaJasaPeny(totalJasaPeny);

        Double biayaTutup = 0.00;      
        if(product.getProductBiayaAdminTutupTipe().trim().equalsIgnoreCase("persen")){
            biayaTutup = cicilanTetapModel.getNilaiPencairanPelanggan() * (product.getProductBiayaAdminTutup()/100);
        }
        else if( product.getProductBiayaAdminTutupTipe().trim().equalsIgnoreCase("nominal")){
            biayaTutup = product.getProductBiayaAdminTutup();
        }
        cicilanTetap.setTxBiayaAdmTutup(biayaTutup);
        cicilanTetap.setTotalPengem(totalPinjaman + totalJasaPeny + biayaTutup );
        cicilanTetap.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        
        return cicilanTetap;
    } 


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public CicilanTetapEntity dosave(CicilanTetapModel cicilanTetapModel) throws ClientException{
        

        Long countId = customerRepo.countById((cicilanTetapModel.getCustId()));
        if(countId<1){
            throw new ClientException("Id customer tidak ditemukan");
        }
      
        cTetapValidator.validateNilaiPencairanPelanggan(cicilanTetapModel.getNilaiPencairanPelanggan());

        CustomerEntity customer = customerRepo.findById(cicilanTetapModel.getCustId()).orElse( null);
        if(cicilanTetapModel.getNilaiPencairanPelanggan() > customer.getCustLimitTxn()){
            throw new ClientException("Nilai Pencairan Pelanggan Harus lebih kecil atau sama dengan limit transaksi pelanggan. "+
            "(limitTrx untuk pelanggan dengan id: " + cicilanTetapModel.getCustId()+ " adalah " + customer.getCustLimitTxn());
        }


        cTetapValidator.validateTotalNilaiTaksiran(cicilanTetapModel.getTotalNilaiTaksiran());
        cTetapValidator.validateDiskonAdminBuka(cicilanTetapModel.getDiskonAdmBuka());

       if(cicilanTetapModel.getDaftarBarangGadai().size()<1){
            throw new ClientException("Barang Gadai tidak boleh kosong");
       }
       
        for(Integer i = 0; i < cicilanTetapModel.getDaftarBarangGadai().size(); i++) {
            barangValidator.notNullChekNoTransaksi(cicilanTetapModel.getDaftarBarangGadai().get(i).getNoTransaksi());
            barangValidator.nullCheckNamaBarang(cicilanTetapModel.getDaftarBarangGadai().get(i).getNamaBarang());
            barangValidator.nullCheckKondisiBarang(cicilanTetapModel.getDaftarBarangGadai().get(i).getKondisi());
            barangValidator.nullCheckJumlahBarang(cicilanTetapModel.getDaftarBarangGadai().get(i).getJumlah());
            barangValidator.nullCheckHargaBarang(cicilanTetapModel.getDaftarBarangGadai().get(i).getHargaPerSatuan());

            barangValidator.validateNamaBarang(cicilanTetapModel.getDaftarBarangGadai().get(i).getNamaBarang());
            barangValidator.validateDescBarang(cicilanTetapModel.getDaftarBarangGadai().get(i).getKondisi());
            barangValidator.validateJumlahBarang(cicilanTetapModel.getDaftarBarangGadai().get(i).getJumlah());
            barangValidator.validateHargaBarang(cicilanTetapModel.getDaftarBarangGadai().get(i).getHargaPerSatuan());

        }

        CicilanTetapEntity cicilanTetap = new CicilanTetapEntity();
        cicilanTetap = dohitung(cicilanTetapModel);
        cicilanTetap.setCustId(cicilanTetapModel.getCustId());
        cicilanTetap = cicilanTetapRepo.save(cicilanTetap);
        
        Double totalNilaiTaksiran = 0.00; 
        List<BarangEntity> daftarBarang = new ArrayList<>();
        for(int i = 0; i < cicilanTetapModel.getDaftarBarangGadai().size(); i++) {
            BarangEntity barang = new BarangEntity();
            barang.setNoUrut(i+1);
            barang.setNoTransaksi(cicilanTetap.getNoTransaksi());
            barang.setNamaBarang(cicilanTetapModel.getDaftarBarangGadai().get(i).getNamaBarang());
            barang.setJumlah(cicilanTetapModel.getDaftarBarangGadai().get(i).getJumlah());
            barang.setHargaPerSatuan(cicilanTetapModel.getDaftarBarangGadai().get(i).getHargaPerSatuan());
            barang.setKondisi(cicilanTetapModel.getDaftarBarangGadai().get(i).getKondisi());
            totalNilaiTaksiran += (cicilanTetapModel.getDaftarBarangGadai().get(i).getHargaPerSatuan() * cicilanTetapModel.getDaftarBarangGadai().get(i).getJumlah());
           
            daftarBarang.add(barang);

        }
        barangRepo.saveAll(daftarBarang);
        cicilanTetap.setTotalNilaiTaksiran(totalNilaiTaksiran);
        cicilanTetap.setDaftarBarang(daftarBarang);

        ProductEntity product = productRepo.findById(cicilanTetapModel.getProductId()).orElse(null);
        int totalCil = product.getProductJangkaWaktu()/product.getProductBiayaJasaPenyPeriode();
        Double pokok = cicilanTetap.getTotalNilaiPinj() / totalCil;
        Double bunga = product.getProductBiayaJasaPeny() *pokok;
        List<CicilanEntity> tabelCicilan = new ArrayList<>();
        for(int i = 0; i <totalCil; i++){
            CicilanEntity cicilan = new CicilanEntity();
            cicilan.setNoTransaksi(cicilanTetap.getNoTransaksi());
            cicilan.setCicilanKe(i+1);
            cicilan.setTxPokok(pokok);
            cicilan.setTxBunga(bunga);
            cicilan.setStatusTransaksi("BELUM AKTIF");
            if(i==0){
                cicilan.setStatusTransaksi("AKTIF");
            }
            if (LocalDateTime.now().isAfter(cicilanTetap.getTglTx().plusMonths(i*product.getProductBiayaJasaPenyPeriode()))) {
                cicilan.setStatusTransaksi("AKTIF");
            } 
            
            cicilan.setTglAktif(cicilanTetap.getTglTx().plusMonths(i*product.getProductBiayaJasaPenyPeriode()) );
            LocalDateTime tglJthTempo = cicilanTetap.getTglTx().plusMonths((i+1)*product.getProductBiayaJasaPenyPeriode()).minusDays(1);
            cicilan.setTglJatuhTempo(tglJthTempo);
            cicilan.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            
            tabelCicilan.add(cicilan);
        }
        cicilanRepo.saveAll(tabelCicilan);
       
        
        return cicilanTetapRepo.save(cicilanTetap);
        
    } 

    public List<CicilanEntity> doCheckCicStatus() {
        List<CicilanEntity> cicilan = new ArrayList<>();
        cicilanRepo.findAll().forEach(cicilan::add);

        for(Integer i = 0; i < cicilan.size(); i++) {
            if (LocalDateTime.now().isBefore(cicilan.get(i).getTglAktif())) {
                cicilan.get(i).setStatusTransaksi("BELUM AKTIF");
            } 

            else if (LocalDateTime.now().isAfter(cicilan.get(i).getTglAktif()) && LocalDateTime.now().isBefore(cicilan.get(i).getTglJatuhTempo()) ) {
                cicilan.get(i).setStatusTransaksi("AKTIF");
            } 

            else if (LocalDateTime.now().isAfter(cicilan.get(i).getTglAktif()) && LocalDateTime.now().isAfter(cicilan.get(i).getTglJatuhTempo()) ) {
                cicilan.get(i).setStatusTransaksi("TERLAMBAT");
            } 

            else if ( cicilan.get(i).getTglBayar() != null ) {
                cicilan.get(i).setStatusTransaksi("LUNAS");
            }

            
        }
        cicilanRepo.saveAll(cicilan);

        return cicilan;
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public List<DendaKeterlambatanEntity> doHitungDenda() {
        List<CicilanEntity> cicilan = new ArrayList<>();
        cicilanRepo.findAll().forEach(cicilan::add);

        for(Integer i = 0; i < cicilan.size(); i++) {
            if (cicilan.get(i).getStatusTransaksi().equalsIgnoreCase("TERLAMBAT")) {
                DendaKeterlambatanEntity denda = new DendaKeterlambatanEntity();

                denda.setNoTransaksi(cicilan.get(i).getNoTransaksi());
                denda.setCicilanKe(cicilan.get(i).getCicilanKe());
                denda.setTglDenda(LocalDate.now());
                denda.setBiayaDenda(cicilan.get(i).getTxPokok() * 0.0123);
                dendaRepo.save(denda);
            }
        }

        return null;
    }

}
