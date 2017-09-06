import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.LineUnavailableException;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.Image;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JCheckBox;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import javax.swing.border.TitledBorder;

import java.net.URL;

public class Tone {
//Tone generated from code sourced from; https://community.oracle.com/thread/1273219?start=0&tstart=0
	public static void generateTone(int hz, int msecs, int volume) throws LineUnavailableException {

		float frequency = 44100;
		byte[] buf;
		AudioFormat af;

		buf = new byte[2];
		af = new AudioFormat(frequency, 8, 2, true, false);

		SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
		sdl = AudioSystem.getSourceDataLine(af);
		sdl.open(af);
		sdl.start();
		for (int i = 0; i < msecs * frequency / 1000; i++) {
			double angle = i / (frequency / hz) * 2.0 * Math.PI;
			buf[0] = (byte) (Math.sin(angle) * volume);

			double angle2 = (i) / (frequency / hz) * 2.0 * Math.PI;
			buf[1] = (byte) (Math.sin(2 * angle2) * volume * 0.6);
			sdl.write(buf, 0, 2);

		}
		sdl.drain();
		sdl.stop();
		sdl.close();
	}
	static int oneunit = 50;
	public static void dot() throws LineUnavailableException{
		generateTone(590, oneunit, (int) (50 * 1.28));
	}
	public static void dash() throws LineUnavailableException{
		generateTone(590, oneunit*3, (int) (50 * 1.28));
	}
	public static void main(String[] args) throws LineUnavailableException {
		
		dot();
		dot();
		dash();
		dot();
		
	}
}