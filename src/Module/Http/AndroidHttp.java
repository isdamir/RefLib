package Module.Http;

public class AndroidHttp {

    public static crfNlHttp GetHttp() {
        crfNlHttp nlHttp = new crfNlHttp();
        String[] header = new String[]{"User-Agent: Mozilla/4.0 (compatible;Android;320x480)"};
        nlHttp.setHeader(header);
        return nlHttp;
    }

    public static crfCookieHttp GetCookieHttp() {
        crfCookieHttp nl = new crfCookieHttp();
        String[] header = new String[]{"User-Agent: Mozilla/4.0 (compatible;Android;320x480)"};
        nl.setHeader(header);
        return nl;
    }
}
