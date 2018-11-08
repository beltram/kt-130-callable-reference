package com.beltram.springkt130

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

typealias GreetingFunction = (String) -> Unit

@SpringBootTest
class SpringKt130ApplicationTests {
    data class Person(val name: String, val log: GreetingFunction)
    fun sayHello(name: String) = println("Hello $name")
    @Test fun `this works`() { Person("Beltram") { m -> sayHello(m) } }
    @Test fun `strangely this works`() { Person("Beltram", ::sayHello) }
    @Test fun `this fails`() { Person("Beltram", this::sayHello) }
}