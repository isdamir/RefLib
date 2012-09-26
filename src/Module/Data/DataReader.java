package Module.Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 数据读取
 * 
 * @author YuFeng
 * @version 1.0
 * @created 13-四月-2011 8:31:57
 */
public class DataReader {
	BufferedReader r;
	String fileName;
	boolean loop=false,isSum=true;

	public DataReader(String filename) throws FileNotFoundException {
       fileName=filename;
	   r=new BufferedReader(new FileReader(filename));
	}
	public void close() {
		try {
			r.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获得总数后总是会被初始化流
	 * @return
	 */
    public int getSum()
    {
    	int sum=0;
    	if(isSum)
    	{
    	while(read()!=null)
    	{
    		sum++;
    	}
    	reset();
    	isSum=false;
    	}
    	return sum;
    }
    public void skipSum(int i)
    {
    	for(int k=0;k<i;k++)
		{
			read();
		}
    }
	public String read() {
		return read(0);
	}

	/**
	 * 
	 * @param i
	 */
	public String read(int i) {
		String[] strings=readS();
		try {
			return strings[i];
		} catch (Exception e) {
			return null;
		}
	}

	public String[] readS() {
		if (r == null) {
			return null;
		}
		try {
			String qq = r.readLine();
			if(qq==null)
			{
				if(loop)
				{
					reset();
					qq=r.readLine();
				}else{
					return null;
				}
			}
			if(qq.indexOf("----")!=-1)
			{
				return qq.split("----");
			}
			if (qq.indexOf(",") == -1) {
				return new String[]{qq};
			} else {
				return qq.split(",");
			}
		} catch (Exception e) {
			return null;
		}
	}
	public boolean reset()
	{
		try {
			close();
			r=new BufferedReader(new FileReader(fileName));
			isSum=true;
			return true;
		} catch (FileNotFoundException e) {
			return false;
		}
	}
	public boolean isLoop() {
		return loop;
	}
	public void setLoop(boolean loop) {
		this.loop = loop;
	}

}
