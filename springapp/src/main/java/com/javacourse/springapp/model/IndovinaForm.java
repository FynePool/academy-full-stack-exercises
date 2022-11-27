package com.javacourse.springapp.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class IndovinaForm {
    @NotNull
    @Min(100) @Max(200)
    private Integer number;

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

}
