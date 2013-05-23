package Module.Config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Set;

/**
 * @author YuFeng
 * @version 1.0
 * @created 13-四月-2011 8:31:57
 */
public class Config{
	static HashMap<String, String> data = null;
	static String fileName = null;
	/**
	 * 获取一个布尔值数
	 * 
	 * @param key
	 */
	public static boolean getBoolean(String key){
		return getBoolean(key, false);
	}

	/**
	 * 获取一个布尔值数据,不存在则返回默认
	 * 
	 * @param key
	 * @param _default
	 */
	public static boolean getBoolean(String key, boolean _default){
		if (data.containsKey(key)) {
			try {
				return Boolean.parseBoolean(data.get(key));
			} catch (Exception e) {
				return _default;
			}
		} else {
			return _default;
		}
	}

	/**
	 * 获取int值
	 * 
	 * @param key
	 */
	public static int getInt(String key){
		return getInt(key, -1);
	}

	/**
	 * 获取int值,不存在则返回设置值
	 * 
	 * @param key
	 * @param _default
	 */
	public static int getInt(String key, int _default){
		if (data.containsKey(key)) {
			try {
				return Integer.valueOf(data.get(key).toString());
			} catch (Exception e) {
				return _default;
			}
		} else {
			return _default;
		}
	}

	/**
	 * 获取string值
	 * 
	 * @param key
	 */
	public static String getString(String key){
		return getString(key, null);
	}

	/**
	 * 获取string值,不存在则返回设置值
	 * 
	 * @param key
	 * @param _default
	 */
	public static String getString(String key, String _default){
		if (data.containsKey(key)) {
			try {
				return data.get(key).toString();
			} catch (Exception e) {
				return _default;
			}
		} else {
			return _default;
		}
	}

	/**
	 * 加载配置文件,加载的文件信息将得到保留,方便于执行保存,也可以直接加载新的配置
	 * 
	 * @param c
	 */
	static  public  void loadConfig(String c){
		data = new HashMap<String, String>();
		fileName=c;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(c));
			String temp;
			String[] temps={"",""};
			int i;
			while ((temp = br.readLine()) != null) {
				try {

					if (temp.equals("")) {
						continue;
					}
					if (!temp.startsWith("#")) {
						i=temp.indexOf("=");
						if ( i== -1) {
							continue;
						} else {
							temps[0] = temp.substring(0,i);
							temps[1]= temp.substring(i+1,temp.length());
							if(temps[1]==null)
							{
								data.put(temps[0], "");
							}else{
								data.put(temps[0], temps[1]);
							}
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} catch (Exception e) {

		}
		if (br == null) {
			return;
		}
		try {
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 保存配置信息
	 */
	public static boolean saveConfig(){
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
			Set<String> set = data.keySet();
			bw.write("#作者:于风  Email:iscode@qq.com\r\n");
			for (String s : set) {
				bw.write(String.format("%s=%s\r\n", s, data.get(s)));
			}
			bw.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 设置一个值
	 * 
	 * @param key
	 * @param value
	 */
	public static void setValue(String key, Object value){
		data.put(key, value.toString());
	}

}
