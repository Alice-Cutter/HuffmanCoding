import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;

/*
This class takes in a file and calculates the frequencies for each of the characters. It will have a method that returns a hashmap.  
*/

public class FrequencyCalculator {
    // Instance variables
    private String fileName;
    private HashMap<Character, Integer> frequencies;
    private String fullString;
    //private Boolean needFile;

    // Constructor for Frequency Calculator
    public FrequencyCalculator(String fileName, Boolean needFile) {
        this.fileName = fileName;
        frequencies = getFreqs(needFile);
		
    }

	
	/* Returns a HashMap with characters 
	*/
    public HashMap<Character, Integer> getFreqs(boolean needFile) {
		
        HashMap<Character, Integer> freqCounter = new HashMap<Character, Integer>();
	    String fullString = ""; 
        if (needFile == true) {
            fullString = loadWords();
            this.fullString = fullString;
        } else {
            fullString = fileName;
            this.fullString = fullString;
        }

            for (int j = 0; j < fullString.length(); j++) {
                char c = fullString.charAt(j); // this is looking at the specific character in the word for each line of the
                                         // file (as returned in loadWords)

                if (freqCounter.containsKey(c)) {
                    freqCounter.put(c, freqCounter.get(c) + 1); // This is updating the frequency if the map already
                                                                // contains the key
                } else {
                    // If the character is not in freqCounter... add it to the map and initialize
                    // value as one
                    freqCounter.put(c, 1);
                }
            }
    

        return freqCounter;
    }


	
    public String loadWords() { 
        Scanner scanner = null;
		String fullString = "";
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.err.println(e);
            System.exit(1);

        }
        while (scanner.hasNextLine()) {
            String tempWord = scanner.nextLine();
            fullString = fullString + tempWord; //Concatenates the new word onto the end of the existing string as it iterates through the file. 
        }
        return fullString; 

    }

    public String getFullString() {
        return fullString;
    }

}