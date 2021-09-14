package ru.job4j.solid.reportssrp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class XMLReport implements Report {

    Store store;

    public XMLReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        try {
            JAXBContext context = JAXBContext.newInstance(Users.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            String xml = "";
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(new Users(store.findBy(filter)), writer);
                xml = writer.getBuffer().toString();
                System.out.println(xml);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (JAXBException jaxbException) {
            jaxbException.printStackTrace();
        }
        return "";
    }

    @XmlRootElement(name = "employee")
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class Users {

       private List<Employee> users;


        public Users(List<Employee> users) {
            this.users = users;
        }

        public Users() {

        }

        public List<Employee> getUsers() {
            return users;
        }

        public void setUsers(List<Employee> users) {
            this.users = users;
        }
    }

    public static void main(String[] args) throws JAXBException {
        Employee em1 = new Employee("name1", Calendar.getInstance(), Calendar.getInstance(), 100.0);
        Employee em2 = new Employee("name2", Calendar.getInstance(), Calendar.getInstance(), 200.0);
        MemStore memStore = new MemStore();
        memStore.add(em1);
        memStore.add(em2);
        XMLReport xmlReport = new XMLReport(memStore);
        xmlReport.generate(el -> true);
    }
}
