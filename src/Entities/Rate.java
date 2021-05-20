/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Jasser
 */
public class Rate {
    private Double rates;
    private Double Rate;

    public Double getRates() {
        return rates;
    }

    public void setRates(Double rates) {
        this.rates = rates;
    }

    public Double getRate() {
        return Rate;
    }

    public void setRate(Double Rate) {
        this.Rate = Rate;
    }

    @Override
    public String toString() {
        return "Rate{" + "rates=" + rates + ", Rate=" + Rate + '}';
    }

    public Rate() {
    }
    
    
}
