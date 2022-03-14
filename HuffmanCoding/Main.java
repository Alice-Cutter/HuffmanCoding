import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;

class Main {
    public static void main(String[] args) {
		String scanText = args[0]; //Zeroeth argument: if it will read from file or not. 
        String text; // Initializing input (text) variable 
        boolean needFile;
		if (scanText.equals("file")){// if the argument is equal to file...
			text  = args[1]; //The first argument is the input file name
            needFile = true;
			
		}	
		else {
			text = args[0]; //if the input is not a file, the input is the string at the zeroeth argument 
            needFile = false;
        }

		
		FrequencyCalculator freqCal = new FrequencyCalculator(text, needFile);
        TreeMaker tree = new TreeMaker(freqCal.getFreqs(needFile));
        tree.printCodes();
        
        String encoded = tree.encode(freqCal.getFullString()); //just testing encoding method
        System.out.println("Encoded message: " + encoded);
        String decoded = tree.decode(encoded);
        System.out.println("Decoded message: " + decoded); 
   }
	
}