package ru.job4j.solid.reportssrp;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateAdapter extends XmlAdapter<String, Calendar> {
    private static final ThreadLocal<DateFormat> DATE_FORMAT
            = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    @Override
    public Calendar unmarshal(String s) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DATE_FORMAT.get().parse(s));
        return calendar;
    }

    @Override
    public String marshal(Calendar calendar) throws Exception {
        return DATE_FORMAT.get().format(calendar.getTime());
    }
}
