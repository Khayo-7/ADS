package edu.astu.k7m;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.astu.k7m.ShoppingCartSFSBLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ever
 */
public class serv extends HttpServlet {

    ShoppingCartSFSBLocal shoppingCartSFSB = lookupShoppingCartSFSBLocal();

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
            out.println("<title>Servlet serv</title>");            
            out.println("</head>");
            out.println("<body>");
//            out.println("<h1>Servlet serv at " + request.getContextPath() + "</h1>");
            String action = request.getParameter("action");
            
            out.println("<br><br>");
            
            if(action.equals("add_item")){
                String item = request.getParameter("item");
                if(shoppingCartSFSB.check(item)){                    
                    out.println("The item <b>" + item + "</b> is already added");
                }
                else{
                    shoppingCartSFSB.addItem(item);
                    out.println("The item <b>" + item + "</b> is added");
                }
            }
            else if(action.equals("remove_item")){
                String item = request.getParameter("item");                
                if(shoppingCartSFSB.check(item)){        
                    shoppingCartSFSB.removeItem(item);
                    out.println("The item <b>" + item + "</b> is removed");
                }
                else{
                    out.println("The item <b>" + item + "</b> is not in the list");
                }
            }
            else if(action.equals("display_items")){
                
                List<String> allItems = shoppingCartSFSB.display();
                out.println("The following items are found in the list");
                out.println("<ul>");
                for(String item: allItems){
                    
                    out.println("<li><b>");
                    out.println(item);
                    out.println("</li></b>");
                }
                out.println("</ul>");
                
            }
            else if(action.equals("total_item_number")){
                
                int cost = shoppingCartSFSB.numberOfItems();
                out.println("Total number of items is <b>" + cost + "</b>");
                
            }
            
            out.println("</body>");
            out.println("</html>");
    
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

    private ShoppingCartSFSBLocal lookupShoppingCartSFSBLocal() {
        try {
            Context c = new InitialContext();
            return (ShoppingCartSFSBLocal) c.lookup("java:global/ADS_Code_2/ADS_Code_2-ejb/ShoppingCartSFSB!edu.astu.k7m.ShoppingCartSFSBLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
