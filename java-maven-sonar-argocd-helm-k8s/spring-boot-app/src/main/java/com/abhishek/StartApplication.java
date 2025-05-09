package com.abhishek;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class StartApplication {

    private final MeterRegistry meterRegistry;

    // Constructor injection of MeterRegistry
    public StartApplication(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @GetMapping("/")
    public String index(final Model model) {
        // Increment custom counter each time this endpoint is called
        meterRegistry.counter("custom_index_requests_total").increment();

        model.addAttribute("title", "I have successfully built a Spring Boot application using Maven");
        model.addAttribute("msg", "Modified by Jatharthan and deployed via Argo CD");
        return "index";
    }

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }
}
