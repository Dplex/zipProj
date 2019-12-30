package com.example.zip.`interface`.controller

import com.example.zip.const.Api
import com.example.zip.const.WebResponse
import com.example.zip.service.RestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Api.Path.APART)
class ApartController (
    @Autowired private val restService: RestService
) {

    @GetMapping("/{${Api.Param.APART_ID}}")
    @ResponseBody
    fun getDanjiInfo(
        @PathVariable(Api.Param.APART_ID) apartId: String
    ): WebResponse {
        restService.danjiInfo(apartId)

        TODO()
    }

}