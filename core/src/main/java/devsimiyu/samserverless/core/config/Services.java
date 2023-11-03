package devsimiyu.samserverless.core.config;

import jakarta.enterprise.inject.Default;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "devsimiyu.samserverless.core.services", includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Default.class}))
public class Services { }
