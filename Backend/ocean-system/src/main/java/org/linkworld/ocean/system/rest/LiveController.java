package org.linkworld.ocean.system.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author HALOXIAO
 * @since 2021-11-04 01:07
 **/
@RestController
public class LiveController {

    @GetMapping("/beat")
    public String beat() {
        return LocalDateTime.now().toString();
    }

}
