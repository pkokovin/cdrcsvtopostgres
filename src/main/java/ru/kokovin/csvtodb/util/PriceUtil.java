package ru.kokovin.csvtodb.util;

import org.hibernate.Session;

import java.util.List;

public class PriceUtil {
    private PriceUtil(){}
    public static double findPrice(String destination) {
        String dest = destination.substring(0, destination.length()-4);
        for (int i = 0; i < dest.length(); i++) {
            String prefix = dest.substring(0, dest.length()-i);
            try (Session session = HibernateUtil.getSessionFactory().openSession()){
                List<Object[]> prices = session.createNativeQuery(" select * from price " +
                        " where id = (select price_id from direction d where d.route_pattern =:route_pattern) ")
                        .setParameter("route_pattern", prefix).list();
                if (prices.size()>0) {
                    return Double.valueOf("" + prices.get(0)[2]);
                }
            }
        }
        return .0;
    }
}
