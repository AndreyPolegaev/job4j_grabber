package ru.job4j.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SqlRuParse {
    public static void main(String[] args) throws Exception {
        for (int i = 1; i <= 5; i++) {
            Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers/" + i).get();
            Elements row = doc.select(".postslisttopic");
            for (Element td : row) {
                Element href = td.child(0);
                System.out.println(href.attr("href"));
                System.out.println(String.format("Вакансия: %s, Дата создания: %s", href.text(), td.parent().child(5).text()));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");
                SqlRuDateTimeParser sqlRuDateTimeParser = new SqlRuDateTimeParser();
                LocalDateTime localDateTime = sqlRuDateTimeParser.parse(td.parent().child(5).text());
                System.out.println(formatter.format(localDateTime));
                System.out.println("_".repeat(80));
            }
        }
    }
}
