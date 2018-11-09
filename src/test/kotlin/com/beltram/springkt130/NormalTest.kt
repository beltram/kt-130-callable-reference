package com.beltram.springkt130

import org.junit.jupiter.api.Test

typealias GreetingFunction = (String) -> Unit
data class Person(val name: String, val log: GreetingFunction)
internal class NormalTest {
    private fun sayHello(name: String) = println("Hello $name")
    @Test fun `this works`() { Person("Beltram") { m -> sayHello(m) } }
    @Test fun `strangely this works`() { Person("Beltram", ::sayHello) }
    @Test fun `this fails`() { Person("Beltram", this::sayHello) }
}