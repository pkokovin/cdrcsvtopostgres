
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kokovin.csvtodb.ParserApp;

import static ru.kokovin.csvtodb.util.DateTimeUtil.*;
import static ru.kokovin.csvtodb.util.ExcellUtil.createExcel;


public class Application {
    private static Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws IOException {
//        ParserApp parserApp = new ParserApp();
////        parserApp.parseDir("C:\\Users\\pashak\\Documents\\LT_CDR\\cdr_2020_mar\\part1_1-24\\");
////        parserApp.parseDir("C:\\Users\\pashak\\Documents\\LT_CDR\\cdr_2020_mar\\test\\");
//        parserApp.parseDir("C:\\Users\\pashak\\Documents\\LT_CDR\\cdr_2020_jun\\");
//        parserApp.parseDir("C:\\Users\\pashak\\Documents\\LT_CDR\\cdr_2020_jul\\");
//        parserApp.parseDir("C:\\Users\\pashak\\Documents\\LT_CDR\\all\\1\\");
////        String dest_folder_february_2020 = "C:\\Users\\pashak\\Documents\\LT_CDR\\cdr_2020_feb\\test\\";
//        String dest_folder_march_2020 = "C:\\Users\\pashak\\Documents\\LT_CDR\\cdr_2020_mar\\test\\";
//        String dest_folder_apr_2020 = "C:\\Users\\pashak\\Documents\\LT_CDR\\cdr_2020_mar\\test\\";


        String dest_folder_last_month_all = "C:\\Users\\pashak\\Documents\\LT_CDR\\";
        String[] prefixes = new String[]{
                "45611",
                "38202",
                "42977",
                "42577",
                "45636",
                "45637",
                "45645",
                "45646",
                "6114",
                "6115"
        };
        for (String prefix : prefixes) {
//            createExcel(prefix, getBegin(Month.JANUARY),
//                    getEnd(Month.JANUARY),
//                    dest_folder_march_all);
//            createExcel(prefix, getBegin(Month.FEBRUARY),
//                    getEnd(Month.FEBRUARY), dest_folder_march_all);
            createExcel(prefix, getBegin(Month.JULY),
                    getEnd(Month.JULY), dest_folder_last_month_all);
        }
    }
}
