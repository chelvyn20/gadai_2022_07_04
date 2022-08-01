
package id.co.nds.gadai_2022_07_04.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties.RSocket.Client;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import id.co.nds.gadai_2022_07_04.entities.CicilanEntity;
import id.co.nds.gadai_2022_07_04.entities.DendaEntity;
import id.co.nds.gadai_2022_07_04.entities.InformasiTransaksiEntity;
import id.co.nds.gadai_2022_07_04.entities.PembayaranEntity;
import id.co.nds.gadai_2022_07_04.entities.ProductEntity;
import id.co.nds.gadai_2022_07_04.entities.TransaksiCicilanTetapEntity;
import id.co.nds.gadai_2022_07_04.exceptions.ClientException;
import id.co.nds.gadai_2022_07_04.exceptions.NotFoundException;
import id.co.nds.gadai_2022_07_04.models.PembayaranModel;
import id.co.nds.gadai_2022_07_04.repos.CicilanRepo;
import id.co.nds.gadai_2022_07_04.repos.DendaRepo;
import id.co.nds.gadai_2022_07_04.repos.InformasiTransaksiRepo;
import id.co.nds.gadai_2022_07_04.repos.PembayaranRepo;
import id.co.nds.gadai_2022_07_04.repos.ProductRepo;
import id.co.nds.gadai_2022_07_04.repos.TransaksiRepo;
import id.co.nds.gadai_2022_07_04.validators.PembayaranValidator;

@Service

public class PembayaranService {

	@Autowired
	private TransaksiRepo transaksiRepo;

	@Autowired
	private InformasiTransaksiRepo informasiTransaksiRepo;

	@Autowired
	private CicilanRepo cicilanRepo;

	@Autowired
	private DendaRepo dendaRepo;

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private PembayaranRepo pembayaranRepo;

	PembayaranValidator pembayaranValidator = new PembayaranValidator();
	Double Kembalian;

	public InformasiTransaksiEntity InformasiTransaksi(
			PembayaranModel pembayaranModel)
			throws ClientException, NotFoundException {

		double totalkewajiban = 0;
		double totaldenda = 0;
		double totalpembayaran = 0;
		double sisakewajiban = 0;

		InformasiTransaksiEntity transaksiInfo = new InformasiTransaksiEntity();
		transaksiInfo = informasiTransaksiRepo.findTransaksiByProductCustomer(
				pembayaranModel.getNoTransaksi());
		List<CicilanEntity> cicilan = cicilanRepo
				.findCicilanByTransaksi(pembayaranModel.getNoTransaksi());
		for (int i = 0; i < cicilan.size(); i++) {
			totalkewajiban += cicilan.get(i).getTxPokok()
					* cicilan.get(i).getTxBunga();
		}

		List<DendaEntity> denda = new ArrayList<>();
		dendaRepo.findallByTransaksi(pembayaranModel.getNoTransaksi())
				.forEach(denda::add);

		for (int i = 0; i < denda.size(); i++) {
			totaldenda += denda.get(i).getBiayaDenda();
		}

		totalpembayaran = pembayaranModel.getJumlahInputPembayaran();
		sisakewajiban = (totalkewajiban + totaldenda) - totalpembayaran;

		return transaksiInfo;

	}

