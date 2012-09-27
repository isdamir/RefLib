package Module.Http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author YuFeng
 * @version 1.0
 * @created 13-四月-2011 8:31:57
 */
public class CookieHttp implements HttpI {

    byte buffer[] = new byte[1024];
    int ReadTimeout = 10000;
    int ConnectTimeout = 10000;
    String[] Header;
    String Cookie;

    /**
     * 下载数据到指定的文件名(GET)
     *
     * @param url
     * @param filename
     * @throws IOException
     */
    @Override
    public boolean downData(String url, String filename) throws IOException {
        return downData(url, new File(filename));
    }

    void initHeader(URLConnection uc) {
        if (Header == null) {
            return;
        }
        for (String s : Header) {
            String[] k = s.split(":");
            uc.setRequestProperty(k[0], k[1]);
        }
        if (Cookie != null && !Cookie.equals("")) {
            uc.setRequestProperty("Cookie", Cookie);
        }
    }

    void getCookie(URLConnection uc) {
        Map<String,List<String>> k=uc.getHeaderFields();
        String s = k.get("Set-Cookie").toString();
        if (s != null && !s.equals("")) {
            Cookie = s;
        }
    }

    public String[] getHeader() {
        return Header;
    }

    public void setHeader(String[] header) {
        Header = header;
    }

