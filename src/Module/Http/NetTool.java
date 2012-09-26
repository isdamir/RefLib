package Module.Http;

import java.util.regex.Pattern;

public class NetTool {
	public static String GetIp(){
		HttpI httpI=new crfNlHttp();
		String[] result=new String[]{"",""};
		if(httpI.getData("http://checkip.dyndns.org/", result))
		{
			Pattern pattern=Pattern.compile("(?<=Address ).+?(?=<)");
			java.util.regex.Matcher m=pattern.matcher(result[0]);
			if(m.find())
			{
				return m.group();
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
}
