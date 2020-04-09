package ru.kokovin.csvtodb.util;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.Session;
import ru.kokovin.csvtodb.model.Record;
import ru.kokovin.csvtodb.model.RecordTO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ExcellUtil {
    private ExcellUtil(){
    }

    public static void createExcel(String prefix, String destinationPath) {
        HSSFWorkbook workbook = new HSSFWorkbook();

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            List<Record> recordList = session.getNamedNativeQuery("GET_RECORDS_BY_CLID")
                    .setParameter("clid", prefix).getResultList();
            HSSFSheet sheet = workbook.createSheet(prefix + "xx_" + LocalDateTime.now().getMonth().minus(1));
//            HSSFSheet sheet = workbook.createSheet(prefix + "2020-jan - 2020-24march");

            List<RecordTO> recordTOS = new ArrayList<>();
            for (Record record : recordList) {
                recordTOS.add(new RecordTO(record.getCalldate()
                        .format(DateTimeUtil.FORMATTER), record.getSrc(),
                        record.getDst(), record.getDcontext(), record.getLastdata() ,record.getBillsec()));
            }

            int rowNum = 0;

            Row row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Calldate");
            row.createCell(1).setCellValue("Source");
            row.createCell(2).setCellValue("Destination");
            row.createCell(3).setCellValue("Lastdata");
            row.createCell(4).setCellValue("Billsec");

            for (RecordTO recordTO : recordTOS) {
                createSheetHeader(sheet, ++rowNum, recordTO);
            }

//            try (FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\pashak\\Documents\\LT_CDR\\cdr_2020_feb\\test\\45611bigAsterCalls.xls"))) {
            try (FileOutputStream fos = new FileOutputStream(new File(destinationPath +
                    prefix +"bigAsterCalls" + LocalDateTime.now().getMonth().minus(1)) + ".xls")) {
                workbook.write(fos);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Excell файл успешно создан");

        }

    }

    private static void createSheetHeader(HSSFSheet sheet, int rownum, RecordTO recordTO) {
        Row row = sheet.createRow(rownum);
        row.createCell(0).setCellValue(recordTO.getCalldate());
        row.createCell(1).setCellValue(recordTO.getSource());
        row.createCell(2).setCellValue(recordTO.getDestinadion());
        row.createCell(3).setCellValue(recordTO.getLastdata());
        row.createCell(4).setCellValue(recordTO.getBillsec());
    }
}
