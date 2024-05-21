package controllers;

import java.util.List;

import dao.ProductDAO;
import models.Product;
import webserver.WebServerContext;

public class ProductsController{

    public static List<Product> findAll(WebServerContext context){
        ProductDAO productDAO = new ProductDAO();
        context.getResponse().json(productDAO.findAll());
        return List.of();
    }

    public static void bid(WebServerContext context){
        int id = Integer.valueOf(context.getRequest().getParam("productId"));
        ProductDAO productDAO = new ProductDAO();
        boolean result = productDAO.bid(id);
        if(result){
            Product product = productDAO.find(id);
            String message = "{\"id\": \""+product.id()+"\", \"value\": \"" + product.bid()+"\"}";
            System.out.println(message);
            context.getSSE().emit("bids", message);
        }else{
            context.getResponse().serverError("Error occured when server try to bid");
        }
    }

}