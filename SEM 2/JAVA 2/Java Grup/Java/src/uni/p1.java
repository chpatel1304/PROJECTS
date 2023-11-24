package uni;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class p1 {
    public static void main(String[] args) throws Exception {
        System.out.println("WELCOME TO LJ UNIVERSITY !!");
                Scanner sc = new Scanner(System.in);

        
        String choice;
       
       do
    {   
        int c=0;
        do{
        System.out.println("1.STUDENT");
        System.out.println("2.FACULTY");
        System.out.print("ENTER : ");
        
         choice = sc.next();
         if(choice.equalsIgnoreCase("student")||choice.equalsIgnoreCase("faculty"))
         {
            c=1;
         }
         else{
             System.out.println("");
            System.out.println("Enter from above options");
   System.out.println("");
        }
        }while(c==0);
        System.out.println("---------------------------- ");
        if (choice.equalsIgnoreCase("student")) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
            Thread.sleep(200);
            
            System.out.println("CHOOSE ONE ");
            System.out.println("SIGNUP");
            System.out.println("LOGIN");
            System.out.print("ENTER : ");
            String choice1 = sc.next();
            
            System.out.println("---------------------------- ");
            if (choice1.equalsIgnoreCase("signup")) {
                Thread.sleep(200);
            System.out.println("ENTER ");
                System.out.print("NAME : ");
                String name = sc.next();
                
                System.out.print("ROLL NO : ");
                int roll_no = sc.nextInt();
                System.out.print("DIVISION : ");
                String division = sc.next();
                System.out.print("EMAIL : ");
                String email = sc.next();
                do {
                    System.out.print("ENTER PASSWORD : ");
                    String password = sc.next();
                    System.out.print("CONFIRM PASSWORD : ");
                    String Cpassword = sc.next();
                    if (password.equals(Cpassword)) {
                        Statement st = conn.createStatement();
                        String sql = "insert into student(Student_Name,Student_Roll,Student_Div,Student_email,Student_password) values('"
                                + name + "'," + roll_no + ",'" + division + "','" + email + "','" + password + "')";
                        int r = st.executeUpdate(sql);
                        Thread.sleep(200);
                        if (r > 0) {
                            System.out.println("YOUR INFORMATION IS SAVED !");
                        } else {
                            System.out.println("DATABASE HAS BEEN CRASHED !");
                        }
                        break;
                    }
                } while (true);
            } else if (choice1.equalsIgnoreCase("login")) {
                Thread.sleep(200);
                Statement st1 = conn.createStatement();
                System.out.println("ENTER DETAILS");
                String log_email;
                int flag=1;
                int flag1=1;
               do{ 
                System.out.print("EMAIL ID : ");
                log_email = sc.next();
                String check1 = "select Student_email from student where Student_email='" + log_email + "'";
                ResultSet rs1 = st1.executeQuery(check1);
                while (rs1.next()) {
                    String true_email = rs1.getString("Student_email");
                    if (!(true_email.equals(log_email))) {
                        flag=1;
                        System.out.println("INCORRECT EMAIL_ID TRY AGAIN");
                    }
                    else{
                       flag=0;
                        
                    }
                    
                }}while(flag==1);


                do{
                System.out.print("PASSWORD : ");
                String log_password = sc.next();
                String check = "select Student_password from student where Student_email='" + log_email + "'";
                ResultSet rs = st1.executeQuery(check);
                while (rs.next()) {
                    String true_password = rs.getString("Student_password");
                    if (true_password.equals(log_password)) {
                        flag1=0;
                    
                    }
                    else {
                        System.out.println("INCORRECT PASSWORD TRY AGAIN");
                        flag1=1;
                    }
                }}while(flag1==1);
                String returnname = "select Student_Name from student where Student_email='" + log_email + "'";
                ResultSet rs1 = st1.executeQuery(returnname);
                Thread.sleep(10);
                while (rs1.next()) {
                    String username = rs1.getString("Student_name");
                    System.out.println("");
                    System.out.println("HELLO " + username +" !!");
                    System.out.println("---------------------------- ");

                    student s = new student();
                    s.stud_options();
                }
            }

        } else if (choice.equalsIgnoreCase("faculty")) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
            Thread.sleep(400);

            
            System.out.println("LOGIN");
            System.out.println("SIGNUP");
            System.out.print("ENTER : ");
            String choice2 = sc.next();
            System.out.println("---------------------------- ");

            if (choice2.equalsIgnoreCase("signup")) {
                Thread.sleep(200);
                System.out.println("ENTER");
                System.out.print(" Name : ");
                String name1 = sc.next();

                String short_name;
                do {
                    System.out.print("SHORT NAME (Please Enter In Capital Letters and Only 3 Letters) : ");
                    short_name = sc.next().toUpperCase();
                    if (short_name.length() > 3) {
                        System.out.println("Please Enter Valid Short Name ");
                        continue;
                    } else {
                        break;
                    }
                } while (true);
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
                        String sql = "insert into faculty(faculty_name,faculty_short,faculty_subject,faculty_email,faculty_password) values('"
                                + name1 + "','" + short_name + "','" + subject + "','" + email1 + "','" + password
                                + "')";
                        int r = st.executeUpdate(sql);
                        if (r > 0) {
                            System.out.println("YOUR INFORMATION IS SAVED !");
                        } else {
                            System.out.println("DATABASE HAS BEEN CRASHED !");
                        }
                        break;
                    }

                } while (true);
                System.out.println("---------------------------- ");

            } else if (choice2.equalsIgnoreCase("login")) {
                 Thread.sleep(200);
                Statement st1 = conn.createStatement();
                System.out.println("ENTER DETAILS");
                String log_email;
                int flag=1;
                int flag1=1;
               do{ 
                System.out.print("EMAIL ID : ");
                log_email = sc.next();
                String check1 = "select faculty_email from faculty where faculty_email='" + log_email + "'";
                ResultSet rs1 = st1.executeQuery(check1);
                while (rs1.next()) {
                    String true_email = rs1.getString("faculty_email");
                    if (!(true_email.equals(log_email))) {
                        flag=1;
                        System.out.println("INCORRECT EMAIL_ID TRY AGAIN");
                    }
                    else{
                       flag=0;
                        
                    }
                    
                }}while(flag==1);


                do{
                System.out.print("PASSWORD : ");
                String log_password = sc.next();
                String check = "select faculty_password from faculty where faculty_email='" + log_email + "'";
                ResultSet rs = st1.executeQuery(check);
                while (rs.next()) {
                    String true_password = rs.getString("faculty_password");
                    if (true_password.equals(log_password)) {
                        flag1=0;
                    
                    }
                    else {
                        System.out.println("INCORRECT PASSWORD TRY AGAIN");
                        flag1=1;
                    }
                }}while(flag1==1);
                String returnname = "select faculty_Name from faculty where faculty_email='" + log_email + "'";
                ResultSet rs1 = st1.executeQuery(returnname);
                Thread.sleep(10);
                while (rs1.next()) {
                    String username = rs1.getString("faculty_name");
                    System.out.println("");
                    System.out.println("HELLO " + username +" !!");
                    System.out.println("---------------------------- ");

                
                System.out.println("---------------------------- ");

                }
                facultyf f = new facultyf();
            f.options();
            }
            
        }
        
    }
    while(!choice.equalsIgnoreCase("student") && !choice.equalsIgnoreCase("faculty"));

    }
    

}