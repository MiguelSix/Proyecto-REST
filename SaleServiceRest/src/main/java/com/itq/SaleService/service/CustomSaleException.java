package com.itq.SaleService.service;

@SuppressWarnings("serial")
public class CustomSaleException extends RuntimeException {

    public CustomSaleException(String message) {
        super(message);
    }
}