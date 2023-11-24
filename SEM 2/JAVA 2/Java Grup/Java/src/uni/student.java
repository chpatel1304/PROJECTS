package uni;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

class student {
    Scanner sc = new Scanner(System.in);

    void ReadingAssignments(String string) throws Exception {
        System.out.println("Enter Your Divsion Which You Study ?");
        String div = sc.next();
        System.out.println("FETCHINGG DETIALS...");
        Thread.sleep(500);
        System.out.println("Here Are your Tasks :");
        String fileName = div + ".txt";
        File f = new File("Assignments", fileName);
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while (line != null) {
            System.out.println(line);
            line = br.readLine();
        }
    }

    void FetchingMarks() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
        Statement st = conn.createStatement();
        System.out.println("ENTER STUDENT ROLLNO :");
        int id = sc.nextInt();
        System.out.println("FETCHINGG DETIALS...");
        Thread.sleep(500);
        String found_marks = "select studentfac_rollno from marks where studentfac_rollno=" + id + "";
        if (!found_marks.equalsIgnoreCase(String.valueOf(id))) {
            Thread.sleep(20);
            System.out.println("STUDENT NOT FOUND");
            System.out.println("TRY AGAIN");
            System.out.println("");
        }
        String marks = "select * from marks where studentfac_rollno=" + id + "";
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
            if (total >= 400 || total <= 500) {
                System.out.println("Great Job ! Amazing");
            } else if (total >= 300 || total <= 400) {
                System.out.println("Well Done !");
            } else if (total >= 200 || total <= 300) {
                System.out.println("Good");
            } else if (total >= 100 || total < 200) {
                System.out.println("Do More Practice");
            } else {
                System.out.println("Fail");
            }
            System.out.println("");

        }
    }

    void Appearfortest() throws Exception {
        facultyf ff = new facultyf();
        Thread.sleep(100);
        System.out.println("ENTER YOUR ROLL NUMBER ");
        String roll_number = sc.next();
        String file_name = roll_number + ".txt";

        System.out.println("QUESTIONS ARE GIVEN BELOW ANSWER ACCORDINGLY ");
        File f = new File("exam_student", file_name);
        // f.createNewFile();
        File d1 = new File("exam", "questions.txt");
        FileReader fr = new FileReader(d1);
        BufferedReader br = new BufferedReader(fr);

        String line = br.readLine();
        while (line != null) {
            System.out.println(line);
            line = br.readLine();

        }
        br.close();
        FileWriter fw = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(fw);
        System.out.println("ENTER ANSWERS ");
        String answer;
        do {

            answer = sc.next();
            bw.write(answer);
            bw.newLine();

            System.out.println(" DO YOU WANT TO ENTER ANSWER");
            int yes_no = sc.nextInt();
            if (answer.equalsIgnoreCase("no")) {
                bw.close();
                break;
            }
            bw.flush();
        } while (answer.equalsIgnoreCase("yes"));

    }

    void submittingAssignments() throws Exception {

        System.out.print("ENTER YOUR ROLL NO : ");

        String roll = sc.next();

        String filename = roll + ".txt";
        File directory = new File("Submitted_Assignments");
        // directory.mkdir();
        File dir = new File(directory, filename);
        // dir.createNewFile();
        FileWriter fw = new FileWriter(dir, true);
        BufferedWriter bw = new BufferedWriter(fw);
        String ch;
        do {

            System.out.println("Enter Your Code Here : ");
            sc.nextLine();
            String que = sc.nextLine();

            bw.write(que);
            bw.flush();
            bw.newLine();
            System.out.println("Do you want to enter another Code ?");

            ch = sc.next();

            if (ch.equalsIgnoreCase("no")) {

                bw.close();
                break;
            }

        } while (ch.equalsIgnoreCase("yes"));

    }

    void checkresult() throws Exception {

        facultyf f = new facultyf();
        f.check_paper();

    }

    void stud_options() throws Exception {
        int choice;
        System.out.println("WELCOME TO STUDENT FUNCTIONALITIES ! ");
        do {

            System.out.println("---------------------------- ");
            System.out.println("1.CHECK PENDING ASSIGNMENT");
            System.out.println("2.CHECK MARKS ");
            System.out.println("3.SUBMIT ASSIGNMENT ");
            System.out.println("4.APPEAR FOR TEST");
            System.out.println("5.CHECK RESULT OF EXAM");
            System.out.println("0. EXIT ");
            System.out.print("ENTER: ");
            choice = sc.nextInt();
            System.out.println("---------------------------- ");

            switch (choice) {
                case 1: {

                    Thread.sleep(30);
                    ReadingAssignments();
                }
                    break;
                case 2: {
                    Thread.sleep(30);
                    FetchingMarks();
                }
                    break;
                case 3: {
                    Thread.sleep(30);
                    submittingAssignments();
                }
                    break;
                case 4: {
                    Thread.sleep(30);
                    Appearfortest();
                }
                    break;
                case 5: {
                    Thread.sleep(30);
                    checkresult();
                }
                    break;
                case 0: {
                    System.exit(0);
                }
                default: {
                    System.out.println("INCORRECT OPTION TRY AGIAN");
                }
            }
        } while (choice != 0);
    }
}