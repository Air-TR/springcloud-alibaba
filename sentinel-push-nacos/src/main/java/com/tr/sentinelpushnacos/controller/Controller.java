package com.tr.sentinelpushnacos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: TR
 */
@RestController
public class Controller {

    @GetMapping("/call")
    public String call() {
        return "Call Success";
    }

}
