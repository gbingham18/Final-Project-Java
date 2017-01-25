import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class Main {
	
	public ArrayList<Element> Elements = new ArrayList<Element>(); 
	public ArrayList<JButton> Buttons = new ArrayList<JButton>();
	public int x = 0;
	private JTextField textField;
	private JTextField textField_1;
	public static void main(String[] args)
	{
		Main main = new Main();
	}
	
	public Main()
	{
		createElements();
		createTable();
		addActionListener();
	}
	
	public void createElements()
	{
		ArrayList<Integer> AtomicNumbers = new ArrayList<Integer>();
		ArrayList<String> ElementNames = new ArrayList<String>();
		ArrayList<Double> MolecularWeights = new ArrayList<Double>();
		try (FileInputStream is = new FileInputStream("\\C:\\Users\\gbingham18\\elements.txt")) {
            InputStreamReader ir = new InputStreamReader(is);
            BufferedReader rdr = new BufferedReader(ir);
            String line = rdr.readLine();
            
            while (line != null) {
                String[] parts = line.split(", ");
                line = rdr.readLine();
                int i = 1;
                for (String p : parts) {
                	if(i % 3 == 1)
                	{
                		int x = Integer.parseInt(p);
                		AtomicNumbers.add(x);
                	}
                	else if(i % 3 == 2)
                	{
                		ElementNames.add(p);
                	}
                	else
                	{
                		double x = Double.parseDouble(p);
                		MolecularWeights.add(x);
                	}
                    i++;
                }
            }

        }
        catch (Exception ex) {
        	System.out.printf(ex.getMessage());
        }
		
		for(int i = 1; i < 119; i++)
		{
			if(i == 3 || i == 11 || i == 19 || i == 37 || i == 55 || i == 87)
			{
				Elements.add(new AlkiliMetal(AtomicNumbers.get(i - 1), ElementNames.get(i - 1), MolecularWeights.get(i - 1)));
			}
			else if(i == 4 || i == 12 || i == 20 || i == 38 || i == 56 || i == 88)
			{
				Elements.add(new AlkilineEarth(AtomicNumbers.get(i - 1), ElementNames.get(i - 1), MolecularWeights.get(i - 1)));
			}
			else if(i == 21 || i == 22 || i == 23 || i == 24 || i == 25 || i == 26 || i == 27 || i == 28 || i == 29 ||
					i == 30 || i == 39 || i == 40 || i == 41 || i == 42 || i == 43 || i == 44 || i == 45 || i == 46 ||
					i == 47 || i == 48 || i == 72 || i == 73 || i == 74 || i == 75 || i == 76 || i == 77 || i == 78 ||
					i == 79 || i == 80 || i == 104 || i == 105 || i == 106 || i == 107 || i == 108 || i == 109 ||
					i == 110 || i == 111 || i == 112)
			{
				Elements.add(new TransitionMetal(AtomicNumbers.get(i - 1), ElementNames.get(i - 1), MolecularWeights.get(i - 1)));
			}
			else if(i == 13 || i == 31 || i == 49 || i == 50 || i == 81 || i == 82 || i == 83)
			{
				Elements.add(new BasicMetal(AtomicNumbers.get(i - 1), ElementNames.get(i - 1), MolecularWeights.get(i - 1)));
			}
			else if(i == 5 || i == 14 || i == 32 || i == 33 || i == 51 || i == 52 || i == 84)
			{
				Elements.add(new SemiMetal(AtomicNumbers.get(i - 1), ElementNames.get(i - 1), MolecularWeights.get(i - 1)));
			}
			else if(i == 1 || i == 6 || i == 7 || i == 8 || i == 15 || i == 16 || i == 34)
			{
				Elements.add(new NonMetal(AtomicNumbers.get(i - 1), ElementNames.get(i - 1), MolecularWeights.get(i - 1)));
			}
			else if(i == 9 || i == 17 || i == 35 || i == 53 || i == 85)
			{
				Elements.add(new Halogen(AtomicNumbers.get(i - 1), ElementNames.get(i - 1), MolecularWeights.get(i - 1)));
			}
			else if(i == 2 || i == 10 || i == 18 || i == 36 || i == 54 || i == 86)
			{
				Elements.add(new NobleGas(AtomicNumbers.get(i - 1), ElementNames.get(i - 1), MolecularWeights.get(i - 1)));
			}
			else if(i == 57 || i == 58 || i == 59 || i == 60 || i == 61 || i == 62 || i == 63 || 
					i == 64 || i == 65 || i == 66 || i == 67 || i == 68 || i == 69 || i == 70 || 
					i == 71 || i == 89 || i == 90 || i == 91 || i == 92 || i == 93 || i == 94 ||
					i == 95 || i == 96 || i == 97 || i == 98 || i == 99 || i == 100 || i == 101 ||
					i == 102 || i == 103)
			{
				Elements.add(new RareEarth(AtomicNumbers.get(i - 1), ElementNames.get(i - 1), MolecularWeights.get(i - 1)));
			}
			
		}
	
		
		
	}
	
	//Creates Buttons associated with each element, and aligns them like the Periodic Table
	//Also Creates Title and Search Bar
	//Calls addSearchListener(), addInstructions(), addgb(), and ColorButtons()
	public void createTable()
	{
		JFrame frame = new JFrame();
		frame.setBounds(50, 50, 1020, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 150, 1000, 600);
		panel.setLayout(new GridBagLayout());
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(220, 50, 780, 100);
		panel2.setLayout(null);
		
		JPanel panel3 = new JPanel();
		panel3.setBounds(0, 0, 200, 100);
		
		JLabel label = new JLabel();
		label.setText("Periodic Table of Elements");
		label.setBounds(0, 20, 600, 60);
		label.setFont(label.getFont().deriveFont(44.0f));
		
		panel2.add(label);
		
		
		textField_1 = new JTextField();
		panel3.add(textField_1);
		textField_1.setColumns(10);
		
		JButton InstructionsButton = new JButton("Instructions");
		panel3.add(InstructionsButton);
		
		addSearchListener(textField_1);
		addInstructions(InstructionsButton);
		
		
		
		Buttons.add(new JButton("*"));
		Buttons.add(new JButton("H"));
		Buttons.add(new JButton("He"));
		Buttons.add(new JButton("Li"));
		Buttons.add(new JButton("Be"));
		Buttons.add(new JButton("B"));
		Buttons.add(new JButton("C"));
		Buttons.add(new JButton("N"));
		Buttons.add(new JButton("O"));
		Buttons.add(new JButton("F"));
		Buttons.add(new JButton("Ne"));
		Buttons.add(new JButton("Na"));
		Buttons.add(new JButton("Mg"));
		Buttons.add(new JButton("Al"));
		Buttons.add(new JButton("Si"));
		Buttons.add(new JButton("P"));
		Buttons.add(new JButton("S"));
		Buttons.add(new JButton("Cl"));
		Buttons.add(new JButton("Ar"));
		Buttons.add(new JButton("K"));
		Buttons.add(new JButton("Ca"));
		Buttons.add(new JButton("Sc"));
		Buttons.add(new JButton("Ti"));
		Buttons.add(new JButton("V"));
		Buttons.add(new JButton("Cr"));
		Buttons.add(new JButton("Mn"));
		Buttons.add(new JButton("Fe"));
		Buttons.add(new JButton("Co"));
		Buttons.add(new JButton("Ni"));
		Buttons.add(new JButton("Cu"));
		Buttons.add(new JButton("Zn"));
		Buttons.add(new JButton("Ga"));
		Buttons.add(new JButton("Ge"));
		Buttons.add(new JButton("As"));
		Buttons.add(new JButton("Se"));
		Buttons.add(new JButton("Br"));
		Buttons.add(new JButton("Kr"));
		Buttons.add(new JButton("Rb"));
		Buttons.add(new JButton("Sr"));
		Buttons.add(new JButton("Y"));
		Buttons.add(new JButton("Zr"));
		Buttons.add(new JButton("Nb"));
		Buttons.add(new JButton("Mo"));
		Buttons.add(new JButton("Tc"));
		Buttons.add(new JButton("Ru"));
		Buttons.add(new JButton("Rh"));
		Buttons.add(new JButton("Pd"));
		Buttons.add(new JButton("Ag"));
		Buttons.add(new JButton("Cd"));
		Buttons.add(new JButton("In"));
		Buttons.add(new JButton("Sn"));
		Buttons.add(new JButton("Sb"));
		Buttons.add(new JButton("Te"));
		Buttons.add(new JButton("I"));
		Buttons.add(new JButton("Xe"));
		Buttons.add(new JButton("Cs"));
		Buttons.add(new JButton("Ba"));
		Buttons.add(new JButton("La"));
		Buttons.add(new JButton("Ce"));
		Buttons.add(new JButton("Pr"));
		Buttons.add(new JButton("Nd"));
		Buttons.add(new JButton("Pm"));
		Buttons.add(new JButton("Sm"));
		Buttons.add(new JButton("Eu"));
		Buttons.add(new JButton("Gd"));
		Buttons.add(new JButton("Tb"));
		Buttons.add(new JButton("Dy"));
		Buttons.add(new JButton("Ho"));
		Buttons.add(new JButton("Er"));
		Buttons.add(new JButton("Tm"));
		Buttons.add(new JButton("Yb"));
		Buttons.add(new JButton("Lu"));
		Buttons.add(new JButton("Hf"));
		Buttons.add(new JButton("Ta"));
		Buttons.add(new JButton("W"));
		Buttons.add(new JButton("Re"));
		Buttons.add(new JButton("Os"));
		Buttons.add(new JButton("Ir"));
		Buttons.add(new JButton("Pt"));
		Buttons.add(new JButton("Au"));
		Buttons.add(new JButton("Hg"));
		Buttons.add(new JButton("Tl"));
		Buttons.add(new JButton("Pb"));
		Buttons.add(new JButton("Bi"));
		Buttons.add(new JButton("Po"));
		Buttons.add(new JButton("At"));
		Buttons.add(new JButton("Rn"));
		Buttons.add(new JButton("Fr"));
		Buttons.add(new JButton("Ra"));
		Buttons.add(new JButton("Ac"));
		Buttons.add(new JButton("Th"));
		Buttons.add(new JButton("Pa"));
		Buttons.add(new JButton("U"));
		Buttons.add(new JButton("Np"));
		Buttons.add(new JButton("Pu"));
		Buttons.add(new JButton("Am"));
		Buttons.add(new JButton("Cm"));
		Buttons.add(new JButton("Bk"));
		Buttons.add(new JButton("Cf"));
		Buttons.add(new JButton("Es"));
		Buttons.add(new JButton("Fm"));
		Buttons.add(new JButton("Md"));
		Buttons.add(new JButton("No"));
		Buttons.add(new JButton("Lr"));
		Buttons.add(new JButton("Rf"));
		Buttons.add(new JButton("Db"));
		Buttons.add(new JButton("Sg"));
		Buttons.add(new JButton("Bh"));
		Buttons.add(new JButton("Hs"));
		Buttons.add(new JButton("Mt"));
		Buttons.add(new JButton("Ds"));
		Buttons.add(new JButton("Rg"));
		Buttons.add(new JButton("Cn"));
		Buttons.add(new JButton("Nh"));
		Buttons.add(new JButton("Fl"));
		Buttons.add(new JButton("Mc"));
		Buttons.add(new JButton("Lv"));
		Buttons.add(new JButton("Ts"));
		Buttons.add(new JButton("Og"));
		Buttons.add(new JButton("#"));
		
		for(int i = 0; i < 120; i++)
		{
			Buttons.get(i).setPreferredSize(new Dimension(53, 40));
		}
		
		addgb(Buttons.get(1), 0, 0, panel);
		addgb(Buttons.get(3), 0, 1, panel);
		addgb(Buttons.get(11), 0, 2, panel);
		addgb(Buttons.get(19), 0, 3, panel);
		addgb(Buttons.get(37), 0, 4, panel);
		addgb(Buttons.get(55), 0, 5, panel);
		addgb(Buttons.get(87), 0, 6, panel);
		addgb(Buttons.get(4), 1, 1, panel);
		addgb(Buttons.get(12), 1, 2, panel);
		addgb(Buttons.get(20), 1, 3, panel);
		addgb(Buttons.get(38), 1, 4, panel);
		addgb(Buttons.get(56), 1, 5, panel);
		addgb(Buttons.get(88), 1, 6, panel);
		addgb(Buttons.get(21), 2, 3, panel);
		addgb(Buttons.get(39), 2, 4, panel);
		addgb(Buttons.get(0), 2, 5, panel);
		addgb(Buttons.get(119), 2, 6, panel);
		addgb(Buttons.get(22), 3, 3, panel);
		addgb(Buttons.get(40), 3, 4, panel);
		addgb(Buttons.get(72), 3, 5, panel);
		addgb(Buttons.get(104), 3, 6, panel);
		addgb(Buttons.get(57), 3, 7, panel);
		addgb(Buttons.get(89), 3, 8, panel);
		addgb(Buttons.get(23), 4, 3, panel);
		addgb(Buttons.get(41), 4, 4, panel);
		addgb(Buttons.get(73), 4, 5, panel);
		addgb(Buttons.get(105), 4, 6, panel);
		addgb(Buttons.get(58), 4, 7, panel);
		addgb(Buttons.get(90), 4, 8, panel);
		addgb(Buttons.get(24), 5, 3, panel);
		addgb(Buttons.get(42), 5, 4, panel);
		addgb(Buttons.get(74), 5, 5, panel);
		addgb(Buttons.get(106), 5, 6, panel);
		addgb(Buttons.get(59), 5, 7, panel);
		addgb(Buttons.get(91), 5, 8, panel);
		addgb(Buttons.get(25), 6, 3, panel);
		addgb(Buttons.get(43), 6, 4, panel);
		addgb(Buttons.get(75), 6, 5, panel);
		addgb(Buttons.get(107), 6, 6, panel);
		addgb(Buttons.get(60), 6, 7, panel);
		addgb(Buttons.get(92), 6, 8, panel);
		addgb(Buttons.get(26), 7, 3, panel);
		addgb(Buttons.get(44), 7, 4, panel);
		addgb(Buttons.get(76), 7, 5, panel);
		addgb(Buttons.get(108), 7, 6, panel);
		addgb(Buttons.get(61), 7, 7, panel);
		addgb(Buttons.get(93), 7, 8, panel);
		addgb(Buttons.get(27), 8, 3, panel);
		addgb(Buttons.get(45), 8, 4, panel);
		addgb(Buttons.get(77), 8, 5, panel);
		addgb(Buttons.get(109), 8, 6, panel);
		addgb(Buttons.get(62), 8, 7, panel);
		addgb(Buttons.get(94), 8, 8, panel);
		addgb(Buttons.get(28), 9, 3, panel);
		addgb(Buttons.get(46), 9, 4, panel);
		addgb(Buttons.get(78), 9, 5, panel);
		addgb(Buttons.get(110), 9, 6, panel);
		addgb(Buttons.get(63), 9, 7, panel);
		addgb(Buttons.get(95), 9, 8, panel);
		addgb(Buttons.get(29), 10, 3, panel);
		addgb(Buttons.get(47), 10, 4, panel);
		addgb(Buttons.get(79), 10, 5, panel);
		addgb(Buttons.get(111), 10, 6, panel);
		addgb(Buttons.get(64), 10, 7, panel);
		addgb(Buttons.get(96), 10, 8, panel);
		addgb(Buttons.get(30), 11, 3, panel);
		addgb(Buttons.get(48), 11, 4, panel);
		addgb(Buttons.get(80), 11, 5, panel);
		addgb(Buttons.get(112), 11, 6, panel);
		addgb(Buttons.get(65), 11, 7, panel);
		addgb(Buttons.get(97), 11, 8, panel);
		addgb(Buttons.get(5), 12, 1, panel);
		addgb(Buttons.get(13), 12, 2, panel);
		addgb(Buttons.get(31), 12, 3, panel);
		addgb(Buttons.get(49), 12, 4, panel);
		addgb(Buttons.get(81), 12, 5, panel);
		addgb(Buttons.get(113), 12, 6, panel);
		addgb(Buttons.get(66), 12, 7, panel);
		addgb(Buttons.get(98), 12, 8, panel);
		addgb(Buttons.get(6), 13, 1, panel);
		addgb(Buttons.get(14), 13, 2, panel);
		addgb(Buttons.get(32), 13, 3, panel);
		addgb(Buttons.get(50), 13, 4, panel);
		addgb(Buttons.get(82), 13, 5, panel);
		addgb(Buttons.get(114), 13, 6, panel);
		addgb(Buttons.get(67), 13, 7, panel);
		addgb(Buttons.get(99), 13, 8, panel);
		addgb(Buttons.get(7), 14, 1, panel);
		addgb(Buttons.get(15), 14, 2, panel);
		addgb(Buttons.get(33), 14, 3, panel);
		addgb(Buttons.get(51), 14, 4, panel);
		addgb(Buttons.get(83), 14, 5, panel);
		addgb(Buttons.get(115), 14, 6, panel);
		addgb(Buttons.get(68), 14, 7, panel);
		addgb(Buttons.get(100), 14, 8, panel);
		addgb(Buttons.get(8), 15, 1, panel);
		addgb(Buttons.get(16), 15, 2, panel);
		addgb(Buttons.get(34), 15, 3, panel);
		addgb(Buttons.get(52), 15, 4, panel);
		addgb(Buttons.get(84), 15, 5, panel);
		addgb(Buttons.get(116), 15, 6, panel);
		addgb(Buttons.get(69), 15, 7, panel);
		addgb(Buttons.get(101), 15, 8, panel);
		addgb(Buttons.get(9), 16, 1, panel);
		addgb(Buttons.get(17), 16, 2, panel);
		addgb(Buttons.get(35), 16, 3, panel);
		addgb(Buttons.get(53), 16, 4, panel);
		addgb(Buttons.get(85), 16, 5, panel);
		addgb(Buttons.get(117), 16, 6, panel);
		addgb(Buttons.get(70), 16, 7, panel);
		addgb(Buttons.get(102), 16, 8, panel);
		addgb(Buttons.get(2), 17, 0, panel);
		addgb(Buttons.get(10), 17, 1, panel);
		addgb(Buttons.get(18), 17, 2, panel);
		addgb(Buttons.get(36), 17, 3, panel);
		addgb(Buttons.get(54), 17, 4, panel);
		addgb(Buttons.get(86), 17, 5, panel);
		addgb(Buttons.get(118), 17, 6, panel);
		addgb(Buttons.get(71), 17, 7, panel);
		addgb(Buttons.get(103), 17, 8, panel);
		
		frame.getContentPane().add(panel);	
		frame.getContentPane().add(panel2);
		frame.getContentPane().add(panel3);
		
		colorButtons();
	}
	
	//Used for aligning JButtons relative to each other
	public void addgb(Component c, int x, int y, JPanel p)
	{
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		p.add(c,gbc);
	}
	
	
	//Gives function to all the buttons on the Periodic Table
	public void addActionListener()
	{	
		for(int i = 0; i < 110; i++)
		{
			Buttons.get(i + 1).addActionListener(new ActionListener()
				{		
					public void actionPerformed(ActionEvent ae) {
					
						JFrame newFrame = new JFrame();
						newFrame.setBounds(600, 50, 300, 300);
						newFrame.setVisible(true);
				
						JPanel newPanel = new JPanel();
								
						String s = "The name of this Element is: " + Elements.get(x).getName();
						String ss =	"The Atomic Number of this Element is: " + Elements.get(x).getAtomicNumber();
						String sss = "The Molecular Weight of this Element is: " + Elements.get(x).getMolecularWeight();
				
						JLabel label = new JLabel();
						label.setText("<html>" + s + "<br>" + ss + "<br>" + sss + "<html>");
					
						newPanel.add(label);
						newFrame.getContentPane().add(newPanel);
						x++;			
					}
								
				});
					
			
		};
			
	}
	
	//Gives function to Search bar
	//Returns info on element that was searched for by Atomic Number
	//Returns Exception if user enters number out of range
	public void addSearchListener(JTextField text)
	{
		text.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					try {
					int x = Integer.parseInt(text.getText());
					JFrame newFrame = new JFrame();
					newFrame.setBounds(600, 50, 300, 300);
					newFrame.setVisible(true);
					
					JPanel newPanel = new JPanel();
									
					String s = "The name of this Element is: " + Elements.get(x-1).getName();
					String ss =	"The Atomic Number of this Element is: " + Elements.get(x-1).getAtomicNumber();
					String sss = "The Molecular Weight of this Element is: " + Elements.get(x-1).getMolecularWeight();
					
					JLabel label = new JLabel();
					label.setText("<html>" + s + "<br>" + ss + "<br>" + sss + "<html>");
						
					newPanel.add(label);
					newFrame.getContentPane().add(newPanel);
					} catch (Exception ex) {
						JFrame newFrame = new JFrame();
						newFrame.setBounds(600, 50, 300, 300);
						newFrame.setVisible(true);
						
						JPanel newPanel = new JPanel();
						
						String s = "The number you searched for was out of range!"; 
						JLabel label = new JLabel();
						label.setText("<html>" + s + "<html>");
						
						newPanel.add(label);
						newFrame.getContentPane().add(newPanel);
					}
				}
			});
	}
	
	//Gives function to the Instructions button
	public void addInstructions(JButton button)
	{
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				
				JFrame newFrame = new JFrame();
				newFrame.setBounds(600, 50, 350, 300);
				newFrame.setVisible(true);
				
				JPanel newPanel = new JPanel();
								
				String s = "Search for Elements by their Atomic Number (1 - 112)";
				String ss =	"to get their information, or click on an Element's ";
				String sss = "button to get that Element's information.";
				
				JLabel label = new JLabel();
				label.setText("<html>" + s + "<br>" + ss + "<br>" + sss + "<html>");
					
				newPanel.add(label);
				newFrame.getContentPane().add(newPanel);
			}
		});
	}
	
	//Colors buttons based on what type of element they hold.
	public void colorButtons()
	{
		for(int i = 1; i < 113; i++)
		{
			if(Elements.get(i - 1) instanceof AlkiliMetal)
			{
				Buttons.get(i).setBackground(Color.orange);
			}
			else if(Elements.get(i - 1) instanceof AlkilineEarth)
			{
				Buttons.get(i).setBackground(Color.yellow);
			}
			else if(Elements.get(i - 1) instanceof TransitionMetal)
			{
				Buttons.get(i).setBackground(Color.white);
			}
			else if(Elements.get(i - 1) instanceof SemiMetal)
			{
				Buttons.get(i).setBackground(Color.green);
			}
			else if(Elements.get(i - 1) instanceof NonMetal)
			{
				Buttons.get(i).setBackground(Color.cyan);
			}
			else if(Elements.get(i - 1) instanceof Halogen)
			{
				Buttons.get(i).setBackground(Color.magenta);
			}
			else if(Elements.get(i - 1) instanceof NobleGas)
			{
				Buttons.get(i).setBackground(Color.pink);
			}
			else if(Elements.get(i - 1) instanceof RareEarth)
			{
				Buttons.get(i).setBackground(Color.lightGray);
			}
			
		}
	}
}
