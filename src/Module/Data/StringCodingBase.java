package Module.Data;

/**
 * 字符串加密接口
 * @author 于风
 * @version 1.0
 * @updated 11-三月-2011 17:03:12
 */
public interface StringCodingBase {

	/**
	 * 解密字符串
	 * 
	 * @param source
	 * @exception nbsp ;Exception&nbsp;
	 * <!-- end-UML-doc -->
	 * @exception Exception
	 */
	public String deString(String source)
	  throws Exception;

	/**
	 * 加密字符串
	 * 
	 * @param source
	 * @exception nbsp ;Exception&nbsp;
	 * <!-- end-UML-doc -->
	 * @exception Exception
	 */
	public String enString(String source)
	  throws Exception;

	/**
	 * 获取加密信息
	 */
	public String getKeyType();

}
