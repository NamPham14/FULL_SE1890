package controller;

import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOProduct;

@WebServlet(name = "ProductController", urlPatterns = {"/ProductURL"})
public class ProductController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOProduct dao = new DAOProduct();

        try (PrintWriter out = response.getWriter()) {

            String service = request.getParameter("service");
            
            if (service.equals("deleteProduct")) {
                dao.deleteProduct(Integer.parseInt(request.getParameter("pid")));
                response.sendRedirect("ProductURL?service=listAllProducts");

            }

            if (service.equals("insertProduct")) {
                // get data
                String ProductName = request.getParameter("ProductName");
                String SupplierID = request.getParameter("SupplierID");
                String CategoryID = request.getParameter("CategoryID");
                String QuantityPerUnit = request.getParameter("QuantityPerUnit");
                String UnitPrice = request.getParameter("UnitPrice");
                String UnitsInStock = request.getParameter("UnitsInStock");
                String UnitsOnOrder = request.getParameter("UnitsOnOrder");
                String ReorderLevel = request.getParameter("ReorderLevel");
                String Discontinued = request.getParameter("Discontinued");

                // check data (double check)
                if (ProductName.equals("")) {
                    out.print("Product name must not be empty");

                }

                      // convert values
                      
                int SupplierId = Integer.parseInt(SupplierID);
                int CategoryId = Integer.parseInt(CategoryID);
                double UnitPricE = Double.parseDouble(UnitPrice);
                int UnitsInStocK = Integer.parseInt(UnitsInStock);
                int UnitsOnOrdeR = Integer.parseInt(UnitsOnOrder);
                int ReorderLeveL = Integer.parseInt(ReorderLevel);
                boolean DiscontinueD = (Integer.parseInt(Discontinued) == 1 ? true : false);

                Product pro = new Product(ProductName, SupplierId, CategoryId, QuantityPerUnit, UnitPricE, UnitsInStocK, UnitsOnOrdeR, ReorderLeveL, DiscontinueD);
                //Product pro = new Product(ProductName, CategoryId, CategoryId, QuantityPerUnit, UnitPricE, UnitsInStocK, UnitsOnOrdeR, ReorderLeveL, DiscontinueD);
                //Product pro = new Product(ProductName, CategoryId, QuantityPerUnit, UnitPricE, UnitsInStocK, UnitsOnOrdeR, ReorderLeveL, DiscontinueD);
                int n= dao.addProduct(pro);
                response.sendRedirect("ProductURL?service=listAllProducts");

                
            }
            if (service.equals("listAllProducts")) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet ProductController</title>");
                out.println("</head>");
                out.println("<body>");

                // Form for searching
                out.print("""
                         <form action="ProductURL" method="get">
                      <p>Search Name: <input type="text" name="pname" id="">
                      <input type="submit" value="Search" name="submit">
                      <input type="reset" value="Clear">
                      <input type="hidden" name="service" value="listAllProducts">   
                      </p>
                          </form>
                      """);

                // Link for inserting a new product
                out.print("<p><a href=\"HTML/InsertProduct.html\">Insert Product</a></p>");

                String sql = "SELECT * FROM Products";
                String submit = request.getParameter("submit");
                if (submit == null) { //chua nhan submit --> khong search --> sql default
                    sql = "select * from Products";
                } else {
                    String pname = request.getParameter("pname");
                    sql = "select * from Products where ProductName like '%" + pname + "%'";
                }
                Vector<Product> vector = dao.getProduct(sql);

                // Product table
                out.println("<table border=1>\n"
                        + "        <tr>\n"
                        + "            <th>ProductID</th>"
                        + "            <th>ProductName</th>"
                        + "            <th>SupplierID</th>"
                        + "            <th>CategoryID</th>"
                        + "            <th>QuantityPerUnit</th>"
                        + "            <th>UnitPrice</th>"
                        + "            <th>UnitsInStock</th>"
                        + "            <th>UnitsOnOrder</th>"
                        + "            <th>ReorderLevel</th>"
                        + "            <th>Discontinued</th>"
                        + "            <th>Update</th>"
                        + "            <th>Delete</th>"
                        + "        </tr>");

                for (Product product : vector) {
                    out.print("<tr>\n"
                            + "            <td>" + product.getProductID() + "</td>\n"

                            + "            <td>" + product.getProductName() + "</td>\n"
                            + "            <td>" + product.getSupplierID() + "</td>\n"
                            + "            <td>" + product.getCategoryID() + "</td>\n"
                            + "            <td>" + product.getQuantityPerUnit() + "</td>\n"
                            + "            <td>" + product.getUnitPrice() + "</td>\n"
                            + "            <td>" + product.getUnitsInStock() + "</td>\n"
                            + "            <td>" + product.getUnitsOnOrder() + "</td>\n"
                            + "            <td>" + product.getReorderLevel() + "</td>\n"
                            + "            <td>" + product.isDiscontinued() + "</td>\n"
                            + "            <td> </td>\n"
                            + "<td><a href=\"ProductURL?service=deleteProduct&pid="+product.getProductID()+"\">Delete product</a></td>\n"
                            + "        </tr>");
                }
                out.print("</table>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Product Controller Servlet";
    }
}
