package ru.kokovin.csvtodb;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import ru.kokovin.csvtodb.model.Record;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static ru.kokovin.csvtodb.util.ParserUtil.parse;
import static ru.kokovin.csvtodb.util.ValidationUtil.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kokovin.csvtodb.util.HibernateUtil;

public class ParserApp {
    private static Logger log = LoggerFactory.getLogger(ParserApp.class);
    List<Record> recordList = null;
    Charset utf8 = StandardCharsets.UTF_8;

    public void parseDir(String path) throws IOException {
        File parseDir = new File(path);
        File[] files = parseDir.listFiles();
        for (
                File curr : files) {
            List<String> records = new ArrayList<>();
            if (curr.isFile() && curr.getName().startsWith("Master")) {
                records = Files.readAllLines(curr.toPath());
            }
            for (String rec : records) {
                log.info("Trying to parse record: " + rec);
                Record record = parse(rec);
                log.info("Inserting record: " + record.toString());
                save(record);
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