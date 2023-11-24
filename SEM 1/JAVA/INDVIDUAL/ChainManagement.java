import java.util.*;
class ChainManagement
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        ChainManagement c=new ChainManagement();
        ChainManagement[] products=new ChainManagement[100];
        ChainManagement[] orders=new ChainManagement[100];
        int n=0;
        for(;;)
        {
            System.out.println("Warehouse Management System");
            System.out.println("1. Add Product");
            System.out.println("2. change stock of existing Product");
            System.out.println("3. Place Order");
            System.out.println("4. Display Products");
            System.out.println("5. Display Orders");
            System.out.println("6. Exit");
            System.out.println();
            System.out.println();
            System.out.println("Enter your choice");
            String choice = sc.nextLine();
            switch (choice)
            {
                case "1":
                {
                    System.out.println("Enter number of items to be added");
                    n = sc.nextInt();
                    for (int i = 0; i < n; i++)
                    {
                        products[i] = new ChainManagement();
                        orders[i]=new ChainManagement();
                        products[i].addProduct(products);
                    }
                    sc.nextLine();
                    break;
                }
                case "2":
                {
                    c.addStockOfPdt(products, n);
                    break;
                }
                case "3":
                {
                    c.placeOrder(orders,products,n);
                    break;
                }
                case "4":
                {
                    c.displayProduct(products,n);
                    break;
                }
                case "5":
                {
                    c.displayOrders(orders);
                    break;
                }
                case "6":
                {
                    System.exit(0);
                }
                default:
                {
                    System.out.println("Enter valid choice");
                }
            }
        }
    }


    Scanner sc=new Scanner(System.in);
    String name;
     int n=0;
    int totalItem;
    String date;


    void addProduct(ChainManagement[] products)
    {
        System.out.println("Enter name of product");
        name=sc.nextLine();
        System.out.println("Enter total number of same product");
        totalItem=sc.nextInt();
    }

    void addStockOfPdt(ChainManagement[] products,int m)
    {
        System.out.println("Enter name of that product to increase its size");
        String s1=sc.nextLine();
        boolean t=false;
        for(int i=0;i<m;i++)
        {
            if(products[i].name.compareToIgnoreCase(s1)==0)
            {
                System.out.println("Enter total items to be added");
                int n1= sc.nextInt();
                products[i].totalItem+=n1;
                t=true;
            }
        }
        if(!t)
        {
            System.out.println("Product not found");
        }
    }

    void placeOrder(ChainManagement[] orders,ChainManagement[] products,int m)
    {
        System.out.println("Enter name of that product to make order");
        String s1=sc.nextLine();
        boolean t=false;
        for(int i=0;i< m;i++)
        {
            if(products!=null)
            {
                if (products[i].name.compareToIgnoreCase(s1) == 0)
                {

                    orders[n].name = s1;
                    System.out.println("Enter number of items of that product");
                    int n1 = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter date by which it must be delivered");
                    orders[n].date = sc.nextLine();
                    orders[n].totalItem = n1;
                    products[i].totalItem -= n1;
                    n++;
                    t = true;
                }
            }
        }
        if(!t)
        {
            System.out.println("Product not found");
        }
    }

    void displayProduct(ChainManagement[] products,int m)
    {
        for(int i=0;i<m;i++)
        {
            System.out.print("Name:"+products[i].name+"   ");
            System.out.println("Quantity:"+products[i].totalItem);
            System.out.println();
            System.out.println();
        }
    }

    void displayOrders(ChainManagement[] orders)
    {
        if(n>0)
        {
            for(int i=0;i<n;i++)
            {
                System.out.print("Name:"+orders[i].name+"   ");
                System.out.print("Quantity:"+orders[i].totalItem+"   ");
                System.out.println("Delivery by:"+orders[i].date+"   ");
                System.out.println();
                System.out.println();
            }
        }
    }
}
