package ru.job4j.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SqlRuParse {
    public static void main(String[] args) throws Exception {
        for (int i = 1; i <= 5; i++) {
            Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers/" + i).get();
            Elements row = doc.select(".postslisttopic");
            for (Element td : row) {
                Element href = td.child(0);
                // System.out.println(href.attr("href"));
                getDescription(href.attr("href"));
                /*
                 System.out.println(String.format("Вакансия: %s, Дата создания: %s", href.text(), td.parent().child(5).text()));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");
                SqlRuDateTimeParser sqlRuDateTimeParser = new SqlRuDateTimeParser();
                LocalDateTime localDateTime = sqlRuDateTimeParser.parse(td.parent().child(5).text());
                System.out.println(formatter.format(localDateTime));
                */
                System.out.println("_".repeat(80));
            }
        }
    }

    private static List<Post> getDescription(String link) throws IOException {
        Document doc = Jsoup.connect(link).get();
        Element title = doc.select(".messageHeader").get(0); // получить заголовок вакансии
        Element description = doc.select(".msgBody").get(1); // описание вакансии
        Element dateTimeSite = doc.select(".msgFooter").get(0); // дата - время
        SqlRuDateTimeParser sqlRuParse = new SqlRuDateTimeParser(); // парсинг даты и времени с сайта
        LocalDateTime localDateTime = sqlRuParse.parse(dateTimeSite.text());
        List<Post> listDesc = new ArrayList<>();
        listDesc.add(new Post(0, title.text(), link, description.text(), localDateTime));
        return listDesc;
    }
}
