import controllers.ProductsController;
import dao.ProductDAO;
import models.Product;
import webserver.WebServer;
import webserver.WebServerContext;

public class App {
    public static void main(String[] args) throws Exception {
        WebServer server = new WebServer();
        server.listen(26790);

        server.getRouter().get(
            "/products",
            (WebServerContext context) -> {ProductsController.findAll(context);} 
        );

        server.getRouter().post(
            "/bid/:productId", 
            (WebServerContext context) -> {ProductsController.bid(context);}
        );
        
        ProductDAO productDAO = new ProductDAO();
    }
}
