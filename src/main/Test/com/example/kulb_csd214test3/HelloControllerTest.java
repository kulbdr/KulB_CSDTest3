package com.example.kulb_csd214test3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloControllerTest {

    @Test
    void calculateTotalBill() {
        HelloController x = new HelloController();

        assertEquals(x.calculateTotalBill(3), 30);
    }
}