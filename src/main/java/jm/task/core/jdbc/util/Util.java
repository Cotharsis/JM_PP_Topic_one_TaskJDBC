package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.metamodel.MetadataSources;

import java.sql.*;
import java.util.Properties;

public class Util {
   // private final static String URL = "jdbc:mysql://localhost:3306/jm_schema_one?verifyServerCertificate=false&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTCeSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC";

    static final private String URL = "jdbc:mysql://localhost:3306/jm_schema_one?autoReconnect=true&useSSL=false";
    static final private String USER = "root";
    static final private String PASSWORD = "vfhc45rbibH23";
    Connection connection;


    public Util() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.err.println("Connection not gut!!!");
        }
    }

    public Connection getConnection() {
        return connection;
    }



    private static   SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration()
                        .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                        .setProperty("hibernate.connection.url", URL)
                        .setProperty("hibernate.connection.username", USER)
                        .setProperty("hibernate.connection.password", PASSWORD)
                        .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                        .setProperty("hibernate.show_sql", "true")
                     //   .setProperty("hibernate.current_session_context_class", "thread")
                       .addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                e.printStackTrace();
            }}
//        }else{
//            System.out.println("you lox");}

        return sessionFactory;
    }






/*




    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }*/
   /* public static void main(String arg[]) {
        Properties prop= new Properties();

        prop.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        prop.setProperty("hibernate.connection.url", URL);
        prop.setProperty("hibernate.connection.username", USER);
        prop.setProperty("hibernate.connection.password", PASSWORD);
        prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        prop.setProperty("hibernate.show_sql", "true");
        prop.setProperty("hibernate.current_session_context_class", "thread");

        SessionFactory sessionFactory = new Configuration().addProperties(prop).buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = new User();

        user.setName("test");
        user.setLastName("Whore");
        user.setAge((byte)12);
        session.save(user);
        session.getTransaction().commit();
        session.close();

    }
*/

   /* public static SessionFactory getSessionFactory(){

        final StandardServiceRegistry builder = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources( builder ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy( builder );
        }
        return sessionFactory;
    }*/



}


