package com.example.dodo.presantation.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author cyclamen on 1/1/20
 */

@RestController
@RequestMapping("build")
class BuildingRestController {

    @GetMapping
    fun getBuild() : String {
        return "hello"
    }
}
