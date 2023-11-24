package Files;

import java.util.Scanner;

class Employee {
    void LoginORSignup() throws Exception {
        Employee_P E = new Employee_P();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1.SIGNUP \n 2.LOGIN ");
            int choice = sc.nextInt();
            if (choice == 1) {
                E.signUp();
                break;
            } else if (choice == 2) {
                E.login();
                break;
            } else {
                continue;
            }
        }
    }

    void OptionsDisplay() throws Exception {
        int choice;
        Employee_P E = new Employee_P();
        do {
            System.out.println("Welcome To Employee Portal");
            System.out.println("1.Fetching Your Data .");
            System.out.println("2.Checking Your Salary Statement .");
            System.out.println("3.Mail Admin For Leave or Any Doubt ");
            System.out.println("4.Get Details Of Your Department Employess ");
            System.out.println("5.Exit");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    E.gettingYourDetails();
                }
                    break;
                case 2: {
                    E.salaryStatement();
                }
                    break;
                case 3: {
                    E.mailingAdmin();
                }
                    break;
                case 4: {
                    E.gettingAllEmployee();
                }
                    break;
                case 5: {
                    System.out.println("Thank you For Visiting !");
                }
                    break;
                default: {
                    System.out.println("Enter Valid Input !");
                }
                    break;
            }
        } while (choice != 5);
    }
}

class Admin {

    void LoginORSignup() throws Exception {
        Admin_P A = new Admin_P();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1.SIGNUP \n 2.LOGIN ");
            int choice = sc.nextInt();
            if (choice == 1) {
                A.signUp();
                break;
            } else if (choice == 2) {
                A.login();
                break;
            } else {
                continue;
            }
        }
    }

    void OptionsDisplay() throws Exception {
        int choice;
        Admin_P A = new Admin_P();
        do {
            System.out.println("Welcome To ADMIN Portal");
            System.out.println("1.Fetching Employee Data .");
            System.out.println("2.Credting Salary Statement .");
            System.out.println("3.Reply Mail For Leave or Any Doubt ");
            System.out.println("4.Get Details Of All Department Employess ");
            System.out.println("5.Exit");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    A.fetchingEmployee();
                }
                    break;
                case 2: {
                    A.credtingSalary();
                }
                    break;
                case 3: {
                    A.replyingMail();
                }
                    break;
                case 4: {
                    A.allEmployee();
                }
                    break;
                case 5: {
                    System.out.println("Thank you For Visiting !");
                }
                    break;
                default: {
                    System.out.println("Enter Valid Input !");
                }
                    break;
            }
        } while (choice != 5);

    }
}

class Office_Staff_Management {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int choice;
        Admin a = new Admin();
        Employee e = new Employee();
        do {
            System.out.println("-----------------------------------------");
            System.out.println("Welcome To Office Staff Portal !");
            System.out.println("Please Choose What to do you want to ?");
            System.out.println("1.Employee Portal");
            System.out.println("2.Admin Portal ");
            System.out.println("3.For Exit ");
            System.out.println("-----------------------------------------");
            choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    e.LoginORSignup();
                    e.OptionsDisplay();
                }
                    break;
                case 2: {
                    a.LoginORSignup();
                    a.OptionsDisplay();
                }
                    break;
                case 3: {
                    System.out.println("Thank You For Coming ");
                }
                    break;
                default: {
                    System.out.println("Please Enter Valide Input !");
                }
            }
        } while (choice != 3);
    }
}