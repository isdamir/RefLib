package Module.Http;

import java.io.File;
import java.io.IOException;

/**
 * Http访问接口
 * @author YuFeng
 * @version 1.0
 * @created 13-四月-2011 8:31:57
 */
public interface HttpI {

	/**
	 * 下载数据到指定的文件名(GET)
	 * 
	 * @param url
	 * @param filename
	 * @throws IOException 
	 */
	public boolean downData(String url, String filename) throws IOException;

	/**
	 * 下载数据到指定的文件(GET)
	 * 
	 * @param url
	 * @param file
	 * @throws IOException 
	 */
	public boolean downData(String url, File file) throws IOException;

	/**
	 * 下载数据到指定的文件名(POST)
	 * 
	 * @param url
	 * @param filed
	 * @param filename
	 * @throws IOException 
	 */
	public boolean downData(String url, String filed, String filename) throws IOException;

	/**
	 * 下载数据到指定的文件(POST)
	 * 
	 * @param url
	 * @param filed
	 * @param file
	 * @throws IOException 
	 */
	public boolean downData(String url, String filed, File file) throws IOException;

	/**
	 * GET获取数据
	 * 
	 * @param url
	 * @throws IOException 
	 */
	public boolean getData(String url,String[] result);

	/**
	 * GET获取数据,标志为false则不返回数据
	 * 
	 * @param url
	 * @param ret
	 * @throws IOException 
	 */
	public boolean getData(String url, Boolean ret,String[] result);

	/**
	 * POST获取数据
	 * 
	 * @param url
	 * @param filed
	 * @throws IOException 
	 */
	public boolean getData(String url, String filed,String[] result);

	/**
	 * POST获取数据,标志为false则不返回数据
	 * 
	 * @param url
	 * @param filed
	 * @param ret
	 * @throws IOException 
	 */
	public boolean getData(String url, String filed, Boolean ret,String[] result);

	/**
	 * 获取regex制定的信息,找到则返回true,如果不是正则,则搜索字符串
	 * 
	 * @param url
	 * @param regex
	 * @throws IOException 
	 */
	public boolean getFindRegexData(String url, String regex) throws IOException;

	/**
	 * 获取regex制定的信息,找到则返回true,如果不是正则,则搜索字符串
	 * 
	 * @param url
	 * @param filed
	 * @param regex
	 * @throws IOException 
	 */
	public boolean getFindRegexData(String url, String filed, String regex) throws IOException;

	/**
	 * 获取regex指定的数据..如果是字符串,,返回从开始到找到位置的信息
	 * 
	 * @param url
	 * @param regex
	 * @throws IOException 
	 */
	public boolean getRegexData(String url, String regex,String[] result);

	/**
	 * 获取regex指定的数据..如果是字符串,,返回从开始到找到位置的信息
	 * 
	 * @param url
	 * @param filed
	 * @param regex
	 * @throws IOException 
	 */
	public boolean getRegexData(String url, String filed, String regex,String[] result);

	/**
	 * 设置代理
	 * 
	 * @param ip
	 */
	public void setProxy(String ip);
	/**
	 * 设置代理
	 * 
	 * @param ip
	 */
	public void setProxy();

}
