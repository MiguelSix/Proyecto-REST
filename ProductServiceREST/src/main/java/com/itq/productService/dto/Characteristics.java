package com.itq.productService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Characteristics {

    @JsonProperty("characteristicName")
    private String name;
    @JsonProperty("characteristicValue")
    private String value;
    
    public String getName() {
        return this.name;
    }
    public String getValue() {
        return this.value;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setValue(String value) {
        this.value = value;
    }

}
