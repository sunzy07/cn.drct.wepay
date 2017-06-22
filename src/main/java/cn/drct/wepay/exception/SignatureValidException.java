package cn.drct.wepay.exception;

public class SignatureValidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1832568039667340370L;

	public SignatureValidException(String code){
		super(code);
	}
}
