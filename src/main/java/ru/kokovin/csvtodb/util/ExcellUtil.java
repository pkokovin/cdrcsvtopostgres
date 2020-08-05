package ru.kokovin.csvtodb.util;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kokovin.csvtodb.model.Record;
import ru.kokovin.csvtodb.model.RecordTO;
import static ru.kokovin.csvtodb.util.PriceUtil.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ExcellUtil {
    private static Logger log = LoggerFactory.getLogger(ExcellUtil.class);
    private ExcellUtil(){
    }

    public static void createExcel(String prefix, LocalDateTime begin, LocalDateTime end, String destinationPath) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        Workbook[] wbs = new Workbook[] {new HSSFWorkbook(), new XSSFWorkbook()};

        for (int i = 0; i<wbs.length; i++) {
            Workbook wb = wbs[i];
            CreationHelper createHelper = wb.getCreationHelper();

            List<RecordTO> recTOS = new ArrayList<>();
            try(Session session = HibernateUtil.getSessionFactory().openSession()) {

                List<Record> recordList = session.getNamedNativeQuery("GET_RECORDS_BY_CLID")
                        .setParameter("clid", prefix)
                        .setParameter("d1", begin)
                        .setParameter("d2", end)
                        .getResultList();

                for (Record record : recordList) {
                    recTOS.add(new RecordTO(record.getCalldate()
                            .format(DateTimeUtil.FORMATTER), record.getSrc(),
                            record.getDst(), record.getDcontext(), record.getLastdata() ,record.getBillsec()));
                }
            }
//            create a new sheet
            Sheet s = wb.createSheet(prefix + "xx_" + LocalDateTime.now().getMonth().minus(1));
            s.setColumnWidth(0, 5000);
            s.setColumnWidth(1, 3000);
            s.setColumnWidth(2, 4000);
            s.setColumnWidth(3, 7000);
            s.setColumnWidth(4, 2000);
            s.setColumnWidth(5, 4000);
            s.setColumnWidth(6, 4000);
            s.setColumnWidth(7, 4000);

            int rNum = 0;

            Row row = s.createRow(rNum);
            row.createCell(0).setCellValue("Calldate");
            row.createCell(1).setCellValue("Source");
            row.createCell(2).setCellValue("Destination");
            row.createCell(3).setCellValue("Lastdata");
            row.createCell(4).setCellValue("Billsec");
            row.createCell(5).setCellValue("Duration in minutes");
            row.createCell(6).setCellValue("Cost of minute");
            row.createCell(7).setCellValue("Cost of Call");

            for (RecordTO recordTO : recTOS) {
                createSheetHeader(s, ++rNum, recordTO);
            }

            try (FileOutputStream fos = new FileOutputStream(new File(destinationPath +
                    prefix +"_bigAsterCalls_" + (begin.getMonth() == end.getMonth()? (begin.getMonth()).toString()
                    : (begin.getMonth().toString().concat("-").concat(end.getMonth().toString())) ) ) + "XSSF.xlsx")) {
                wb.write(fos);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            System.out.println("Excell файл успешно создан");
            log.info("Excell new файл успешно создан");

        }

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            List<Record> recordList = session.getNamedNativeQuery("GET_RECORDS_BY_CLID")
                    .setParameter("clid", prefix)
                    .setParameter("d1", begin)
                    .setParameter("d2", end)
                    .getResultList();
            HSSFSheet sheet = workbook.createSheet(prefix + "xx_" + LocalDateTime.now().getMonth().minus(1));
//            HSSFSheet sheet = workbook.createSheet(prefix + "-2020-jan");
            sheet.setColumnWidth(0, 5000);
            sheet.setColumnWidth(1, 3000);
            sheet.setColumnWidth(2, 4000);
            sheet.setColumnWidth(3, 7000);
            sheet.setColumnWidth(4, 2000);
            sheet.setColumnWidth(5, 4000);
            sheet.setColumnWidth(6, 4000);
            sheet.setColumnWidth(7, 4000);

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
            row.createCell(5).setCellValue("Duration in minutes");
            row.createCell(6).setCellValue("Cost of minute");
            row.createCell(7).setCellValue("Cost of Call");

            for (RecordTO recordTO : recordTOS) {
                createSheetHeader(sheet, ++rowNum, recordTO);
            }

            try (FileOutputStream fos = new FileOutputStream(new File(destinationPath +
                    prefix +"_bigAsterCalls_" + (begin.getMonth() == end.getMonth()? (begin.getMonth()).toString()
                    : (begin.getMonth().toString().concat("-").concat(end.getMonth().toString())) ) ) + ".xls")) {
                workbook.write(fos);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            System.out.println("Excell файл успешно создан");
            log.info("Excell файл успешно создан");
        }

    }

    private static void createSheetHeader(Sheet sheet, int rownum, RecordTO recordTO) {
        Row row = sheet.createRow(rownum);
        row.createCell(0).setCellValue(recordTO.getCalldate());
        row.createCell(1).setCellValue(recordTO.getSource());
        row.createCell(2).setCellValue(recordTO.getDestination());
        row.createCell(3).setCellValue(recordTO.getLastdata());
        row.createCell(4).setCellValue(recordTO.getBillsec());
        long billMin = recordTO.getBillsec()%60==0?recordTO.getBillsec()/60:recordTO.getBillsec()/60+1;
        row.createCell(5).setCellValue(billMin);
        double price = findPrice(recordTO.getDestination());
        row.createCell(6).setCellValue(price);
        row.createCell(7).setCellValue(billMin * price);
    }

    private static void createSheetHeader(HSSFSheet sheet, int rownum, RecordTO recordTO) {
        Row row = sheet.createRow(rownum);
        row.createCell(0).setCellValue(recordTO.getCalldate());
        row.createCell(1).setCellValue(recordTO.getSource());
        row.createCell(2).setCellValue(recordTO.getDestination());
        row.createCell(3).setCellValue(recordTO.getLastdata());
        row.createCell(4).setCellValue(recordTO.getBillsec());
        long billMin = recordTO.getBillsec()%60==0?recordTO.getBillsec()/60:recordTO.getBillsec()/60+1;
        row.createCell(5).setCellValue(billMin);
        double price = findPrice(recordTO.getDestination());
        row.createCell(6).setCellValue(price);
        row.createCell(7).setCellValue(billMin * price);
    }
}
