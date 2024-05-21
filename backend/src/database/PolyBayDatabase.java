package database;

import java.sql.SQLException;

public class PolyBayDatabase extends MySQLDatabase {

    public PolyBayDatabase() throws SQLException{
        super("127.0.0.1", 3306, "poly_bay", "poly_bdd", "PolyBDD21*");      
    }
    
}
