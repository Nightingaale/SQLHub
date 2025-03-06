import Entity.User;
import org.hibernate.Session;
public class Registration {

    public static void main(String[] args) {
        Session session = Connection.getSF().openSession();

        session.getTransaction().begin();

        User user = new User();
        user.setLogin("");
        user.setPassword("");

        session.persist(user);
        session.getTransaction().commit();

        session.close();
        Connection.shutdown();
    }
}
