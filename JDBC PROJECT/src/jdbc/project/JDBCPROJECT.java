package jdbc.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCPROJECT {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3308/sample"; // Database URL
        String username = "root"; // Database username
        String password = "root"; // Database password

        try {
            // Establish a connection to the database
            Connection con = DriverManager.getConnection(url, username, password);
            if (con != null) {
                System.out.println("Connection established successfully");
            }

            Scanner sc = new Scanner(System.in);

            OUTER:
            while (true) {
                System.out.println("\n=== Menu ===");
                System.out.println("1. Insert a New User");
                System.out.println("2. View All Users");
                System.out.println("3. Update a User's Name");
                System.out.println("4. Delete a User");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine();
                // Use if-else instead of switch for readability
                switch (choice) {
                    case 1:
                        insertUser(con, sc); // Call method to insert user
                        break;
                    case 2:
                        viewAllUsers(con); // Call method to view all users
                        break;
                    case 3:
                        updateUser(con, sc); // Call method to update user
                        break;
                    case 4:
                        deleteUser(con, sc); // Call method to delete user
                        break;
                    case 5:
                        System.out.println("Exiting program.");
                        break OUTER;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }

            sc.close(); // Close scanner
            con.close(); // Close database connection

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // Method to insert a new user
    private static void insertUser(Connection con, Scanner sc) {
        try {
            System.out.print("Enter username: ");
            String name = sc.nextLine();
            System.out.print("Enter password: ");
            String pass = sc.nextLine();
            System.out.print("Enter user_id: ");
            int id = sc.nextInt();
            sc.nextLine(); // Clear the buffer

            // SQL query for inserting a new user
            String sql = "INSERT INTO users(username, password, user_id) VALUES(?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            // Setting the values in the SQL statement
            ps.setString(1, name);
            ps.setString(2, pass);
            ps.setInt(3, id);

            // Execute the query
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("User added successfully.");
            } else {
                System.out.println("Failed to add user.");
            }
            ps.close(); // Close the PreparedStatement

        } catch (SQLException e) {
            System.out.println("Error inserting user: " + e.getMessage());
        }
    }

    // Method to view all users
    private static void viewAllUsers(Connection con) {
        try {
            String query = "SELECT * FROM users";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            System.out.println("\n=== User List ===");
            while (rs.next()) {
                System.out.println("UserName: " + rs.getString("username"));
                System.out.println("Password: " + rs.getString("password"));
                System.out.println("User_id: " + rs.getInt("user_id"));
                System.out.println("------------------------");
            }
            rs.close(); // Close ResultSet
            st.close(); // Close Statement

        } catch (SQLException e) {
            System.out.println("Error retrieving users: " + e.getMessage());
        }
    }

    // Method to update a user's name
    private static void updateUser(Connection con, Scanner sc) {
        try {
            System.out.print("Enter the user_id of the user to update: ");
            int id = sc.nextInt();
            sc.nextLine(); // Clear the buffer
            System.out.print("Enter the new username: ");
            String newName = sc.nextLine();

            // SQL query for updating a user's name
            String sql = "UPDATE users SET username = ? WHERE user_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            // Setting the values in the SQL statement
            ps.setString(1, newName);
            ps.setInt(2, id);

            // Execute the update
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("User updated successfully.");
            } else {
                System.out.println("User not found or update failed.");
            }
            ps.close(); // Close the PreparedStatement

        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }

    // Method to delete a user
    private static void deleteUser(Connection con, Scanner sc) {
        try {
            System.out.print("Enter the user_id of the user to delete: ");
            int id = sc.nextInt();
            sc.nextLine(); // Clear the buffer

            // SQL query for deleting a user
            String sql = "DELETE FROM users WHERE user_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            // Setting the user_id in the SQL statement
            ps.setInt(1, id);

            // Execute the deletion
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("User not found or delete failed.");
            }
            ps.close(); // Close the PreparedStatement

        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }
}
