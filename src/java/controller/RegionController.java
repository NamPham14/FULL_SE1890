/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Regions;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import javax.swing.plaf.synth.Region;
import model.DAORegion;

/**
 *
 * @author Admin
 */
@WebServlet(name = "RegionController", urlPatterns = {"/RegionURL"})
public class RegionController extends HttpServlet {

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
        DAORegion dao = new DAORegion();
        
        try (PrintWriter out = response.getWriter()) {
            
            String service = request.getParameter("service");
            if (service.equals("deleteRegion")) {
                dao.deleteRegion(Integer.parseInt(request.getParameter("regionID")));
                response.sendRedirect("RegionURL?service=listAllRegion");
                
            }
            
            if(service.equals("insertRegion")){
                //get data
                String RegionID = request.getParameter("RegionID");
                String RegionDescription = request.getParameter("RegionDescription");
                
                int regionID = Integer.parseInt(RegionID);
                
                Regions reg = new Regions(regionID, RegionDescription) ;
                int n = dao.addRegion(reg);
                response.sendRedirect("RegionURL?service=listAllRegion");
                
               
            }
            if(service.equals("listAllRegion")){
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegionController</title>");
            out.println("</head>");
            out.println("<body>");

            out.print("<form action=\"RegionURL\" method=\"get\">\n"
                    + "  <p>\n"
                    + "    Search: <input type=\"text\" name=\"rname\" id=\"\">\n"
                    + "    <input type=\"submit\" name=\"submit\"  value=\"Search\">\n" 
                    + "    <input type=\"reset\" value=\"Clear\">\n"
                    + "    <input type=\"hidden\" name=\"service\"  value=\"listAllRegion\">\n"
                    + "  </p>\n"
                    + "</form>");
            // link insert
            out.print("<p><a href=\"HTML/insertRegion.html\">Insert Region</a></p>");

            String sql = "SELECT * FROM Region";
            String submit = request.getParameter("submit");
            if (submit == null) {
                sql = "SELECT * FROM Region";
            } else {
                String rname = request.getParameter("rname");
                sql = "  SELECT * FROM Region\n"
                        + "  where RegionDescription like '%" + rname + "%'";
            }
            Vector<Regions> vector = dao.getRegion(sql);
            //Region table
            out.println("<table border=\"1\">\n"
                    + "  <th>RegionID</th>\n"
                    + "  <th>RegionDescription</th>\n"
                    + "  <th>Update</th>\n"
                    + "  <th>Delete</th>\n"
            );
            for (Regions regions : vector) {
                out.println("<tr>\n"
                        + "  <td>"+regions.getRegionID()+"</td>\n"
                        + "  <td>"+regions.getRegionDescription()+"</td>\n"
                        + "  <td></td>\n"
                       + "<td><a href=\"RegionURL?service=deleteRegion&regionID="+regions.getRegionID()+"\">Delete Region</a></td>\n"
                        + "</tr>");

            }

            out.println("</table>");
            out.println("<h1>Servlet RegionController at " + request.getContextPath() + "</h1>");
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
