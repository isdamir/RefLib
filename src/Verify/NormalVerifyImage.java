package Verify;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Module.Config.Config;
import Module.Http.HttpI;
import Module.Http.QQMoblieHttp;
import Module.UI.UserInterface;

public class NormalVerifyImage implements UserInterface, VerifyImage {
	protected JFrame gui;
	protected boolean isOk = false, state = false, asyncState = false;
	JLabel label=new JLabel();
	JTextField textField=new JTextField(10);
    HttpI hi=QQMoblieHttp.GetHttp();
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
	public void show() {
		try {
			if (!isOk) {
				gui = new JFrame("验证码输入窗口");
				gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//
				gui.setLayout(new FlowLayout());
				gui.add(label);
				gui.add(textField);
				textField.addKeyListener(new KeyAdapter() {
					public void keyReleased(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							state = true;
						}
					}

				});
			}
			isOk = true;
			gui.pack();
			gui.setVisible(true);
			gui.setBounds(400, 400, 250, 180);
		} catch (Exception e) {
		}
	}

	@Override
	synchronized public String exec(String path) {
		Image i = new ImageIcon(path).getImage().getScaledInstance(200, 70,
				Image.SCALE_DEFAULT);
		i.flush();
		label.setIcon(new ImageIcon(i));
		while (!state) {
			Thread.yield();
		}
		if(!Config.getBoolean("isShow",true))
		{
			gui.setVisible(false);
		}
		state = false;
		String imgString = textField.getText();
		textField.setText("");
		return imgString;
	}

	@Override
	synchronized public String exec(File file) {
		return exec(file.getAbsolutePath());
	}
                    //下载验证码图片并要求输入
	synchronized public String DownLoadVerify(String url)
	{
		//下载到临时目录
		File dir=new File("tmp");
		if(!dir.exists())
		{
			dir.mkdir();
		}
		String path="tmp//"+ System.currentTimeMillis()+".gif";
		try {
			if(hi.downData(url, path))
			{
				if(gui==null||!gui.isShowing())show();
				String viString=exec(path);
				new File(path).delete();
				return viString;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public void restart() {
		gui.setVisible(true);
	}

}
