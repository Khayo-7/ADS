package edu.astu.k7m;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.astu.k7m.CurrencyAndTempConverterLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ever
 */
public class conversion extends HttpServlet {

    @EJB
    private CurrencyAndTempConverterLocal currencyAndTempConverter;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
                
            RequestDispatcher rd= request.getRequestDispatcher("/index.html");
            rd.include(request, response);
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet conversion</title>");            
            out.println("</head>");
            out.println("<body>");
            
            
            String action = request.getParameter("action");
            out.println("<br><br>");
            
            if(action.equals("BIRR_to_USD")){
                double currValue = Double.parseDouble(request.getParameter("currValue"));
                double convertedValue = currencyAndTempConverter.convertBirrToUSD(currValue);
                out.println("The equivalent of <b>" + currValue + " Birr</b> is <b>" + convertedValue + " USD</b>");
            }
            else if(action.equals("USD_to_BIRR")){
                double currValue = Double.parseDouble(request.getParameter("currValue"));
                double convertedValue = currencyAndTempConverter.convertUSDToBirr(currValue);
                out.println("The equivalent of <b>" + currValue + " USD</b> is <b>" + convertedValue + " Birr</b>");
            }
            
            if(action.equals("CEL_to_FAH")){
                double tempValue = Double.parseDouble(request.getParameter("tempValue"));
                double convertedValue = currencyAndTempConverter.convertCelsiusToFahrenheit(tempValue);
                out.println("The equivalent of <b>" + tempValue + " Celsius</b> is <b>" + convertedValue + " Fahrenheit</b>");
            }
            else if(action.equals("FAH_to_CEL")){
                double tempValue = Double.parseDouble(request.getParameter("tempValue"));
                double convertedValue = currencyAndTempConverter.convertFahrenheitToCelsius(tempValue);
                out.println("The equivalent of <b>" + tempValue + " Fahrenheit</b> is <b>" + convertedValue + " Celsius</b>");
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
