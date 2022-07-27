
package id.co.nds.gadai_2022_07_04.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.nds.gadai_2022_07_04.entities.DendaEntity;
import id.co.nds.gadai_2022_07_04.entities.ProductEntity;
import id.co.nds.gadai_2022_07_04.entities.TransaksiCicilanTetapEntity;
import id.co.nds.gadai_2022_07_04.exceptions.ClientException;
import id.co.nds.gadai_2022_07_04.repos.DendaRepo;
import id.co.nds.gadai_2022_07_04.repos.ProductRepo;
import id.co.nds.gadai_2022_07_04.repos.TransaksiRepo;

@Service
public class DendaService {

	@Autowired
	private DendaRepo dendaRepo;

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private TransaksiRepo transaksiRepo;

	public List<DendaEntity> findAllByTransaksiCicilan(String noTransaksi,
			Integer cicilanKe) {

		List<DendaEntity> denda = new ArrayList<>();
		dendaRepo.listDendaByTransaksiCicilanke(noTransaksi, cicilanKe)
				.forEach(denda::add);

		return denda;
	}

	public DendaEntity add(String noTransaksi, Integer cicilanKe,
			Double txpokok, Double txBunga) throws ClientException {

		double totalcicilan = 0;
		double hasildenda = 0;
		double totaldenda = 0;
		TransaksiCicilanTetapEntity transaksi = new TransaksiCicilanTetapEntity();
		transaksi = transaksiRepo.findById(noTransaksi).orElse(null);

		ProductEntity product = new ProductEntity();
		product = productRepo.findById(transaksi.getProductId()).orElse(null);

		totalcicilan = txpokok + txBunga;
		hasildenda = totalcicilan * (product.getbDKeterlambatanRate() / 100);

		DendaEntity denda = new DendaEntity();

		denda.setNoTransaksi(transaksi.getNoTransaksi());
		denda.setCicilanKe(cicilanKe);
		denda.setTxBunga(txBunga);

		LocalDateTime now = LocalDateTime.now();
		Date Tgl = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());

		denda.setTglDenda(Tgl);
		denda.setBiayaDenda(hasildenda);
		Date TanggalJatuhTemp = transaksi.getTanggalJatuhTempo();
		LocalDateTime cicilanTgl = TanggalJatuhTemp.toInstant()
				.atZone(ZoneId.systemDefault()).toLocalDateTime();

		return dendaRepo.save(denda);

	}

	public DendaEntity addDendaBerikut(String noTransaksi, Integer cicilanKe,
			Double txpokok, Double txBunga) throws ClientException {

		double totalcicilan = 0;
		double hasildenda = 0;
		double totaldenda = 0;
		TransaksiCicilanTetapEntity transaksi = new TransaksiCicilanTetapEntity();
		transaksi = transaksiRepo.findById(noTransaksi).orElse(null);

		ProductEntity product = new ProductEntity();
		product = productRepo.findById(transaksi.getProductId()).orElse(null);

		totalcicilan = txpokok + txBunga;
		hasildenda = totalcicilan * (product.getbDKeterlambatanRate() / 100);

		DendaEntity denda = new DendaEntity();

		denda.setNoTransaksi(transaksi.getNoTransaksi());
		denda.setCicilanKe(cicilanKe);
		denda.setTxBunga(txBunga);

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime plusDays = now.plusDays(3);
		Date Tgl = Date.from(plusDays.atZone(ZoneId.systemDefault()).toInstant());

		denda.setTglDenda(Tgl);
		denda.setBiayaDenda(hasildenda);

		return dendaRepo.save(denda);
	}
}
