package engine;

import com.jogamp.newt.event.KeyEvent;

public class TextMod extends Engine{

	Text t;
	@Override
	protected void start() {
		// TODO Auto-generated method stub
		t = parent.addProperty(Text.class).set("Example text").set(100, 500);
		Engine.renderQueue.setLastInQueue(t);
	}

	@Override
	protected void fixedUpdate() {
		// TODO Auto-generated method stub
		if(Engine.input.getKey(KeyEvent.VK_Q))
			t.set("Pressing q");
		else if(Engine.input.getKey(KeyEvent.VK_E))
			t.set("Pressing e");
	}

}
