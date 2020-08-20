/*****************************************************************************

 * Programmer: Sophia Ciocca, University of Washington - github
 * 
 * Adjusted Programmer: Alex Pondok, 08/19/2020 - 22:00
 * 
 * Compilation:  javac GuitarHero.java

 *  Execution:    java  GuitarHero

 *  Dependencies: RingBuffer.java, GuitarString.java, StdAudio.java, StdDraw.java

 *

 *  Simulates a 37-string guitar using the keys on a standard computer keyboard.

 *

 ****************************************************************************/
package guitar;

// This program constructs a Guitar object that it allows the user to play.

public class GuitarHero {
    public static void main(String[] args) {
        //set keyboard
        String keyboard = "zaq2xsw3cde4vfr5bgt6nhy7mju8,ki9";
        //set number of strings we want to create
        int numberOfStrings = 37;
        //create an array of guitar strings
        GuitarString[] arrayOfStrings = new GuitarString[numberOfStrings];
        //for loop: initialize array of guitar strings
        for (int i = 0; i < numberOfStrings; i++) {
        //calculate frequency for each string in array
            double frequency = 440 * Math.pow(2, ((i - 24) / 12.0));
            //fill that array spot with a guitar string                               
            arrayOfStrings[i] = new GuitarString(frequency);
        }
        // this is an infinite loop--user must quit the application
        for (;;) {
            // check if the user has typed a key; if so, process it   
            if (StdDraw.hasNextKeyTyped()) {
                char key = Character.toLowerCase(StdDraw.nextKeyTyped());
                // pluck the corresponding string
                int indexOfKeyboard = keyboard.indexOf(key);
                if (indexOfKeyboard == -1) {
                    continue;
                }
                arrayOfStrings[indexOfKeyboard].pluck();
            }
            //define sample
            double sample = 0;
            //calculate superposition of samples
            for (int j = 0; j < arrayOfStrings.length; j++) {
                sample += arrayOfStrings[j].sample();
            }
            // send the result to standard audio
            StdAudio.play(sample);
            // advance the simulation of each guitar string by one step
            for (int j = 0; j < arrayOfStrings.length; j++) {
                arrayOfStrings[j].tic();
            }
        }
    }
}