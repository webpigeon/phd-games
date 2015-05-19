package uk.me.webpigeon.games.world.gui;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class DialogueWindow {
	private String MSG_FORMAT = "<%s> %s\n";
	
	private JComponent base;
	private JTextPane panel;
	private JComboBox<String> selection;
	private DefaultComboBoxModel<String> chatOptions;
	
	public DialogueWindow() {
		this.base = Box.createVerticalBox();
		this.panel = new JTextPane();
		this.panel.setPreferredSize(new Dimension(800, 200));
		base.add(new JScrollPane(panel));
		
		Box chatSelectionBox = Box.createHorizontalBox();
		chatOptions = new DefaultComboBoxModel<String>();
		chatOptions.addElement("Hello, I am a demo");
		chatOptions.addElement("How are you today?");
		this.selection = new JComboBox<String>(chatOptions);
		chatSelectionBox.add(selection);
		
		JButton sayBtn = new JButton("Say");
		sayBtn.addActionListener(new ChatListener(this));
		
		chatSelectionBox.add(sayBtn);
		base.add(chatSelectionBox);
	}
	
	public JComponent getComponent() {
		return base;
	}
	

	public String getMessage() {
		return (String)chatOptions.getSelectedItem();
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("JFrame sucks");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(800, 600));
		frame.add(new DialogueWindow().getComponent());
		frame.pack();
		frame.setVisible(true);
	}

	public void sayMessage(String who, String what) {
		String message = String.format(MSG_FORMAT, who, what);
		panel.setText(panel.getText()+message);
	}
	
}
