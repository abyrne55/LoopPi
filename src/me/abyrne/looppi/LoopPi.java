//The MIT License (MIT)
//
//Copyright (c) 2015 Anthony Byrne
//
//Permission is hereby granted, free of charge, to any person obtaining a copy
//of this software and associated documentation files (the "Software"), to deal
//in the Software without restriction, including without limitation the rights
//to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//copies of the Software, and to permit persons to whom the Software is
//furnished to do so, subject to the following conditions:
//
//The above copyright notice and this permission notice shall be included in
//all copies or substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
//THE SOFTWARE.
package me.abyrne.looppi;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.sound.sampled.*;

/**
 *
 * @author Anthony Byrne
 */
public class LoopPi {

    private Looper[] loopers;
    private MinimContainer mc;
    
    //Pedal Keymappings
    private static final char pedal1     = '6';
    private static final char pedal1mute = 'u';
    private static final char pedal2     = 'j';
    private static final char pedal2mute = 'm';

    public LoopPi() {
        loopers = new Looper[2];
        mc = new MinimContainer();
        
        for (int i=0; i < loopers.length; i++){
            loopers[i]= new Looper(i, mc);
        }
    }

    public static void main(String[] args) {
        LoopPi lp = new LoopPi();
        char key = '?';
        boolean exit = false;
        
        //Knock UNIX terminals into raw mode, for keyboard input without hitting enter
        KeyboardControl.setRaw();
        
        //Play "ready" tone
        lp.mc.getMinim().loadFile("ding.wav").play();
        
        while (!exit){
            key = readKey();
            
            //Loop toggling via number keys
//            if (Character.isDigit(key)) {
//                if (Character.getNumericValue(key) < lp.loopers.length){
//                    System.out.print("--Toggling looper "+key);
//                    lp.loopers[Character.getNumericValue(key)].toggle();
//                }
//            }
            
            //Loop toggling via pedal keys
            switch (key) {
                case pedal1:
                    System.out.println("Pedal 1: Toggle");
                    lp.loopers[0].toggle();
                    break;
                case pedal1mute:
                    System.out.println("Pedal 1: Mute/Unmute");
                    lp.loopers[0].mute();
                    break;
                case pedal2:
                    System.out.println("Pedal 2: Toggle");
                    lp.loopers[1].toggle();
                    break;
                case pedal2mute:
                    System.out.println("Pedal 2: Mute/Unmute");
                    lp.loopers[1].mute();
                    break;
                case 0x3: //Handle Ctrl-C
                    System.out.println("KTHXBYE");
                    exit=true;
                    break;
            }
            
            
            //Mute all loopers
//            if (key == 'q') {
//                for (Looper looper : lp.loopers) {
//                    looper.endPlayback();
//                }
//            }
            
            
        }
        
        
        //Knock UNIX terminals back into regular ("cooked") keyboard mode, 
        KeyboardControl.setCooked();
        
        for (Looper looper : lp.loopers) {
            looper.close();
        }
        System.exit(0);
    }
    
    public static void delay(int seconds){
        try {
            sleep(seconds * 1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(LoopPi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static char readKey(){
        char key = '?';
        try {
            key = (char) System.in.read();
        } catch (IOException ex) {
            Logger.getLogger(LoopPi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return key;
    }
    
//    public static void test(){
//        looper0 = new Looper(0);
//        looper1 = new Looper(1);
//        
//        System.out.println("Starting recording on channel 0...");
//        looper0.startRecording();
//        System.out.println("Waiting 3 sec...");
//        delay(3);
//        looper0.endRecording();
//        
//        System.out.println("Starting recording on channel 1...");
//        looper1.startRecording();
//        System.out.println("Waiting 3 sec...");
//        delay(3);
//        looper1.endRecording();
//        
//        System.out.println("Beginning playback on channel 0 for 10 sec...");
//        looper0.startPlayback();
//        delay(2);
//        
//        System.out.println("Beginning playback channel 1 for 10 sec...");
//        looper1.startPlayback();
//        delay(8);
//        
//        System.out.println("End Channel 0 playback");
//        looper0.endPlayback();
//        looper0.close();
//        delay(2);
//        
//        System.out.println("End Channel 1 playback");
//        looper1.endPlayback();
//        looper1.close();
//
//        System.exit(0);
//    }
}
