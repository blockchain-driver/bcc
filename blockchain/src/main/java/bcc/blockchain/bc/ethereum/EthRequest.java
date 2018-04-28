package bcc.blockchain.bc.ethereum;

import java.util.LinkedList;
import java.util.List;

public class EthRequest {
	private String jsonrpc = "2.0";
	private String method;
	//private List<String> params = new LinkedList<>();
	// must be List<Object> to pass boolean, e.g. "params":["0x0",true]
	private List<Object> params = new LinkedList<>();
	private String id;
	
//	public boolean addParam(String param) {
//		return params.add(param);
//	}
	public boolean addParam(Object param) {
		return params.add(param);
	}
	
	//public static final BCRequest eth_blockNumber
	
	// --- Getters and Setters ---
	public String getJsonrpc() {
		return jsonrpc;
	}
	public void setJsonrpc(String jsonrpc) {
		this.jsonrpc = jsonrpc;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public List<Object> getParams() {
		return params;
	}
//	public void setParams(List<String> params) {
//		this.params = params;
//	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "EthRequest [jsonrpc=" + jsonrpc + ", method=" + method + ", params=" + params + ", id=" + id + "]";
	}
}
