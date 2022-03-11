/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.astu.k7m;

import javax.ejb.Local;

/**
 *
 * @author Ever
 */
@Local
public interface CurrencyAndTempConverterLocal {
//Following function should convert Ethiopian Birr value to US Dollar$
public double convertBirrToUSD(double birr);
//Following function should convert US Dollar$ value to Ethiopian Birr
 public double convertUSDToBirr(double usd);
//Following function should convert Celsius temperature value to Fahrenheit value
 public double convertCelsiusToFahrenheit(double celsius);
//Following function should convert Fahrenheit value to Celsius temperature value
 public double convertFahrenheitToCelsius(double fahrenheit);
}
