package employeemanagementsystem;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.parser.DTDConstants;

public class DbOperations {

    String url = "jdbc:mysql://localhost:3306/employee";
    String username = "root";
    String password = "";
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    boolean addEmployeeDetails(EmployeeDetails em) {
        try {
            con = (Connection) DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO employeedetails VALUES (?,?,?,?,?,?,?,?)";
            pst = (PreparedStatement) con.prepareStatement(query);

            pst.setInt(1, em.getRegId());
            pst.setString(2, em.getFirstName());
            pst.setString(3, em.getLastName());
            pst.setInt(4, em.getAge());
            pst.setString(5, em.getCountry());
            pst.setString(6, em.getEmail());
            pst.setString(7, em.getUserName());
            pst.setString(8, em.getPassword());
            pst.executeUpdate();

            return true;

        } catch (Exception e) {
            System.out.print(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {

            }
        }

    }

    int checkUserName(String username) {
        try {
            con = (Connection) DriverManager.getConnection(url, this.username, password);
            String query = "SELECT username FROM employeedetails";
            pst = (PreparedStatement) con.prepareStatement(query);

            rs = pst.executeQuery();
            while (rs.next()) {
                if (username.equals(rs.getString(1))) {
                   return 0;
                }
            }
            return 1;

        } catch (Exception e) {
            System.out.print(e);
            return 2;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {

            }
        }

    }

    int checkLogin(String username, String password) {
        try {
            con = (Connection) DriverManager.getConnection(url, this.username, this.password);
            String query = "SELECT username,password FROM employeedetails";
            pst = (PreparedStatement) con.prepareStatement(query);

            rs = pst.executeQuery();
            while (rs.next()) {
                if (username.equals(rs.getString(1))) {
                    if (password.equals(rs.getString(2))) {
                        return 0;
                    }
                    return 1;
                }
            }
            return 2;

        } catch (Exception e) {
            System.out.print(e);
            return 3;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {

            }
        }
    }

    void getAllEmployeeDetails(DefaultTableModel model) {
        try {
            con = (Connection) DriverManager.getConnection(url, this.username, this.password);
            String query = "SELECT * FROM employeedetails";
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Object[] a = new Object[7];
                a[0] = rs.getInt(1);
                a[1] = rs.getString(2);
                a[2] = rs.getString(3);
                a[3] = rs.getInt(4);
                a[4] = rs.getString(5);
                a[5] = rs.getString(6);
                a[6] = rs.getString(7);
                model.insertRow(model.getRowCount(), a);
            }

        } catch (Exception e) {
            System.out.println(e);

        }

    }

}
