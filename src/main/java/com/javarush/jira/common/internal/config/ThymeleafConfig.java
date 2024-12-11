package com.javarush.jira.common.internal.config;//package com.javarush.jira.common.internal.config;
//
//import com.javarush.jira.common.internal.config.AppProperties;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.spring6.view.ThymeleafViewResolver;
//import org.thymeleaf.templatemode.TemplateMode;
//import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
//import org.thymeleaf.templateresolver.FileTemplateResolver;
//
//import java.util.Set;
//
//@Configuration
////http://www.thymeleaf.org/doc/articles/thymeleaf3migration.html
//@RequiredArgsConstructor
//public class ThymeleafConfig implements WebMvcConfigurer {
//    private final AppProperties appProperties;
//
//    @Bean
//    public SpringTemplateEngine thymeleafTemplateEngine() {
//        SpringTemplateEngine engine = new SpringTemplateEngine();
//        FileTemplateResolver viewResolver = createTemplateResolver("src/main/resources/view/mails/"); // Изменили путь
//        viewResolver.setCheckExistence(true); // Для разработки
//        viewResolver.setOrder(1);
//        FileTemplateResolver mailResolver = createTemplateResolver("src/main/resources/view/"); // Изменили путь
//        mailResolver.setOrder(2);
//        engine.setTemplateResolvers(Set.of(viewResolver, mailResolver));
//        return engine;
//    }
//
//    private FileTemplateResolver createTemplateResolver(String pfx) {
//        return new FileTemplateResolver() {{
//            setPrefix(pfx);
//            setSuffix(".html");
//            setTemplateMode(TemplateMode.HTML);
//            setCacheable(false); // Для разработки
//            setCacheTTLMs(appProperties.getTemplatesUpdateCache().toMillis());
//            setCharacterEncoding("UTF-8");
//        }};
//    }
//
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//        resolver.setTemplateEngine(thymeleafTemplateEngine());
//        resolver.setCharacterEncoding("UTF-8");
//        registry.viewResolver(resolver);
//    }
//
//    // Attention: with TemplateEngine clear cache doesn't work
////    @Bean
////    public SpringTemplateEngine thymeleafTemplateEngine() {
////        SpringTemplateEngine engine = new SpringTemplateEngine();
////        FileTemplateResolver viewResolver = createTemplateResolver("src/main/resources/view/");
////        viewResolver.setCheckExistence(true);
////        viewResolver.setOrder(1);
////        FileTemplateResolver mailResolver = createTemplateResolver("src/main/resources/view/mails/");
////        mailResolver.setOrder(2);
////        engine.setTemplateResolvers(Set.of(viewResolver, mailResolver));
////        return engine;
////    }
////
////    private FileTemplateResolver createTemplateResolver(String pfx) {
////        return new FileTemplateResolver() {{
////            setPrefix(pfx);
////            setSuffix(".html");
////            setTemplateMode(TemplateMode.HTML);
////            setCacheable(true);
////            setCacheTTLMs(appProperties.getTemplatesUpdateCache().toMillis());
////            setCharacterEncoding("UTF-8");
////        }};
////    }
//}