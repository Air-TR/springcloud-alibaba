package com.tr.sentinelpushnacos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TR
 * @date 2022/8/20 下午4:52
 */
@RestController
public class Controller {

    @GetMapping("/call")
    public String call() {
        return "Call Success";
    }

}
