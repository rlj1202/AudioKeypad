package redlaboratory.audioKeypad;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

public class Main {
	
	private static HashMap<Integer, String> keyDesignated = new HashMap<Integer, String>();
	private static HashMap<Integer, String> keyDescription = new HashMap<Integer, String>();
	
	private static HashMap<String, Integer> buttonIndexes = new HashMap<String, Integer>();
	
	private static HashMap<String, String> audioLocation = new HashMap<String, String>();
	private static HashMap<String, JButton> buttons = new HashMap<String, JButton>();
	private static HashMap<String, Clip> clips = new HashMap<String, Clip>();
	
	private static int buttonIndex = 0;
	
	public static int width = 7;
	public static int height = 5;
	
	private static ConfigurationManager cm;
	
	public static void main(String[] args) {
		setSystemVolume(1.0f);
		
		setAudio(0, 'q', "쾅 1", "/redlaboratory/audioKeypad/audios/effects/deep_metal_effect_1.wav");
		setAudio(1, 'w', "쾅 2", "/redlaboratory/audioKeypad/audios/effects/deep_metal_effect_2.wav");
		setAudio(2, 'e', "쾅 3", "/redlaboratory/audioKeypad/audios/effects/deep_metal_effect_3.wav");
		
		setAudio(3, 'r', "문 여는 소리", "/redlaboratory/audioKeypad/audios/effects/sliding_door_1.wav");
		setAudio(4, 't', "문 닫는 소리", "/redlaboratory/audioKeypad/audios/effects/sliding_door_2.wav");
		
		setAudio(5, 'y', "종소리", "/redlaboratory/audioKeypad/audios/effects/class_end.wav");
		
		//
		
		setAudio(7, 'a', "thunder 1", "/redlaboratory/audioKeypad/audios/effects/heavy-thunder-01.wav");
		setAudio(8, 's', "thunder 2", "/redlaboratory/audioKeypad/audios/effects/heavy-thunder-02.wav");
		setAudio(9, 'd', "thunder 3", "/redlaboratory/audioKeypad/audios/effects/heavy-thunder-03.wav");
		setAudio(10, 'f', "thunder 4", "/redlaboratory/audioKeypad/audios/effects/heavy-thunder-04.wav");
		setAudio(11, 'g', "thunder 5", "/redlaboratory/audioKeypad/audios/effects/heavy-thunder-05.wav");
		setAudio(12, 'h', "thunder 6", "/redlaboratory/audioKeypad/audios/effects/heavy-thunder-06.wav");
		setAudio(13, 'j', "thunder 7", "/redlaboratory/audioKeypad/audios/effects/heavy-thunder-07.wav");
		
		//
		
		setAudio(19, 'p', "눈물", "/redlaboratory/audioKeypad/audios/미생_눈물.wav");
		setAudio(20, ';', "외로운 시간을 견디며", "/redlaboratory/audioKeypad/audios/미생_외로운 시간을 견디며.wav");
		
		setAudio(14, 'u', "Indigo", "/redlaboratory/audioKeypad/audios/sceneChangeMusics/Yiruma_Indigo.wav");
		setAudio(15, 'i', "River Flows In You", "/redlaboratory/audioKeypad/audios/sceneChangeMusics/Yiruma_River Flows In You.wav");
		setAudio(16, 'o', "Dream", "/redlaboratory/audioKeypad/audios/sceneChangeMusics/Yiruma_Dream.wav");
		setAudio(17, 'k', "Reminiscent", "/redlaboratory/audioKeypad/audios/sceneChangeMusics/Yiruma_Reminiscent.wav");
		setAudio(18, 'l', "When the love falls", "/redlaboratory/audioKeypad/audios/sceneChangeMusics/Yiruma_When The Love Falls.wav");
		
		//
		
		setAudio(28, 'z', "Sitting on top of the world", "/redlaboratory/audioKeypad/audios/Sitting On Top Of The World.wav");
		setAudio(29, 'x', "We're all in this together 편집", "/redlaboratory/audioKeypad/audios/We're_all_in_this_together_mixed.wav");
		setAudio(30, 'c', "스토커 mr", "/redlaboratory/audioKeypad/audios/stalker_mr.wav");
		setAudio(31, 'v', "스토커 mr down", "/redlaboratory/audioKeypad/audios/stalker_mr_down.wav");
		setAudio(32, 'b', "데스 노트", "/redlaboratory/audioKeypad/audios/death_note.wav");
		setAudio(33, 'n', "그림자는 길어지고", "/redlaboratory/audioKeypad/audios/The Shadows Grow Longer.wav");
		setAudio(34, 'm', "풍선", "/redlaboratory/audioKeypad/audios/balloons.wav");
		
		//
		
		JFrame frame = new JFrame();
		JPanel audioPane = new JPanel();
		JPanel controlPane = new JPanel();
		GridLayout lm = new GridLayout();
		
		JTextPane info = new JTextPane();
		JProgressBar volumeBar = new JProgressBar();
		
		frame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent paramWindowEvent) {
				
			}

