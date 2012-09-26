package Module.Logic.Tool;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NormalTool {
	/**
	 * 处理amp的存在，过滤掉。
	 * 
	 * @param old
	 * @return
	 */
	public static String Filteramp(String old) {
		return old.replace("amp", "");
	}

	/**
	 * 执行CMD命令,并返回String字符串
	 */
	public static String executeCmd(String strCmd) throws Exception {
		Process p = Runtime.getRuntime().exec("cmd /c " + strCmd);
		StringBuilder sbCmd = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				p.getInputStream()));
		String line;
		while ((line = br.readLine()) != null) {
			sbCmd.append(line + "\n");
		}
		return sbCmd.toString();
	}
}
