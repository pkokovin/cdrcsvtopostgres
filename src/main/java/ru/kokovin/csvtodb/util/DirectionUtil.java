package ru.kokovin.csvtodb.util;

import org.hibernate.Session;
import ru.kokovin.csvtodb.model.Direction;

import java.util.List;

public class DirectionUtil {
    private DirectionUtil() {}
    public static Direction findDirection(String destination) {
        String dest = "";
        if (destination.length() > 10 && !destination.startsWith("8812")) {
            dest = destination.substring(0, destination.length() - 4);
        } else if (destination.length() < 11 || destination.startsWith("8812") ){
            dest = destination;
            Direction direction = new Direction();
            if (destination.startsWith("0")) {
                direction.setId(61068l);
                direction.setPattern("0..$");
            } else if(destination.startsWith("8812")) {
                direction.setId(54850l);
                direction.setPattern("8812");
            } else if (destination.startsWith("812")) {
                direction.setId(61280l);
                direction.setPattern("812");
            } else {
                direction.setId(61067l);
                direction.setPattern("[1-7,9].{6,}");
            }
            direction.setDescription("Санкт-Петербург");
            direction.setZone(1);
            return direction;
        }
        for (int i = 0; i < dest.length(); i++) {
            String prefix = dest.substring(0, dest.length()-i);
            try (Session session = HibernateUtil.getSessionFactory().openSession()){
                List<Direction> directions = session
                        .getNamedNativeQuery("GET_DIRECTIONS_BY_DEST")
                        .setParameter("route_pattern", prefix).getResultList();
                if (directions.size()>0) {
                    return directions.get(0);
                }
            }
        }
        return new Direction();
    }
}
