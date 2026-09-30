package blash.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener{

	public boolean dl, dr, du, dd;
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == e.VK_RIGHT) {
			dr = true;
		} else if(code == e.VK_LEFT) {
			dl = true;
		} else if(code == e.VK_UP) {
			du = true;
		} else if(code == e.VK_DOWN) {
			dd = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == e.VK_RIGHT) {
			dr = false;
		} else if(code == e.VK_LEFT) {
			dl = false;
		} else if(code == e.VK_UP) {
			du = false;
		} else if(code == e.VK_DOWN) {
			dd = false;
		}
	}

}
