package connectionpack;

import java.sql.*;
import java.util.logging.Logger;

/**
 * clasa creeaza un obiect de tipul ConnectionFactory si
 * stabileste conexiunea la baza de date
 *
 */
public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/assignment3_pt";
    private static final String USER = "root";
    private static final String PASS ="daria2004";
    private static  ConnectionFactory singleInstance = new ConnectionFactory();

    /**
     * contructorul clasei; incarca JDBC ptr MySQL
     */
    private ConnectionFactory(){
        try{
            Class.forName(DRIVER);
        } catch ( ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     *creeaza o conexiune la baza de date
     * @return connexiunea
     */
    private Connection createConnection(){
        Connection connection=null;
        try{
            connection = DriverManager.getConnection(ConnectionFactory.DBURL, ConnectionFactory.USER, ConnectionFactory.PASS);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return connection;
    }

    /**
     * obtine o instanta a conexiunii la baza de date
     * @return instanta
     */
    public static Connection getConnection(){
        return singleInstance.createConnection();
    }

    /**
     * verifica daca conexiunea nu este inchisa deja,
     * iar in cazul in care nu este o inchide
     * @param connection
     */
    public static void close(Connection connection){
        try {
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * verifica daca un statement nu este inchis deja,
     *   iar in cazul in care nu este il inchide
     * @param statement
     */
    public static void close(Statement statement){
        try{
            if(statement != null){
                statement.close();
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * verifica daca result setul nu este inchis deja,
     *  iar in cazul in care nu este, il inchide
     * @param rs
     */
     public static void close(ResultSet rs){
        try{
            if(rs!=null){
                rs.close();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
     }
}
