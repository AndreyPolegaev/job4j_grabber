package ru.job4j.solid.reportssrp;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import java.io.StringWriter;
import java.util.function.Predicate;

/**
 * IT department requires HTML report.
 * Use html template (thymeleaf library)
 */

public class ItReportEngine implements Report {

    private Store store;

    public ItReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        TemplateEngine templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setCharacterEncoding("UTF-8");
        templateEngine.setTemplateResolver(templateResolver);
        for (Employee employee : store.findBy(filter)) {
            Context context = new Context();
            context.setVariable("Name", employee.getName());
            context.setVariable("Hired", employee.getHired().getTime());
            context.setVariable("Fired", employee.getFired().getTime());
            context.setVariable("Salary", employee.getSalary());
            StringWriter stringWriter = new StringWriter();
            templateEngine.process("reportTemplate.html", context, stringWriter);
            text.append(stringWriter);
        }
        return text.toString();
    }
}
