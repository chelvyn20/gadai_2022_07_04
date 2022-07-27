
package id.co.nds.gadai_2022_07_04.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.nds.gadai_2022_07_04.entities.CicilanEntity;
import id.co.nds.gadai_2022_07_04.entities.DendaEntity;
import id.co.nds.gadai_2022_07_04.exceptions.ClientException;
import id.co.nds.gadai_2022_07_04.repos.CicilanRepo;
import id.co.nds.gadai_2022_07_04.repos.DendaRepo;

@Service
public class CicilanService {

	@Autowired
	private DendaRepo dendaRepo;

	@Autowired
	private CicilanRepo cicilanRepo;

	public List<CicilanEntity> findAll() {

		List<CicilanEntity> cicilan = new ArrayList<>();
		cicilanRepo.findAll().forEach(cicilan::add);

		return cicilan;
	}

	public CicilanEntity editAktif(String noTransaksi, Integer cicilanKe)
			throws ClientException {

		CicilanEntity cicilan = cicilanRepo.UpdateStatusAktif(noTransaksi,
				cicilanKe);

		return cicilan;
	}

	public CicilanEntity editTerlambat(String noTransaksi, Integer cicilanKe) {

		CicilanEntity cicilan = cicilanRepo.UpdateStatusTerlambat(noTransaksi,
				cicilanKe);
		return cicilan;
	}
	
	

}
