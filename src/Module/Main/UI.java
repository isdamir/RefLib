package Module.Main;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Module.Config.ObjectData;
import Module.Logic.Tool.SysData;
import Module.UI.UserInterfaceBase;

/**
 * 界面
 * 
 * @author YuFeng
 * @version 1.0
 * @created 13-四月-2011 8:31:35
 */
public abstract class UI extends UserInterfaceBase {
	Menu menu[] = null;
	MenuItem mit[] = null;
	JButton startButton;
	ConfigUI configUI;

	@Override
	protected void initGui() {
		gui = new JFrame();
		ObjectData.SaveObject("MainUi", gui);
		try {
			gui.setIconImage(SysData.getSelfImageIcon().getImage());
		} catch (Exception e) {
		}
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.addWindowListener(new WindowAdapter() {
			@Override
			public void windowIconified(WindowEvent arg0) {
				if(SysData.isTray())
				{
					gui.setVisible(false);
				}
			}
			
		});
		startButton = new JButton();
		menu = new Menu[] { new Menu(), new Menu() };
		mit = new MenuItem[] { new MenuItem(), new MenuItem(), new MenuItem(),
				new MenuItem() };
		MenuBar mb = new MenuBar();
		for (Menu m : menu) {
			mb.add(m);
		}
		menu[0].add(mit[0]);
		menu[0].add(mit[1]);
		menu[1].add(mit[2]);
		menu[1].add(mit[3]);
		mit[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SysData.DoExit();
			}
		});
		mit[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(gui, SysData.getHelp(), "软件帮助",
						JOptionPane.PLAIN_MESSAGE);
			}
		});
		mit[3].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(gui, SysData.getAbout(), "关于作者",
						JOptionPane.PLAIN_MESSAGE);
			}
		});
		mit[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (configUI == null) {
					configUI = new ConfigUI(gui);
				}
				configUI.show();
			}
		});
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startTask();
			}
		});
		initGuiStart();
		gui.setMenuBar(mb);
		gui.add("South", startButton);
		gui.pack();
		gui.setLocation(150, 100);
		initGuiEnd();
	}

	@Override
	protected void initData() {
		try {
			gui.setTitle(String.format("%s v%s", SysData.getSoftName(),
					SysData.getSoftVersion()));
			menu[0].setLabel("菜单");
			menu[1].setLabel("其他");
			mit[0].setLabel("配置");
			mit[1].setLabel("退出");
			mit[2].setLabel("帮助");
			mit[3].setLabel("关于");
			startButton.setText("开始任务");
			initDataOther();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected abstract void initGuiStart();

	protected abstract void initGuiEnd();

	protected abstract void initDataOther();

	protected abstract void startTask();

}