			@Override
			public void windowClosing(WindowEvent paramWindowEvent) {
				try {
					cm.save();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void windowClosed(WindowEvent paramWindowEvent) {
				
			}

			@Override
			public void windowIconified(WindowEvent paramWindowEvent) {
				
			}

			@Override
			public void windowDeiconified(WindowEvent paramWindowEvent) {
				
			}

			@Override
			public void windowActivated(WindowEvent paramWindowEvent) {
				
			}

			@Override
			public void windowDeactivated(WindowEvent paramWindowEvent) {
				
			}
			
		});
		
		audioPane.setLayout(lm);
		
		lm.setColumns(width);
		lm.setRows(height);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("AudioKeyPad made by Jisu Sim, RedLaboratory");
		frame.setMinimumSize(new Dimension(1200, 700));
		frame.setSize(1200, 700);
		frame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyPressed(KeyEvent event) {
				String selectedDesignation = keyDesignated.get(buttonIndex);
				
				switch (event.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					buttonIndex += -1;
					if (buttonIndex < 0) buttonIndex = 0;
					if (buttonIndex > width * height - 1) buttonIndex = width * height - 1;
					
					break;
				case KeyEvent.VK_RIGHT:
					buttonIndex += 1;
					if (buttonIndex < 0) buttonIndex = 0;
					if (buttonIndex > width * height - 1) buttonIndex = width * height - 1;
					
					break;
				case KeyEvent.VK_UP:
					buttonIndex += -width;
					if (buttonIndex < 0) buttonIndex = 0;
					if (buttonIndex > width * height - 1) buttonIndex = width * height - 1;
					
					break;
				case KeyEvent.VK_DOWN:
					buttonIndex += width;
					if (buttonIndex < 0) buttonIndex = 0;
					if (buttonIndex > width * height - 1) buttonIndex = width * height - 1;
					
					break;
				case KeyEvent.VK_SPACE:
					if (selectedDesignation != null) {
						Clip clip = clips.get(selectedDesignation);
						
						if (clip != null) {
							if (clip.isRunning()) clip.stop();
							else clip.start();
						}
					}
					
					break;
				case KeyEvent.VK_ENTER:
					if (selectedDesignation != null) {
						JButton button = buttons.get(selectedDesignation);
						
						if (button != null) {
							button.doClick();
						}
					}
					
					break;
				}
				
				char keyChar = event.getKeyChar();
				
				switch (keyChar) {
				case '1': case '2': case '3': case '4': case '5':
				case '6': case '7': case '8': case '9': case '0':
				case '-':
					int i;
					if (keyChar == '-') i = 10;
					else i = Integer.parseInt(String.valueOf(keyChar));
					
					if (i == 0) {
						i = 9;
					} else if (i != 10) {
						i--;
					}
					
					setSystemVolume(i / 10.0f);
					
					break;
				case '`':
					for (Clip clip : clips.values()) {
						clip.stop();
						clip.setFramePosition(0);
					}
					
					break;
				case '[':
					setSystemVolume(getSystemVolume() - 0.01f);
					
					break;
				case ']':
					setSystemVolume(getSystemVolume() + 0.01f);
					
					break;
				case ',':
					if (selectedDesignation != null) {
						Clip clip = clips.get(selectedDesignation);
						
						if (clip != null) {
							clip.setFramePosition(clip.getFramePosition() - (int) clip.getFormat().getFrameRate());
						}
					}
					
					break;
				case '.':
					if (selectedDesignation != null) {
						Clip clip = clips.get(selectedDesignation);
						
						if (clip != null) {
							clip.setFramePosition(clip.getFramePosition() + (int) clip.getFormat().getFrameRate());
						}
					}
					
					break;
				case '<':
					if (selectedDesignation != null) {
						Clip clip = clips.get(selectedDesignation);
						
						if (clip != null) {
							clip.setFramePosition(clip.getFramePosition() - (int) clip.getFormat().getFrameRate() * 10);
						}
					}
					
					break;
				case '>':
					if (selectedDesignation != null) {
						Clip clip = clips.get(selectedDesignation);
						
						if (clip != null) {
							clip.setFramePosition(clip.getFramePosition() + (int) clip.getFormat().getFrameRate() * 10);
						}
					}
					
					break;
				case '\'':
					if (selectedDesignation != null) {
						Clip clip = clips.get(selectedDesignation);
						
						if (clip != null) {
							FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
							if (control.getValue() < 5) control.setValue(control.getValue() + 1);
						}
					}
					
					break;
				case '/':
					if (selectedDesignation != null) {
						Clip clip = clips.get(selectedDesignation);
						
						if (clip != null) {
							FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
							if (control.getValue() > -80) control.setValue(control.getValue() - 1);
						}
					}
					
					break;
				default:
					JButton button = buttons.get(String.valueOf(keyChar));
					
					if (button != null) {
						button.doClick();
						buttonIndex = buttonIndexes.get(String.valueOf(keyChar));
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent paramKeyEvent) {
				
			}

			@Override
			public void keyTyped(KeyEvent paramKeyEvent) {
				
			}
			
		});
		
