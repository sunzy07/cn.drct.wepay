package cn.drct.wepay.exception;

public class TradeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4735172784989026754L;
	
	private String msg;
	
	public TradeException(String code,String msg){
		super(code);
		this.msg=msg;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
