package ru.job4j.solid.reportssrp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.function.Predicate;

public class XMLReport implements Report {

    Store store;

    public XMLReport(Store store) {
        this.store = store;
    }


    @Override
    public String generate(Predicate<Employee> filter) {
        String rsl = null;
        try {
            JAXBContext context = null;
            context = JAXBContext.newInstance(Employee.class);
            Marshaller marshaller = null;
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            try (StringWriter writer = new StringWriter()) {

                for (Employee temp : store.findBy(filter)) {
                    marshaller.marshal(temp, writer);
                    rsl = writer.getBuffer().toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (JAXBException jaxbException) {
            jaxbException.printStackTrace();
        }
        return rsl;
    }

//    public static void main(String[] args) throws JAXBException {
//        Employee em1 = new Employee("name1", Calendar.getInstance(), Calendar.getInstance(), 100.0);
//        Employee em2 = new Employee("name2", Calendar.getInstance(), Calendar.getInstance(), 200.0);
//        MemStore memStore = new MemStore();
//        memStore.add(em1);
//        memStore.add(em2);
//        XMLReport xmlReport = new XMLReport(memStore);
//        System.out.println(xmlReport.generate(el -> true));
//    }
}