		{
			for (int i = 0; i < width * height; i++) {
				String buttonKeyDesignation = keyDesignated.get(i);
				String buttonKeyDescription = keyDescription.get(i);
				
				JButton button = new JButton();
				button.setFocusable(false);
				
				if (buttonKeyDesignation != null) {
					String fileLoc = audioLocation.get(buttonKeyDesignation);
					
					Clip clip = null;
					try {
						if (fileLoc != null) {
							URL url = Main.class.getResource(fileLoc);
							AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
							clip = AudioSystem.getClip();
							clip.open(audioIn);
							
							clips.put(buttonKeyDesignation, clip);
						}
					} catch (UnsupportedAudioFileException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (LineUnavailableException e) {
						e.printStackTrace();
					}
					
					button.setText("<html>" + buttonKeyDesignation + "<br />" + buttonKeyDescription + "</html>");
					if (fileLoc != null) button.addActionListener(new AudioAction(i, clip));
					button.addMouseListener(new ButtonMouseListener(i));
					
					buttons.put(buttonKeyDesignation, button);
				}
				
				button.setBorder(new LineBorder(Color.WHITE));
				button.setBorderPainted(false);
				button.setBackground(new Color(0xB2, 0xEB, 0xF2));
				
				audioPane.add(button);
			}
		}
		
		{
			GridBagLayout gl = new GridBagLayout();
			GridBagConstraints glc = new GridBagConstraints();
			
			info.setFocusable(false);
			info.setEditable(false);
			info.setBackground(Color.GRAY);
			info.setText(
					"1-1 Lost Memory \n" +
					"Jisu Sim, RedLaboratory \n" +
					"\n"
						);
			
			glc.fill = GridBagConstraints.BOTH;
			glc.weightx = 1.0f;
			glc.weighty = 1.0f;
			controlPane.setLayout(gl);
			
			glc.gridx = 0;
			glc.gridy = 0;
			glc.gridwidth = 2;
			glc.gridheight = 2;
			controlPane.add(volumeBar, glc);
			
			glc.gridx = 0;
			glc.gridy = 2;
			glc.gridwidth = 2;
			glc.gridheight = 2;
			controlPane.add(info, glc);
		}
		
		{
			GridBagConstraints c = new GridBagConstraints();
			frame.setLayout(new GridBagLayout());
			
			c.fill = GridBagConstraints.BOTH;
			
			c.weightx = 0.8;
			c.weighty = 1.0;
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			frame.add(audioPane, c);
			
			c.weightx = 0.2;
			c.weighty = 1.0;
			c.gridx = 1;
			c.gridy = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			frame.add(controlPane, c);
			
			frame.setVisible(true);
		}
		
		Thread thread = new Thread() {
			
			@Override
			public void run() {
				cm = new ConfigurationManager();
				try {
					cm.load();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				while (frame.isDisplayable()) {
					volumeBar.setMaximum(1000);
					volumeBar.setMinimum(0);
					volumeBar.setValue((int) (getSystemVolume() * 1000));
					volumeBar.setString((getSystemVolume() * 100) + "%");
					volumeBar.setStringPainted(true);
					
					for (int i = 0; i < width * height; i++) {
						String designation = keyDesignated.get(i);
						String description = keyDescription.get(i);
						
						if (designation != null) {
							JButton button = buttons.get(designation);
							Clip clip = clips.get(designation);
							
							FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
							
							if (i == buttonIndex) {
								button.setBorderPainted(true);
							} else {
								button.setBorderPainted(false);
							}
							
							int secsCurrent = (int) (clip.getFramePosition() / clip.getFormat().getFrameRate());
							int secsMax = (int) (clip.getFrameLength() / clip.getFormat().getFrameRate());
							
							button.setText(
									"<html>" +
											i + " " + "<font size=\"9\"><center>" + designation + "</center></font>" +
									"<br />" +
											"<font size=\"2\">" + description + "</font>" +
									"<br />" +
											"<center>" + toMinutesAndSecondsString(secsCurrent) + "/" + toMinutesAndSecondsString(secsMax) + " # " + control.getValue() + "</center>" +
									"</html>");
							
							if (clip.isRunning()) {
								button.setBackground(new Color(0x4C, 0xAF, 0x50));
								button.setForeground(Color.WHITE);
							} else {
								button.setBackground(new Color(0xD3, 0x2F, 0x2F));
								button.setForeground(Color.WHITE);
							}
						}
					}
				}
			}
			
		};
		thread.start();
	}
	
	public static float getMasterGain(int index) {
		String designation = keyDesignated.get(index);
		
		if (designation != null) {
			Clip clip = clips.get(designation);
			
			if (clip != null) {
				FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				
				return control.getValue();
			}
		}
		
		return -1;
	}

	public static void setMasterGain(int index, float gain) {
		String designation = keyDesignated.get(index);
		Clip clip = clips.get(designation);
		
		if (clip != null) {
			FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			control.setValue(gain);
		}
	}

	public static void setAudio(int index, char key, String description, String location) {
		keyDesignated.put(index, String.valueOf(key));
		keyDescription.put(index, description);
		
		buttonIndexes.put(String.valueOf(key), index);
		
		audioLocation.put(String.valueOf(key), location);
	}
	
	public static float getSystemVolume() {
		for (Mixer.Info mixerInfo : AudioSystem.getMixerInfo()) {
			Mixer mixer = AudioSystem.getMixer(mixerInfo);
			try {
				mixer.open();
			} catch (LineUnavailableException e) {
//				e.printStackTrace();
			}
			for (Line.Info lineInfo : mixer.getTargetLineInfo()) {
				try {
					Line line = mixer.getLine(lineInfo);
					if (line instanceof Clip) {
						continue;
					}
					line.open();
					if (line.isControlSupported(FloatControl.Type.VOLUME)) {
						FloatControl control = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
						return control.getValue();
					}
				} catch (LineUnavailableException e) {
//					e.printStackTrace();
					continue;
				}
			}
		}
		
		return -1;
	}
	
	public static void setSystemVolume(float volume) {
		if (volume > 1) volume = 1;
		if (volume < 0) volume = 0;
		
		for (Mixer.Info mixerInfo : AudioSystem.getMixerInfo()) {
			Mixer mixer = AudioSystem.getMixer(mixerInfo);
			try {
				mixer.open();
			} catch (LineUnavailableException e) {
//				e.printStackTrace();
			}
			for (Line.Info lineInfo : mixer.getTargetLineInfo()) {
				try {
					Line line = mixer.getLine(lineInfo);
					if (line instanceof Clip) {
						continue;
					}
					line.open();
					if (line.isControlSupported(FloatControl.Type.VOLUME)) {
						FloatControl control = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
						control.setValue(volume);
					}
				} catch (LineUnavailableException e) {
//					e.printStackTrace();
				}
			}
		}
	}
	
	public static String toMinutesAndSecondsString(int secs) {
		return secs / 60 + ":" + secs % 60;
	}

	public static class AudioAction implements ActionListener {
		
		private int buttonIndex;
		private Clip clip;
		
		public AudioAction(int buttonIndex, Clip clip) {
			this.buttonIndex = buttonIndex;
			this.clip = clip;
		}
		
		@Override
		public void actionPerformed(ActionEvent paramActionEvent) {
			if (clip != null) {
				Main.buttonIndex = buttonIndex;
				
				if (clip.isRunning()) {
					clip.stop();
					clip.setFramePosition(0);
				} else {
					clip.start();
					clip.setFramePosition(0);
				}
			}
		}
		
	}
	
	public static class ButtonMouseListener implements MouseListener {
		
		private int buttonIndex;
		
		public ButtonMouseListener(int buttonIndex) {
			this.buttonIndex = buttonIndex;
		}
		
		@Override
		public void mouseClicked(MouseEvent event) {
			
		}

		@Override
		public void mousePressed(MouseEvent event) {
			if (event.getButton() == MouseEvent.BUTTON3) {
				Main.buttonIndex = this.buttonIndex;
			}
		}

		@Override
		public void mouseReleased(MouseEvent event) {
			
		}

		@Override
		public void mouseEntered(MouseEvent event) {
			
		}

		@Override
		public void mouseExited(MouseEvent event) {
			
		}
		
	}
	
}
