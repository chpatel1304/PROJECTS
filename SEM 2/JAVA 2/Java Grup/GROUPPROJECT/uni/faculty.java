package uni;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

class Myexception1 extends Exception {
    public Myexception1(String message) {
        super(message);
    }
}

class Myexception2 extends Exception {
    public Myexception2(String message) {
        super(message);
    }
}

class facultyf {

    void WrittingAssignments() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter For What Divsion Number You want To make Assignments ?");
        System.out.println("Please Enter Between 1 To 11");
        int div_num = 0;
        while (true) {
            try {
                div_num = sc.nextInt();
                if (div_num > 11) {
                    throw new Myexception1("Enter Value Between 1 to 11 !");
                }
                break;
            } catch (Exception e) {
                System.out.println("INPUT  INVALID !");
                System.out.println("Enter Again ");
                System.out.println("Please Enter Between 1 To 11");
                
                    sc.nextLine();
                    continue;
                
            }
        }
        String div = "D" + div_num;
        String filename = div + ".txt";
        File directory = new File("Assignments");
        // directory.mkdir();
        File dir = new File(directory, filename);
        // dir.createNewFile();
        FileWriter fw = new FileWriter(dir, true);
        BufferedWriter bw = new BufferedWriter(fw);
        String ch;
        do {

            System.out.println("Enter Your Questions To Assigns : ");
            sc.next();
            String que = sc.nextLine();

            bw.write(que);
            bw.flush();
            bw.newLine();
            System.out.println("Do you want to enter another Questions ?");

            ch = sc.next();

            if (ch.equalsIgnoreCase("no")) {

                bw.close();
                break;
            }

        } while (ch.equalsIgnoreCase("yes"));

    }

    void EnteringMarks() throws Exception {
        Scanner sc = new Scanner(System.in);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
        Statement st = conn.createStatement();
        System.out.println("ENTER MARKS BETWEEN 0 TO 100");
        System.out.println();
        System.out.println("ENTER NAME OF STUDENT :");
        String search_name = "";
         int flag=0;
        while(true)
        {
  search_name = sc.next();
        String database_name = "select studentfac_name from marks ";
        // where studentfac_name=' "+search_name +"'"
        ResultSet k = st.executeQuery(database_name);
        while (k.next()) {
            String mila_name = k.getString("studentfac_name");
            if ((search_name.equalsIgnoreCase(mila_name))) {
                
                System.out.println("NAME ALREADY EXISTS");    
            flag=0; 
            break; } 
            else
            {
                flag=1;
                break;
                
            }   
        }
        if(flag==1)
        {
            break;
        }

        }
        int flag1=0;
        if(flag==1)
        {
System.out.println("ENTER ROLL NO :");
       String search_roll;
        while(true)
        {
         search_roll = sc.next();
        String database_roll = "select Student_Roll from student where Student_Name='"+search_name+"'";
        
        ResultSet k1 = st.executeQuery(database_roll);
        while (k1.next()) {
            String mila_roll = k1.getString("Student_Roll");
            if ((search_roll.equalsIgnoreCase(mila_roll))) {    
            flag1=1; 
        break;
        }    else
        {
            System.out.println("ENTER AGAIN");
            flag=0;
        }
        }
        if(flag1==1)
        {
            break;
        }

        }

            if(flag1==1)
            {
        System.out.println("ENTER FEE MARKS :");
        int fee_marks;
        while (true) {
            try {
                fee_marks = sc.nextInt();
                if (fee_marks > 100) {
                    throw new Myexception2("Please enter marks between 100");
                }
                break;
            } catch (Exception e) {
                System.out.println("Enter FEE marks Again");
                ;
                sc.nextLine();
                continue;
            }
        }
        System.out.println("ENTER JAVA MARKS :");
        int java_marks;
        while (true) {
            try {
                java_marks = sc.nextInt();
                if (java_marks > 100) {
                    throw new Myexception2("Please enter marks between 100");
                }
                break;
            } catch (Exception e) {
                System.out.println("Enter JAVA marks Again");
                ;
                sc.nextLine();
                continue;
            }
        }
        System.out.println("ENTER MATHS MARKS :");
        int maths_marks;
        while (true) {
            try {
                maths_marks = sc.nextInt();
                if (maths_marks > 100) {
                    throw new Myexception2("Please enter marks between 100");
                }
                break;
            } catch (Exception e) {
                System.out.println("Enter MATHS marks Again");
                ;
                sc.nextLine();
                continue;
            }
        }
        System.out.println("ENTER DS MARKS :");
        int ds_marks;
        while (true) {
            try {
                ds_marks = sc.nextInt();
                if (ds_marks > 100) {
                    throw new Myexception2("Please enter marks between 100");
                }
                break;
            } catch (Exception e) {
                System.out.println("Enter DS marks Again");
                ;
                sc.nextLine();
                continue;
            }
        }
        System.out.println("ENTER DBMS MARKS :");
        int dbms_marks;
        while (true) {
            try {
                dbms_marks = sc.nextInt();
                if (dbms_marks > 100) {
                    throw new Myexception2("Please enter marks between 100");
                }
                break;
            } catch (Exception e) {
                System.out.println("Enter DBMS marks Again");
                ;
                sc.nextLine();
                continue;
            }
        }
        int total_marks = fee_marks + java_marks + maths_marks + ds_marks + dbms_marks;

        String sql1 = "insert into marks (studentfac_name,studentfac_rollno,FEE,JAVA,MATHS,DS,DBMS,total_marks) values('"
                + search_name + "'," + search_roll + "," + fee_marks + "," + java_marks + "," + maths_marks + ","
                + ds_marks + "," + dbms_marks + "," + total_marks + ")";
        int r = st.executeUpdate(sql1);
        if (r > 0) {
            System.out.println("Your Information Has Been Added To Database !");
        } else {
            System.out.println("Database Has Been Crashed !");
        }
        }
    }
    }

    void UpdatingMarks() throws Exception {
        Scanner sc = new Scanner(System.in);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
        Statement st = conn.createStatement();
        int flag=0;
        String search_name;
        System.out.println("UPDATE MARKS OF ANY STUDENT");
        System.out.println("ENTER NAME");
        while(true)
        {
 search_name = sc.next();
        String database_name = "select studentfac_name from marks ";
        ResultSet k = st.executeQuery(database_name);
        while (k.next()) {
            String mila_name = k.getString("studentfac_name");
            if ((search_name.equalsIgnoreCase(mila_name))) {    
            flag=1;  }    
        }
        if(flag==0)
        {
            System.out.println("NAME NOT FOUND ENTER AGAIN");
        }
        if(flag==1)
        {
            break;
        }

        }

int flag1=0;
        if(flag==1){

String search_roll;
System.out.println("ENTER ROLL NO");
        while(true)
        {
         search_roll = sc.next();
        String database_roll = "select studentfac_rollno from marks where studentfac_name='"+search_name+"'";
        
        ResultSet k1 = st.executeQuery(database_roll);
        while (k1.next()) {
            String mila_roll = k1.getString("studentfac_rollno");
            if ((search_roll.equalsIgnoreCase(mila_roll))) {    
            flag1=1;  } 
               
        }
        if(flag==0)
        {
            System.out.println(" INCORRECT ROLL NO ");
        }
        if(flag1==1)
        {
            break;
        }

        }

            if(flag1==1)
            {

System.out.println("ENTER SUBJECT : ");
        String subject = sc.next();
        String data_subject = subject.toUpperCase();
        int marks_update=0;
        while(true)
        {
        try
        {
        System.out.println("ENTER MARKS");

        marks_update = sc.nextInt();
        String update_karle = "update marks set  " + data_subject + "=" + marks_update
                + " where studentfac_rollno='" + search_roll + "'";
        int c = st.executeUpdate(update_karle);
        while (c > 0) {
            break;
        }
        System.out.println("UPDATE DONE");
        }
        catch(Exception e)
        {
            System.out.println("ENTER AGAIN");
            sc.nextLine();
        }
    }
        


        }
    }
        
    }

    void GettingMarks() throws Exception {
        Scanner sc = new Scanner(System.in);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
        Statement st = conn.createStatement();
        System.out.println("ENTER STUDENT ID :");
        int id;
        while (true) {
            try {
                id = sc.nextInt();
                if (id < 0) {
                    throw new Myexception2("Please enter valid id");
                }
                break;

            } catch (Exception e) {
                System.out.println("Please enter student id again");
                
                sc.nextLine();
                continue;
            }
        }
        String details = "select * from marks where studentfac_rollno=" + id + "";
        ResultSet d = st.executeQuery(details);
        while (d.next()) {
            System.out.println("");
            System.out.print("NAME : " + d.getString("studentfac_name"));
            System.out.println("");
            System.out.print("ROLL NUMBER : " + d.getInt("studentfac_rollno"));
            System.out.println("");
            System.out.print("FEE : " + d.getInt("FEE"));
            System.out.println("");

            System.out.print("JAVA : " + d.getInt("JAVA"));
            System.out.println("");

            System.out.print("MATHS : " + d.getInt("MATHS"));
            System.out.println("");

            System.out.print("DS : " + d.getInt("DS"));
            System.out.println("");

            System.out.print("DBMS : " + d.getInt("DBMS"));
            System.out.println("");
            int total = d.getInt("FEE") + d.getInt("JAVA") + d.getInt("MATHS") + d.getInt("DS")
                    + d.getInt("DBMS");
            System.out.println("TOTAL MARKS : " + total);
            System.out.println("");
        }
    }

    void conducttest(int question) throws Exception {
        File f = new File("exam", "questions.txt");
        FileWriter fw = new FileWriter(f);
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(fw);
        System.out.println("TEST !!!!");
        String questions, ch, options;

        int i = 1;

        do {

            System.out.println("ENTER MULTIPLE CHOICE QUESTION ");
            System.out.print("QUESTION " + i + ". ");

            questions = sc.next();
            bw.write("question : " + i);
            bw.newLine();
            bw.write(questions);
            bw.newLine();
            int j = 1;
            char k = 'A';
            do {
                System.out.print("OPTION " + k + ". ");
                sc.nextLine();
                options = sc.next();
                bw.write("options : " + k);
                bw.newLine();
                bw.write(options);

                bw.newLine();
                j++;
                k++;
                if ((k % 5) == 'E') {
                    bw.newLine();
                    break;
                }
            } while (j != 5);

            i++;

        } while (i != (question + 1));
        bw.flush();
        bw.close();

    }

    void ReadingAssignments() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Rollno");
        String roll_no = sc.next();
        String file_name = roll_no + ".txt";

        System.out.println("FETCHINGG DETIALS...");
        Thread.sleep(500);

        File f = new File("Submitted_Assignments", file_name);
        if (!f.isFile()) {
            System.out.println("  ASSIGNMENT NOT FOUND!");
            return;
        }
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while (line != null) {
            System.out.println(line);
            line = br.readLine();
        }
        System.out.println(roll_no + " CHECKED ");
    }

    void answerkey(int question) throws Exception {
        Scanner sc = new Scanner(System.in);

        File ans = new File("exam", "anskey.txt");

        // directory.mkdir();

        // dir.createNewFile();
        FileWriter fw = new FileWriter(ans);
        BufferedWriter bw = new BufferedWriter(fw);

        String ch;
        System.out.println("ENTER YOUR ANSWER FOR FOLLOWING QUESTION : ");
        int i = 1;
        do {
            System.out.print(i + ". ");

            String anskeyy = sc.next();

            bw.write(anskeyy);

            bw.newLine();

            i++;
        } while (i != (question + 1));
        bw.flush();
        bw.close();
    }

    void check_paper() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("ENTER");
        System.out.println("ROLL NO");
        int roll_no=0;
        while(true)
        {
        try{
 roll_no = sc.nextInt();
 String roll = roll_no + ".txt";
        File f = new File("exam_student", roll);
        f.createNewFile();
        File f1 = new File("exam", "anskey.txt");
        FileReader fr = new FileReader(f);
        FileReader fr2 = new FileReader(f1);
        BufferedReader br = new BufferedReader(fr);
        BufferedReader br2 = new BufferedReader(fr2);
        String line = br.readLine();
        String line1 = br2.readLine();
        if (line == null) {
            System.out.println(roll_no + " HAS NOT GIVEN EXAM");
        } else {

            int count = 0;
            while (line != null && line1 != null) {

                if (line.equalsIgnoreCase(line1)) {

                    count++;

                }
                line = br.readLine();
                line1 = br2.readLine();

            }
            br.close();
            br2.close();
            System.out.println("STUDENT " + roll_no + " GOT " + count + " MARKS");
            break;
        }}catch(Exception e)
        {
            System.out.println("enter again");
            sc.nextLine();
        }}
        
        
        

    }

    void check_feedback() throws Exception {
        String sum = "select Faculty_name,sum(feedback) from feedback group by Faculty_name  ";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
        PreparedStatement ps = conn.prepareStatement(sum);
        ResultSet r = ps.executeQuery();
        System.out.println("FEEDBACKS RESULT :");
        while (r.next()) {
            System.out.println(r.getInt("sum(feedback)") + " : " + r.getString("Faculty_name"));
        }
    }

    void options() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
        Statement st = conn.createStatement();
        Scanner sc = new Scanner(System.in);
        int choice=0;
        System.out.println("WELCOME TO FACULTIES FUNCTIONALITIES  !");

        while(true) {
            System.out.println("---------------------------- ");

            System.out.println("1.GIVE ASSIGNMENT ");
            System.out.println("2.ENTER RESULT ");
            System.out.println("3.UPDATE RESULT ");
            System.out.println("4.CHECK RESULT  ");
            System.out.println("5.CHECK ASSIGNMENT ");
            System.out.println("6.CONDUCT EXAM ");
            System.out.println("7.MAKE ASNWER KEY");
            System.out.println("8.CHECK EXAM");
            System.out.println("9. CHECK FEEDBACK");
            System.out.println("0. EXIT");
            System.out.print("ENTER : ");
            try{
            choice = sc.nextInt();}
            catch(Exception e)
            {
                sc.nextLine();
                continue;
            }
            System.out.println("---------------------------- ");

            switch (choice) {
                case 1: {
                    Thread.sleep(30);
                    WrittingAssignments();
                    break;
                }
                case 2: {
                    Thread.sleep(30);
                    EnteringMarks();
                    break;
                }
                case 3: {
                    Thread.sleep(30);
                    UpdatingMarks();
                    break;
                }
                case 4: {
                    Thread.sleep(30);
                    GettingMarks();
                    break;
                }
                case 5: {
                    Thread.sleep(30);
                    ReadingAssignments();
                    break;
                }
                case 6: {
                    int questions1=0;
                    System.out.print("ENTER NUMBER OF QUESTIONS :");
                    while(true)
                    {
                        try {
                        questions1 = sc.nextInt();
                        Thread.sleep(30);
                    conducttest(questions1);
                    break;
                    } catch (Exception e) {
                        System.out.println("ENTER AGAIN");
                        sc.nextLine();                    
                    }
                    
                    } 
                    break; 
                }
                case 7: {
                    int questions=0;
                    System.out.print("ENTER NUMBER OF QUESTIONS :");
                    while(true)
                    {

                    
                    try {
                        questions = sc.nextInt();
                        answerkey(questions);
                    break;

                    } catch (Exception e) {
                        System.out.println("ENTER AGAIN");
                        sc.nextLine();
                    }}
                    break;
                    
                }
                case 8: {

                    check_paper();
                    System.out.println("");
                    System.out.println("paper checked!");
                    break;

                }
                case 9: {
                    check_feedback();
                    System.out.println("");
                    Thread.sleep(30);
                    break;
                }
                case 0: {
                    System.exit(0);
                }
                default: {
                    System.out.println("INCORRECT OPTION TRY AGAIN");
                }
            }
        } 
    }

}