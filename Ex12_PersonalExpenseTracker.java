import java.sql.*;
import java.util.Scanner;

class Expense {

    private int id;
    private String title;
    private String category;
    private double amount;
    private String date;

    Expense(String title, String category, double amount, String date) {
        this.title = title;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }
}

public class PersonalExpenseTracker {

    static final String URL =
            "jdbc:mysql://localhost:3306/expense_tracker";

    static final String USER = "root";

    static final String PASSWORD = "root";

    static Scanner sc = new Scanner(System.in);

    // Database Connection
    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Add Expense
    static void addExpense() {

        System.out.print("Enter expense title: ");
        String title = sc.nextLine();

        System.out.print("Enter category: ");
        String category = sc.nextLine();

        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = sc.nextLine();

        String sql = "INSERT INTO expenses " +
                "(title, category, amount, expense_date) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, category);
            ps.setDouble(3, amount);
            ps.setDate(4, Date.valueOf(date));

            ps.executeUpdate();

            System.out.println("\nExpense added successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // View Expenses
    static void viewExpenses() {

        String sql = "SELECT * FROM expenses ORDER BY expense_date DESC";

        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\n--------- EXPENSE DETAILS ---------");

            System.out.printf("%-5s %-20s %-15s %-10s %-15s%n",
                    "ID", "Title", "Category", "Amount", "Date");

            while (rs.next()) {

                System.out.printf("%-5d %-20s %-15s %-10.2f %-15s%n",
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("category"),
                        rs.getDouble("amount"),
                        rs.getDate("expense_date"));
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Search by Category
    static void searchCategory() {

        System.out.print("Enter category: ");
        String category = sc.nextLine();

        String sql = "SELECT * FROM expenses WHERE category = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, category);

            ResultSet rs = ps.executeQuery();

            System.out.println("\nExpenses in category: " + category);

            while (rs.next()) {

                System.out.println(
                        "ID: " + rs.getInt("id") +
                                " | Title: " + rs.getString("title") +
                                " | Amount: " + rs.getDouble("amount") +
                                " | Date: " + rs.getDate("expense_date"));
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Calculate Total Expense
    static void totalExpense() {

        String sql = "SELECT SUM(amount) AS total FROM expenses";

        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) {

                double total = rs.getDouble("total");

                System.out.println("\nTotal Expense = ₹" + total);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Delete Expense
    static void deleteExpense() {

        System.out.print("Enter expense ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        String sql = "DELETE FROM expenses WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Expense deleted successfully!");
            else
                System.out.println("Expense ID not found.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Main Menu
    public static void main(String[] args) {

        while (true) {

            System.out.println("\n================================");
            System.out.println("     PERSONAL EXPENSE TRACKER");
            System.out.println("================================");

            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Search by Category");
            System.out.println("4. Calculate Total Expense");
            System.out.println("5. Delete Expense");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addExpense();
                    break;

                case 2:
                    viewExpenses();
                    break;

                case 3:
                    searchCategory();
                    break;

                case 4:
                    totalExpense();
                    break;

                case 5:
                    deleteExpense();
                    break;

                case 6:
                    System.out.println("Thank you for using Expense Tracker!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
