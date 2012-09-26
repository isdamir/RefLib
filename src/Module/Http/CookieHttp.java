package Module.Http;

import java.net.Authenticator;
import java.util.Properties;

import Module.Config.Config;
import Module.Logic.Tool.SysData;

/**
 * 专为方便使用设计 使用普通方法访问就自动区分是否急速
 * 并且自动支持Cookie
 * @author yufeng
 * 
 */
public class CookieHttp extends nlHttp {
	
	public CookieHttp() {
		setProxy();
	}

	@Override
	public boolean getData(String url, String[] result) {
		return getData(url, !SysData.isSpeed(), result);
	}

	@Override
	public boolean getData(String url, String filed, String[] result) {
		return getData(url, filed, !SysData.isSpeed(), result);
	}

	@Override
	public void setProxy() {
		if (Config.getInt("proxySelect", 0) != 0) {
			Properties prop = System.getProperties();
			prop.setProperty("http.proxyHost", Config.getString("proxyIp","10.0.0.172"));
			prop.setProperty("http.proxyPort", Config.getString("proxyPort","80"));
			if (Config.getBoolean("proxyAuth", false)) {
				Authenticator.setDefault(new MyAuthenticator(Config.getString("proxyUser"), Config.getString("proxyPwd")));
			}
		}
	}
}
