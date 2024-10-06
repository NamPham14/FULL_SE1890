/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Customer;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Stack;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class DAOCustomer extends DBConnection {

    public int insertCustomer(Customer pro) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Customers]\n"
                + "           ([CustomerID]\n"
                + "           ,[CompanyName]\n"
                + "           ,[ContactName]\n"
                + "           ,[ContactTitle]\n"
                + "           ,[Address]\n"
                + "           ,[City]\n"
                + "           ,[Region]\n"
                + "           ,[PostalCode]\n"
                + "           ,[Country]\n"
                + "           ,[Phone]\n"
                + "           ,[Fax])\n"
                + "     VALUES('" + pro.getCustomerID() + "','" + pro.getCompanyName() + "','" + pro.getContactName() + "','" + pro.getContactTitle() + "',"
                + "'" + pro.getAddress() + "','" + pro.getCity() + "','" + pro.getRegion() + "','" + pro.getPostalCode() + "','" + pro.getCountry() + "','" + pro.getPhone() + "','" + pro.getFax() + "')";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;

    }

    public int addCustomer(Customer pro) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Customers]\n"
                + "([CustomerID]\n"
                + "           ,[CompanyName]\n"
                + "           ,[ContactName]\n"
                + "           ,[ContactTitle]\n"
                + "           ,[Address]\n"
                + "           ,[City]\n"
                + "           ,[Region]\n"
                + "           ,[PostalCode]\n"
                + "           ,[Country]\n"
                + "           ,[Phone]\n"
                + "           ,[Fax])\n"
                + "     VALUES(?,?,?,?,?,?,?,?,?,?,?)";
      
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pro.getCustomerID());
            pre.setString(2, pro.getCompanyName());
            pre.setString(3, pro.getContactName());
            pre.setString(4, pro.getContactTitle());
            pre.setString(5, pro.getAddress());
            pre.setString(6, pro.getCity());
            pre.setString(7, pro.getRegion());
            pre.setString(8, pro.getPostalCode());
            pre.setString(9, pro.getCountry());
            pre.setString(10, pro.getPhone());
            pre.setString(11, pro.getFax());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateCustomer(Customer pro) {
        int n = 0;
        String sql = "UPDATE [dbo].[Customers]\n"
                + "   SET [CustomerID] = ?\n"
                + "      ,[CompanyName] = ?\n"
                + "      ,[ContactName] = ?\n"
                + "      ,[ContactTitle] = ?\n"
                + "      ,[Address] = ?\n"
                + "      ,[City] =?\n"
                + "      ,[Region] = ?\n"
                + "      ,[PostalCode] =?\n"
                + "      ,[Country] = ?\n"
                + "      ,[Phone] =?\n"
                + "      ,[Fax] =?\n"
                + " WHERE [CustomerID]=?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pro.getCustomerID());
            pre.setString(2, pro.getCompanyName());
            pre.setString(3, pro.getContactName());
            pre.setString(4, pro.getContactTitle());
            pre.setString(5, pro.getAddress());
            pre.setString(6, pro.getCity());
            pre.setString(7, pro.getRegion());
            pre.setString(8, pro.getPostalCode());
            pre.setString(9, pro.getCountry());
            pre.setString(10, pro.getPhone());
            pre.setString(11, pro.getFax());
            pre.setString(12, pro.getCustomerID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    public int deleteCustomer(Customer pid) {
        int n = 0;
        String sql = "delete from Customer where CustomerID=" + pid;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;

    }

    public Vector<Customer> getCustomers(String sql) {
        Vector<Customer> vector = new Vector<Customer>();

        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String CustomerID = rs.getString("CustomerID");
                String CompanyName = rs.getString("CompanyName");
                String ContactName = rs.getString("ContactName");
                String ContactTitle = rs.getString("ContactTitle");
                String Address = rs.getString("Address");
                String City = rs.getString("City");
                String Region = rs.getString("Region");
                String PostalCode = rs.getString("PostalCode");
                String Country = rs.getString("Country");
                String Phone = rs.getString("Phone");
                String Fax = rs.getString("Fax");
               
                Customer pro= new Customer(CustomerID, CompanyName, ContactName, ContactTitle, Address, City, Region, PostalCode, Country, Phone, Fax);
                vector.add(pro);
            }

        } catch (SQLException ex) {

            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;

    }

    public static void main(String[] args) {

        DAOCustomer dao = new DAOCustomer();
//        int n = dao.insertCustomer(new Customer( "100", "FPT", "Nam Pham", "ABC",
//                "HL", "Ha Noi", "AA", "12345", "VN", "123456789", "A"));
//        if (n > 0) {
//            System.out.println("Inserted");
//        }

//        int n = dao.addCustomer(new Customer("100", "FPT", "Nam Pham", "ABC",
//                "HL", "Ha Noi", "AA", "12345", "VN", "123456789", "A"));
//        if (n > 0) {
//            System.out.println("Added");
//        }
//        int n = dao.updateCustomer(new Customer("99", "FPT", "Nam Pham", "ABC",
//                "HL", "Ha Noi", "AA", "12345", "VN", "123456789", "A"));
//        if (n > 0) {
//            System.out.println("updated");
//        }
//        int n= dao.deleteCustomer(1);
//        if(n>0){
//            System.out.println("success");
//        }


    Vector<Customer> vector = dao.getCustomers("Select * from Customers");
        for (Customer customer : vector) {
            System.out.println(customer);
        }
           
     
    }

}
