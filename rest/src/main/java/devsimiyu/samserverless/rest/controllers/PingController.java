package devsimiyu.samserverless.rest.controllers;

import devsimiyu.samserverless.core.services.PingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @Autowired
    private PingService pingService;

    @GetMapping("/ping")
    public String message() {
        return pingService.ping();
    }
}