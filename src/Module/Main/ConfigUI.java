package Module.Main;

import java.awt.Choice;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Module.Config.Config;
import Module.Logic.Tool.NumOnlyDocument;
import Module.Logic.Tool.SysData;
import Module.UI.UserInterfaceBase;

public class ConfigUI extends UserInterfaceBase{

	JCheckBox errCheckBox;
	JCheckBox logCheckBox;
	JCheckBox speedCheckBox;
	JCheckBox nowRunCheckBox;
	JFrame sup;
	Choice proxyChoice;
	JTextField proxyIpField;
	JTextField proxyPortField;
	JTextField threadField;
	JTextField proxyUserField;
	JPasswordField proxyPwdFiled;
	JButton saveButton;
	JCheckBox proxyAuthBox;
	JRadioButton logXmlJRadioButton,logTxtJRadioButton;
	JTextField logSizeFiled;
	
	public ConfigUI(JFrame sup) {
		this.sup=sup;
	}

	@Override
	protected void initGui() {
		gui=new JFrame("配置程序");
		try {
			gui.setIconImage(SysData.getSelfImageIcon().getImage());
		} catch (Exception e) {
		}
		proxyChoice = new Choice();
		logCheckBox = new JCheckBox("日志记录");
		speedCheckBox = new JCheckBox("高速模式");
		nowRunCheckBox = new JCheckBox("立即任务");
		proxyAuthBox=new JCheckBox("登陆代理");
		saveButton = new JButton("保存配置");
		proxyIpField=new JTextField(6);
		proxyPortField=new JTextField(3);
		proxyPortField.setDocument(new NumOnlyDocument());
		proxyUserField=new JTextField(6);
		proxyPwdFiled=new JPasswordField(6);
		proxyPwdFiled.setEchoChar('*');
		threadField = new JTextField(2);
		threadField.setDocument(new NumOnlyDocument());
		logXmlJRadioButton=new JRadioButton("记录为XML");
		logTxtJRadioButton=new JRadioButton("记录为TXT");
		logSizeFiled=new JTextField(4);
		logSizeFiled.setDocument(new NumOnlyDocument());
		JPanel proxyJPanel = new JPanel();
		JPanel otherJPanel = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		proxyChoice.add("不使用代理");
		proxyChoice.add("移动(CMWAP)");
		proxyChoice.add("自定义代理");
		p1.add(new JLabel("代理选择"));
		p1.add(proxyChoice);
		p2.add(new JLabel("地址"));
		p2.add(proxyIpField);
		p2.add(new JLabel("端口:"));
		p2.add(proxyPortField);
		JPanel p4=new JPanel();
		p4.add(new JLabel("帐号:"));
		p4.add(proxyUserField);
		p4.add(new JLabel("密码:"));
		p4.add(proxyPwdFiled);
		p3.add(new JLabel("线程数量"));
		p3.add(threadField);
		proxyJPanel.setLayout(new GridLayout(4, 1));
		otherJPanel.setLayout(new GridLayout(2, 2));
		proxyJPanel.add(p1);
		proxyJPanel.add(p2);
		proxyJPanel.add(proxyAuthBox);
		proxyJPanel.add(p4);
		otherJPanel.add(logCheckBox);
		otherJPanel.add(nowRunCheckBox);
		otherJPanel.add(speedCheckBox);
		otherJPanel.add(p3);
		ButtonGroup bg=new ButtonGroup();
		bg.add(logXmlJRadioButton);
		bg.add(logTxtJRadioButton);
		JPanel logPanel=new JPanel();
		logPanel.setLayout(new GridLayout(2,2));
		logPanel.add(logTxtJRadioButton);
		logPanel.add(logXmlJRadioButton);
		JPanel pp2=new JPanel();
		pp2.add(new JLabel("记录大小:"));
		pp2.add(logSizeFiled);
		pp2.add(new JLabel("KB"));
		logPanel.add(pp2);
		logPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.blue), "日志设置"));
		proxyJPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.blue), "代理设置"));
		otherJPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.blue), "高级设置"));
		JPanel po=new JPanel();
		po=new JPanel();
		po.setLayout(new GridLayout(2,1));
		po.add(logPanel);
		po.add(otherJPanel);
		logXmlJRadioButton.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if(logXmlJRadioButton.isSelected())
				{
					Config.setValue("isTxt", false);
				}
			}
		});
		logTxtJRadioButton.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				Config.setValue("isTxt", true);
				
			}
		});
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Config.setValue("proxySelect", proxyChoice.getSelectedIndex());
				Config.setValue("log", logCheckBox.isSelected());
				Config.setValue("speed", speedCheckBox.isSelected());
				Config.setValue("nowRun", nowRunCheckBox.isSelected());
				Config.setValue("thread", threadField.getText());
				Config.setValue("proxyIp", proxyIpField.getText());
				Config.setValue("proxyPort", proxyPortField.getText());
				Config.setValue("proxyAuth", proxyAuthBox.isSelected());
				Config.setValue("proxyUser", proxyUserField.getText());
				Config.setValue("proxyPwd", new String(proxyPwdFiled.getPassword()));
				Config.setValue("logSize", logSizeFiled.getText());
				Config.saveConfig();
				SysData.setConfig();
				hide();
			}
		});
		proxyAuthBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(proxyAuthBox.isSelected())
				{
					proxyUserField.setEditable(true);
					proxyPwdFiled.setEditable(true);
				}else{
					proxyUserField.setEditable(false);
					proxyPwdFiled.setEditable(false);
				}
				
			}
		});
		proxyChoice.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				switch (proxyChoice.getSelectedIndex()) {
				case 0:
					proxyIpField.setText("");
					proxyPortField.setText("");
					proxyIpField.setEditable(false);
					proxyPortField.setEditable(false);
					break;
				case 1:
					proxyIpField.setText("10.0.0.172");
					proxyPortField.setText("80");
					proxyIpField.setEditable(false);
					proxyPortField.setEditable(false);
					break;
				case 2:
					proxyIpField.setText(Config.getString("proxyIp", ""));
					proxyPortField.setText(Config.getString("proxyPort", ""));
					proxyIpField.setEditable(true);
					proxyPortField.setEditable(true);
					break;
				default:
					break;
				}
			}
		});
		gui.add("North",proxyJPanel);
		gui.add("Center",po);
		gui.add("South",saveButton);
		gui.pack();
		gui.setLocation(sup.getX(), sup.getY());
	}

	@Override
	protected void initData() {
		int proxyselect = Config.getInt("proxySelect", 0);
		proxyChoice.select(proxyselect);
		proxyIpField.setEditable(false);
		proxyPortField.setEditable(false);
		proxyUserField.setText(Config.getString("proxyUser",""));
		proxyPwdFiled.setText(Config.getString("proxyPwd",""));
		proxyAuthBox.setSelected(Config.getBoolean("proxyAuth",false));
		proxyUserField.setEditable(false);
		proxyPwdFiled.setEditable(false);
		if(proxyAuthBox.isSelected())
		{
			proxyUserField.setEditable(true);
			proxyPwdFiled.setEditable(true);
		}
		if (proxyselect == 2) {
			proxyIpField.setEditable(true);
			proxyPortField.setEditable(true);
			proxyIpField.setText(Config.getString("proxyIp", ""));
			proxyPortField.setText(Config.getString("proxyPort", ""));
		} else if (proxyselect == 1) {
			proxyIpField.setText("10.0.0.172");
			proxyPortField.setText("80");
		}
		//加载配置
		logCheckBox.setSelected(Config.getBoolean("log"));
		speedCheckBox.setSelected(Config.getBoolean("speed"));
		nowRunCheckBox.setSelected(Config.getBoolean("nowRun"));
		threadField.setText(Config.getString("thread", "1"));
		logSizeFiled.setText(Config.getString("logSize","50000"));
		if(Config.getBoolean("isTxt",true))
		{
			logTxtJRadioButton.setSelected(true);
		}else{
			logXmlJRadioButton.setSelected(false);
		}
	}

	
}
