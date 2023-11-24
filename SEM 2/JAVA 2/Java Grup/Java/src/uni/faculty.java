package uni;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

class facultyf {

    void WrittingAssignments() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter For What Divsion You want To make Assignments ?");
        System.out.println("Please Enter Between D1 To D11");

        String div = sc.next();

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
        String sf_name = sc.next();
        System.out.println("ENTER ROLL NO :");
        int roll_no = sc.nextInt();
        System.out.println("ENTER FEE MARKS :");
        int fee_marks = sc.nextInt();
        System.out.println("ENTER JAVA MARKS :");
        int java_marks = sc.nextInt();
        System.out.println("ENTER MATHS MARKS :");
        int maths_marks = sc.nextInt();
        System.out.println("ENTER DS MARKS :");
        int ds_marks = sc.nextInt();
        System.out.println("ENTER DBMS MARKS :");
        int dbms_marks = sc.nextInt();
        int total_marks = fee_marks + java_marks + maths_marks + ds_marks + dbms_marks;

        String sql1 = "insert into marks (studentfac_name,studentfac_rollno,FEE,JAVA,MATHS,DS,DBMS,total_marks) values('"
                + sf_name + "'," + roll_no + "," + fee_marks + "," + java_marks + "," + maths_marks + ","
                + ds_marks + "," + dbms_marks + "," + total_marks + ")";
        int r = st.executeUpdate(sql1);
        if (r > 0) {
            System.out.println("Your Information Has Been Added To Database !");
        } else {
            System.out.println("Database Has Been Crashed !");
        }
    }

    void UpdatingMarks() throws Exception {
        Scanner sc = new Scanner(System.in);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
        Statement st = conn.createStatement();
        System.out.println("UPDATE MARKS OF ANY STUDENT");
        System.out.println("enter name");
        String search_name = sc.next();
        String database_name = "select studentfac_name from marks where studentfac_name='" + search_name
                + "'";
        ResultSet k = st.executeQuery(database_name);

        while (k.next()) {
            String mila_name = k.getString("studentfac_name");
            if (mila_name.equals(search_name)) {

                break;
            }

        }
        System.out.println("enter rollno");
        String search_rollno = sc.next();
        String database_rollno = "select studentfac_rollno from marks where studentfac_rollno='"
                + search_rollno + "'";
        ResultSet v = st.executeQuery(database_rollno);
        while (v.next()) {
            String mila_rollno = v.getString("studentfac_rollno");
            if (mila_rollno.equals(search_rollno)) {
                break;
            }
        }
        System.out.println("ENTER SUBJECT : ");
        String subject = sc.next();
        String data_subject = subject.toUpperCase();
        System.out.println("ENTER MARKS");
        int marks_update = sc.nextInt();

        String update_karle = "update marks set  " + data_subject + "=" + marks_update
                + " where studentfac_rollno=" + search_rollno;
        int c = st.executeUpdate(update_karle);
        while (c > 0) {
            break;
        }
        System.out.println("UPDATE DONE");
    }

    void GettingMarks() throws Exception {
        Scanner sc = new Scanner(System.in);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
        Statement st = conn.createStatement();
        System.out.println("ENTER STUDENT ID :");
        int id = sc.nextInt();
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
    void conducttest(int question)throws Exception
    {
        File f=new File("exam", "questions.txt");
        FileWriter fw=new FileWriter(f);
       Scanner sc=new Scanner(System.in);
        BufferedWriter bw=new BufferedWriter(fw); 
       System.out.println("TEST !!!!");
       String questions,ch,options;
        
       int i=1;

       
       do {
            
            System.out.println("ENTER MULTIPLE CHOICE QUESTION ");
            System.out.print("QUESTION "+i+". ");
            
            questions = sc.next();
            bw.write("question : " +i);
            bw.newLine();
            bw.write(questions);
            bw.newLine();
            int j=1;
            do
            {
                System.out.print("OPTION "+(j%5)+". ");
              sc.nextLine();
                options=sc.next();
                bw.write("options : " +j);
                bw.newLine();
                bw.write(options);
               
                
                bw.newLine();
                j++;
                if((j%5)==0)
                {
                    bw.newLine();
                    break;
                }
            }
            while(j!=5);
        
        
           
            
            
            // System.out.println("Do you want to enter another question ?");
            
            // ch = sc.next();
            
            // if (ch.equalsIgnoreCase("no")) {

            //     bw.close();
            //     break;
            // }
            i++;
            
        } while (i!=(question+1));
         bw.flush();
        bw.close();

    }
       void ReadingAssignments() throws Exception {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Rollno");
        String roll_no=sc.next();
        String file_name=roll_no +".txt";
        
        System.out.println("FETCHINGG DETIALS...");
        Thread.sleep(500);
        
        File f=new File("Submitted_Assignments",file_name );
        if(!f.isFile())
        {
            System.out.println("STUDENT " +roll_no +" HAS NOT SUBMITTED ASSIGNMENT YET!" );
            return;
        }
        FileReader fr=new FileReader(f);
        BufferedReader br=new BufferedReader(fr);
        String line=br.readLine();
        while(line!=null)
        {
            System.out.println(line);
            line=br.readLine();
        }
        System.out.println(roll_no +" CHECKED ");
     }


        void answerkey(int question)throws Exception{
            Scanner sc=new Scanner(System.in);
            

        File ans=new File("exam","anskey.txt" ); 
        
        // directory.mkdir();
        
        // dir.createNewFile();
        FileWriter fw = new FileWriter(ans);
        BufferedWriter bw = new BufferedWriter(fw);

        String ch;
            System.out.println("ENTER YOUR ANSWER FOR FOLLOWING QUESTION : ");
            int i=1;
        do {
            System.out.print(i +". ");
            
            String anskeyy = sc.next();

            bw.write(anskeyy);
            
            bw.newLine();
            
            i++;
        } while (i!=(question+1));
bw.flush();
bw.close();
    }
        

    String check_paper()throws Exception{
        Scanner sc=new Scanner(System.in);
         System.out.println("ENTER");
                    System.out.println(" ROLL NO");
                    int roll_no=sc.nextInt();
                    String roll=roll_no+".txt";
                    File f=new File("exam_student",roll);
                    f.createNewFile();
                    File f1=new File("exam","anskey.txt" );
                    FileReader fr=new FileReader(f);
                    FileReader fr2=new FileReader(f1);
                    BufferedReader br=new BufferedReader(fr);
                    BufferedReader br2=new BufferedReader(fr2);
                    String line=br.readLine();
                    String line1=br2.readLine();
                    
                    int count=1;
                    while(line!=null && line1!=null)
                    {
                
                            if(line1==line)
                            {
                                count++;
                            }
                        line=br.readLine();
                        line1=br.readLine();
                        count++;
                    }
                    System.out.println("STUDENT "+roll_no +" GOT "+count +" MARKS");

               return "STUDENT "+roll_no +" GOT "+count +" MARKS";
    }

    void options() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
        Statement st = conn.createStatement();
        Scanner sc = new Scanner(System.in);
            int choice,questions;
            System.out.println("WELCOME TO FACULTIES FUNCTIONALITIES  !");

            do {
            System.out.println("---------------------------- ");

            System.out.println("1.GIVE ASSIGNMENT ");
            System.out.println("2.ENTER RESULT ");
            System.out.println("3.UPDATE RESULT ");
            System.out.println("4.CHECK RESULT  ");
            System.out.println("5.CHECK ASSIGNMENT ");
            System.out.println("6.CONDUCT EXAM ");
            System.out.println("7.MAKE ASNWER KEY");
            System.out.println("8.CHECK EXAM");
            System.out.println("0. EXIT");
            System.out.print("ENTER : ");
            choice = sc.nextInt();
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
                case 6 :{
                    System.out.print("ENTER NUMBER OF QUESTIONS :");
                    questions=sc.nextInt();
                    Thread.sleep(30);
                    conducttest(questions);
                    break;
                }
                case 7 :{
                    System.out.print("ENTER NUMBER OF QUESTIONS :");
                    questions=sc.nextInt();
                    Thread.sleep(30);
                    answerkey(questions);
                    break;
                }
                case 8:{
                   
                    check_paper();
                    System.out.println("");
                    System.out.println("paper checked!");
                    break;

                }
                case 0:
                {
                    System.exit(0);
                }
                default :
                {
                    System.out.println("INCORRECT OPTION TRY AGAIN");
                }
            }
        } while (choice!=0);
    }

    
}