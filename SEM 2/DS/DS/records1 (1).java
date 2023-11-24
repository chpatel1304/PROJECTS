import java.util.*;
import java.io.*;
import java.sql.*;

class StudentRecordSystem1 {
    class Node {
        String name, course;
        double java, maths, dbms, ds, fee, total_marks;
        int rollNumber;
        Node prev, next;
        double spi;

        public Node(String name, String course, double java, double maths, double ds, double dbms, double fee,
                double total_marks, int rollNumber, double spi) {
            this.name = name;
            this.course = course;
            this.java = java;
            this.maths = maths;
            this.ds = ds;
            this.dbms = dbms;
            this.fee = fee;
            this.total_marks = total_marks;
            this.spi = spi;
            this.rollNumber = rollNumber;
            prev = null;
            next = null;
        }
    }

    Node first = null;

    public void addRecord(String name, String course, double java, double maths, double ds, double dbms, double fee,
            double total_marks, int rollNumber, double spi) {
        Node newNode = new Node(name, course, java, maths, ds, dbms, fee, total_marks, rollNumber, spi);
        if (first == null) {
            first = newNode;
        } else {
            Node current = first;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            newNode.prev = current;
        }
    }

    void SPICalculate() {
        int java_credit = 6;
        int maths_credit = 5;
        int dbms_credit = 6;
        int ds_credit = 6;
        int fee_credit = 4;
        int total_credit = 27;
        Node temp = first;
        while (temp != null) {
            temp.spi = ((temp.java * java_credit) + (temp.maths * maths_credit) + (temp.dbms * dbms_credit)
                    + (temp.ds * ds_credit) + (temp.fee * fee_credit)) / (10 * total_credit);
            temp = temp.next;
        }
    }

    public void sortRecordsByMarks() {
        if (first == null) {
            System.out.println("Data is empty");
        }
        boolean swapped;
        Node current;
        Node tail = null;

        do {
            swapped = false;
            current = first;

            while (current.next != tail) {
                if (current.total_marks < current.next.total_marks) {
                    // Swap nodes
                    Node temp = current.next;
                    current.next = temp.next;
                    temp.next = current;
                    temp.prev = current.prev;
                    current.prev = temp;

                    if (temp.prev != null) {
                        temp.prev.next = temp;
                    } else {
                        first = temp;
                    }

                    if (current.next != null) {
                        current.next.prev = current;
                    }

                    swapped = true;
                } else {
                    current = current.next;
                }
            }
            tail = current;
        } while (swapped);
    }

    void merge(StudentRecordSystem1 s1, StudentRecordSystem1 s2) {
        Node temp = s1.first;
        s2.first = temp;
    }

    void sort() {
        int i = 1;
        Node temp = first;
        while (temp != null) {
            temp.rollNumber = i;
            i++;
            temp = temp.next;
        }
    }

