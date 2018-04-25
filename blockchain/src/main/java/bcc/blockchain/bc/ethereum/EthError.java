package bcc.blockchain.bc.ethereum;

public class EthError {
	private Integer code;
	private String  message;
	
	public EthError() {
		code = 0;
		message = "";
	}
	
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	@Override
	public String toString() {
	return "BCError{ code=" + code + ", message="+message + "}";
	}
	
}
