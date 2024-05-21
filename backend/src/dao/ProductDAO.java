package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.PolyBayDatabase;
import models.Product;

public class ProductDAO {

    private PolyBayDatabase database;

    public ProductDAO(){
        try {
            this.database = new PolyBayDatabase();
        } catch (SQLException e) {
            System.err.println("La connexion à la base de données est impossible !");
        }
    }    

    public List<Product> findAll(){
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement ps = this.database.prepareStatement("SELECT * FROM product ORDER BY name;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Product product = new Product(rs.getInt("id"), rs.getString("name"), rs.getString("owner"), rs.getFloat("bid"));
                products.add(product);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.err.println("On n'a pas pu récupérer tous les products dans la base de donneés : " +e.getMessage());
        }
        return products;
    }

    public Product find(int id){
        try {
            PreparedStatement ps = this.database.prepareStatement("SELECT * FROM product WHERE id = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Product product = new Product(rs.getInt("id"), rs.getString("name"), rs.getString("owner"), rs.getFloat("bid"));
                return product;
            }
        } catch (SQLException e) {
            System.err.println("On n'a pas pu récupérer le produit avec l'identifiant "+ id + " dans la base de donneés : " +e.getMessage());

        }
        return null;
    }

    public boolean bid(int id){
        Product product = this.find(id);
        boolean result = false;
        try{
            PreparedStatement ps = this.database.prepareStatement("UPDATE product SET bid = ? WHERE id = ?");
            ps.setFloat(1, product.bid() + 50);
            ps.setInt(2, id);
            ps.execute();
            result = true;
        }catch(SQLException e){
            System.err.println("On n'a pas pu ajouter le prix du produit avec l'identifiant "+ id + " dans la base de donneés : " +e.getMessage());
        }
        return result;
    }

}