    public void displayRecords() throws Exception {

        Node current = first;
        SPICalculate();
        System.out.println();
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-7s | %-7s | %-14s | %-11s | %-12s | %-9s | %-11s | %-10s | %-12s | %-17s |%n", "COURSE",
                "ROLLNO", "NAME", "JAVA MARKS", "MATHS MARKS", "DS MARKS", "DBMS MARKS", "FEE MARKS", "TOTAL MARKS",
                "SPI");
        while (current != null) {
            System.out.println(
                    "-------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-7s | %-7d | %-14s | %-11s | %-12s | %-9s | %-11s | %-10s | %-12s | %-17s |%n",
                    current.course.toUpperCase(), current.rollNumber, current.name, current.java, current.maths,
                    current.ds, current.dbms, current.fee, current.total_marks, String.format("%2f", current.spi));
            System.out.println(
                    "----------------------------------------------------------------------------------------------------------------------------------------------");
            current = current.next;
        }
        System.out.println();
    }

    void deleteData(int rno) {
        if (first == null) {
            System.out.println("PLZ ENTER DATA FIRST");
        } else if (first.rollNumber == rno) {
            if (first.next == null) {
                first = null;
            } else {
                first.next.prev = null;
                first = first.next;
            }

        } else {
            Node temp = first;
            while (temp.rollNumber != rno) {
                temp = temp.next;
            }
            if (temp.rollNumber == rno) {
                if (temp.next != null) {
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev;
                } else {
                    temp.prev.next = null;
                    temp.prev = null;
                }
            } else {
                System.out.println();
                System.out.println("NO SUCH DATA EXISTS");
            }
        }
    }

    void searchby_name(String name) throws Exception {
        Node temp = first;
        Node dummy = first;
        int flag = 0;
        while (dummy != null) {
            if (dummy.name.equalsIgnoreCase(name)) {
                flag = 1;
            }
            dummy = dummy.next;
        }
        if (flag == 0) {
            System.out.println();
            System.out.println("NO SUCH DATA EXISTS");
        } else {
            while (!temp.name.equalsIgnoreCase(name)) {
                temp = temp.next;
            }
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-7s | %-7s | %-14s | %-11s | %-12s | %-9s | %-11s | %-10s | %-12s | %-17s |%n",
                    "COURSE", "ROLLNO", "NAME", "JAVA MARKS", "MATHS MARKS", "DS MARKS", "DBMS MARKS", "FEE MARKS",
                    "TOTAL MARKS", "SPI");

            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-7s | %-7d | %-14s | %-11s | %-12s | %-9s | %-11s | %-10s | %-12s | %-17s |%n",
                    temp.course.toUpperCase(), temp.rollNumber, temp.name, temp.java, temp.maths, temp.ds, temp.dbms,
                    temp.fee, temp.total_marks, temp.spi);
            System.out.println(
                    "----------------------------------------------------------------------------------------------------------------------------------------------");

            // displayRecords();
        }
    }

    void searchby_rollNumber(int rno) throws Exception {
        Node temp = first;
        Node dummy = first;
        int flag = 0;
        while (dummy != null) {
            if (dummy.rollNumber == rno) {
                flag = 1;
            }
            dummy = dummy.next;
        }
        if (flag == 0) {
            System.out.println();
            System.out.println("NO SUCH DATA EXISTS");
        } else {
            while (temp.rollNumber != rno) {
                temp = temp.next;
            }
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-7s | %-7s | %-14s | %-11s | %-12s | %-9s | %-11s | %-10s | %-12s | %-17s |%n",
                    "COURSE", "ROLLNO", "NAME", "JAVA MARKS", "MATHS MARKS", "DS MARKS", "DBMS MARKS", "FEE MARKS",
                    "TOTAL MARKS", "SPI");

            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-7s | %-7d | %-14s | %-11s | %-12s | %-9s | %-11s | %-10s | %-12s | %-17s |%n",
                    temp.course.toUpperCase(), temp.rollNumber, temp.name, temp.java, temp.maths, temp.ds, temp.dbms,
                    temp.fee, temp.total_marks, temp.spi);
            System.out.println(
                    "----------------------------------------------------------------------------------------------------------------------------------------------");

            // displayRecords();
        }
    }

    void update() throws Exception {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("0.Exit");
            System.out.println("1.Name");
            System.out.println("2.Marks");
            System.out.println("3.Course");
            System.out.println();
            System.out.print("Select the parameter u want to update by their respective number : ");
            choice = sc.nextInt();
            switch (choice) {
                case 0: {
                    // System.out.println("Thank you visit again!!!");
                    break;
                }
                case 1: {
                    displayRecords();
                    System.out.println();
                    System.out.print("Enter the name u want to update : ");
                    sc.nextLine();
                    String name = sc.nextLine();
                    Node temp = first;
                    Node dummy = first;
                    int flag = 0;
                    while (dummy != null) {
                        if (dummy.name.equalsIgnoreCase(name)) {
                            flag = 1;
                        }
                        dummy = dummy.next;
                    }
                    if (flag == 0) {
                        System.out.println("No such entry available");
                    } else {
                        while (!temp.name.equalsIgnoreCase(name)) {
                            temp = temp.next;
                        }
                        System.out.print("Enter the updated name : ");
                        String n = sc.nextLine();
                        System.out.println();
                        temp.name = n;
                        System.out.println("Your name is updated");
                        // System.out.println();
                        displayRecords();
                    }
                    break;
                }
                case 2: {
                    displayRecords();
                    System.out.println();
                    System.out.print("Enter the name u want to update marks for : ");
                    sc.nextLine();
                    String name = sc.nextLine();
                    Node dummy = first;
                    Node temp = null;
                    int flag = 0;
                    while (dummy != null) {
                        if (dummy.name.equalsIgnoreCase(name)) {
                            flag = 1;
                            temp = dummy;
                        }
                        dummy = dummy.next;
                    }
                    if (flag == 0) {
                        System.out.println();
                        System.out.println("NO SUCH DATA EXISTS");
                    } else {
                        System.out.print("Enter subject name in which u want to update marks : ");
                        String n = sc.nextLine();
                        if (n.equalsIgnoreCase("java")) {
                            double total = temp.total_marks - temp.java;
                            System.out.print("Enter updated marks : ");
                            double java_updated = sc.nextDouble();
                            temp.java = java_updated;
                            temp.total_marks = total + java_updated;
                        } else if (n.equalsIgnoreCase("maths")) {
                            double total = temp.total_marks - temp.maths;
                            System.out.print("Enter updated marks : ");
                            double maths_updated = sc.nextDouble();
                            temp.maths = maths_updated;
                            temp.total_marks = total + maths_updated;
                        } else if (n.equalsIgnoreCase("ds")) {
                            double total = temp.total_marks - temp.ds;
                            System.out.print("Enter updated marks : ");
                            double ds_updated = sc.nextDouble();
                            temp.ds = ds_updated;
                            temp.total_marks = total + ds_updated;
                        } else if (n.equalsIgnoreCase("dbms")) {
                            double total = temp.total_marks - temp.dbms;
                            System.out.print("Enter updated marks : ");
                            double dbms_updated = sc.nextDouble();
                            temp.dbms = dbms_updated;
                            temp.total_marks = total + dbms_updated;
                        } else if (n.equalsIgnoreCase("fee")) {
                            double total = temp.total_marks - temp.fee;
                            System.out.print("Enter updated marks : ");
                            double fee_updated = sc.nextDouble();
                            temp.fee = fee_updated;
                            temp.total_marks = total + fee_updated;
                        } else {
                            System.out.println("Enter valid subject");
                        }
                        // System.out.println();
                        System.out.println("Your marks are updated");
                        displayRecords();
                    }
                    break;
                }
                case 3: {
                    System.out.print("Enter the name u want to update course for : ");
                    sc.nextLine();
                    String name = sc.nextLine();
                    Node temp = first;
                    Node dummy = first;
                    int flag = 0;
                    while (dummy != null) {
                        if (dummy.name.equalsIgnoreCase(name)) {
                            flag = 1;
                        }
                        dummy = dummy.next;
                    }
                    if (flag == 0) {
                        System.out.println("No such entry available");
                    } else {
                        while (!temp.name.equalsIgnoreCase(name)) {
                            temp = temp.next;
                        }
                        System.out.print("Enter the updated course : ");
                        String n = sc.next();
                        System.out.println();
                        temp.name = n;
                        System.out.println("Your course is updated");
                        System.out.println();
                        displayRecords();
                    }
                    break;
                }
            }
        } while (choice != 0);
    }
}

