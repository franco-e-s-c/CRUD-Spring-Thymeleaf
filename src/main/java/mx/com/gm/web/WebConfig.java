package mx.com.gm.web;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

public class WebConfig implements WebMvcConfigurer {
//    @Bean
//    public LocaleResolver localeResolver(){
//         var slr = new SessionLocaleResolver();
//         slr.setDefaultLocale(new Locale("en"));
//         return slr;
//    }
//
//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor(){
//        var lci = new LocaleChangeInterceptor();
//        lci.setParamName("lang");
//        return null;
//    }
    @Override
    public void addViewControllers(ViewControllerRegistry registro){
        registro.addViewController("/").setViewName("index");
        registro.addViewController("/login");
        registro.addViewController("/403").setViewName("/403");
    }

}
