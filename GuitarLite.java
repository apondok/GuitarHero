/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitar;
// This is a sample class that implements the Guitar interface.  It is not well
// documented.

public class GuitarLite implements Guitar {
    // separate list
    private GuitarString stringA;
    private GuitarString stringB;
    private GuitarString stringC;

    // create two guitar strings, for concert A and C
    public GuitarLite() {
        double concertA = 440.0;
        double concertB = concertA * Math.pow(2, 3.0/12.0);
        double concertC = concertA * Math.pow(2, 1.0/12.0);  
        stringA = new GuitarString(concertA);
        stringB = new GuitarString(concertB);
        stringC = new GuitarString(concertC);
    }

    public void playNote(int pitch) {
        if (pitch == 0) {
            stringA.pluck();
        } else if (pitch == 1) {
            stringB.pluck();
        } else if (pitch == 3) {
            stringC.pluck();
        }
    }

    public boolean hasString(char string) {
        return (string == 'a' || string =='b' || string == 'c');
    }
    
    public void pluck(char string) {
        if (string == 'a') {
            stringA.pluck();
        } else if (string == 'b') {
            stringB.pluck();
        } else if (string == 'c') {
            stringC.pluck();
        }
    }

    public double sample() {
        return stringA.sample() + stringB.sample() + stringC.sample();
    }

    public void tic() {
        stringA.tic();
        stringB.tic();
        stringC.tic();
    }

    public int time() {
        return -1;  // not implemented
    }
}