    /**
     * 下载数据到指定的文件(GET)
     *
     * @param url
     * @param file
     * @throws IOException
     */
    @Override
    public boolean downData(String url, File file) throws IOException {
        URL Url = new URL(url);
        URLConnection Uc = Url.openConnection();
        initHeader(Uc);
        Uc.setReadTimeout(ReadTimeout);
        Uc.setConnectTimeout(ConnectTimeout);
        getCookie(Uc);
        InputStream is = Uc.getInputStream();
        int ch;
        try {
            FileOutputStream os = new FileOutputStream(file);
            while ((ch = is.read(buffer, 0, 1024)) != -1) {
                os.write(buffer, 0, ch);
            }
            is.close();
            os.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 下载数据到指定的文件名(POST)
     *
     * @param url
     * @param filed
     * @param filename
     * @throws IOException
     */
    @Override
    public boolean downData(String url, String filed, String filename)
            throws IOException {
        return downData(url, filed, new File(filename));
    }

    /**
     * 下载数据到指定的文件(POST)
     *
     * @param url
     * @param filed
     * @param file
     * @throws IOException
     */
    @Override
    public boolean downData(String url, String filed, File file)
            throws IOException {
        URL Url = new URL(url);
        int ch;
        URLConnection Uc = Url.openConnection();
        initHeader(Uc);
        Uc.setReadTimeout(ReadTimeout);
        Uc.setConnectTimeout(ConnectTimeout);
        getCookie(Uc);
        Uc.setDoOutput(true);
        OutputStreamWriter out = new OutputStreamWriter(Uc.getOutputStream());
        out.write(filed);
        out.flush();
        out.close();
        InputStream dis = Uc.getInputStream();
        try {
            FileOutputStream os = new FileOutputStream(file);
            while ((ch = dis.read(buffer, 0, 1024)) != -1) {
                os.write(buffer, 0, ch);
            }
            dis.close();
            os.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * GET获取数据
     *
     * @param url
     * @throws IOException
     */
    @Override
    public boolean getData(String url, String[] result) {
        return getData(url, true, result);
    }

    /**
     * GET获取数据,标志为false则不返回数据
     *
     * @param url
     * @param ret
     * @throws IOException
     */
    @Override
    public boolean getData(String url, Boolean ret, String[] result) {
        try {
            URL Url = new URL(url);
            URLConnection Uc = Url.openConnection();
            initHeader(Uc);
            Uc.setReadTimeout(ReadTimeout);
            Uc.setConnectTimeout(ConnectTimeout);
            getCookie(Uc);
            if (ret) {
                InputStreamReader dis = new InputStreamReader(
                        Uc.getInputStream(), "UTF-8");
                StringBuilder input = new StringBuilder();
                int ch;
                while ((ch = dis.read()) != -1) {
                    input.append((char) ch);
                }
                dis.close();
                result[0] = input.toString();
            } else {
                InputStreamReader dis = new InputStreamReader(
                        Uc.getInputStream(), "UTF-8");
                dis.read();
                dis.close();
                result[0] = "ok";
            }
            return true;
        } catch (Exception e) {
            result[1] = e.getMessage();
            return false;
        }
    }

    /**
     * POST获取数据
     *
     * @param url
     * @param filed
     * @throws IOException
     */
    @Override
    public boolean getData(String url, String filed, String[] result) {
        return getData(url, filed, true, result);
    }

    /**
     * POST获取数据,标志为false则不返回数据
     *
     * @param url
     * @param filed
     * @param ret
     * @throws IOException
     */
    @Override
    public boolean getData(String url, String filed, Boolean ret,
            String[] result) {
        try {
            URL Url = new URL(url);
            URLConnection Uc = Url.openConnection();
            initHeader(Uc);
            Uc.setReadTimeout(ReadTimeout);
            Uc.setConnectTimeout(ConnectTimeout);
            getCookie(Uc);
            Uc.setDoOutput(true);
            OutputStreamWriter out = new OutputStreamWriter(
                    Uc.getOutputStream(), "UTF-8");
            out.write(filed);
            out.flush();
            out.close();
            if (ret) {
                InputStreamReader dis = new InputStreamReader(
                        Uc.getInputStream(), "UTF-8");
                StringBuilder input = new StringBuilder();
                int ch;
                while ((ch = dis.read()) != -1) {
                    input.append((char) ch);
                }
                dis.close();
                result[0] = input.toString();
            } else {
                InputStreamReader dis = new InputStreamReader(
                        Uc.getInputStream(), "UTF-8");
                dis.read();
                dis.close();
                result[0] = "ok";
            }
            return true;
        } catch (Exception e) {
            result[1] = e.getMessage();
            return false;
        }
    }

    /**
     * 获取regex制定的信息,找到则返回true,如果不是正则,则搜索字符串
     *
     * @param url
     * @param regex
     * @throws IOException
     */
    @Override
    public boolean getFindRegexData(String url, String regex)
            throws IOException {
        Pattern p = null;
        int ch;
        Matcher m;
        boolean b = false, ret = false;
        try {
            p = Pattern.compile(regex);
            b = true;
        } catch (Exception e) {
        }
        URL Url = new URL(url);
        URLConnection Uc = Url.openConnection();
        initHeader(Uc);
        Uc.setReadTimeout(ReadTimeout);
        Uc.setConnectTimeout(ConnectTimeout);
        getCookie(Uc);
        InputStreamReader dis = new InputStreamReader(Uc.getInputStream(),
                "UTF-8");
        StringBuffer input = new StringBuffer();
        while ((ch = dis.read()) != -1) {
            if (b) {
                m = p.matcher(input);
                if (m.find()) {
                    ret = true;
                    break;
                }
            } else {
                if (input.indexOf(regex) != -1) {
                    ret = true;
                    break;
                }
            }
            input.append((char) ch);
        }
        dis.close();
        return ret;
    }

    /**
     * 获取regex制定的信息,找到则返回true,如果不是正则,则搜索字符串
     *
     * @param url
     * @param filed
     * @param regex
     * @throws IOException
     */
    @Override
    public boolean getFindRegexData(String url, String filed, String regex)
            throws IOException {
        Pattern p = null;
        int ch;
        Matcher m;
        boolean b = false, ret = false;
        try {
            p = Pattern.compile(regex);
            b = true;
        } catch (Exception e) {
        }
        URL Url = new URL(url);
        URLConnection Uc = Url.openConnection();
        initHeader(Uc);
        Uc.setReadTimeout(ReadTimeout);
        Uc.setConnectTimeout(ConnectTimeout);
        getCookie(Uc);
        Uc.setDoOutput(true);
        OutputStreamWriter out = new OutputStreamWriter(Uc.getOutputStream(),
                "UTF-8");
        out.write(filed);
        out.flush();
        out.close();
        InputStreamReader dis = new InputStreamReader(Uc.getInputStream(),
                "UTF-8");
        StringBuffer input = new StringBuffer();
        while ((ch = dis.read()) != -1) {
            if (b) {
                m = p.matcher(input);
                if (m.find()) {
                    ret = true;
                    break;
                }
            } else {
                if (input.indexOf(regex) != -1) {
                    ret = true;
                    break;
                }
            }
            input.append((char) ch);
        }
        dis.close();
        return ret;
    }

    /**
     * 获取regex指定的数据..如果是字符串,,返回从开始到找到位置的信息
     *
     * @param url
     * @param regex
     * @throws IOException
     */
    @Override
    public boolean getRegexData(String url, String regex, String[] result) {
        try {
            Pattern p = null;
            boolean b = false;
            int ch;
            Matcher m;
            try {
                p = Pattern.compile(regex);
                b = true;
            } catch (Exception e) {
            }
            URL Url = new URL(url);
            URLConnection Uc = Url.openConnection();
            initHeader(Uc);
            Uc.setReadTimeout(ReadTimeout);
            Uc.setConnectTimeout(ConnectTimeout);
            getCookie(Uc);
            InputStreamReader dis = new InputStreamReader(Uc.getInputStream(),
                    "UTF-8");
            StringBuffer input = new StringBuffer();
            while ((ch = dis.read()) != -1) {
                if (b) {
                    m = p.matcher(input);
                    if (m.find()) {
                        break;
                    }
                } else {
                    if (input.indexOf(regex) != -1) {
                        break;
                    }
                }
                input.append((char) ch);
            }
            dis.close();
            result[0] = input.toString();
            return true;
        } catch (Exception e) {
            result[1] = e.getMessage();
            return false;
        }
    }

    /**
     * 获取regex指定的数据..如果是字符串,,返回从开始到找到位置的信息
     *
     * @param url
     * @param filed
     * @param regex
     * @throws IOException
     */
    @Override
    public boolean getRegexData(String url, String filed, String regex,
            String[] result) {
        try {
            Pattern p = null;
            boolean b = false;
            int ch;
            Matcher m;
            try {
                p = Pattern.compile(regex);
                b = true;
            } catch (Exception e) {
            }
            URL Url = new URL(url);
            URLConnection Uc = Url.openConnection();
            initHeader(Uc);
            Uc.setReadTimeout(ReadTimeout);
            Uc.setConnectTimeout(ConnectTimeout);
            getCookie(Uc);
            Uc.setDoOutput(true);
            OutputStreamWriter out = new OutputStreamWriter(
                    Uc.getOutputStream(), "UTF-8");
            out.write(filed);
            out.flush();
            out.close();
            InputStreamReader dis = new InputStreamReader(Uc.getInputStream(),
                    "UTF-8");
            StringBuffer input = new StringBuffer();
            while ((ch = dis.read()) != -1) {
                if (b) {
                    m = p.matcher(input);
                    if (m.find()) {
                        break;
                    }
                } else {
                    if (input.indexOf(regex) != -1) {
                        break;
                    }
                }
                input.append((char) ch);
            }
            dis.close();
            result[0] = input.toString();
            return true;
        } catch (Exception e) {
            result[1] = e.getMessage();
            return false;
        }
    }

    /**
     * 设置代理
     *
     * @param ip
     */
    @Override
    public void setProxy(String ip) {
        Properties prop = System.getProperties();
        String[] s = ip.split(":");
        prop.setProperty("http.proxyHost", s[0]);
        prop.setProperty("http.proxyPort", s[1]);
    }

    @Override
    public void setProxy() {
    }
}
