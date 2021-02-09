
import java.io.IOException;
import java.time.Month;

import lombok.extern.slf4j.Slf4j;
import ru.kokovin.csvtodb.ParserApp;

import static ru.kokovin.csvtodb.util.DateTimeUtil.*;
import static ru.kokovin.csvtodb.util.ExcellUtil.createExcel;

@Slf4j
public class Application {

    public static void main(String[] args) throws IOException {
        ParserApp parserApp = new ParserApp();
//        path to dir with CDRs
//        like "C:\\dir1\\dir2\\dit3\\"
        parserApp.parseDir("path_to_cdrs");
//        path to dst folder
//        like "C:\\Users\\user\\Documents\\some_dir_name\\"
        String dest_folder_last_month_all = "path_to_output_dir";
        String[] prefixes = new String[]{
//                write prefixes of phone numbers
                "XXXXX"
        };
        for (String prefix : prefixes) {
            createExcel(prefix, getBegin(Month.JANUARY),
                    getEnd(Month.JANUARY), dest_folder_last_month_all);
        }
    }
}
