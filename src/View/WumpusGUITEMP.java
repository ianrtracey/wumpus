package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import models.Goop;

import java.awt.Font;


public class WumpusGUITEMP {
	
	int[][] myArray = new int[10][10];
	
	
	// ------------------------------------------|
	// ### GUI OBJ INSTANTIATION / MAIN METHOD   |
	// ------------------------------------------|
	public static void main(String[] args) {	
		WumpusGUITEMP myGUI = new WumpusGUITEMP(); // Create an instance
	}
	
	// ------------------------------------------|
	// ### GUI OBJECT DECLARATIONS               |
	// ------------------------------------------|
	JFrame myFrame = new JFrame(); // The JFrame for this GUI
	
	// The label objects and their containers
	JPanel moveLabelPanel = new JPanel();
	JPanel fireLabelPanel = new JPanel();
	JLabel moveLabel;
	JLabel fireLabel;
	
	// The containers for the two player controls, move and fire
	JPanel movePanel = new JPanel();
	JPanel firePanel = new JPanel();
	
	// The containers for the controls and view-ports
	JPanel moveAndFireContainer = new JPanel();
	JPanel viewAndTextContainer = new JPanel();
	
	// The images correspond to the buttons and are loaded / initialized via loadAndAssignImages()
	ImageIcon upArrow;
	ImageIcon downArrow;
	ImageIcon leftArrow;
	ImageIcon rightArrow;
	JButton moveUpButton;
	JButton moveDownButton;
	JButton moveLeftButton;
	JButton moveRightButton;
	JButton fireUpButton;
	JButton fireDownButton;
	JButton fireLeftButton;
	JButton fireRightButton;
	
	// Model view GUI Objects
	JTabbedPane myPane = new JTabbedPane();
	JTextArea myTextArea;
	
	

	// ------------------------------------------|
	// ### GUI OBJECT CONSTRUCTOR                |
	// ------------------------------------------|
	public WumpusGUITEMP() {
		this.loadAndAssignImages(); // Load and assign our image resources
		this.setUpGUI();            // Set up the rest of the GUI
		this.setUpListeners();      // Set up the listeners
		this.TEMPBUILDARRAY();
	}
	
	// ------------------------------------------|
	// ### GUI CONSTRUCTION AND RUNTIME METHODS  |
	// ------------------------------------------|	
	private void setUpGUI() {
		// Take care of the basic frame settings first
		myFrame.setResizable(false);   // Lock the frame, we don't want people resizing
		myFrame.setLocation(120, 120); // Set location to 120 down, 120 right
		myFrame.setTitle("Steve and Ian's Super Turbo Hunt the Wumpus III"); // fer teh lulz
		
		// Prepare labels instructing which arrow controls move player, fire weapon
		moveLabel = new JLabel("Move Player");
		fireLabel = new JLabel("Fire Arrow");
		moveLabel.setFont(new Font("Arial", Font.BOLD, 24));
		moveLabel.setForeground(Color.BLUE);
		fireLabel.setFont(new Font("Arial", Font.BOLD, 24));
		fireLabel.setForeground(Color.BLUE);
		moveLabelPanel.add(moveLabel);
		fireLabelPanel.add(fireLabel);
		
		// Prepare the Player Move Panel, add components
		movePanel.setLayout(new GridLayout(3,3));
		movePanel.add(new JPanel());
		movePanel.add(moveUpButton);
		movePanel.add(new JPanel());
		movePanel.add(moveLeftButton);
		movePanel.add(new JPanel());
		movePanel.add(moveRightButton);
		movePanel.add(new JPanel());
		movePanel.add(moveDownButton);
		movePanel.add(new JPanel());
		
		// Prepare the Weapon Fire Panel, add components
		firePanel.setLayout(new GridLayout(3,3));
		firePanel.add(new JPanel());
		firePanel.add(fireUpButton);
		firePanel.add(new JPanel());
		firePanel.add(fireLeftButton);
		firePanel.add(new JPanel());
		firePanel.add(fireRightButton);
		firePanel.add(new JPanel());
		firePanel.add(fireDownButton);
		firePanel.add(new JPanel());
		
		// Prepare the left side container (move and fire controls / labels), add components
		moveAndFireContainer.setLayout(new BoxLayout(moveAndFireContainer, BoxLayout.Y_AXIS));
		moveAndFireContainer.setPreferredSize(new Dimension(240,600));
		moveAndFireContainer.add(moveLabelPanel);
		moveAndFireContainer.add(movePanel);
		moveAndFireContainer.add(fireLabelPanel);
		moveAndFireContainer.add(firePanel);
		
		// Prepare the TextArea view
		myTextArea = new JTextArea(100, 100);
		myTextArea.setEditable(false);
		myTextArea.setFont(new Font("Arial", Font.BOLD, 16));
		myTextArea.setForeground(Color.WHITE);
		myTextArea.setBackground(Color.BLUE);
		this.TEMPBUILDARRAY();
		
		for (int i = 0; i < myArray.length; i++) {
			for (int j = 0; j < myArray.length; j++) {
				myTextArea.append(" [ " + myArray[i][j] + " ] ");
				
			}
			myTextArea.append("\n\n");
		}
		viewAndTextContainer.setLayout(new BoxLayout(viewAndTextContainer, BoxLayout.Y_AXIS));
		viewAndTextContainer.setPreferredSize(new Dimension(350,600));
		viewAndTextContainer.add(myTextArea);
		
		
		
		
		
		
		
		
		
		// Add the two left/right side containers to the frame
		myFrame.getContentPane().add(BorderLayout.WEST, moveAndFireContainer);
		myFrame.getContentPane().add(BorderLayout.EAST, viewAndTextContainer);
		myFrame.setSize(600, 600);
	
		// Lastly, let's make sure we can see it!
		myFrame.setVisible(true);
		
	} // Ends Method setUpGUI

	private void loadAndAssignImages() {
		
		ImageIcon temp[] = new ImageIcon[4];
		temp[0] = upArrow = new ImageIcon("WumpArrowsSimple/Up.jpg");
		temp[1] = downArrow = new ImageIcon("WumpArrowsSimple/Down.jpg");
		temp[2] = leftArrow = new ImageIcon("WumpArrowsSimple/Left.jpg");
		temp[3] = rightArrow = new ImageIcon("WumpArrowsSimple/Right.jpg");
		
		// My quick and dirty try-catch in case an ImageIcon image we're not to work...
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].getIconHeight() == -1){
				System.out.println(">>>ERROR - DID NOT FIND IMAGE: \"" + temp[i].toString() + "\" \n   Closing Program...");
				System.exit(1);
			}
		}
	
		moveUpButton    =  new JButton(upArrow);
		moveDownButton  =  new JButton(downArrow);
		moveLeftButton  =  new JButton(leftArrow);
		moveRightButton =  new JButton(rightArrow);
		fireUpButton    =  new JButton(upArrow);
		fireDownButton  =  new JButton(downArrow);
		fireLeftButton  =  new JButton(leftArrow);
		fireRightButton =  new JButton(rightArrow);
		
	} // Ends Method loadAndAssignImages



	private void setUpListeners() {
		// TODO Auto-generated method stub
		
	}



	
	
	
	
	public void TEMPBUILDARRAY(){
		for (int i = 0; i < myArray.length; i++) {
			for (int j = 0; j < myArray.length; j++) {
				myArray[i][j] = 6;
			}
		}
	}
	
	

}