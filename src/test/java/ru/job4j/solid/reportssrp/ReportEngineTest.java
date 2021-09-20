package ru.job4j.solid.reportssrp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Ignore;
import org.junit.Test;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHTMLReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 100);
        store.add(worker1);
        store.add(worker2);
        Report engine = new ItReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE html>")
                .append(System.lineSeparator())
                .append("<html lang=\"en\"")
                .append(System.lineSeparator())
                .append("      xmlns=\"http://www.w3.org/1999/xhtml\">")
                .append(System.lineSeparator())
                .append("<head>")
                .append(System.lineSeparator())
                .append("</head>")
                .append(System.lineSeparator())
                .append("<body>")
                .append(System.lineSeparator())
                .append("<h1>Ivan</h1>")
                .append(System.lineSeparator())
                .append("<h2>")
                .append(worker1.getHired().getTime())
                .append("</h2>")
                .append(System.lineSeparator())
                .append("<h3>")
                .append(worker1.getFired().getTime())
                .append("</h3>")
                .append(System.lineSeparator())
                .append("<h4>")
                .append(worker1.getSalary())
                .append("</h4>")
                .append(System.lineSeparator())
                .append("</body>")
                .append(System.lineSeparator())
                .append("</html>")
                .append("<!DOCTYPE html>")
                .append(System.lineSeparator())
                .append("<html lang=\"en\"")
                .append(System.lineSeparator())
                .append("      xmlns=\"http://www.w3.org/1999/xhtml\">")
                .append(System.lineSeparator())
                .append("<head>")
                .append(System.lineSeparator())
                .append("</head>")
                .append(System.lineSeparator())
                .append("<body>")
                .append(System.lineSeparator())
                .append("<h1>Petr</h1>")
                .append(System.lineSeparator())
                .append("<h2>")
                .append(worker2.getHired().getTime())
                .append("</h2>")
                .append(System.lineSeparator())
                .append("<h3>")
                .append(worker2.getFired().getTime())
                .append("</h3>")
                .append(System.lineSeparator())
                .append("<h4>")
                .append(worker2.getSalary())
                .append("</h4>")
                .append(System.lineSeparator())
                .append("</body>")
                .append(System.lineSeparator())
                .append("</html>");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenBookerReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 150);
        store.add(worker1);
        store.add(worker2);
        Report engine = new BookerReportEngine(store, 0.5);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append("Ivan").append(";")
                .append(now).append(";")
                .append(now).append(";")
                .append(50.0).append(";")
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("Petr").append(";")
                .append(now).append(";")
                .append(now).append(";")
                .append(75.0).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHRReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 150);
        store.add(worker1);
        store.add(worker2);
        Report engine = new HRReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append("Petr").append(";")
                .append(150.0).append(";")
                .append(System.lineSeparator())
                .append("Ivan").append(";")
                .append(100.0).append(";");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

//    @Test
//    public void whenXMLReport() {
//        MemStore memStore = new MemStore();
//        Calendar calendar1 = Calendar.getInstance();
//       // calendar1.set(2020, 10, 10, 00, 00);
//        Calendar calendar2 = Calendar.getInstance();
//       // calendar2.set(2021, 10, 10, 00, 00);
//        Employee em1 = new Employee("name1", calendar1, calendar2, 100.0);
//        Employee em2 = new Employee("name2", calendar1, calendar2, 200.0);
//        memStore.add(em1);
//        memStore.add(em2);
//        Report xmlReport = new XMLReport(memStore);
//        String result = xmlReport.generate(el -> true);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS+03:00");
//        LocalDateTime localDateTime = LocalDateTime.ofInstant(calendar1.toInstant(), ZoneId.systemDefault());
//        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(calendar2.toInstant(), ZoneId.systemDefault());
//        String r = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
//                + "<employee>\n"
//                + "    <users>\n"
//                + "        <fired>" + formatter.format(localDateTime2) + "</fired>\n"
//                + "        <hired>" + formatter.format(localDateTime) + "</hired>\n"
//                + "        <name>name1</name>\n"
//                + "        <salary>" + em1.getSalary() + "</salary>\n"
//                +  "    </users>\n"
//                + "    <users>\n"
//                + "        <fired>" + formatter.format(localDateTime2) + "</fired>\n"
//                + "        <hired>" + formatter.format(localDateTime) +   "</hired>\n"
//                + "        <name>name2</name>\n"
//                + "        <salary>" + em2.getSalary() + "</salary>\n"
//                + "    </users>\n"
//                + "</employee>\n";
//        assertThat(result, is(r));
//    }
//
//    @Ignore
//    @Test
//    public void whenJSONReport() {
//        MemStore memStore = new MemStore();
//        Calendar calendar1 = Calendar.getInstance();
//        Calendar calendar2 = Calendar.getInstance();
////        calendar1.set(2020, 10, 10, 23, 00);
////        calendar2.set(2021, 10, 10, 23, 00);
//        Employee em1 = new Employee("name1", calendar1, calendar2, 100.0);
//        Employee em2 = new Employee("name2", calendar1, calendar2, 200.0);
//        memStore.add(em1);
//        memStore.add(em2);
//        Report jsonReport = new JsonReport(memStore);
//        String result = jsonReport.generate(el -> true);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
//        LocalDateTime localDateTime = LocalDateTime.ofInstant(calendar1.toInstant(), ZoneId.systemDefault());
//        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(calendar2.toInstant(), ZoneId.systemDefault());
////        Gson gson = new GsonBuilder().create();
////        String data1 = gson.toJson(formatter.format(localDateTime));
////        String data2 = gson.toJson(formatter.format(localDateTime2));
//
//        String rsl = "[{\"name\":\"name1\",\"hired\":{\"year\":2021,\"month\":8,\"dayOfMonth\":16,\"hourOfDay\":11,\"minute\":40,\"second\":23},\"fired\":{\"year\":2021,\"month\":8,\"dayOfMonth\":16,\"hourOfDay\":11,\"minute\":40,\"second\":23},\"salary\":100.0},{\"name\":\"name2\",\"hired\":{\"year\":2021,\"month\":8,\"dayOfMonth\":16,\"hourOfDay\":11,\"minute\":40,\"second\":23},\"fired\":{\"year\":2021,\"month\":8,\"dayOfMonth\":16,\"hourOfDay\":11,\"minute\":40,\"second\":23},\"salary\":200.0}]";
//        assertThat(result, is(rsl));
//    }
}