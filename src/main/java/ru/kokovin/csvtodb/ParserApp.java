package ru.kokovin.csvtodb;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import ru.kokovin.csvtodb.model.Record;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static ru.kokovin.csvtodb.util.ParserUtil.parse;
import static ru.kokovin.csvtodb.util.ValidationUtil.*;

import ru.kokovin.csvtodb.util.HibernateUtil;

@Slf4j
public class ParserApp {
    List<Record> recordList = null;
    Charset utf8 = StandardCharsets.UTF_8;

    public void parseDir(String path) throws IOException {
        File parseDir = new File(path);
        File[] files = parseDir.listFiles();
        if (files != null) {
            for (
                    File curr : files) {
                Reader in = new FileReader(curr);
                Iterable<CSVRecord> records = null;
                if (curr.isFile()
                        && curr.getName().startsWith("Master")
                        && curr.getName().endsWith(".csv")) {
                    records = CSVFormat.DEFAULT
                            .withIgnoreSurroundingSpaces()
//                        .withNullString("")
                            .parse(in);
                }
                if (records != null) {
                    for (CSVRecord rec : records) {
                        log.info("Trying to parse record: " + rec);
                        Record record = parse(rec);
                        log.info("Inserting record: " + record.toString());
                        save(record);
                    }
                }
                String absPath = curr.getAbsolutePath();
                curr.renameTo(new File(absPath + ".bak"));
            }
        }
    }

    private static void save(Record record) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            try {
                session.saveOrUpdate(record);
            } catch (Exception e) {
                String message = getRootCause(e).getMessage();
                log.info(message);
                if (message.contains("cdr_unique_const")) {
                    if (transaction != null) {
                        transaction.rollback();
                    }
                }
                if (!message.contains("cdr_unique_const")) {
                    throw e;
                }
            }
            if (transaction.getStatus().equals(TransactionStatus.ACTIVE)) {
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }
}
