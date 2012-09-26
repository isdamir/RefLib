package Module.Data;

import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;

/**
 * DES实现
 * @author 于风
 * @version 1.0
 * @updated 11-三月-2011 17:03:12
 */
public class StringCodingDES implements StringCodingBase {

	private Cipher decryptCipher = null;
	private Cipher encryptCipher = null;
	public String keyType = "DES";

	public StringCodingDES(String strKey) throws Exception {
		// begin-user-code
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		Key key = getKey(strKey.getBytes());

		encryptCipher = Cipher.getInstance("DES");
		encryptCipher.init(Cipher.ENCRYPT_MODE, key);

		decryptCipher = Cipher.getInstance("DES");
		decryptCipher.init(Cipher.DECRYPT_MODE, key);
		// end-user-code
	}

	/**
	 * 解密字符串
	 * 
	 * @param source
	 * @exception Exception
	 */
	public String deString(String source) throws Exception {
		return new String(decrypt(hexStr2ByteArr(source)));
	}

	/**
	 * 加密字符串
	 * 
	 * @param source
	 * @exception Exception
	 */
	public String enString(String source) throws Exception {
		return byteArr2HexStr(encrypt(source.getBytes()));
	}

	/**
	 * 获取加密信息
	 */
	public String getKeyType() {
		return keyType;
	}

	private String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	public byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	public byte[] encrypt(byte[] arrB) throws Exception {
		return encryptCipher.doFinal(arrB);
	}

	public byte[] decrypt(byte[] arrB) throws Exception {
		return decryptCipher.doFinal(arrB);
	}

	public String decrypt(String strIn) throws Exception {
		return new String(decrypt(hexStr2ByteArr(strIn)));
	}

	private Key getKey(byte[] arrBTmp) throws Exception {
		byte[] arrB = new byte[8];

		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}

		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");

		return key;
	}
}
