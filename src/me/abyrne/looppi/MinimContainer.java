/*
 * LoopPi: MinimContainer.java
 * A container for the Minim object and a Line In and Line Out to be shared.
 * Created because the RPi only supports one LineIn object at a time
 * 
 * See LoopPi.java for MIT License
 */
package me.abyrne.looppi;

import ddf.minim.AudioInput;
import ddf.minim.AudioOutput;
import ddf.minim.Minim;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anthony
 */
public class MinimContainer {
    private Minim minim;
    private AudioInput in;
    private AudioOutput out;
    
    public MinimContainer() {
        //Create a Minim object
        minim = new Minim(this);
        
        // get a stereo line-in: sample buffer length of 2048
        // default sample rate is 44100, default bit depth is 16
        in = minim.getLineIn(Minim.MONO, 1024);
        
        // get an output we can playback the recording on
        out = minim.getLineOut( Minim.MONO );
    }

    public Minim getMinim() {
        return minim;
    }

    public AudioInput getIn() {
        return in;
    }

    public AudioOutput getOut() {
        return out;
    }
    
    
    //The next two methods are required by Minim
    //sketchPath simply returns an absolute path.
    public String sketchPath(String fileName){
        return System.getProperty("user.dir")+"/"+fileName;
    }
    //createInput returns an input stream for a given filename
    public InputStream createInput(String fileName){
        File initialFile = new File(fileName);
        InputStream targetStream;
        try {
            targetStream = new FileInputStream(initialFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MinimContainer.class.getName()).log(Level.SEVERE, null, ex);
            targetStream = null;
        }
        
        return targetStream;
    }
    //End Minim-required methods
    
}
