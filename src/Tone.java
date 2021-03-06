import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.LineUnavailableException;



public class Tone {

	static char[] alphabetAndNumbers = {'a','b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
			'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', ')'};

	static String[] morseCode = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--",
			"-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
			"..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", "-----", ")" };

	// Tone generated from code sourced from;
	// https://community.oracle.com/thread/1273219?start=0&tstart=0
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

	static int oneunit = 100;

	public static void dot() throws LineUnavailableException {
		generateTone(590, oneunit, (int) (50 * 1.28));
	}

	public static void dash() throws LineUnavailableException {
		generateTone(590, oneunit * 5, (int) (50 * 1.28));
	}

	public static void SoundOutput(char c) throws LineUnavailableException {
			for (int i = 0; i < alphabetAndNumbers.length;i++){
				if (alphabetAndNumbers[i] == (Character.toLowerCase(c))){
					morsetoSound(morseCode[i]);
				}
			}
	}

	public static void morsetoSound(String input) throws LineUnavailableException {
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '.'){
				dot();
			}else{
				dash();
			}
		
		}
	}
	public static void input(String input) throws LineUnavailableException, InterruptedException{
		ArrayList<Character> charList = new ArrayList<Character>();
		
		
		for (int i = 0; i < input.length(); i++) {
			charList.add(input.charAt(i));
			
			if (input.charAt(i) == ' '){
				System.out.println(System.getProperty("line.separator"));
				Thread.sleep(2000);
			}
			
			SoundOutput(input.charAt(i));
		}
	}
}