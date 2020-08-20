/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitar;

// This class is used for debugging the Guitar37 class.  It is not an example
// to be emulated.  When a string is plucked, it is set to the integer part of
// the string's frequency plus 0.25.  It goes down by 10 each time tic is
// called until it becomes less than or equal to 10 when it is set to 0.

import java.util.*;

public class GuitarString {
    private final double  DECAY_FACTOR = 0.996;	
    private Queue<Double> ringbuffer;
    private int sampleNum;
    
    static Set<Integer> nums = new TreeSet<>();  // observed frequency values
    double value;
    double freq;

    public GuitarString(double frequency) {
        sampleNum = Math.round((float)(StdAudio.SAMPLE_RATE/frequency));
            if( frequency <= 0 || sampleNum < 2 ){
		throw new IllegalArgumentException();
            }
	ringbuffer = new LinkedList<>();		
            for( int i = 0; i < sampleNum; i++){
                ringbuffer.add(0.0);
            }
    }
    
    public GuitarString(double[] init) {
        sampleNum = init.length;
            if(sampleNum < 2) {
                throw new IllegalArgumentException();
            }
        ringbuffer = new LinkedList<>();
            for(Double values: init){
                ringbuffer.add(values);			
            }	
        System.out.println(ringbuffer);
    }
   
    public void pluck() {
        for(int i = 0; i < sampleNum; i++){
            ringbuffer.add(Math.random() - 0.5);
            ringbuffer.remove();
        }
    }

    public void tic() {
        double first = ringbuffer.remove();
        double second = ringbuffer.peek();	
        ringbuffer.add(DECAY_FACTOR * 0.5 * (first + second));
    }

    public double sample() {
        return ringbuffer.peek();
    }
}