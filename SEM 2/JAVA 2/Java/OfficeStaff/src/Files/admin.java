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

class Admin_P {

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
            String check1 = "select mail from admin1 where mail='" + log_email + "'";
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
            String check = "select password from admin1 where mail='" + log_email + "'";
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
        String returnname = "select a_name from admin1 where mail='" + log_email + "'";
        ResultSet rs1 = st1.executeQuery(returnname);
        while (rs1.next()) {
            String username = rs1.getString("a_name");
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
                String sql = "insert into admin1(a_name,a_dept,salary,position,number,mail,password) values('" + name
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

    void fetchingEmployee() throws Exception {
        System.out.println("Fetching Employee Data !");
        Scanner sc = new Scanner(System.in);
        System.out.println("Particular Employeee Data !");
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/office", "root", "");
        System.out.println("Enter Employees's Email Id :");
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

    void credtingSalary() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/office", "root", "");
        System.out.println("Salary Credting !");
        System.out.println("Enter Employee Id For Whom You want to Credit Salary :");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        String filename = id + ".txt";
        File f = new File("Salary", filename);
        FileWriter fw = new FileWriter(f, true);
        BufferedWriter bw = new BufferedWriter(fw);
        String sql = "select * from emp where e_id=" + id;
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        int id1;
        String name, salary;
        int days_absent = 0;
        int deduction = 0;
        while (rs.next()) {
            id1 = rs.getInt("e_id");
            name = rs.getString("e_name");
            salary = rs.getString("salary");
            System.out.println("Enter For which Month You want to Credit Salary ?");
            String month = sc.next();
            System.out.println("This Is Employee's Salary :" + salary);
            System.out.println("Do You want Add Bonus ?(Enter 0 or Any Amount)");
            String bonus = sc.next();
            System.out.println("Enter How Many Days Employee has Been On Leave in these Month ?");
            ;
            days_absent = sc.nextInt();
            if (days_absent == 0) {
                deduction = 0;
            } else if (days_absent == 1 || days_absent == 2) {
                deduction = 1000;
            } else if (days_absent == 3 || days_absent == 4) {
                deduction = 2000;
            } else {
                deduction = 5000;
            }
            int final_salary = Integer.parseInt(salary) - deduction + Integer.parseInt(bonus);
            bw.write("=============================================================");
            bw.newLine();
            bw.write("| Employee ID :" + id1);
            bw.newLine();
            bw.write("| Employee NAME:" + name);
            bw.newLine();
            bw.write("| MONTH       SALARY      BONUS       DAYS ABSENT     FINAL SALARY");
            bw.newLine();
            bw.write("| " + month + "     " + salary + "          " + bonus + "       " + days_absent + "         "
                    + final_salary);
            bw.newLine();
            bw.write("=============================================================");
            bw.close();
        }

    }

    void replyingMail() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/office", "root", "");
        Scanner sc = new Scanner(System.in);
        System.out.println("Replying Mail !");
        System.out.println("Enter ID of Employee For Which You Have To Check Mail ?");
        int id = sc.nextInt();
        String filename = id + "_mail.txt";
        File f = new File("Mails", filename);
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while (line != null) {
            System.out.println(line);
            line = br.readLine();
        }
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
        String e_name = "";
        String mail_a;
        while (rs1.next()) {
            String mail_e = rs1.getString("mail");
            e_name = rs1.getString("e_name");
            bw.write("TO : " + e_name);
            bw.newLine();
            bw.write("Email : " + mail_e);
            bw.newLine();

        }
        rs1.close();
        while (rs.next()) {
            ad = rs.getString("a_name");
            ad_id = rs.getInt(1);
            mail_a = rs.getString("mail");
            bw.write("FROM :  " + ad_name);
            bw.newLine();
            bw.write("Email : " + mail_a);
            bw.newLine();
            bw.write("Dear " + e_name);
            bw.newLine();
            System.out.println("Enter Whatever You want to mail employee?");
            String mail = sc.next();
            bw.write(mail);
            bw.newLine();
            bw.write("Thank You !!");
            bw.write("===========================================================");
        }
        bw.flush();
        bw.close();
    }

    void allEmployee() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/office", "root", "");
        Scanner sc = new Scanner(System.in);
        System.out.println("By How Want To Search Employee ?");
        System.out.println("1. By Employee Id :");
        System.out.println("2. By Employee Name :");
        System.out.println("3. Employees List Of Particular Department  :");
        int choice = sc.nextInt();
        switch (choice) {
            case 1: {
                System.out.println("Particular Employee Details !");
                System.out.println("Enter Id Of The Employee :");
                int in_id = sc.nextInt();
                String sql = "select * from emp where id=" + in_id;
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
                break;
            case 2: {
                System.out.println("Particular Employee Details !");
                System.out.println("Enter Name Of The Employee :");
                String name = sc.next();
                String sql = "select * from emp where e_name='" + name + "'";
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
                break;
            case 3: {
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
                break;
            default: {

            }
        }

    }
}
