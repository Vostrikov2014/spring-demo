package org.example.springdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling requests related to greeting.
 */
@RestController
public class HelloController {

    /**
     * Returns a greeting message.
     *
     * @return A string containing the greeting message "Hello World".
     */
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    /**
     * Returns the length of the provided string.
     *
     * @param text The string whose length is to be calculated.
     * @return The length of the string.
     */
    @GetMapping("/length")
    public int getLength(@RequestParam String text) {
        return text.length();
    }

    /**
     * Returns the result of adding 2 + 2.
     *
     * @return The result of 2 + 2 calculation.
     */
    @GetMapping("/calculate")
    public int calculate() {
        return 2 + 2;
    }

    /**
     * Returns the result of adding 3 + 3.
     *
     * @return The result of 3 + 3 calculation.
     */
    @GetMapping("/calculate33")
    public int calculate33() {
        return 3 + 3;
    }

// ... existing code ...

    /**
     * Returns the result of adding 4 + 4.
     *
     * @return The result of 4 + 4 calculation.
     */
    @GetMapping("/calculate44")
    public int calculate44() {
        return 4 + 4;
    }

    /**
     * Returns the result of adding 5 + 5.
     *
     * @return The result of 5 + 5 calculation.
     */
    @GetMapping("/calculate55")
    public int calculate55() {
        return 5 + 5;
    }

}



