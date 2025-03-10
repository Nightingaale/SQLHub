import Entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.security.SecureRandom;
import java.util.Scanner;

public class Registration {
    private static final SecureRandom random = new SecureRandom();
    private static final String VALID_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890-_+";
    private static final String VALID_DIGITS = "1234567890";
    private static final int MIN_LENGTH = 5;
    private static final int MAX_LENGTH = 30;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Your login: ");
        String username = in.nextLine();

        try {
            if (!username.matches("[a-zA-Z]+")) {
                throw new IllegalArgumentException("Username must contain only letters");
            }
            Profile profile = new Profile(username);
            profile.displayProfile();
            saveUserToDatabase(profile.getUsername(), profile.getPassword(), profile.getID());
        }
        catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        finally {
            in.close();
        }
    }

    public static String generatePassword(int length) {
        validateLength(length);
        return generateRandomString(length, VALID_CHARS);
    }

    public static String generateID(int length) {
        validateLength(length);
        return generateRandomString(length, VALID_DIGITS);
    }

    private static String generateRandomString(int length, String characters) {
        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }
        return result.toString();
    }

    private static void validateLength(int length) {
        if (length < MIN_LENGTH || length > MAX_LENGTH) {
            throw new IllegalArgumentException("Length must be between " + MIN_LENGTH + " and " + MAX_LENGTH);
        }
    }

    public static void saveUserToDatabase(String username, String password, String userID) {
        Session session = Connection.getSF().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            User user = new User();
            user.setLogin(username);
            user.setPassword(password);
            user.setId(Integer.parseInt(userID));

            session.persist(user);
            transaction.commit();
            System.out.println("User saved to database successfully!");

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error saving user to database: " + e.getMessage());
        }
        finally {
            if (session != null) {
                session.close();
            }
            Connection.shutdown();
        }
    }
}

class Profile {
    private final String username;
    private final String password;
    private final String ID;

    public Profile(String username) {
        this.username = username;
        this.password = Registration.generatePassword(15);
        this.ID = Registration.generateID(7);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getID() {
        return ID;
    }

    public void displayProfile() {
        System.out.println("Your username: " + username);
        System.out.println("Your password: " + password);
        System.out.println("Your ID: " + ID);
    }
}