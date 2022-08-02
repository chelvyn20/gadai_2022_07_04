package id.co.nds.gadai_2022_07_04.generators;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class PembayaranNoGenerator implements IdentifierGenerator{
    @Override
    public Serializable generate(SharedSessionContractImplementor ssci, Object o) throws HibernateException {
        Connection connection = ssci.connection();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) AS seq FROM TX_PEMBAYARAN_H");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int seq = rs.getInt("seq") + 1;
                String code = String.format("PCT-YYMM%05d", seq);
                System.out.println("Generated Stock Code: " + code);
                return code;
            } else {
                throw new HibernateException("Generator gagal membuat pembayaran id");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}