	public PembayaranEntity HitungTagihanPembayaran(
			PembayaranModel pembayaranModel)
			throws ClientException, NotFoundException {

		List<CicilanEntity> listcicilan = new ArrayList<>();
		List<DendaEntity> listdenda = new ArrayList();

		double totalcicilan = 0;
		double totaldenda = 0;
		double totaltagihan = 0;
		double biayaAdmTutup = 0;
		double kembalian = 0;
		for (int i = 0; i < pembayaranModel.getSelectedNoCic().size(); i++) {
			CicilanEntity cicilan = new CicilanEntity();
			cicilan = cicilanRepo.listCicilanByTransaksicicilanke(
					pembayaranModel.getNoTransaksi(),
					pembayaranModel.getSelectedNoCic().get(i).getCicilan_ke());
			
			 dendaRepo.listDendaByTransaksiKe(
					pembayaranModel.getNoTransaksi(),
					pembayaranModel.getSelectedNoCic().get(i).getCicilan_ke());
			if (cicilan.getTxStatus().equals("AKTIF")
					|| cicilan.getTxStatus().equals("BELUM AKTIF")
					|| cicilan.getTxStatus().equals("TERLAMBAT")) {

				listcicilan.add(cicilan);
			}
		}

		PembayaranEntity pembayaran = new PembayaranEntity();

		for (int i = 0; i < listcicilan.size(); i++) {
			totalcicilan = listcicilan.get(i).getTxPokok()
					+ listcicilan.get(i).getTxBunga();
		}
		pembayaran.setTotalTagihanCicilan(totalcicilan);

		for (int i = 0; i < listdenda.size(); i++) {
			totaldenda += listdenda.get(i).getBiayaDenda();
		}

		pembayaran.setTotalTagihanDenda(totaldenda);

		TransaksiCicilanTetapEntity transaksi = transaksiRepo
				.findById(pembayaranModel.getNoTransaksi()).orElse(null);

		pembayaran.setBiayaAdmTutup(transaksi.getTxBiayaAdmTutup());
		biayaAdmTutup = transaksi.getTxBiayaAdmTutup();
		double resultdendaCicilan = totalcicilan + totaldenda;
		if (pembayaranModel.getDiskon() > resultdendaCicilan) {
			throw new ClientException(
					"diskon More High than total cicilan Nominal");
		} else {
			totaltagihan += totalcicilan + totaldenda + biayaAdmTutup
					- pembayaranModel.getDiskon();
		}

		double pembulatan = Math.floor(totaltagihan / 10000);
		double resultbulat = pembulatan * 10000;
		double hasilPembulatan = totaltagihan - resultbulat;

		pembayaran.setPembulatan(hasilPembulatan);
		pembayaran.setTotalTagihan(resultbulat);
		pembayaran.setJumlahPembayaran(
				pembayaranModel.getJumlahInputPembayaran());
		kembalian = pembayaranModel.getJumlahInputPembayaran() - resultbulat;
		Kembalian = kembalian;
		pembayaran.setNoTransaksi(pembayaranModel.getNoTransaksi());
		if (pembayaranModel.getMetodeBayar().equals("DEBIT")
				|| pembayaranModel.getMetodeBayar().equals("TRANSFER")
				|| pembayaranModel.getMetodeBayar().equals("CASH")
				|| pembayaranModel.getMetodeBayar().equals("CREDIT")) {
			pembayaran.setMetodeBayar(pembayaranModel.getMetodeBayar());
		} else {
			pembayaranValidator
					.ValidateMetodePembayaran(pembayaranModel.getMetodeBayar());
		}

		return pembayaran;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { Exception.class})
	public PembayaranEntity DoSavePembayaran(PembayaranModel pembayaranModel)
			throws ClientException, NotFoundException {

		PembayaranEntity pembayaran = HitungTagihanPembayaran(pembayaranModel);
		pembayaranRepo.save(pembayaran);
		DoUpdatePembayaran(pembayaran, pembayaranModel);
		return pembayaran;
	}

	public PembayaranEntity DoUpdatePembayaran(PembayaranEntity pembayaran,
			PembayaranModel pembayaranModel)
			throws ClientException, NotFoundException {

		LocalDateTime now = LocalDateTime.now();
		Date TglBayar = Date
				.from(now.atZone(ZoneId.systemDefault()).toInstant());
		// cicilanRepo.UpdateCicilan(pembayaran.getMetodeBayar(), TglBayar,
		// pembayaran.getNoTransaksi(), CicilanKe)
		for (int i = 0; i < pembayaranModel.getSelectedNoCic().size(); i++) {
			cicilanRepo.UpdateCicilan(pembayaran.getNoPembayaran(), TglBayar,
					pembayaran.getNoTransaksi(),
					pembayaranModel.getSelectedNoCic().get(i).getCicilan_ke());

			dendaRepo.updateDendaByTransaksiKe(TglBayar,
					pembayaran.getNoPembayaran(), pembayaran.getNoTransaksi(),
					pembayaranModel.getSelectedNoCic().get(i).getCicilan_ke());
		}

		if (Kembalian >= 0) {
			transaksiRepo.updateStatusByTransaksi(pembayaran.getNoTransaksi());
		}

		return null;
	}

	
}
