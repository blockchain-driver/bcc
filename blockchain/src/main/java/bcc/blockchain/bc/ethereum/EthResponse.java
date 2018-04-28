package bcc.blockchain.bc.ethereum;

// result as String
public class EthResponse {
	private String jsonrpc = "2.0";
	private String id;
	private String result;
	private EthError error;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJsonrpc() {
		return jsonrpc;
	}
	public void setJsonrpc(String jsonrpc) {
		this.jsonrpc = jsonrpc;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public EthError getError() {
		return error;
	}
	public void setError(EthError error) {
		this.error = error;
	}
	@Override
	public String toString() {
		return "EthResponse [jsonrpc=" + jsonrpc + ", id=" + id + ", result=" + result + ", error=" + error + "]";
	}
	
	
}
