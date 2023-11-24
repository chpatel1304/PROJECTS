import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

class FileSorter {
    public static void main(String[] args) throws IOException {
        // Create a new file and write numbers into it
        String inputFilePath = "input.txt";

        FileWriter writer = new FileWriter(inputFilePath);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        // Add some numbers to the file
        bufferedWriter.write("10\n5\n8\n1\n3\n");
        bufferedWriter.close();

        // Sort the content of the file and write into another file
        String outputFilePath = "output.txt";

		FileReader reader = new FileReader(inputFilePath);
        BufferedReader bufferedReader = new BufferedReader(reader);

        ArrayList<Integer> numbers = new ArrayList<>();

        // Read numbers from the file and add them to the ArrayList
        String line = bufferedReader.readLine();
        while (line != null) {
            numbers.add(Integer.parseInt(line));
			line = bufferedReader.readLine();
        }
        bufferedReader.close();

        // Sort the numbers in ascending order
        Collections.sort(numbers);

        // Write the sorted numbers into the output file
        FileWriter writer2 = new FileWriter(outputFilePath);
        BufferedWriter bufferedWriter2 = new BufferedWriter(writer2);
        
		for (int number : numbers) {
            bufferedWriter2.write(String.valueOf(number));
            bufferedWriter2.newLine();
        }
        bufferedWriter2.close();

        System.out.println("File sorting successful.");
    }
}