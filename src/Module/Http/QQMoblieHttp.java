package Module.Http;

public class QQMoblieHttp {
	public static crfNlHttp GetHttp()
	{
		crfNlHttp nlHttp=new crfNlHttp();
		String[] header=new String[]{"User-Agent: MQQBrowser/2.9/Adr (Linux; U; 2.3.6; zh-cn; ME525+ Build/4.5.3-109_DPP-11_0.65_bbs.mfunz.com;480*854)","Q-UA: ADRQB29_GA/290220&AMTT_3/010220&ADR&305314&ME525+&0&6226&Android2.3.6&V3","Q-GUID: 00000000000000000000000000000000"};
		nlHttp.setHeader(header);
		return nlHttp;
	}

}
