package dev.rozhkova.ibank.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PaymentOperationController {

    public ResponseEntity pay() {
        return new ResponseEntity(HttpStatus.OK);
    }
}