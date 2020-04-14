package ru.kokovin.csvtodb.util;

import ru.kokovin.csvtodb.model.Record;


import javax.transaction.Transactional;

import static ru.kokovin.csvtodb.util.DateTimeUtil.*;
import static ru.kokovin.csvtodb.util.DirectionUtil.findDirection;

public class ParserUtil {
    public static Record parse(String rec) {
        Record record = new Record();
        if (!rec.contains("Dial")
                && !rec.contains("Queue")
                && !rec.contains("GosubIf")
                && !rec.matches("(.*),\\s(.*)")
                && !rec.matches("(.*)\\s,(.*)")
                && !rec.matches("(.*)\\w,(.*)")
                && !rec.matches("(.*),\\w(.*)")) {
            String[] params = rec.split(",");
            record.setAccountcode(cut(params[0]));
            record.setSrc(cut(params[1]));
            record.setDst(cut(params[2]));
            record.setDcontext(cut(params[3]));
            record.setClid(cut(params[4]));
            record.setChannel(cut(params[5]));
            record.setDstchannel(cut(params[6]));
            record.setLastapp(cut(params[7]));
            record.setLastdata(cut(params[8]));
            record.setCalldate(convert(cut(params[9])));
            record.setDuration(durationParse(params[12]));
            record.setBillsec(durationParse(params[13]));
            record.setDisposition(cut(params[14]));
            record.setAmaflags(cut(params[15]).equals("DOCUMENTATION") ? 3 : 0);
            record.setUniqueid(cut(params[16]));
            record.setUserfield(cut(params[17]));
            record.setDirection(findDirection(record.getDst()));
        } else if ((rec.contains("Dial") && rec.contains("ANSWERED"))) {
            String[] paramsFirst = rec.split("\",\"");
            String[] paramsSec = rec.split(",");
            record.setAccountcode(paramsFirst[0]);
            record.setSrc(paramsFirst[1]);
            record.setDst(paramsFirst[2]);
            record.setDcontext(paramsFirst[3]);
            record.setClid(paramsFirst[4]);
            record.setChannel(paramsFirst[5]);
            record.setDstchannel(paramsFirst[6]);
            record.setLastapp(paramsFirst[7]);
            record.setLastdata(paramsFirst[8]);
            record.setCalldate(convert(paramsFirst[9]));
            record.setDuration(durationParse(paramsSec[paramsSec.length - 6]));
            record.setBillsec(durationParse(paramsSec[paramsSec.length - 5]));
            record.setDisposition(cut(paramsSec[paramsSec.length - 4]));
            record.setAmaflags(cut(paramsSec[paramsSec.length - 3]).equals("DOCUMENTATION") ? 3 : 0);
            record.setUniqueid(cut(paramsSec[paramsSec.length - 2]));
            record.setUserfield(cut(paramsSec[paramsSec.length - 1]));
            record.setDirection(findDirection(record.getDst()));
        } else {
            String[] paramsFirst = rec.split("\",\"");
            String[] paramsSec = rec.split(",");
            record.setAccountcode(paramsFirst[0]);
            record.setSrc(paramsFirst[1]);
            record.setDst(paramsFirst[2]);
            record.setDcontext(paramsFirst[3]);
            record.setClid(paramsFirst[4]);
            record.setChannel(paramsFirst[5]);
            record.setDstchannel(paramsFirst[6]);
            record.setLastapp(paramsFirst[7]);
            record.setLastdata(paramsFirst[8]);
            record.setCalldate(convert(cut(paramsSec[paramsSec.length - 9])));
            record.setDuration(durationParse(paramsSec[paramsSec.length - 6]));
            record.setBillsec(durationParse(paramsSec[paramsSec.length - 5]));
            record.setDisposition(cut(paramsSec[paramsSec.length - 4]));
            record.setAmaflags(cut(paramsSec[paramsSec.length - 3]).equals("DOCUMENTATION") ? 3 : 0);
            record.setUniqueid(cut(paramsSec[paramsSec.length - 2]));
            record.setUserfield(cut(paramsSec[paramsSec.length - 1]));
            record.setDirection(findDirection(record.getDst()));
        }
        return record;
    }

    private static long durationParse(String duration) {
        return duration == null || duration.isEmpty() ? 0 : Long.parseLong(duration);
    }

    private static String cut(String row) {
        String result = "";
        if (row != null && row.startsWith("\"") && row.endsWith("\"")) {
            result = row.substring(1, row.length() - 1);
        }
        return result;
    }

}
