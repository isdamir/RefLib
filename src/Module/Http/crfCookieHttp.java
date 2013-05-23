/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Module.Http;

import Module.Config.Config;
import Module.Logic.Tool.SysData;
import java.net.Authenticator;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.Properties;

/**
 *
 * @author yufeng
 */
public final class crfCookieHttp extends nlHttp {

    public crfCookieHttp() {
        setProxy();
        initCookie();
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
            prop.setProperty("http.proxyHost", Config.getString("proxyIp", "10.0.0.172"));
            prop.setProperty("http.proxyPort", Config.getString("proxyPort", "80"));
            if (Config.getBoolean("proxyAuth", false)) {
                Authenticator.setDefault(new MyAuthenticator(Config.getString("proxyUser"), Config.getString("proxyPwd")));
            }
        }
    }
    CookieManager manager;
    public void initCookie() {
        manager = new CookieManager();
        CookieHandler.setDefault(manager);
        manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
    }
    public CookieManager GetManager()
    {
        return manager;
    }
}
