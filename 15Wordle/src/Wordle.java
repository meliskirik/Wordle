
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class Wordle {

    //Main program start
    public static void main(String[] args) throws IOException {

        //We start file operations
        String path = "dict.txt";
        int lines = 2317;
        String[] strings = new String[lines];

        BufferedReader br = new BufferedReader(new FileReader(path));
        int index = 0;
        String line;
        while ((line = br.readLine()) != null && index < lines) {
            strings[index++] = line;
        }

        //A random keyword is selected
        Random rand = new Random();
        String key = strings[rand.nextInt(2317)];
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please be careful to write in capital letters.");
        
        for (int guessNum = 1; guessNum <= 10 ; guessNum++) {
        	
        	System.out.print("Enter your guess " + guessNum + ": ");
           

            //We convert the entered words to uppercase letters
            //But char "i" still "İ", so when user enter "bride", return "BRİDE", BRİDE does not exist in dict
            String guess = scanner.nextLine().toUpperCase();

            /* I tried such a method, but this time things got too complicated and it didn't work.
                public static String replaceTurkishI(String guess) {
            converted "i" with "ı"
                    return guess.replaceAll("i", "ı").replaceAll("I", "I");
                } */

            int guessLength = guess.length();
                        
            //Guess length should be 5
            if (guessLength != 5) {
                System.out.println("The length of the word must be five!");
                continue;
            }

            //Checks whether guess is in the dict or not
            if (!Arrays.asList(strings).contains(guess)) {
                System.out.println("The word does not exist in the dictionary!");
                continue;
            }

            //If guess is true, the process is terminated
            if (guess.equals(key)) {
                System.out.println("Congratulations! You guessed right in " + guessNum + getSuffix(guessNum) + " shot!");
                return; //End the program
            }

            //If guess is wrong, hints are given
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < 5; j++) {
                char keyChar = key.charAt(j);
                char guessChar = guess.charAt(j);

                //Each letter of guess is compared with the keyword
                if (keyChar == guessChar) {
                    builder.append(j + 1).append(". letter exists and located in right position.\n");
                } else if (key.indexOf(guessChar) != -1) {
                    builder.append(j + 1).append(". letter exists but located in wrong position.\n");
                } else {
                    builder.append(j + 1).append(". letter does not exist.\n");
                }
            }
            //Print the results of the guess
            System.out.println(builder.toString().trim());
        }

        //If guess fails, the keyword is shown
        System.out.print("You failed! The key word is " + key + ".");
        
    }
    //Method to get the suffix for ordinal numbers
    public static String getSuffix(int number){
        return switch (number){
            case 1 -> "st";
            case 2 -> "nd";
            case 3 -> "rd";
            default -> "th";
        };
    }
}

