import java.util.Scanner;

class LL {
    class node {
        node next;
        int data;

        node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    node Orignal_first = null;
    node First_LL_head = null;
    node Second_LL_head = null;

    void insertAtLast(int data) {
        node n = new node(data);
        if (Orignal_first == null) {
            Orignal_first = n;
            n.next = Orignal_first;
        } else {
            node temp = Orignal_first;
            while (temp.next != Orignal_first) {
                temp = temp.next;
            }
            temp.next = n;
            n.next = Orignal_first;
        }
    }

    void Spliting() {
        node slow = Orignal_first;
        node fast = Orignal_first.next;
        while (fast != Orignal_first && fast.next != Orignal_first) {
            slow = slow.next;
            fast = fast.next.next;
        }
        First_LL_head = Orignal_first;
        Second_LL_head = slow.next;
        slow.next = First_LL_head;
        node temp = Second_LL_head;
        while (temp.next != Orignal_first) {
            temp = temp.next;
        }
        temp.next = Second_LL_head;
    }

    void display() {
        System.out.println("Orignal LinKlist");
        System.out.println();
        node temp = Orignal_first;
        if (temp != null) {
            do {
                System.out.print(temp.data + " ");
                temp = temp.next;
            } while (temp != Orignal_first);
        }
        System.out.println();
    }

    void printList() {
        System.out.println("--------------------------");
        System.out.println("First Half On LinkedList !");
        node temp1 = First_LL_head;
        if (temp1 != null) {
            do {
                System.out.print(temp1.data + " ");
                temp1 = temp1.next;
            } while (temp1 != First_LL_head);
        }
        System.out.println();
        System.out.println("--------------------------");
        System.out.println("Second Half On LinkedList !");
        node temp2 = Second_LL_head;
        if (temp2 != null) {
            do {
                System.out.print(temp2.data + " ");
                temp2 = temp2.next;
            } while (temp2 != Second_LL_head);
        }
    }
}

class Main {
    public static void main(String[] args) {
        LL l = new LL();
        System.out.println("Enter How Many Numbers You Want To Enter ?");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            System.out.println("Enter Data You Want to Enter !");
            int num = sc.nextInt();
            l.insertAtLast(num);
        }
        l.display();
        l.Spliting();
        l.printList();
        System.out.println();
    }
}