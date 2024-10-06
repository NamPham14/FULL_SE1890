/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.OrderDetails;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOOrderDetails;

/**
 *
 * @author Admin
 */
@WebServlet(name = "OrderDetailsController", urlPatterns = {"/OrderDetailsURL"})
public class OrderDetailsController extends HttpServlet {

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
        DAOOrderDetails dao = new DAOOrderDetails();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String service = request.getParameter("service");

            if (service.equals("deleteOrderDetails")) {
                int orderID = Integer.parseInt(request.getParameter("orderID"));
                int productID = Integer.parseInt(request.getParameter("productID"));
                dao.deleteOrderDetails(orderID, productID);  // Pass both orderID and productID
                response.sendRedirect("OrderDetailsURL?service=listAllOrderDetails");
            }
            
            if (service.equals("insertOrderDetails")) {
                //get data
                String OrderID = request.getParameter("OrderID");
                String ProductID = request.getParameter("ProductID");
                String UnitPrice = request.getParameter("UnitPrice");
                String Quantity = request.getParameter("Quantity");
                String Discount = request.getParameter("Discount");

                int orderID = Integer.parseInt(OrderID);
                int productID = Integer.parseInt(ProductID);
                double unitPrice = Double.parseDouble(UnitPrice);
                int quantity = Integer.parseInt(Quantity);
                double discount = Double.parseDouble(Discount);

                OrderDetails ord = new OrderDetails(orderID, productID, unitPrice, quantity, discount);
                int n = dao.addOrderDetails(ord);
                response.sendRedirect("OrderDetailsURL?service=listAllOrderDetails");

            }
            if (service.equals("listAllOrderDetails")) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet OrderDetailsController</title>");
                out.println("</head>");
                out.println("<body>");

                out.print("<form action=\"OrderDetailsURL\" method=\"get\">\n"
                        + "   <p>\n"
                        + "    Search Name: <input type=\"text\" name=\"orname\" id=\"\">\n"
                        + "    <input type=\"submit\" name=\"submit\" value=\"Search\">\n"
                        + "    <input type=\"reset\" value=\"Clear\">\n"
                        + "    <input type=\"hidden\" name=\"service\" value=\"listAllOrderDetails\">\n"
                        + "   </p>\n"
                        + "\n"
                        + "</form>");
                //link insert
                out.print("<p><a href=\"HTML/insertOrderDetails.html\">Insert OrderDetails</a></p>");

                String sql = "SELECT * from [Order Details]";
                String submit = request.getParameter("submit");
                if (submit == null) {
                    sql = "SELECT * from [Order Details]";
                } else {
                    String orname = request.getParameter("orname");
                    sql = "SELECT * from [Order Details] Where OrderID like '%" + orname + "%'";
                }

                Vector<OrderDetails> vector = dao.getOrderDetails(sql);

                out.println("<table border=\"1\">\n"
                        + "   <tr>\n"
                        + "    <th>OrderID</th>\n"
                        + "    <th>ProductID</th>\n"
                        + "    <th>UnitPrice</th>\n"
                        + "    <th>Quantity</th>\n"
                        + "    <th>Discount</th>\n"
                        + "    <th>Update</th>\n"
                        + "    <th>Delete</th>\n"
                        + "   </tr>");

                for (OrderDetails orderDetails : vector) {
                    out.println("  <tr>\n"
                            + "    <td>" + orderDetails.getOrderID() + "</td>\n"
                            + "    <td>" + orderDetails.getProductID() + "</td>\n"
                            + "    <td>" + orderDetails.getUnitPrice() + "</td>\n"
                            + "    <td>" + orderDetails.getQuantity() + "</td>\n"
                            + "    <td>" + orderDetails.getDiscount() + "</td>\n"
                            + "    <td></td>\n"
                            + "<td><a href=\"OrderDetailsURL?service=deleteOrderDetails&orderID=" + orderDetails.getOrderID() +
                              "&productID=" +orderDetails.getProductID()+"\" >Delete</a></td>\n"
                            + "   </tr>");

                }

                out.println("</table>");
                out.println("<h1>Servlet OrderDetailsController at " + request.getContextPath() + "</h1>");
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
