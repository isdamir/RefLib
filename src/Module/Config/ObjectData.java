package Module.Config;

import java.util.HashMap;

public class ObjectData {
	static HashMap<String, Object> data = new HashMap<String, Object>();
	public static void SaveObject(String key,Object obj)
	{
		data.put(key, obj);
	}
	public static Object GetObjetc(String Key)
	{
		return data.get(Key);
	}
}
