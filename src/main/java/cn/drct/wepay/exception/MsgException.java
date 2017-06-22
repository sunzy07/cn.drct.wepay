package cn.drct.wepay.exception;

public class MsgException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1832568039667340370L;

	public MsgException(String code){
		super(code);
	}
}
