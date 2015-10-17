/*
 * LoopPi: Looper.java
 * Does most of the work
 *
 * See LoopPi.java for MIT License
 */
package me.abyrne.looppi;

import ddf.minim.*;
import ddf.minim.ugens.FilePlayer;

public class Looper {

    private Minim minim;
    // for recording
    private AudioInput in;
    private AudioRecorder recorder;

    private enum Status {

        EMPTY, RECORDING, PLAYING, MUTED
        //EMPTY = no sound has been recorded
        //MUTED = sound has been recorded, but is not playing
    }

    private Status status;

    // for playing back
    private AudioOutput out;
    private FilePlayer player;
    private int number;

    public Looper(int number, MinimContainer mc) {
        minim = mc.getMinim();

        // get a stereo line-in: sample buffer length of 2048
        // default sample rate is 44100, default bit depth is 16
        in = mc.getIn();

        //Set number (used to pick a filename for loop file
        this.number = number;

        // get an output we can playback the recording on
        out = mc.getOut();

        status = Status.EMPTY;
    }

    private void startRecording() {
        recorder = null;
        recorder = minim.createRecorder(in, "myrecording" + number + ".wav", false);
        recorder.beginRecord();
        status = Status.RECORDING;
    }

    private void endRecording() {
        recorder.endRecord();
        if (player != null) {
            player.unpatch(out);
            player.close();
        }
        player = new FilePlayer(recorder.save());
        player.patch(out);
        status = Status.MUTED;
    }

    private void startPlayback() {
        if (player != null) {
            player.loop();
        }
        status = Status.PLAYING;
    }

    private void endPlayback() {
        if (player != null) {
            player.pause();
            player.rewind();
        }
        status = Status.MUTED;
    }

    public void toggle() {
        switch (status) {
            case EMPTY:
                startRecording();
                break;
            case RECORDING:
                endRecording();
                startPlayback();
                break;
            case MUTED:
            case PLAYING:
                endPlayback();
                player.close();
                startRecording();
                break;
        }
    }

    public void mute() {
        if (status == Status.PLAYING) {
            endPlayback();
        } else if (status == Status.MUTED) {
            startPlayback();
        }
    }
    
    public void playStartupSound(String filename) {
        
    }

    public void close() {
        player.close();
        in.close();
        out.close();
    }
}
