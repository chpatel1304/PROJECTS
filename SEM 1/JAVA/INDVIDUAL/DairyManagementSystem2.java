import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;


public class DairyManagementSystem2
{
    Scanner sc=new Scanner(System.in);
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        DairyManagementSystem2 m=new DairyManagementSystem2();

        if(m.login()==3)
        {
            System.out.println("Locked");
        }
        else
        {
            System.out.println("Enter number of Dairy Products to add in stock");
            int n= sc.nextInt();
            DairyManagementSystem2[] dairyproducts=new DairyManagementSystem2[n];
            for(int i=0;i<n;i++)
            {
                dairyproducts[i] = new DairyManagementSystem2();
                dairyproducts[i].enterStock();
            }
            DairyManagementSystem2 m1=new DairyManagementSystem2(dairyproducts,1);
            l1:
            for(;;)
            {
                System.out.println("Enter 1 to Enter Stock");
                System.out.println("Enter 2 to view stock");
                System.out.println("Enter 3 to generate Bill");
                System.out.println("Enter 4 to shut down system");
                int n1 = sc.nextInt();
                switch (n1)
                {
                    case 1:
                    {
                        System.out.println("Enter how many dairyproducts you want to enter");
                        int a = sc.nextInt();
                        DairyManagementSystem2[] dairyproducts1=new DairyManagementSystem2[a];
                        for (int i = 0; i < a ; i++)
                        {
                            dairyproducts1[i] = new DairyManagementSystem2();
                            dairyproducts1[i].enterStock();
                        }
                        int c=a+n;
                        DairyManagementSystem2[] dairyproducts2=new DairyManagementSystem2[c];
                        System.arraycopy(dairyproducts,0,dairyproducts2,0,n);
                        System.arraycopy(dairyproducts1,0,dairyproducts2,n,a);
                        DairyManagementSystem2 m2 = new DairyManagementSystem2(dairyproducts2, 1);
                        break;
                    }
                    case 2:
                    {
                        l2:
                        for(;;)
                        {
                            System.out.println("Enter 1 to search dairyproducts by company name");
                            System.out.println("Enter 2 to sort by expiry date");
                            System.out.println("Enter 3 to search by name");
                            System.out.println("Enter 4 to go back");
                            int n2 = sc.nextInt();
                            switch (n2)
                            {
                                case 1:
                                {
                                    m.searchbyCompany(dairyproducts);
                                    break;
                                }
                                case 2:
                                {
                                    m.sortExpDate(dairyproducts);
                                    break;
                                }
                                case 3:
                                {
                                    m.searchbyName(dairyproducts);
                                    break;
                                }
                                case 4:
                                {
                                    break l2;
                                }
                                default:
                                {
                                    System.out.println("Enter valid choice");
                                    break;
                                }
                            }
                        }
                        break;

                    }
                    case 3:
                   {
                        m.Bill(dairyproducts);
                        break;
                    }
                    case 4:
                    {
                        break l1;
                    }
                    default:
                    {
                        System.out.println("Enter correct choice");
                        break;
                    }
                }
            }
        }
    }

    long uid;
    String ProductName;
    String Company;
    int expMonth;
    int expYear;
    double price;
    int Psw=123;






    DairyManagementSystem2()
    {

    }

    int login()
    {

        int m=0;
        for(int i=1;i<=3;i++)
        {
            System.out.println("Enter password");
            int psw=sc.nextInt();
            if(psw==Psw)
            {
                return m;
            }
            else
            {
                System.out.println("Reenter password");
                m++;
            }
        }
        return m;
    }

    DairyManagementSystem2(DairyManagementSystem2[] dairyproducts,int n)
    {
        Date date=Calendar.getInstance().getTime();
        DateFormat dateyear=new SimpleDateFormat("y");
        DateFormat datemonth=new SimpleDateFormat("M");
        String dyear= dateyear.format(date);
        String dmonth= datemonth.format(date);

        System.out.println("dairyproducts expiring next Month :");

        int count=0;
        for (int i=0;i< dairyproducts.length;i++)
        {
            if(dairyproducts[i].uid==0)
            {
                continue;
            }
            StringBuffer sb = new StringBuffer();
            StringBuffer sb1 = new StringBuffer();
            sb.append(dairyproducts[i].expYear);
            sb1.append(dairyproducts[i].expMonth);
            String s = sb.toString();
            String s1 = sb1.toString();

            if (dyear.equalsIgnoreCase(s))
            {
                if (s1.compareTo(dmonth) == 1)
                {
                    dairyproducts[i].printStock();
                    count++;
                }
            }
            //count++;
        }
        if(count==0)
        {
            System.out.println("0");
        }
    }



    void enterStock()
    {


        System.out.println("Enter uid of DairyProduct");
        uid = sc.nextLong();
        sc.nextLine();
        System.out.println("Enter name of DairyProduct");
        ProductName=sc.nextLine();
        System.out.println("Enter name of Company");
        Company = sc.nextLine();
        System.out.println("Enter expiry Month");
        expMonth = sc.nextInt();
        System.out.println("Enter expiry year");
        expYear=sc.nextInt();
        System.out.println("Enter price of"+ProductName+" : ");
        price = sc.nextDouble();

    }



    void Bill(DairyManagementSystem2[] m)
    {
        System.out.println("Enter Number Of dairyproducts : ");
        int b = sc.nextInt();
        long[] id = new long[b];
        double nettotal=0;
        int count=0;
        for(int i = 0;i<b;i++)
        {
            System.out.println("Enter UID Of Dairy Product "+(i+1));
            id[i] = sc.nextLong();
        }
        System.out.println("___________________________________________________________");
        System.out.println("|                   Dairy Parlour                         |");
        System.out.println("|_________________________________________________________|");
        System.out.println("|Sr.no|   Name   |   exp    |  Comp    |  UID   |   mrp   |");
        System.out.println("|-----|----------|----------|----------|--------|---------|");

        for(int i = 0;i<m.length;i++)
        {

            for (int j = 0; j < id.length; j++)
            {
                if (id[j] == m[i].uid)
                {
                    int digit=m[i].expMonth/10;
                    count++;
                    nettotal+=m[i].price;
                    System.out.print("|  "+count+"  |");
                    System.out.print(" "+m[i].ProductName);
                    for(int k=1;k<10-m[i].ProductName.length();k++)
                    {
                        System.out.print(" ");
                    }
                    if(digit<1)
                    {
                        System.out.print("|  "+m[i].expMonth);
                    }
                    else
                    {
                        System.out.print("| "+m[i].expMonth);
                    }
                    System.out.print("/"+m[i].expYear+ "  | "+m[i].Company);
                    for(int k=1;k<10-m[i].Company.length();k++)
                    {
                        System.out.print(" ");
                    }
                    System.out.print("|  "+m[i].uid +"   |  "+m[i].price+"  |");
                    System.out.println();
                }
            }
        }
        System.out.println("|-----------------------------------------------|---------|");
        System.out.println("|                                     Net Total |  "+nettotal+"  |");
        System.out.println("|---------------------------------------------------------|");
        System.out.println("|_________________________________________________________|");
        for(int i = 0;i<id.length;i++)
        {
            for(int j = 0;j<m.length;j++)
            {
                if (m[j].uid == id[i])
                {
                    m[j].expYear=0;
                    m[j].expMonth=0;
                    m[j].ProductName=null;
                    m[j].Company=null;
                    m[j].uid=0;
                    m[j].price=0;
                }
            }
        }

    }


    void searchbyName(DairyManagementSystem2[] dairyproducts)
    {
        int m=0,a=0;
        System.out.println("Enter name of dairyproducts");
        String t=sc.nextLine();
        for (DairyManagementSystem2 medicalStore : dairyproducts) {
            if (medicalStore.ProductName.equalsIgnoreCase(t)) {
                a++;
            }
        }
        System.out.println("Number of dairyproducts found="+a);
        for (DairyManagementSystem2 medicine : dairyproducts) {
            if (medicine.ProductName.equalsIgnoreCase(t)) {
                m++;
                System.out.println("Dairy Product " + m + ":");
                System.out.println();
                medicine.printStock();
                System.out.println();
                System.out.println();
            }
        }
        if(m==0)
        {
            System.out.println("dairyproducts not found");
        }

    }


    void searchbyCompany(DairyManagementSystem2[] dairyproducts)
    {
        int m=0,a=0;
      //  sc.nextLine();
        System.out.println("Enter name of Company");
        //sc.nextLine();
        String t=sc.nextLine();
        //sc.nextLine();
        for (DairyManagementSystem2 medicalStore : dairyproducts) {
            if (medicalStore.Company.equalsIgnoreCase(t)) {
                a++;
            }
        }
        System.out.println("Number of dairyproducts found="+a);
        for (DairyManagementSystem2 medicine : dairyproducts) {
            if (medicine.Company.equalsIgnoreCase(t)) {
                m++;
                System.out.println("Dairy Product " + m + ":");
                System.out.println();
                medicine.printStock();
                System.out.println();
                System.out.println();
            }
        }
        if(m==0)
        {
            System.out.println("dairyproducts not found");
        }

    }


    void sortExpDate(DairyManagementSystem2[] dairyproducts)
    {
        DairyManagementSystem2 temp=new DairyManagementSystem2();
        DairyManagementSystem2 temp1=new DairyManagementSystem2();

        for(int i=0;i< dairyproducts.length;i++)
        {
            for(int j=0;j< dairyproducts.length;j++)
            {
                if(dairyproducts[i].expYear<dairyproducts[j].expYear)
                {
                    temp=dairyproducts[i];
                    dairyproducts[i]=dairyproducts[j];
                    dairyproducts[j]=temp;
                }
            }
        }
        for(int i=0;i< dairyproducts.length;i++)
        {
            for(int j=0;j< dairyproducts.length;j++)
            {
                if(dairyproducts[i].expYear==dairyproducts[j].expYear)
                {
                    if(dairyproducts[i].expMonth<dairyproducts[j].expMonth)
                    {
                        temp1 = dairyproducts[i];
                        dairyproducts[i] = dairyproducts[j];
                        dairyproducts[j] = temp1;
                    }
                }
            }
        }
        for (DairyManagementSystem2 medicine : dairyproducts) {
            medicine.printStock();
        }
    }

    void printStock()
    {
        System.out.println("Dairy Product Name : "+ProductName);
        System.out.println("Number Of  "+ProductName+" : "+uid);
        System.out.println("Company: "+Company);
        System.out.println("Product  expiry date: "+expMonth+"/"+expYear);
        System.out.println("Price: "+price);
        System.out.println();
        System.out.println();
        System.out.println();
        
    }
}
