package com.example.demo

import com.google.cloud.spring.data.spanner.core.SpannerTemplate
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}

interface FooService {
    fun bar(): String
}

@Service
class FooServiceImpl(val spannerTemplate: SpannerTemplate) : FooService {
    override fun bar(): String = spannerTemplate.toString()
}

@RestController
class ApiController(private val fooService: FooService) {
    @GetMapping
    fun getFoo() = fooService.bar()
}
