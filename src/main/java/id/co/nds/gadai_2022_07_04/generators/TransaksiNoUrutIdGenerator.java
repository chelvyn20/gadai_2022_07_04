package id.co.nds.gadai_2022_07_04.generators;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class TransaksiNoUrutIdGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor ssci,
			Object o) throws HibernateException {
		// TODO Auto-generated method stub
		Connection connection = ssci.connection();

		try {
			PreparedStatement ps = connection.prepareStatement(
					"SELECT COUNT(*) AS seq FROM tx_transaksi_barang");
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int seq = rs.getInt("seq") + 1;
				
				return seq;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
		
	
	}
	
}
