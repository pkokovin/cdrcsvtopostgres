import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.internal.log.ConnectionAccessLogger;
import ru.kokovin.csvtodb.ParserApp;
import ru.kokovin.csvtodb.model.Record;
import ru.kokovin.csvtodb.model.RecordTO;
import ru.kokovin.csvtodb.util.*;

import javax.persistence.EntityManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ru.kokovin.csvtodb.util.ParserUtil.*;
import static ru.kokovin.csvtodb.util.ExcellUtil.*;

public class Application {
    private static Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws IOException {
        ParserApp parserApp = new ParserApp();
////        parserApp.parseDir("C:\\Users\\pashak\\Documents\\LT_CDR\\cdr_2020_mar\\part1_1-24\\");
////        parserApp.parseDir("C:\\Users\\pashak\\Documents\\LT_CDR\\cdr_2020_mar\\test\\");
        parserApp.parseDir("C:\\Users\\pashak\\Documents\\LT_CDR\\all\\");
////        String dest_folder_february_2020 = "C:\\Users\\pashak\\Documents\\LT_CDR\\cdr_2020_feb\\test\\";
//        String dest_folder_march_2020 = "C:\\Users\\pashak\\Documents\\LT_CDR\\cdr_2020_mar\\test\\";
//        String[] prefixes = new String[] {
//                "4561129",
//                "4561130",
//                "4561131",
//                "4561132",
//                "4561133",
//                "4561154"
////                "45611",
////                "38202",
////                "42977",
////                "42577",
////                "45636",
////                "45637",
////                "45645",
////                "45646",
////                "6114",
////                "6115"
//        };
//        for (String prefix: prefixes) {
//            createExcel(prefix, dest_folder_march_2020);
//        }
    }
}
