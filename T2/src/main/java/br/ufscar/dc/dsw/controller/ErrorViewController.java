package br.ufscar.dc.dsw.controller;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class ErrorViewController implements ErrorViewResolver {
    @Autowired
    private ResourceBundleMessageSource messageSource;

    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> map) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("status", status.value());

        Locale locale = request.getLocale();

        switch (status.value()) {
            case 403:
                model.addObject("error", messageSource.getMessage("403.error", null, locale));
                model.addObject("message", messageSource.getMessage("403.error", null, locale));
                break;
            case 404:
                model.addObject("error", messageSource.getMessage("404.error", null, locale));
                model.addObject("message", messageSource.getMessage("404.error", null, locale));
                break;
            case 500:
                model.addObject("error", messageSource.getMessage("500.error", null, locale));
                model.addObject("message", messageSource.getMessage("500.error", null, locale));
                break;
            default:
                model.addObject("error", messageSource.getMessage("default.error", null, locale));
                model.addObject("message", messageSource.getMessage("default.error", null, locale));
                break;
        }

        return model;
    }
}
