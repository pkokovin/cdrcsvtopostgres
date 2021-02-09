package ru.kokovin.csvtodb.util;

import org.apache.commons.csv.CSVRecord;
import ru.kokovin.csvtodb.model.Record;


import java.util.function.Function;

import static ru.kokovin.csvtodb.util.DateTimeUtil.*;
import static ru.kokovin.csvtodb.util.DirectionUtil.findDirection;

public class ParserUtil {
    public static Record parse(CSVRecord rec) {
        Function<CSVRecord, Record> function = c -> {
            Record record = new Record();
            record.setAccountcode(c.get(0));
            record.setSrc(c.get(1));
            record.setDst(c.get(2));
            record.setDcontext(c.get(3));
            record.setClid(c.get(4));
            record.setChannel(c.get(5));
            record.setDstchannel(c.get(6));
            record.setLastapp(c.get(7));
            record.setLastdata(c.get(8));
            record.setCalldate(convert(c.get(9)));
            record.setDuration(durationParse(c.get(12)));
            record.setBillsec(durationParse(c.get(13)));
            record.setDisposition(c.get(14));
            record.setAmaflags("DOCUMENTATION".equals(c.get(15)) ? 3 : 0);
            record.setUniqueid(c.get(16));
            record.setUserfield(c.get(17));
            record.setDirection(findDirection(record.getDst()));
            return record;
        };
        return function.apply(rec);
    }

    private static long durationParse(String duration) {
        return duration == null || duration.isEmpty() ? 0 : Long.parseLong(duration);
    }

}
