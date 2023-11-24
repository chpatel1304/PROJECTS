import java.util.HashMap;
import java.util.Scanner;

public class pro1 {
    public static void main(String[] args) {
        HashMap <String,String> hm=new HashMap<>();
        Scanner sc=new Scanner(System.in);
        String name;
        String phone;
        while(true){
            System.out.println("Enter Your Name :");
            name=sc.nextLine();
            System.out.println("Enter Your Phone :");
            phone=sc.nextLine();
            hm.put(name, phone);
            System.out.println("Still Do you want to add ? Yes Or no ");
            String choice=sc.nextLine();
            if(choice.equalsIgnoreCase("No")){
                break;
            }

        }
        System.out.println("Enter Name To search : ");
        String name1=sc.nextLine();
        String SerachNumber=hm.get(name1);
        if(SerachNumber!=null){
            System.out.println("Phone Number is : "+SerachNumber);
        }
        else{
            System.out.println("Not Found !");
        }
    }
}
