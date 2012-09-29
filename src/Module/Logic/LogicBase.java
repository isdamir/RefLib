package Module.Logic;

import java.util.logging.Level;

import Module.Config.Config;
import Module.Data.DataReader;
import Module.Data.DataWriter;
import Module.Data.Log;
import Module.Http.HttpI;
import Module.Logic.Tool.SysData;
import Module.UI.TipUI;
import Module.UI.UserInterface;

/**
 * 逻辑基类
 * 
 * @author YuFeng
 * @version 1.0
 * @created 13-四月-2011 8:31:13
 */
public abstract class LogicBase implements Runnable {
	/**
	 * 线程数量记录.同步
	 */
	private int th = 0;

	public int getTh() {
		return th;
	}

	public void setTh(int th) {
		synchronized (this) {

			this.th = th;
		}
	}

	protected HttpI hi;
	protected DataReader[] dr;
	protected DataWriter[] dw;
	protected TipUI tip;

	@Override
	public void run() {
		setTh(getTh()+1);
		logic();
		setTh(getTh()-1);
		if (th <= 0) {
			SysData.setExit();
			refreshTip();
			close();
		}
	}

	public void init(HttpI hi, TipUI tu, DataReader[] dr, DataWriter[] dw,
			UserInterface... ui) {
		initStart(ui);
		this.hi = hi;
		this.dr = dr;
		this.dw = dw;
		this.tip = tu;
		initEnd(ui);
	}
                
	public void close() {
		hi = null;
		if (dr != null) {
			for (DataReader i : dr) {
				i.close();
			}
		}
		if (dw != null) {
			for (DataWriter i : dw) {
				i.close();
			}
		}
		Config.setValue("start", SysData.getSucceed()+SysData.getFaild());
		closeOther();
	}

	public void log(Level l, String kk, String Title, String Body) {

		if (SysData.isLog()) {
			Log.logRun(l, kk, Title, Body);
		}
	}

	protected abstract void closeOther();

	protected abstract void logic();

	protected abstract void initStart(UserInterface... ui);

	protected abstract void initEnd(UserInterface... ui);

	protected abstract void refreshTip();
	public boolean Get(String url,String title,String sid,String[] result)
	{
		if(hi.getData(url, result))
		{
			log(Level.INFO, sid, String.format("\r\n[成功]\r\n标题:%s\r\n访问地址:%s", title,url), result[0]+"\r\n--------------------------------------------------------------\r\n\r\n");
			return true;
		}else{
			log(Level.WARNING, sid, String.format("\r\n[失败]\r\n标题:%s\r\n访问地址:%s", title,url), result[1]+"\r\n--------------------------------------------------------------\r\n\r\n");
			return false;
		}
	}
	public boolean Post(String url,String filed,String title,String sid,String[] result)
	{
		if(hi.getData(url,filed, result))
		{
			log(Level.INFO,sid, String.format("\r\n[成功]\r\n标题:%s\r\n访问地址:%s", title,url+"~"+filed), result[0]+"\r\n--------------------------------------------------------------\r\n\r\n");
			return true;
		}else{
			log(Level.WARNING, sid, String.format("\r\n[失败]\r\n标题:%s\r\n访问地址:%s", title,url+"~"+filed), result[1]+"\r\n--------------------------------------------------------------\r\n\r\n");
			return false;
		}
	}

}
