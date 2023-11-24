package uni;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
class student {
    Scanner sc = new Scanner(System.in);

    void ReadingAssignments(String div) throws Exception {
        
        System.out.println("FETCHINGG DETIALS...");
        Thread.sleep(500);
        System.out.println("Here Are your Tasks :");
        String fileName = div + ".txt";
        File f = new File("Assignments", fileName);
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        if(line==null)
        {
            System.out.println("NO ASSIGNMENT FOUND");
        }
        while (line != null) {
            System.out.println(line);
            line = br.readLine();
        }
    }
    void give_feedback(String name)throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
        Statement st = conn.createStatement();
        FileReader fr=new FileReader("feedback.txt");
        BufferedReader br=new BufferedReader(fr);
         
        
    

        String check = "select Stud_name from feedback where Stud_name='" + name + "'";
                ResultSet rs = st.executeQuery(check);
                while (rs.next()) {
                    String true_name = rs.getString("Stud_name");
                    if (true_name.equals(name)){
                        Thread.sleep(30);
                        System.out.println();
                        System.out.println("FEEDBACK ALREADY GIVEN");

                        Thread.sleep(100);
                        break;
                    
                    }
                    else
                    {
                        int feedback;
        System.out.println("ENTER FEEDBACK:");
        
        String line1=br.readLine();

         while(line1!=null)
        {
            System.out.print(line1 +" : ");
            
            feedback=sc.nextInt();
            
            String name1="insert into feedback (Stud_name,Faculty_name,feedback) values ('"+name+"','"+line1+"',"+feedback+")";
            int r=st.executeUpdate(name1);
            
           line1=br.readLine();
           
        }
        
        br.close();
                    }
                }
            
    
        
                    }
                
            
    void FetchingMarks(String roll) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
        Statement st = conn.createStatement();
        
        System.out.println("FETCHINGG DETIALS...");
        Thread.sleep(500);
        String found_marks="select studentfac_rollno from marks where studentfac_rollno='"+roll +"'";
        ResultSet rst1= st.executeQuery(found_marks);
         String socho="";
        while(rst1.next())
        {
           socho=rst1.getString("studentfac_rollno");
        }

        if(!socho.equalsIgnoreCase(roll))
        {
            Thread.sleep(20);
                System.out.println("MARKSHEET NOT FOUND");
                System.out.println("");
            
        }
        String marks = "select * from marks where studentfac_rollno='" + socho + "'";
        ResultSet d = st.executeQuery(marks);
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
            if(total>=400 || total<=500)
            {
                System.out.println("Great Job ! Amazing");
            }
            else if(total>=300 || total<=400)
            {
                System.out.println("Well Done !");
            }
            else if(total>=200 || total<=300)
            {
                System.out.println("Good");
            }
            else if(total>=100 || total<200)
            {
                System.out.println("Do More Practice");
            }
            else
            {
                System.out.println("Fail");
            }
            System.out.println("");

        }
    }
void Appearfortest(String roll) throws Exception
{
    
    Thread.sleep(100);
    Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
        Statement st = conn.createStatement();
        
        System.out.println("FETCHINGG DETIALS...");
        Thread.sleep(500);
        String found_marks="select studentfac_rollno from marks where studentfac_rollno='"+roll +"'";
        ResultSet rst1= st.executeQuery(found_marks);
         String socho="";
        while(rst1.next())
        {
           socho=rst1.getString("studentfac_rollno");
        }
    String file_name= socho +".txt";

    System.out.println("QUESTIONS ARE GIVEN BELOW ANSWER ACCORDINGLY ");
    File f=new File("exam_student", file_name);
   // f.createNewFile();
   File d1=new File("exam","questions.txt");
    FileReader fr=new FileReader(d1);
    BufferedReader br=new BufferedReader(fr);
    FileWriter fw=new FileWriter(f);
    BufferedWriter bw=new BufferedWriter(fw);
   int count=0;
    String line=br.readLine();
    while(line!=null)
    {
        System.out.println(line);
        line=br.readLine();
        count++;
        if(count%10==0)
        {
             
    
    System.out.println("ENTER ANSWERS ");
            while(true)
            {
                try {
                    char answer=sc.next().charAt(0);
                    if(answer>=65 && answer<=69)
                    {
                        bw.write(answer);
                        bw.flush();
                        bw.newLine();
                        break;
                    }
                    else
                    {
                        System.out.println("ENTER ANSWER BETWEEN A AND D");
                    }
        
                } catch (Exception e) {
                    System.out.println("try again");
                    sc.nextLine();
                }
            }
        
    
        }

    }
    bw.close();
    br.close();
   
    


}
    void submittingAssignments(String roll) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
        Statement st = conn.createStatement();
         String found_marks="select studentfac_rollno from marks where studentfac_rollno='"+roll +"'";
        ResultSet rst1= st.executeQuery(found_marks);
         String socho="";
        while(rst1.next())
        {
           socho=rst1.getString("studentfac_rollno");
        }
        
        
        String filename = socho + ".txt";
        File directory = new File("Submitted_Assignments");
        
        File dir = new File(directory, filename);
     
        FileWriter fw = new FileWriter(dir,true);
        BufferedWriter bw = new BufferedWriter(fw);
        String ch;
        System.out.println("ENTER NO TO SUBMIT");
         System.out.println("Enter Your Code Here : ");
        do {
              String que = sc.nextLine();
            if (que.equalsIgnoreCase("no")) {

                bw.close();
                break;
            }
            bw.write(que);
            bw.flush();
            bw.newLine();
        } while (true);
        
    }
    void checkresult() throws Exception
    {
        
        facultyf f=new facultyf();
        f.check_paper();
        
        
    }

}