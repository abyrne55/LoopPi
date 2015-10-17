/*
 * LoopPi: KeyboardControl.java
 * "Cooks" and "Uncooks" Unix Keyboard (to allow for single char input w/o hitting enter
 *
 * See LoopPi.java for MIT License
 */
package me.abyrne.looppi;

import java.util.logging.Level;
import java.util.logging.Logger;

public class KeyboardControl {
    
    public static void setRaw(){
        String[] cmd = {"/bin/sh", "-c", "stty raw </dev/tty"};
        try {
            Runtime.getRuntime().exec(cmd).waitFor();
        } catch (Exception ex) {
            Logger.getLogger(LoopPi.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println();
    }
    
    public static void setCooked(){
        String[] cmd = {"/bin/sh", "-c", "stty cooked </dev/tty"};
        try {
            Runtime.getRuntime().exec(cmd).waitFor();
        } catch (Exception ex) {
            Logger.getLogger(LoopPi.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println();
    }
}
