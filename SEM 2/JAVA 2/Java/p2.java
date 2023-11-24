import java.util.ArrayDeque;
import java.util.Collection;

class ArrayDequeExample {
public static void main(String[] args) {
 // ArrayDeque creation with String type
 ArrayDeque<String> stringArrayDeque = new ArrayDeque<>();

 // add(Element e) for String type
 stringArrayDeque.add("Apple");
 stringArrayDeque.add("Banana");
 stringArrayDeque.add("Orange");
 System.out.println("1st : "+stringArrayDeque);

 // addAll(Collection<? extends E> c) for String type
 Collection<String> otherFruits = new ArrayDeque<>();
 otherFruits.add("Grapes");
 otherFruits.add("Watermelon");
 stringArrayDeque.addAll(otherFruits);
 System.out.println("2nd : "+otherFruits);

 // addFirst(Element e) for String type
 stringArrayDeque.addFirst("Pineapple");

 // addLast(Element e) for String type
 stringArrayDeque.addLast("Mango");

 System.out.println("3rd : "+stringArrayDeque);

 // clear() for String type
 stringArrayDeque.clear();

 // isEmpty() for String type
 boolean isStringArrayDequeEmpty = stringArrayDeque.isEmpty();
 System.out.println("Is String ArrayDeque empty? " + isStringArrayDequeEmpty);

 // offerFirst(Element e) for String type
 stringArrayDeque.offerFirst("Cherry");

 // offerLast(Element e) for String type
 stringArrayDeque.offerLast("Pear");

 // peek() for String type
 String firstStringElement = stringArrayDeque.peek();
 System.out.println("First String element: " + firstStringElement);
 // getFirst() for String type
 String firstStringElementAgain = stringArrayDeque.getFirst();
 System.out.println("First String element again: " + firstStringElementAgain);

 // getLast() for String type
 String lastStringElement = stringArrayDeque.getLast();
 System.out.println("Last String element: " + lastStringElement);

 // remove() for String type
 stringArrayDeque.remove(); // Removes the first String element
 // removeFirst() for String type
 stringArrayDeque.removeFirst();

 // removeLast() for String type
 stringArrayDeque.removeLast();

// size() for String type
 int stringArrayDequeSize = stringArrayDeque.size();
 System.out.println("String ArrayDeque size: " + stringArrayDequeSize);
}
}