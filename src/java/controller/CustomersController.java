/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOCustomer;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CustomersController", urlPatterns = {"/Customers"})
public class CustomersController extends HttpServlet {

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
        
        DAOCustomer dao = new DAOCustomer();
        

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
          String service = request.getParameter("service");
          
          if(service.equals("insertCustomers")){
              //get data
              String CustomerID = request.getParameter("CustomerID");
              String CompanyName = request.getParameter("CompanyName");
              String ContactName = request.getParameter("ContactName");
              String ContactTitle = request.getParameter("ContactTitle");
              String Address = request.getParameter("Address");
              String City = request.getParameter("City");
              String Region = request.getParameter("Region");
              String PostalCode = request.getParameter("PostalCode");
              String Country = request.getParameter("Country");
              String Phone = request.getParameter("Phone");
              String Fax = request.getParameter("Fax");
              
              
              if(CustomerID.equals("")){
                  out.print("CustomerID must be empty");
                  
              }
              
              Customer pro = new Customer(CustomerID, CompanyName, ContactName, ContactTitle, Address, City, Region, PostalCode, Country, Phone, Fax);
              int n = dao.insertCustomer(pro);
              response.sendRedirect("Customers?service=listAllCustomers");
              
              
          }
            
            if(service.equals("listAllCustomers")){
                
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CustomersController</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<form action=\"Customers\" method=\"get\">\n"
                    + "  <p>\n"
                    + "    Search ID: <input type=\"text\" name=\"customer\" >\n"
                    + "    <input type=\"submit\" name=\"submit\" value=\"Search\">\n"
                    + "    <input type=\"reset\" name=\"clear\" value=\"reset\">\n"
                    + "     <input type=\"hiden\" name=\"service\" value=\"listAllCustomers\">\n"
                    + "</p>\n"
                    + "\n"
                    + "</form>");

            out.println("<p><a href=\"HTML/insertCustomers.html\">Insert Customers</a></p>");
            
            String sql = "Select * from Customers";
            String submit = request.getParameter("submit");
            if (submit == null) {
                sql = "select * from Customers";
            } else {
                String pname = request.getParameter("customer");
                sql = "select * from Customers\n"
                        + "where CustomerID like '%"+pname+"%'";
            }

            Vector<Customer> vector = dao.getCustomers(sql);

            out.println("""
                        <table border=1>
                          <tr>
                            <th>CustomerID</th>
                            <th>CompanyName</th>
                            <th>ContactName</th>
                            <th>ContactTitle</th>
                            <th>Address</th>
                            <th>City</th>
                            <th>Region</th>
                            <th>PostalCode</th>
                            <th>Country</th>
                            <th>Phone</th>
                            <th>Fax</th>
                          </tr>""");
            
            for (Customer customer : vector) {
               out.println(" <tr>\n"
                        + "   <td>" + customer.getCustomerID() + "</td>\n"
                        + "   <td>" + customer.getCompanyName() + "</td>\n"
                        + "   <td>" + customer.getContactName() + "</td>\n"
                        + "   <td>" + customer.getContactTitle() + "</td>\n"
                        + "   <td>" + customer.getAddress() + "</td>\n"
                        + "   <td>" + customer.getCity() + "</td>\n"                
                        + "   <td>" + customer.getRegion() + "</td>\n"        
                        + "   <td>" + customer.getPostalCode() + "</td>\n"        
                        + "   <td>" + customer.getCountry() + "</td>\n"
                        + "   <td>" + customer.getPhone() + "</td>\n" 
                        + "   <td>" + customer.getFax() + "</td>\n"         
                        + "\n"
                        + " </tr>");
                
            }
            
            out.println("</table>");
           
            out.println("</body>");
            out.println("</html>");
        }
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
