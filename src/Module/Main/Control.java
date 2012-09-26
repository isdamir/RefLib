package Module.Main;

import java.awt.AWTException;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Module.Config.Config;
import Module.Config.ObjectData;
import Module.Logic.Tool.SysData;

public abstract class Control implements ActionListener {
	protected String configFileName = "Config.cfg";
	MenuItem item1;
	MenuItem item2;
	MenuItem item3;
	public void init() {
		Config.loadConfig(configFileName);
		initOther();
		try {
			if (SystemTray.isSupported()) {
				SystemTray tray = SystemTray.getSystemTray();
				// 装载托盘图象
				Image image = SysData.getSelfImageIcon().getImage();
				// 为这个托盘加一个弹出菜单
				PopupMenu popup = new PopupMenu();
				 item1 = new MenuItem("关于");
				 item2 = new MenuItem("显示/隐藏");
				 item3 = new MenuItem("退出");
				item1.addActionListener(this);
				item2.addActionListener(this);
				item3.addActionListener(this);
				popup.add(item2);
				popup.add(item1);
				popup.add(item3);
				// 为这个托盘加一个提示信息
				TrayIcon trayIcon = new TrayIcon(image, SysData.getSoftName(), popup);
				trayIcon.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						JFrame jf=(JFrame) ObjectData.GetObjetc("MainUi");
						jf.setVisible(true);
						jf.setExtendedState(Frame.NORMAL);
					}
				});
				try {
					tray.add(trayIcon);
				} catch (AWTException e) {
					System.err.println("无法向这个托盘添加新项： " + e);
				}
				SysData.setTray(true);
			}
		} catch (Exception e) {
		}
		SysData.setConfig();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==item1)
		{
			JOptionPane.showMessageDialog(null, SysData.getAbout(), "关于作者",
					JOptionPane.PLAIN_MESSAGE);
		}else if(arg0.getSource()==item2)
		{
			JFrame jf=(JFrame) ObjectData.GetObjetc("MainUi");
			try {
				if(jf.isShowing())
				{
					jf.setVisible(false);
				}else{
					jf.setVisible(true);
					jf.setExtendedState(Frame.NORMAL);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else if(arg0.getSource()==item3)
		{
			System.exit(0);
			
		}
	}

	protected abstract void initOther();
}
