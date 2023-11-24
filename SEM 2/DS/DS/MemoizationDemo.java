import java.util.Scanner;

class Node {
    int key;
    long value;
    Node prev;
    Node next;

    public Node(int key, long value) {
        this.key = key;
        this.value = value;
    }
}

class LRUCache {
    private int capacity;
    private Node[] cache = new Node[1000];
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public long get(int key) {
        if (cache[key] == null) {
            return -1;
        }

        Node node = cache[key];
        removeNode(node, key);
        addToFront(node);

        return node.value;
    }

    public void put(int key, long value) {
        if (cache[key] != null) {
            // Update the value and move the node to the front
            Node node = cache[key];
            node.value = value;
            removeNode(node, key);
            addToFront(node);
        } else {
            if (capacity == 0) {
                // Cache is full, remove the least recently used node
                Node removedNode = tail.prev;
                removeNode(removedNode, key);
                cache[removedNode.key] = null;
            } else {
                capacity--;
            }

            // Create a new node and add it to the front
            Node newNode = new Node(key, value);
            addToFront(newNode);
            cache[key] = newNode;
        }
    }

    private void removeNode(Node node, int key) {
        cache[key] = null;
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToFront(Node node) {
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    public void printCache() {
        Node current = head.next;
        System.out.print(" [");
        while (current != tail) {
            System.out.print("[" + current.key + ", " + current.value + "] ");
            current = current.next;
        }
        System.out.print("]\n");
    }

}

public class MemoizationDemo {
    public static LRUCache cache = new LRUCache(3);
    public static long modulo = 1000000007;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        long startTime, endTime, timeTaken;

        System.out.print("\nEnter the number of term for Fibonacci Series: ");
        int n = sc.nextInt();
        sc.nextLine();
        System.out.print("\n\nFibonacci Series By Recursion Method: \n[ ");
        startTime = System.currentTimeMillis();
        for (int i = 1; i <= n; i++) {
            long febo = fibonacci(i);
            System.out.print(febo + "  ");
        }
        endTime = System.currentTimeMillis();
        timeTaken = endTime - startTime;
        System.out.println("]\n\nTime Taken by Recursion Method: " + timeTaken + " ms\n");

        System.out.print("Fibonacci Series By LRUCache Method: \n[ ");

        startTime = System.currentTimeMillis();
        for (int i = 1; i <= n; i++) {
            long febo = fibonacciMemoized(i);
            System.out.print(febo + "  ");
        }
        endTime = System.currentTimeMillis();
        timeTaken = endTime - startTime;
        System.out.println("]\n\nTime Taken by LRUCache Method: " + timeTaken + " ms\n");

        sc.close();
    }

    public static long fibonacciMemoized(int n) {
        Long cachedValue = cache.get(n);
        if (cachedValue != -1) {
            return cachedValue;
        }
        long result;
        if (n <= 1) {
            result = n;
        } else {
            long fibNMinus1 = fibonacciMemoized(n - 1);
            long fibNMinus2 = fibonacciMemoized(n - 2);
            result = (fibNMinus1 + fibNMinus2) % modulo;
        }
        cache.put(n, result);
        return result;
    }

    public static long fibonacci(int n) {
        long result;
        if (n <= 1) {
            result = n;
        } else {
            long fibNMinus1 = fibonacci(n - 1);
            long fibNMinus2 = fibonacci(n - 2);
            result = (fibNMinus1 + fibNMinus2) % modulo;
        }
        return result;
    }

}