package uk.me.webpigeon.games.world.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatListener implements ActionListener {
	private DialogueWindow chat;
	
	public ChatListener(DialogueWindow chat) {
		this.chat = chat;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		chat.sayMessage("us", chat.getMessage());
	}

}
