/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.astu.k7m;

import javax.ejb.Stateless;

/**
 *
 * @author Ever
 */
@Stateless
public class CurrencyAndTempConverter implements CurrencyAndTempConverterLocal {
        
    private double USD_to_BIRR;
    private static double CEL_to_FAH = 1.8;

    public CurrencyAndTempConverter() {
        this.USD_to_BIRR = 30.2;
    } 
    
    public void setUSD_to_BIRR(double USD_to_BIRR){
        
        this.USD_to_BIRR = USD_to_BIRR;
        
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

     public double convertBirrToUSD(double birr){
         return birr / USD_to_BIRR;
     }
    //Following function should convert US Dollar$ value to Ethiopian Birr
     public double convertUSDToBirr(double usd){
         return usd * USD_to_BIRR;
         
     }
    //Following function should convert Celsius temperature value to Fahrenheit value
     public double convertCelsiusToFahrenheit(double celsius){
         return celsius * CEL_to_FAH + 32;
     }
    //Following function should convert Fahrenheit value to Celsius temperature value
     public double convertFahrenheitToCelsius(double fahrenheit){
         return (fahrenheit - 32) / CEL_to_FAH;
     }

}
