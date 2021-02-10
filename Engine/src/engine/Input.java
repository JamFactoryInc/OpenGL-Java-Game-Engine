package engine;

import java.util.Map;

import com.jogamp.newt.event.KeyEvent;

import java.util.HashMap;


public class Input implements java.awt.event.KeyListener
{

	public Map<Short, Boolean> keys = new HashMap<>();
	
	public Input() 
	{
		for(short s = 0; s < 256; s++)
			keys.put(s, false);
	}
	
	/**
	 * Gets if a key is being pressed. Formatted:<p><code>input.getKey(KeyEvent.VK_A)</code><p>Where VK_A is the keycode for the 'A' key.
	 * @param s Keycode
	 * @return if the key is being pressed
	 */
	public boolean getKey(short s) 
	{
		return keys.get(s);

	}

	@Override
	public void keyTyped(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println(KeyEvent.VK_WINDOWS);
	}

	@Override
	public void keyPressed(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub
		keys.put((short)e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub
		keys.put((short)e.getKeyCode(), false);
	}
	
}
