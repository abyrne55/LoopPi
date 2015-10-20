# LoopPi

Simple console-mode app designed to be the software backend for loop pedals running on a Raspberry Pi 2. Requires the Minim Audio library (http://code.compartmental.net/tools/minim/) and a UNIX style terminal (read: this will work natively on Linux and Mac OSX, but probably requires cygwin if you wish to run this on Windows) 

## Usage & Controls
Upon startup, you'll hear a 'ding' sound that indicates that the app is ready to accept keystrokes. The app will continually listen for raw keystrokes from any connected keyboard. My "pedals" were created by ripping the control board out of an old USB keyboard and wiring it up to 4 [industrial momentary footswitches](http://amzn.com/B00GWFTCYW). See [this Instructable](http://www.instructables.com/id/DIY-USB-pedal-board-for-live-looping/) for more details. 

By default, the keys are mapped as follows:
* Channel 1
  * '6' - "Begin Recording" or "End Recording and Start Playback" 
  * 'u' - "Mute Channel" or "Unmute Channel"
* Channel 2
  * 'j' - "Begin Recording" or "End Recording and Start Playback" 
  * 'm' - "Mute Channel" or "Unmute Channel"

These keymappings may be changed in LoopPi.java under "Pedal Keymappings" near the beginning of the class definition. I chose these keys because the contacts on the keyboard chip to generate them were spaced out fairly well, making soldering a bit easier. 

## Channels
The two channels are functionally independent of eachother, except for the fact that I was forced to make them share a Minim LineIn and LineOut due to some quirks regarding the Raspberry Pi's sound driver (hence the MinimContainer object). You may add more channels by increasing the size of the `loopers` array (in LoopPi.java) and adding the keystroke cases to the `switch` statement in LoopPi.java's `main` needed to control the additional `Looper` objects.

### P.S.
This was created (admittedly hastily) for a school project and probably won't see too much further development. Feel free to fork and develop this documentation further as you wish. 
