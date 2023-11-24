import java.util.ArrayList;
import java.util.PriorityQueue;

class collections {
    public static void main(String[] args) {
        
        System.out.println("Array List Programs ! ");
        ArrayList <Integer> ar=new ArrayList<>();
        ar.add(30);
        ar.add(20);
        ar.add(10);
        ar.add(40);
        System.out.println("Array List Is : "+ar);
            System.out.println();
        System.out.println(" Pioroty Queue Programs  !");
        PriorityQueue<Integer> pq1=new PriorityQueue<>(10);
        pq1.offer(10);
        pq1.add(23);
        pq1.offer(1);
        pq1.add(21);
        pq1.add(7);

        System.out.println("PQ :"+pq1);
        System.out.println("Peek : "+pq1.peek());
        System.out.println("Element :"+pq1.element());

        System.out.println(pq1.poll());
                System.out.println("PQ :"+pq1);
                System.out.println(pq1.poll());
                System.out.println("PQ :"+pq1);        System.out.println(pq1.poll());
                System.out.println("PQ :"+pq1);        System.out.println(pq1.poll());
                System.out.println("PQ :"+pq1);

    }
}