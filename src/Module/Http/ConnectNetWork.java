package Module.Http;

import Module.Logic.Tool.NormalTool;
  
public class ConnectNetWork {  
  
  
    /** 
     * 连接ADSL 
     */  
    public static boolean connAdsl(String adslTitle, String adslName, String adslPass) throws Exception {  
        System.out.println("正在建立连接.");  
        String adslCmd = "rasdial " + adslTitle + " " + adslName + " "  
                + adslPass;  
        try {
            String tempCmd = NormalTool.executeCmd(adslCmd);  
            // 判断是否连接成功  
            if (tempCmd.indexOf("已连接") > 0) {  
                System.out.println("已成功建立连接.");  
                return true;  
            } else {  
                System.err.println(tempCmd);  
                System.err.println("建立连接失败");  
                return false;  
            }  
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }  
  
    /** 
     * 断开ADSL 
     */  
    public static boolean cutAdsl(String adslTitle) throws Exception {  
        String cutAdsl = "rasdial " + adslTitle + " /disconnect"; 
        	try{
                String result = NormalTool.executeCmd(cutAdsl);  
                
                if (result.indexOf("没有连接")!=-1){  
                    System.err.println(adslTitle + "连接不存在!");  
                    return false;  
                } else {  
                    System.out.println("连接已断开");  
                    return true;  
                } 
        		
        	} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
    }  
}  