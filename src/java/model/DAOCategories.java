/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Categories;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Stack;
import java.util.Vector;
import jdk.jfr.Description;

/**
 *
 * @author Admin
 */
public class DAOCategories extends DBConnection {

    public int insertCategories(Categories pro) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Categories]\n"
                + "           ([CategoryName]\n"
                + "           ,[Description]\n"
                + "           ,[Picture])\n"
                + "     VALUES('" + pro.getCategoryName() + "','"
                + "" + pro.getDescription() + "','"
                + "" + pro.getPicture() + "')";
        System.out.println(sql);

        
        try {
          Statement  state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategories.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;

    }

    public int addCategories(Categories pro) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Categories]\n"
                + "           ([CategoryName]\n"
                + "           ,[Description])\n"
                
                + "     VALUES(?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pro.getCategoryName());
            pre.setString(2, pro.getDescription());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;

    }
    
   
    

    
    public int deleteCategories(int pid){
        int n =0;
    // ....
        try {
            
            
            String sql="DELETE FROM Categories WHERE CategoriesID="+pid;
            Statement state = conn.createStatement();
            n= state.executeUpdate(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategories.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
         return n;
    }
    
    
    public int updateCategories(Categories pro) {
        int n = 0;
        String sql = "UPDATE [dbo].[Categories]\n"
                + "   SET [CategoryName] = ?\n"
                + "      ,[Description] = ?\n"
                + "      ,[Picture] = ?\n"
                + " WHERE CategoryID =?";
        try {
            PreparedStatement pre= conn.prepareStatement(sql);
            pre.setString(1, pro.getCategoryName());
            pre.setString(2, pro.getDescription());
            pre.setString(3, pro.getPicture());
            pre.setInt(4, pro.getCategoryID());
            n=pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return n;

    }
    
    
    public Vector<Categories> getCategorieses(String sql){
        Vector<Categories> vector= new Vector<Categories>();
        
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
            int CategoryID= rs.getInt("CategoryID");
            String CategoryName = rs.getString("CategoryName");
            String Description = rs.getString("Description");
            String Picture = rs.getString("Picture");
            Categories pro= new Categories(CategoryID, CategoryName, Description, Picture) ;
            vector.add(pro);
                 
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategories.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        return vector;
    }

    public static void main(String[] args) {
        DAOCategories dao = new DAOCategories();
//        int x = dao.insertCategories(new Categories("Name2", "Dec2", "Pic2"));
//        if (x > 0) {
//            System.out.println("inserted");
//        } 
//        int n = dao.insertCategories(new Categories( "ABC", "ABC", "ABC"));
//        if (n > 0) {
//            System.out.println("added");
//        }
//       int n = dao.updateCategories(new Categories(13, "new Categories", "ABC", "ABC"));
//       if (n > 0) {
//           System.out.println("update");
//       }
//        Vector<Categories> vector = dao.getCategorieses("Select * FROM Categories");
//        for (Categories categories : vector) {
//            System.out.println(categories);
//            
//        }
        
        int x = dao.addCategories(new Categories("sáa", "aaa"));
        if(x>0){
            System.out.println("ADDD");
        }
                
       
    }

}
