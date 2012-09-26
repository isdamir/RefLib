package Module.Logic.Tool;

import java.io.File;
import java.io.FileInputStream;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.FileHandler;

import javax.swing.ImageIcon;
import javax.swing.filechooser.FileSystemView;

import Module.Config.Config;
import Module.Data.StringCodingDES;
import Module.Logic.LogicBase;

/**
 * 程序状态,全局的控制程序的运行状态
 * 
 * @author YuFeng
 * @version 1.0
 * @created 13-四月-2011 8:31:57
 */
public class SysData {

	/**
	 * 退出状态
	 */
	private static boolean exit;
	private static int[] taskState = { 0, 0, 0 };
	private static ExecutorService ex;
	private static FileHandler tfh;
	private static FileHandler xfh;
	private static  boolean tray=false;
	private static ImageIcon icon=null;
	public static  boolean isTray() {
		return tray;
	}

	public static  void setTray(boolean tr) {
		tray = tr;
	}

	public static ImageIcon getSelfImageIcon()
	{
		try {
			if(icon==null)
			{
				File file = new File(System.getProperty("java.class.path"));
				icon= (ImageIcon) FileSystemView.getFileSystemView()
				.getSystemIcon(file);
			}
			return icon;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String getRandomString(int length) {   
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";   
        Random random = new Random();   
        StringBuffer sb = new StringBuffer();   
        for (int i = 0; i < length; i++) {   
            int number = random.nextInt(base.length());   
            sb.append(base.charAt(number));   
        }   
        return sb.toString();   
    }   
	public static FileHandler getXfh() {
		if(xfh==null)
		{
			try {
				File f=new File("记录");
				if(!f.exists()||!f.isDirectory())
				{
					f.mkdir();
				}
				xfh = new FileHandler("记录/"+softName+".xml", Config.getInt("logSize",50000)*1024, 1, false);
			} catch (Exception e) {
				return null;
			}
		}
		return xfh;
	}

	public static void setXfh(FileHandler xfh) {
		SysData.xfh = xfh;
	}
	private static boolean isSpeed;
	private static String softName;
	private static String softVersion;

	public static String getSoftName() {
		return softName;
	}

	public static void setSoftName(String pluginName) {
		SysData.softName = pluginName;
	}

	public static String getSoftVersion() {
		return softVersion;
	}

	public static void setSoftVersion(String softVersion) {
		SysData.softVersion =softVersion;
	}

	public static boolean isSpeed() {
		return isSpeed;
	}

	public static void setSpeed(boolean isSpeed) {
		SysData.isSpeed = isSpeed;
	}

	public static int getThread() {
		return thread;
	}

	public static void setThread(int thread) {
		SysData.thread = thread;
	}

	public static boolean isLog() {
		return isLog;
	}

	public static void setLog(boolean isLog) {
		SysData.isLog = isLog;
	}

	private static int thread;
	private static boolean isLog;
	public static FileHandler getTfh() {
		if (tfh == null) {
			try {
				File f=new File("记录");
				if(!f.exists()||!f.isDirectory())
				{
					f.mkdir();
				}
				tfh = new FileHandler("记录/"+softName+".txt", Config.getInt("logSize",50000)*1024, 1, false);
			} catch (Exception e) {
				return null;
			}
		}
		return tfh;
	}

	public static void setTfh(FileHandler tfh) {
		SysData.tfh = tfh;
	}

	private static String crfHelp = "请联系开发商",
			crfAbout = "默认的备注信息";

	
	public static String getHelp() {
		return crfHelp;
	}

	public static void setHelp(String help) {
		SysData.crfHelp = help;
	}

	public static String getAbout() {
		return crfAbout;
	}

	public static void setAbout(String about) {
		SysData.crfAbout = about;
	}
	private static LogicBase logic;
	public static  LogicBase getLogic() {
		return logic;
	}

	public static  void setLogic(LogicBase logic) {
		SysData.logic = logic;
	}

	public static void DoExit() {
		try {
			Config.saveConfig();
		} catch (Exception e) {
		}
		if(logic!=null)
		{
			logic.close();
		}
		if (ex != null) {
			ex.shutdownNow();
		}
		if (tfh != null) {
			tfh.close();
		}
		if (tfh != null) {
			tfh.close();
		}
		System.exit(0);
	}

	public static ExecutorService getEx() {
		return ex;
	}

	public static void setEx(int x) {
		if (ex != null) {
			ex.shutdownNow();
		}
		SysData.ex = Executors.newFixedThreadPool(x);
	}

	public static int[] getTaskState() {
		return taskState;
	}

	public static void setTaskState(int[] taskState) {
		SysData.taskState = taskState;
	}

	public static void setSucceed() {
		synchronized (taskState) {
			taskState[1]++;
		}
	}

	public static void setSucceed(int i) {
		synchronized (taskState) {
			taskState[1] = i;
		}
	}

	public static int getSucceed() {
		return taskState[1];
	}

	public static void setFaild() {
		synchronized (taskState) {

			taskState[2]++;
		}
	}

	public static void setFaild(int i) {
		synchronized (taskState) {

			taskState[2] = i;
		}
	}

	public static int getFaild() {
		synchronized (taskState) {

			return taskState[2];
		}
	}

	public static void setSum(int i) {
		synchronized (taskState) {

			taskState[0] = i;
		}
	}

	public static void addSum() {
		synchronized (taskState) {

			taskState[0]++;
		}
	}

	public static void addSum(int i) {
		synchronized (taskState) {

			taskState[0] += i;
		}
	}

	public static int getSum() {
		synchronized (taskState) {

			return taskState[0];
		}
	}

	public static boolean isExit() {
		return exit;
	}

	/**
	 * 设置为退出状态
	 */
	public static void setExit() {
		exit = true;
	}
	public static void setExit(Boolean b)
	{
		exit=b;
	}

	/**
	 * 设置为非退出状态
	 */
	public static void noExit() {
		exit = false;
	}

	/**
	 * 设置config常用信息到这儿
	 */
	public static void setConfig() {
		setLog(Config.getBoolean("log",false));
		setSpeed(Config.getBoolean("speed",false));
		setThread(Config.getInt("thread", 1));
	}

}

class cload extends ClassLoader {
	StringCodingDES sc;

	public cload() {
		try {
			sc = new StringCodingDES("javafeng");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public cload(ClassLoader parent) {
		super(parent);
	}

	@SuppressWarnings("rawtypes")
	public Class load(String namefile, String classname)
			throws java.lang.Exception {
		Class ctmp = this.findLoadedClass(classname);
		if (ctmp != null) {
			return ctmp;
		}
		FileInputStream fi2 = new FileInputStream(new File(namefile));
		byte encryptedData[] = new byte[fi2.available()];
		fi2.read(encryptedData);
		fi2.close();
		byte decryptedData[] = sc.decrypt(encryptedData);
		return defineClass(classname, decryptedData, 0, decryptedData.length);
	}
}
