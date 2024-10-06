/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOCustomerCustomerDemo;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CustomerCustomerDemoController", urlPatterns = {"/CustomerCustomerDemoURL"})
public class CustomerCustomerDemoController extends HttpServlet {

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
        DAOCustomerCustomerDemo dao = new DAOCustomerCustomerDemo();
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CustomerCustomerDemoController</title>");            
            out.println("</head>");
            out.println("<body>");
            
            out.print("<form action=\"CustomerCustomerDemoURL\" method=\"get\">\n"
                    + "  <p>\n"
                    + "    Search : <input type=\"text\" name=\"cus1name\" >\n"
                    + "    <input type=\"submit\" name=\"submit\" value=\"Search\">\n"
                    + "    <input type=\"reset\" value=\"clear\">\n"
                    + "  </p>\n"
                    + "\n"
                    + "\n"
                    + "</form>");
            
            String sql =" SELECT * FROM CustomerCustomerDemo";
            String submit = request.getParameter("submit");
            if(submit== null){
                sql =" SELECT * FROM CustomerCustomerDemo";
            }
            else{
                String cus1name= request.getParameter("cus1name");
                sql="SELECT * FROM CustomerCustomerDemo WHERE CustomerTypeID ='%"+cus1name+"%'";
            }
            
            
            out.println("<h1>Servlet CustomerCustomerDemoController at " + request.getContextPath() + "</h1>");
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
