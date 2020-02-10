import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.kokovin.csvtodb.model.Record;
import ru.kokovin.csvtodb.util.HibernateUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static ru.kokovin.csvtodb.util.ParserUtil.*;

public class Application {
    public static void main(String[] args) throws IOException {
        File parseDir = new File(".");
        File[] files = parseDir.listFiles();
        for (File curr: files) {
            List<String> records = new ArrayList<>();
            if (curr.isFile()&&curr.getName().startsWith("Master")) {
                records = Files.readAllLines(curr.toPath());
            }
            for (String rec: records) {
                saveOrUpdateRecord(parse(rec));
            }
        }
    }

    public static void saveOrUpdateRecord(Record record) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(record);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }
}
