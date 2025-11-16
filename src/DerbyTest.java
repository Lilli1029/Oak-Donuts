import java.sql.Connection;
import java.sql.DriverManager;

public class DerbyTest {
    public static void main(String[] args) {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

            Connection conn = DriverManager.getConnection(
                    "jdbc:derby:/Users/lisab/DerbyDatabase/DonutShop;create=true"
            );
            System.out.println("Derby connected!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}