package Files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

class Employee_P {
    void login() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome login site !");
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/office", "root", "");
        Statement st1 = conn.createStatement();
        System.out.println("ENTER DETAILS");
        int flag = 1;
        int flag1 = 1;
        String log_email;
        do {
            System.out.print("EMAIL ID : ");
            log_email = sc.next();
            String check1 = "select mail from emp where mail='" + log_email + "'";
            ResultSet rs1 = st1.executeQuery(check1);
            while (rs1.next()) {
                String true_email = rs1.getString("mail");
                if (!(true_email.equals(log_email))) {
                    flag = 1;
                    System.out.println("INCORRECT EMAIL_ID TRY AGAIN");
                } else {
                    flag = 0;
                }
            }
        } while (flag == 1);

        do {
            System.out.print("PASSWORD : ");
            String log_password = sc.next();
            String check = "select password from emp where mail='" + log_email + "'";
            ResultSet rs = st1.executeQuery(check);
            while (rs.next()) {
                String true_password = rs.getString("password");
                if (true_password.equals(log_password)) {
                    flag1 = 0;
                } else {
                    System.out.println("INCORRECT PASSWORD TRY AGAIN");
                    flag1 = 1;
                }
            }
        } while (flag1 == 1);
        String returnname = "select e_name from emp where mail='" + log_email + "'";
        ResultSet rs1 = st1.executeQuery(returnname);
        while (rs1.next()) {
            String username = rs1.getString("e_name");
            System.out.println("HELLO " + username);
        }

    }

    void signUp() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome Signup Site !");
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/office", "root", "");
        System.out.println("Enter Your Name :");
        String name = sc.next();
        System.out.println("Enter Department Name :");
        String dname = sc.next();
        System.out.println("Enter Your Salary :");
        String salary = sc.next();
        System.out.println("Enter Your Position :");
        String pos = sc.next();
        System.out.println("Enter Your Mobile Number :");
        String numz = sc.next();
        System.out.println("Enter Your Mail :");
        String mail = sc.next();
        do {
            System.out.println("Enter Password :");
            String password = sc.next();
            System.out.println("Enter Confirm Password :");
            String Cpassword = sc.next();
            if (password.equals(Cpassword)) {
                Statement st = conn.createStatement();
                String sql = "insert into emp(e_name,e_dept,salary,position,number,mail,password) values('" + name
                        + "','" + dname + "','" + salary + "','" + pos + "','" + numz + "','" + mail + "','" + password
                        + "')";
                int r = st.executeUpdate(sql);
                if (r > 0) {
                    System.out.println("Your Information Has Been Added To Database !");
                } else {
                    System.out.println("Database Has Been Crashed !");
                }
                break;
            } else {
                System.out.println("Password Doesn't Match !");
                continue;
            }
        } while (true);
        login();

    }

    void gettingYourDetails() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Particular Employeee Data !");
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/office", "root", "");
        System.out.println("Enter Your Email Id :");
        String log_email = sc.next();
        String sql = "select * from emp where mail='" + log_email + "'";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            System.out.println("ID :" + rs.getInt("e_id"));
            System.out.println("NAME :" + rs.getString("e_name"));
            System.out.println("DEPT :" + rs.getString("e_dept"));
            System.out.println("SALARY  :" + rs.getString("salary"));
            System.out.println("POSITION :" + rs.getString("position"));
            System.out.println("NUMBER :" + rs.getString("number"));
        }
    }

    void salaryStatement() throws Exception {
        System.out.println("Salary Statement !");
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/office", "root", "");
        System.out.println("Enter Your Employee ID : ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        String filename = id + ".txt";
        File f = new File("Salary", filename);
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String sql = "select * from emp where e_id=" + id;
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        String line = br.readLine();
        while (line != null) {
            System.out.println(line);
            line = br.readLine();
        }
    }

    void mailingAdmin() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/office", "root", "");
        System.out.println("Mailing Admin !");
        System.out.println("Enter Your Employee Id :");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        String filename = id + "_mail.txt";
        File f = new File("Mails", filename);
        FileWriter fw = new FileWriter(f, true);
        BufferedWriter bw = new BufferedWriter(fw);
        System.out.println("Enter Admin Name :");
        String ad_name = sc.next();
        String sql = "select * from admin1 where a_name='" + ad_name + "'";
        String sql1 = "select * from emp where e_id=" + id;
        Statement st = conn.createStatement();
        Statement st1 = conn.createStatement();

        ResultSet rs = st.executeQuery(sql);
        ResultSet rs1 = st1.executeQuery(sql1);
        String ad;
        int ad_id;
        String mail_a;
        while (rs1.next()) {
            bw.write("| ==========================================================");
            bw.newLine();
            String mail_e = rs1.getString("mail");
            String e_name = rs1.getString("e_name");
            bw.write("| FROM : " + e_name);
            bw.newLine();
            bw.write("| Email : " + mail_e);
            bw.newLine();

        }
        rs1.close();
        while (rs.next()) {
            ad = rs.getString("a_name");
            ad_id = rs.getInt(1);
            mail_a = rs.getString("mail");
            bw.write("| TO :  " + ad_name);
            bw.newLine();
            bw.write("| Email : " + mail_a);
            bw.newLine();
            bw.write("| Respected Sir/Ma'am ,");
            bw.newLine();
            System.out.println("Enter Whatever You want to mail admin ?");
            String mail = sc.next();
            bw.write("| " + mail);
            bw.newLine();
            bw.write("| Thank You !!");
            bw.newLine();
            bw.write("| Your Respected !");
            bw.newLine();
            bw.write("| ==========================================================");
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    void gettingAllEmployee() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/office", "root", "");
        Scanner sc = new Scanner(System.in);
        System.out.println("Particluar Department's Employeess ");
        System.out.println("Enter For Which Department You Want List ?");
        String dep = sc.next();
        String sql = "select * from emp where e_dept='" + dep + "'";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            System.out.println("Id  : " + rs.getInt(1));
            System.out.println("Name  : " + rs.getString(2));
            System.out.println("Deptartment  : " + rs.getString(3));
            System.out.println("Salary  : " + rs.getString(4));
            System.out.println("Position  : " + rs.getString(5));
            System.out.println("Number  : " + rs.getString(6));
            System.out.println("Mail  : " + rs.getString(7));
            System.out.println();
        }
    }
}