class studentManagement {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        StudentRecordSystem1 s1 = new StudentRecordSystem1();
        StudentRecordSystem1 s2 = new StudentRecordSystem1();
        int choice;
        int count = 0;
        int c = 1;
        do {
            count++;
            System.out.println();
            System.out.println("0.Exit");
            System.out.println("1.Enter data :");
            System.out.println("2.Display");
            System.out.println("3.assign rollnumber by marks");
            System.out.println("4.Delete the data");
            System.out.println("5.search student");
            System.out.println("6.update student details");
            // System.out.println("7.Assign Spi");
            System.out.println();
            System.out.print("Enter choice : ");
            choice = sc.nextInt();
            // sc.nextLine();
            // System.out.println();

            switch (choice) {
                case 0: {
                    if (count < 2) {
                        System.out.println();
                        System.out.println();
                        System.out.println("                ARE YOU SURE U WANT TO LEAVE!!!                  ");
                        System.out.println();
                        System.out.println();
                        continue;
                    } else {
                        System.out.println();
                        System.out.println();
                        System.out.println("               THANKS FOR VISITING !!!                  ");
                        System.out.println();
                        System.out.println();
                        break;
                    }

                }
                case 1: {
                    System.out.println();
                    String course = null;
                    int flag = 0;
                    do {
                        // System.out.println();
                        System.out.println("1.CE");
                        System.out.println("2.IT");
                        System.out.println("3.CSE");
                        System.out.println();
                        System.out.print("Enter your course name : ");
                        course = sc.next();
                        if ((course.equalsIgnoreCase("Ce")) || (course.equalsIgnoreCase("it"))
                                || (course.equalsIgnoreCase("Cse"))) {
                            flag = 1;
                        } else {
                            System.out.println();
                            System.out.println("Enter from above courses.");
                            System.out.println();
                        }
                    } while (flag == 0);
                    sc.nextLine();
                    System.out.print("Enter name : ");
                    String name = sc.nextLine();
                    double java = 0, maths = 0, ds = 0, dbms = 0, fee = 0;

                    while (true) {
                        System.out.print("Enter Java Marks : ");
                        java = sc.nextDouble();
                        if (java >= 0 && java <= 100)
                            break;
                        System.out.println("Enter marks between 1 to 100");
                    }
                    while (true) {
                        System.out.print("Enter Maths Marks : ");
                        maths = sc.nextDouble();
                        if (maths >= 0 && maths <= 100)
                            break;

                        System.out.println("Enter marks between 1 to 100");
                    }
                    while (true) {
                        System.out.print("Enter Data Structure Marks : ");
                        ds = sc.nextDouble();
                        if (ds >= 0 && ds <= 100)
                            break;

                        System.out.println("Enter marks between 1 to 100");
                    }
                    while (true) {
                        System.out.print("Enter DBMS Marks : ");
                        dbms = sc.nextDouble();
                        if (dbms >= 0 && dbms <= 100)
                            break;

                        System.out.println("Enter marks between 1 to 100");
                    }
                    while (true) {
                        System.out.print("Enter FEE Marks : ");
                        fee = sc.nextDouble();
                        if (fee >= 0 && fee <= 100)
                            break;

                        System.out.println("Enter marks between 1 to 100");
                    }

                    double total_marks = java + maths + ds + dbms + fee;
                    s1.addRecord(name, course, java, maths, ds, dbms, fee, total_marks, c, 0.0);
                    c++;
                    s2.merge(s1, s2);
                    break;
                }
                case 2: {
                    System.out.println();
                    // s2.sortRecordsByMarks();
                    s2.sort();
                    s2.displayRecords();
                    break;
                }
                case 3: {
                    System.out.println();
                    s2.sortRecordsByMarks();
                    s2.sort();
                    System.out.println("roll_number is assigned according to their total marks");
                    break;
                }
                case 4: {
                    s2.displayRecords();
                    System.out.println();
                    System.out.print("Enter the rollnumber u want to delete : ");
                    int n = sc.nextInt();
                    s2.deleteData(n);
                    System.out.println("DATA IS DELETED SUCCESSFULLY");
                    break;
                }
                case 5: {
                    s2.displayRecords();
                    System.out.println("1.By Name ");
                    System.out.println("2.By RollNumber");
                    System.out.print("Select what u want to search by : ");
                    int n = sc.nextInt();
                    System.out.println();
                    if (n == 1) {
                        System.out.print("Enter name u want to search:");
                        String name = sc.next();
                        s2.searchby_name(name);
                    } else if (n == 2) {
                        System.out.print("Enter RollNumber u want to search:");
                        int roll = sc.nextInt();
                        s2.searchby_rollNumber(roll);
                    }
                    break;
                }
                case 6: {
                    s2.update();
                    break;
                }

                default: {
                    System.out.println("Plz select from above options");
                    break;
                }
            }
        } while (choice != 0);
    }
}