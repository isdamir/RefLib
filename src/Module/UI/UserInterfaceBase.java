package Module.UI;

import javax.swing.JFrame;

import Module.Logic.Tool.SysData;

public abstract class UserInterfaceBase implements UserInterface {
	protected JFrame gui;
	protected boolean isOk = false;

	@Override
	public void disponse() {
		hide();
		gui.dispose();
		gui = null;
	}

	@Override
	public void hide() {
		try {
			gui.setVisible(false);
		} catch (Exception e) {
		}
	}

	@Override
	public void restart() {
		initData();
	}

	@Override
	public void show() {
		try {
			if (!isOk) {
				gui=new JFrame(SysData.getSoftName()+"  v"+SysData.getSoftVersion());
				gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				initGui();
				isOk = true;
			}
			initData();
			gui.setVisible(true);
		} catch (Exception e) {
		}
	}

	abstract protected void initGui();

	abstract protected void initData();

}
