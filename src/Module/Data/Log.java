package Module.Data;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import Module.Config.Config;
import Module.Logic.Tool.SysData;

/**
 * @author YuFeng
 * @version 1.0
 * @created 13-四月-2011 8:31:57
 */
public class Log {
	static Logger lr = null;

	/**
	 * 记录运行日志
	 * 
	 * @param level
	 *            等级
	 * @param kk
	 *            唯一标示状态(sid)
	 * @param title
	 * @param body
	 */
	public static void logRun(Level level, String kk, String title, String body) {
		if (SysData.isLog()) {
			if (lr == null) {
				try {
					lr = Logger.getAnonymousLogger();
					if (Config.getBoolean("isTxt",true)) {
						SysData.getTfh().setFormatter(new SimpleFormatter());
						lr.addHandler(SysData.getTfh());
					} else {
						SysData.getXfh();
						lr.addHandler(SysData.getXfh());
					}
				} catch (Exception e) {
					return;
				}
			}
			try {
				lr.logp(level, kk, title, body);
			} catch (Exception e) {
			}
		}
	}

}
