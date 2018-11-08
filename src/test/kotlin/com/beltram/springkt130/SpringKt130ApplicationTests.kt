package com.beltram.springkt130

import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest

typealias GreetingFunction = (String) -> Unit

data class Person(val name: String, val log: GreetingFunction)

@SpringBootTest
class SpringKt130ApplicationTests {

    private val logger = LoggerFactory.getLogger(SpringKt130ApplicationTests::class.java)

    @Test
    fun `this works`() {
        Person("Beltram") { m -> logger.debug(m) }
    }

    @Test
    fun `this fails`() {
        Person("Beltram", logger::debug)
    }
}
