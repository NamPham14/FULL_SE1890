/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Employees;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOEmployees;

/**
 *
 * @author Admin
 */
@WebServlet(name = "EmployeesController", urlPatterns = {"/EmployeesURL"})
public class EmployeesController extends HttpServlet {

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

        DAOEmployees dao = new DAOEmployees();

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");

            if (service.equals("deleteEmployees")) {
                dao.deleteEmployees(Integer.parseInt(request.getParameter("empID")));
                response.sendRedirect("EmployeesURL?service=listAllEmployees");

            }

            if (service.equals("insertEmployees")) {
                // get data
                String LastName = request.getParameter("LastName");
                String FirstName = request.getParameter("FirstName");
                String Title = request.getParameter("Title");
                String TitleOfCourtesy = request.getParameter("TitleOfCourtesy");
                String BirthDate = request.getParameter("BirthDate");
                String HireDate = request.getParameter("HireDate");
                String Address = request.getParameter("Address");
                String City = request.getParameter("City");
                String Region = request.getParameter("Region");
                String PostalCode = request.getParameter("PostalCode");
                String Country = request.getParameter("Country");
                String HomePhone = request.getParameter("HomePhone");
                String Extension = request.getParameter("Extension");
                String Notes = request.getParameter("Notes");
                String PhotoPath = request.getParameter("PhotoPath");
                String ReportsTo = request.getParameter("ReportsTo");

                // convert value
                int reportsTo = Integer.parseInt(ReportsTo);
                
                Employees employee = new Employees(reportsTo, LastName, FirstName, Title, TitleOfCourtesy, BirthDate, HireDate, Address, City, Region, PostalCode, Country, HomePhone, Extension, Notes, PhotoPath);
                int n = dao.addEmployees(employee);
               
                response.sendRedirect("EmployeesURL?service=listAllEmployees");

            }
            if (service.equals("listAllEmployees")) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet EmployeesController</title>");
                out.println("</head>");
                out.println("<body>");

                //form search
                out.print("<form action=\"EmployeesURL\" method=\"get\">\n"
                        + "<P>\n"
                        + "  Search Tile: <input type=\"text\" name=\"pname\" id=\"\">\n"
                        + "  <input type=\"submit\" name=\"submit\" value=\"Search\">\n"
                        + "  <input type=\"reset\" value=\"Clear\">\n"
                        + "<input type=\"hidden\" name=\"service\" value=\"listAllEmployees\">"
                        + "\n"
                        + "</P>\n"
                        + "\n"
                        + "</form>");

                //link inserting a new Employee
                out.print("<p><a href=\"HTML/insertEmployees.html\">Insert Employees</a></p>");

                String sql = "SELECT * FROM Employees";
                String submit = request.getParameter("submit");
                if (submit == null) {
                    sql = "SELECT * FROM Employees";
                } else {
                    String pname = request.getParameter("pname");
                    sql = "SELECT * FROM Employees\n"
                            + "Where Title like '%" + pname + "%'";
                }
                Vector<Employees> vector = dao.getEmployee(sql);

                out.println("<table border=1 >\n"
                        + "    <tr>\n"
                        + "      <th>EmployeeID</th>"
                        + "      <th>LastName</th>"
                        + "      <th>FirstName</th>"
                        + "      <th>Title</th>"
                        + "      <th>TitleOfCourtesy</th>"
                        + "      <th>BirthDate</th>"
                        + "      <th>HireDate</th>"
                        + "      <th>Address</th>"
                        + "      <th>City</th>"
                        + "      <th>Region</th>"
                        + "      <th>PostalCode</th>"
                        + "      <th>Country</th>"
                        + "      <th>HomePhone</th>"
                        + "      <th>Extension</th>"
                        + "      <th>Photo</th>"
                        + "      <th>Notes</th>"
                        + "      <th>ReportsTo</th>"
                        + "      <th>PhotoPath</th>\n"
                        + "            <th>Update</th>"
                        + "            <th>Delete</th>"
                        + "    </tr>");

                for (Employees employees : vector) {
                    out.println("<tr>\n"
                            + "  <td>" + employees.getEmployeeID() + "</td>\n"
                            + "  <td>" + employees.getLastName() + "</td>\n"
                            + "  <td>" + employees.getFirstName() + "</td>\n"
                            + "  <td>" + employees.getTitle() + "</td>\n"
                            + "  <td>" + employees.getTitleOfCourtesy() + "</td>\n"
                            + "  <td>" + employees.getBirthDate() + "</td>\n"
                            + "  <td>" + employees.getHireDate() + "</td>\n"
                            + "  <td>" + employees.getAddress() + "</td>\n"
                            + "  <td>" + employees.getCity() + "</td>\n"
                            + "  <td>" + employees.getRegion() + "</td>\n"
                            + "  <td>" + employees.getPostalCode() + "</td>\n"
                            + "  <td>" + employees.getCountry() + "</td>\n"
                            + "  <td>" + employees.getHomePhone() + "</td>\n"
                            + "  <td>" + employees.getExtension() + "</td>\n"
                            + "  <td>" + employees.getPhoto() + "</td>\n"
                            + "  <td>" + employees.getNotes() + "</td>\n"
                            + "  <td>" + employees.getReportsTo() + "</td>\n"
                            + "  <td>" + employees.getPhotoPath() + "</td>\n"
                            + "  <td></td>\n"
                            + "  <td><a href=\"EmployeesURL?service=deleteEmployees&empID="+employees.getEmployeeID()+"\">Delete</a></td>"
                                    
                            + "</tr>");

                }

                out.println("</table>");
                out.println("<h1>Servlet EmployeesController at " + request.getContextPath() + "</h1>");
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
