package org.breakingit.stock.domain;

import lombok.Data;

@Data
public class Stock {

    private String company;
    private String symbol;
    private Double price;
}
