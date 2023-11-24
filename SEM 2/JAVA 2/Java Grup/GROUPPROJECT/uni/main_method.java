package uni;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
public class main_method {
    static Connection conn;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        System.out.println("WELCOME TO LJ UNIVERSITY !!");
        // Scanner sc = new Scanner(System.in);
        int user_choice;
        // try {
            if (connection()) {
                while(true) {
                    try {
                        System.out.println("1] Student\n2] Faculty\n3] Exit\n");
                        System.out.print("ENTER : ");
                        user_choice = sc.nextInt();
                        switch (user_choice) {
                            case 1: {
                                student_functionalities();
                                break;
                            }
                            case 2: {
                                faculty_functionalities();
                                break;
                            }
                            case 3: {
                                System.exit(0);
                            }
                            default :
                            {
                                System.out.println("ENTER AGAIN");
                            }
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Enter only numeric input");
                        sc.nextLine();
                    }
                } 
            } 
        // } catch (ClassNotFoundException e) {
            // System.out.println("Connection not done!");
            // System.exit(0);
        }
    
 static boolean connection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");// Loading Driver to Database
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");// Making connection with
          // the database
        if (conn == null) {

            return false;
        }
        else{
        return true;
        }
    }
    static void student_functionalities() throws Exception {
        boolean check = true;
        while (true) {
            System.out.println("1] SignUp\n2] Login\n3] Exit\nEnter your choice : ");
            try {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1: {
                        student_SignUp();
                        student_login();
                        check = false;
                        break;
                    }
                    case 2: {
                        student_login();
                        check = false;
                        break;
                    }
                    case 3: {
                        System.exit(0);
                    }
                    default: {
                        System.out.println("Enter from given options only");
                        break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter integer numbers");
                check = true;
                sc.nextLine();
            }
        }

    }

    static void faculty_functionalities() throws Exception {
        boolean check = true;
        while (check) {
            System.out.println("1] SignUp\n2] Login\n3] Exit\nEnter your choice : ");
            try {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1: {
                        faculty_SignUp();
                        faculty_login();
                        check = false;
                        break;
                    }
                    case 2: {
                        faculty_login();
                        check = false;
                        break;
                    }
                    case 3: {
                        System.exit(0);
                    }
                    default: {
                        System.out.println("Enter from given options only");
                        break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter integer numbers");
                check = true;
                sc.nextLine();
            }
        }
    }

    static void student_SignUp() throws Exception {

        Thread.sleep(200);
        System.out.println("PLEASE ENTER YOU DETAILS AS PER GIVEN INSTRUCTIONS BELOW");
        System.out.print("ENTER YOUR NAME : ");
        String name = sc.nextLine();
        System.out.print(" ENTER YOUR ROLL NO : ");
        int roll_no = sc.nextInt();
        System.out.print("DIVISION : ");
        String division = sc.next();
        System.out.print("EMAIL : ");
        String log_email = sc.next();
        do {
            System.out.print("ENTER PASSWORD : ");
            String password = sc.next();
            System.out.print("CONFIRM PASSWORD : ");
            String Cpassword = sc.next();
            if (password.equals(Cpassword)) {
                Statement st = conn.createStatement();
                String sql = "insert into student(Student_Name,Student_Roll,Student_Div,Student_email,Student_password)values('"
                        + name + "'," + roll_no + ",'" + division + "','" + log_email + "','" + password + "')";
                int r = st.executeUpdate(sql);
                Thread.sleep(200);
                if (r > 0) {
                    System.out.println("Your Information has been uploaded successfully");
                    break;
                } else {
                    System.out.println("System was unable to upload you data");
                }
            }
        } while (true);
    }

    static void student_window(String log_email) throws Exception {
        student s = new student();

        int choice78 = 0;
        System.out.println("WELCOME TO STUDENT FUNCTIONALITIES ! ");
        do {
            int count = 0;
            
            
            while(true)
            {
                try {
                    System.out.println("---------------------------- ");
            System.out.println("1.CHECK PENDING ASSIGNMENT");
            System.out.println("2.CHECK MARKS ");
            System.out.println("3.SUBMIT ASSIGNMENT ");
            System.out.println("4.APPEAR FOR TEST");
            System.out.println("5.CHECK RESULT OF EXAM");
            System.out.println("6.GIVE FEEDBACK");
            System.out.println("0. EXIT ");
                    System.out.print("ENTER : ");
                choice78 = sc.nextInt();
                    

                System.out.println("---------------------------- ");
            Statement sp = conn.createStatement();

            switch (choice78) {
                case 1: {

                    Thread.sleep(30);
                    String div = "Select Student_Div from student where Student_email='" + log_email + "'";
                    ResultSet rs = sp.executeQuery(div);
                    while (rs.next()) {
                        s.ReadingAssignments(rs.getString("Student_Div"));
                    }

                    break;
                }
                case 2: {
                    Thread.sleep(30);
                    String roll = "Select Student_Roll from student where Student_email='" +
                            log_email + "'";
                    ResultSet rs = sp.executeQuery(roll);
                    while (rs.next()) {
                        s.FetchingMarks(rs.getString("Student_Roll"));
                    }

                }
                    break;
                case 3: {
                    Thread.sleep(30);
                    String roll = "Select Student_Roll from student where Student_email='" +
                            log_email + "'";
                    ResultSet rs = sp.executeQuery(roll);
                    while (rs.next()) {
                        s.submittingAssignments(rs.getString("Student_Roll"));
                    }

                }
                    break;
                case 4: {
                    Thread.sleep(30);
                    String roll = "Select Student_Roll from student where Student_email='" +
                            log_email + "'";
                    ResultSet rs = sp.executeQuery(roll);
                    while (rs.next()) {
                        s.Appearfortest(rs.getString("Student_Roll"));
                    }
                }
                    break;
                case 5: {
                    Thread.sleep(30);
                    s.checkresult();
                }
                    break;
                case 6: {

                    Thread.sleep(30);
                    String name = "Select Student_Name from student where Student_email='" +
                            log_email + "'";
                    ResultSet rs = sp.executeQuery(name);
                    while (rs.next()) {
                        s.give_feedback(rs.getString("Student_Name"));
                    }

                }
                    break;
                case 0: {
                    System.exit(0);
                }
                default: {
                    System.out.println("INCORRECT OPTION TRY AGIAN");
                }
            }
                } catch (Exception e) {
                    System.out.println("ENTER AGAIN");
                    sc.nextLine();
                }
            }

            
        } while (choice78 != 0);
    }

    static void student_login() throws Exception {
        String log_email;// User input email ID to be checked
        Thread.sleep(200);
        Statement st1 = conn.createStatement();
        System.out.println("ENTER DETAILS");
        int check_email = 1;// temporary variables
        int check_password = 1;
        do {
            System.out.print("EMAIL ID : ");
            log_email = sc.next();
            String check1 = "select Student_email from student where Student_email='" +
                    log_email + "'";
            ResultSet rs1 = st1.executeQuery(check1);
            while (rs1.next()) {
                String true_email = rs1.getString("Student_email");
                if (!(true_email.equals(log_email))) {
                    // flag = 1;
                    System.out.println("INCORRECT EMAIL_ID TRY AGAIN");
                } else {
                    check_email = 0;
                }

            }
        } while (check_email == 1);

        do {
            System.out.print("PASSWORD : ");
            String log_password = sc.next();
            String check = "select Student_password from student where Student_email='" +
                    log_email + "'";
            ResultSet rs = st1.executeQuery(check);
            while (rs.next()) {
                String true_password = rs.getString("Student_password");
                if (true_password.equals(log_password)) {
                    check_password = 0;
                } else {
                    System.out.println("INCORRECT PASSWORD TRY AGAIN");
                    check_password = 1;
                }
            }
        } while (check_password == 1);
        String returnname = "select Student_Name from student where Student_email='"
                + log_email + "'";
        ResultSet rs1 = st1.executeQuery(returnname);// To retrieve student name
        Thread.sleep(10);
        while (rs1.next()) {
            String username = rs1.getString("Student_name");
            System.out.println("");
            System.out.println("HELLO " + username + " !!");
            System.out.println("---------------------------- ");
        }
        student_window(log_email);

    }

    static void faculty_SignUp() throws Exception {
        Thread.sleep(400);

        Thread.sleep(200);
        System.out.println("ENTER YOUR DETAILS");
        System.out.print(" NAME : ");
        String faculty_name = sc.nextLine();

        String short_name;
        do {
            System.out.print("SHORT NAME (Please Enter In Capital Letters and Only 3 Letters) : ");
            short_name = sc.next().toUpperCase();
            if (short_name.length() > 3) {
                System.out.println("Please Enter Valid Short Name ");
            } else {
                break;
            }
        } while (true);
        FileWriter fw = new FileWriter("feedback.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(faculty_name + " ");
        bw.write(short_name);
        bw.newLine();
        bw.flush();
        bw.close();
        System.out.print("SUBJECT : ");
        String subject = sc.next();
        System.out.print("EMAIL : ");
        String email1 = sc.next();
        do {
            System.out.print("PASSWORD :");
            String password = sc.next();
            System.out.println("CONFIRM PASSWORD :");
            String Cpassword = sc.next();
            if (password.equals(Cpassword)) {
                Statement st = conn.createStatement();
                String sql = "insert into faculty(faculty_name,faculty_short,faculty_subject,faculty_email,faculty_password)values('"
                        + faculty_name + "','" + short_name + "','" + subject + "','" + email1 + "','" + password
                        + "')";
                int r = st.executeUpdate(sql);
                if (r > 0) {
                    System.out.println("YOUR INFORMATION IS SAVED !");
                } else {
                    System.out.println("YOUR INFORMATION HAS NOT BEEN UPLOADED SUCCESSFULLY");
                }
                break;
            }
        } while (true);
    }

    static void faculty_login() throws Exception {
        Thread.sleep(200);
        Statement st1 = conn.createStatement();
        System.out.println("ENTER DETAILS");
        String log_email;
        int check_email = 1;
        int check_password = 1;
        do {
            System.out.print("EMAIL ID : ");
            log_email = sc.next();
            String check1 = "select faculty_email from faculty where faculty_email='" + log_email + "'";
            ResultSet rs1 = st1.executeQuery(check1);
            while (rs1.next()) {
                String true_email = rs1.getString("faculty_email");
                if (!(true_email.equals(log_email))) {
                    check_email = 1;
                    System.out.println("INCORRECT EMAIL_ID TRY AGAIN");
                } else {
                    check_email = 0;
                }
            }
        } while (check_email == 1);
        do {
            System.out.print("PASSWORD : ");
            String log_password = sc.next();
            String check = "select faculty_password from faculty where faculty_email='" + log_email + "'";
            ResultSet rs = st1.executeQuery(check);
            while (rs.next()) {
                String true_password = rs.getString("faculty_password");
                if (true_password.equals(log_password)) {
                    check_password = 0;
                } else {
                    System.out.println("INCORRECT PASSWORD TRY AGAIN");
                    check_password = 1;
                }
            }
        } while (check_password == 1);
        String returnname = "select faculty_Name from faculty where faculty_email='" + log_email + "'";
        ResultSet rs1 = st1.executeQuery(returnname);
        Thread.sleep(10);
        while (rs1.next()) {
            String username = rs1.getString("faculty_name");
            System.out.println("");
            System.out.println("HELLO " + username + " !!");
            System.out.println("---------------------------- ");
            System.out.println("---------------------------- ");
        }
        facultyf f = new facultyf();
        f.options();
    }

   

}
// String choice;
// int roll_no = 0;
// do {
// int c = 0;
// do {
// // System.out.println("1.STUDENT");
// // System.out.println("2.FACULTY");
// System.out.print("WHO ARE YOU?\n'STUDENT' OR 'FACULTY' : ");

// choice = sc.next();
// if (choice.equalsIgnoreCase("student") || choice.equalsIgnoreCase("faculty"))
// {
// c = 1;
// } else {
// System.out.println("");
// System.out.println("Please Enter valid identity");
// System.out.println("");
// }
// } while (c == 0);
// System.out.println("---------------------------- ");
// if (choice.equalsIgnoreCase("student")) {
// Class.forName("com.mysql.cj.jdbc.Driver");//Loading Driver to Database
// conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project",
// "root", "");//Making connection with the database
// Thread.sleep(200);//To show a break or buffering
// String log_email = "";//Dummy initialisation

// System.out.println("1.SIGNUP");
// System.out.println("2.LOGIN");
// System.out.print("ENTER YOU CHOICE IN NUMBERS: ");
// int choice1 = sc.nextInt();

// System.out.println("---------------------------- ");
// if (choice1 == 1) {
// Thread.sleep(200);
// System.out.println("PLEASE ENTER YOU DETAILS AS PER GIVEN INSTRUCTIONS
// BELOW");
// System.out.print("ENTER YOUR NAME : ");
// String name = sc.nextLine();
// System.out.print(" ENTER YOUR ROLL NO : ");
// roll_no = sc.nextInt();
// System.out.print("DIVISION : ");
// String division = sc.next();
// System.out.print("EMAIL : ");
// log_email = sc.next();
// do {
// System.out.print("ENTER PASSWORD : ");
// String password = sc.next();
// System.out.print("CONFIRM PASSWORD : ");
// String Cpassword = sc.next();
// if (password.equals(Cpassword)) {
// Statement st = conn.createStatement();
// String sql = "insert into
// student(Student_Name,Student_Roll,Student_Div,Student_email,Student_password)
// values('"
// + name + "'," + roll_no + ",'" + division + "','" + log_email + "','" +
// password
// + "')";
// int r = st.executeUpdate(sql);
// Thread.sleep(200);
// if (r > 0) {
// System.out.println("YOUR INFORMATION IS SAVED !");
// } else {
// System.out.println("DATABASE HAS BEEN CRASHED !");
// }
// break;
// }
// } while (true);
// } else if (choice1 == 2) {
// Thread.sleep(200);
// Statement st1 = conn.createStatement();
// System.out.println("ENTER DETAILS");
// int flag = 1;
// int flag1 = 1;
// do {
// System.out.print("EMAIL ID : ");
// log_email = sc.next();
// String check1 = "select Student_email from student where Student_email='" +
// log_email + "'";
// ResultSet rs1 = st1.executeQuery(check1);
// while (rs1.next()) {
// String true_email = rs1.getString("Student_email");
// if (!(true_email.equals(log_email))) {
// flag = 1;
// System.out.println("INCORRECT EMAIL_ID TRY AGAIN");
// } else {
// flag = 0;

// }

// }
// } while (flag == 1);

// do {
// System.out.print("PASSWORD : ");
// String log_password = sc.next();
// String check = "select Student_password from student where Student_email='" +
// log_email + "'";
// ResultSet rs = st1.executeQuery(check);
// while (rs.next()) {
// String true_password = rs.getString("Student_password");
// if (true_password.equals(log_password)) {
// flag1 = 0;

// } else {
// System.out.println("INCORRECT PASSWORD TRY AGAIN");
// flag1 = 1;
// }
// }
// } while (flag1 == 1);
// String returnname = "select Student_Name from student where Student_email='"
// + log_email + "'";
// ResultSet rs1 = st1.executeQuery(returnname);
// Thread.sleep(10);
// while (rs1.next()) {
// String username = rs1.getString("Student_name");
// System.out.println("");
// System.out.println("HELLO " + username + " !!");
// System.out.println("---------------------------- ");

// }
// }
// student s = new student();

// int choice78 = 0;
// System.out.println("WELCOME TO STUDENT FUNCTIONALITIES ! ");
// do {
// int count = 0;
// System.out.println("---------------------------- ");
// System.out.println("1.CHECK PENDING ASSIGNMENT");
// System.out.println("2.CHECK MARKS ");
// System.out.println("3.SUBMIT ASSIGNMENT ");
// System.out.println("4.APPEAR FOR TEST");
// System.out.println("5.CHECK RESULT OF EXAM");
// System.out.println("6.GIVE FEEDBACK");
// System.out.println("0. EXIT ");
// System.out.print("ENTER: ");
// choice78 = sc.nextInt();

// System.out.println("---------------------------- ");
// Statement sp = conn.createStatement();

// switch (choice78) {
// case 1: {

// Thread.sleep(30);
// String div = "Select Student_Div from student where Student_email='" +
// log_email + "'";
// ResultSet rs = sp.executeQuery(div);
// while (rs.next()) {
// s.ReadingAssignments(rs.getString("Student_Div"));
// }

// break;
// }
// case 2: {
// Thread.sleep(30);
// String roll = "Select Student_Roll from student where Student_email='" +
// log_email + "'";
// ResultSet rs = sp.executeQuery(roll);
// while (rs.next()) {
// s.FetchingMarks(rs.getString("Student_Roll"));
// }

// }
// break;
// case 3: {
// Thread.sleep(30);
// String roll = "Select Student_Roll from student where Student_email='" +
// log_email + "'";
// ResultSet rs = sp.executeQuery(roll);
// while (rs.next()) {
// s.submittingAssignments(rs.getString("Student_Roll"));
// }

// }
// break;
// case 4: {
// Thread.sleep(30);
// String roll = "Select Student_Roll from student where Student_email='" +
// log_email + "'";
// ResultSet rs = sp.executeQuery(roll);
// while (rs.next()) {
// s.Appearfortest(rs.getString("Student_Roll"));
// }
// }
// break;
// case 5: {
// Thread.sleep(30);
// s.checkresult();
// }
// break;
// case 6: {

// Thread.sleep(30);
// String name = "Select Student_Name from student where Student_email='" +
// log_email + "'";
// ResultSet rs = sp.executeQuery(name);
// while (rs.next()) {
// s.give_feedback(rs.getString("Student_Name"));
// }

// }
// break;
// case 0: {
// System.exit(0);
// }
// default: {
// System.out.println("INCORRECT OPTION TRY AGIAN");
// }
// }
// } while (choice78 != 0);

// } else if (choice.equalsIgnoreCase("faculty")) {
// Class.forName("com.mysql.cj.jdbc.Driver");
// conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project",
// "root", "");

// FACOOLTY

// Thread.sleep(400);
// System.out.println("1.SIGNUP");
// System.out.println("2.LOGIN");
// System.out.print("ENTER : ");
// int choice2 = sc.nextInt();
// System.out.println("---------------------------- ");
// if (choice2 == 1) {
// Thread.sleep(200);
// System.out.println("ENTER");
// System.out.print(" Name : ");
// String name1 = sc.next();

// String short_name;
// do {
// System.out.print("SHORT NAME (Please Enter In Capital Letters and Only 3
// Letters) : ");
// short_name = sc.next().toUpperCase();
// if (short_name.length() > 3) {
// System.out.println("Please Enter Valid Short Name ");
// continue;
// } else {
// break;
// }
// } while (true);
// FileWriter fw = new FileWriter("feedback.txt");
// BufferedWriter bw = new BufferedWriter(fw);
// bw.write(name1 + " ");
// bw.write(short_name);
// bw.newLine();
// bw.flush();
// bw.close();
// System.out.print("SUBJECT : ");
// String subject = sc.next();
// System.out.print("EMAIL : ");
// String email1 = sc.next();
// do {
// System.out.print("PASSWORD :");
// String password = sc.next();
// System.out.println("CONFIRM PASSWORD :");
// String Cpassword = sc.next();
// if (password.equals(Cpassword)) {
// Statement st = conn.createStatement();
// String sql = "insert into
// faculty(faculty_name,faculty_short,faculty_subject,faculty_email,faculty_password)
// values('"
// + name1 + "','" + short_name + "','" + subject + "','" + email1 + "','" +
// password
// + "')";
// int r = st.executeUpdate(sql);
// if (r > 0) {
// System.out.println("YOUR INFORMATION IS SAVED !");
// } else {
// System.out.println("DATABASE HAS BEEN CRASHED !");
// }
// break;
// }

// } while (true);
// System.out.println("---------------------------- ");

// } else if (choice2 == 2) {
// Thread.sleep(200);
// Statement st1 = conn.createStatement();
// System.out.println("ENTER DETAILS");
// String log_email;
// int flag = 1;
// int flag1 = 1;
// do {
// System.out.print("EMAIL ID : ");
// log_email = sc.next();
// String check1 = "select faculty_email from faculty where faculty_email='" +
// log_email + "'";
// ResultSet rs1 = st1.executeQuery(check1);
// while (rs1.next()) {
// String true_email = rs1.getString("faculty_email");
// if (!(true_email.equals(log_email))) {
// flag = 1;
// System.out.println("INCORRECT EMAIL_ID TRY AGAIN");
// } else {
// flag = 0;

// }

// }
// } while (flag == 1);

// do {
// System.out.print("PASSWORD : ");
// String log_password = sc.next();
// String check = "select faculty_password from faculty where faculty_email='" +
// log_email + "'";
// ResultSet rs = st1.executeQuery(check);
// while (rs.next()) {
// String true_password = rs.getString("faculty_password");
// if (true_password.equals(log_password)) {
// flag1 = 0;

// } else {
// System.out.println("INCORRECT PASSWORD TRY AGAIN");
// flag1 = 1;
// }
// }
// } while (flag1 == 1);
// String returnname = "select faculty_Name from faculty where faculty_email='"
// + log_email + "'";
// ResultSet rs1 = st1.executeQuery(returnname);
// Thread.sleep(10);
// while (rs1.next()) {
// String username = rs1.getString("faculty_name");
// System.out.println("");
// System.out.println("HELLO " + username + " !!");
// System.out.println("---------------------------- ");

// System.out.println("---------------------------- ");

// }

// }

// facultyf f = new facultyf();
// f.options();
// }

// } while (!choice.equalsIgnoreCase("student") &&
// !choice.equalsIgnoreCase("faculty"));

// }

// }