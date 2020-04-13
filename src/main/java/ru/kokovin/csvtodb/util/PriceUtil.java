package ru.kokovin.csvtodb.util;

import org.hibernate.Session;
import ru.kokovin.csvtodb.model.Price;

import java.util.List;

public class PriceUtil {
    private PriceUtil(){}
    public static double findPrice(String destibation) {
        String dest = destibation.substring(0, destibation.length()-4);
        for (int i = 0; i < dest.length(); i++) {
            String prefix = dest.substring(0, dest.length()-i);
            try (Session session = HibernateUtil.getSessionFactory().openSession()){
//                List<Price> prices = session.createSQLQuery(" select * from price " +
                List<Object[]> prices = session.createNativeQuery(" select * from price " +
                        " where id = (select price_id from direction d where d.route_pattern =:route_pattern) ")
                        .setParameter("route_pattern", prefix).list();
                if (prices.size()>0) {
//                    return (Math.round(prices.get(0)*100))/100;
                    return Double.valueOf("" + prices.get(0)[2]);
                }
            }
        }
        return .0;
    }
}
