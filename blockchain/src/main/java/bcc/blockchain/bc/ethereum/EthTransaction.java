package bcc.blockchain.bc.ethereum;

import bcc.blockchain.bc.BCTransaction;

public class EthTransaction {
	private String from;
	private String to;
	private String value;
	private String data;
	private String gas;
	private String gasPrice;
	//nonce: QUANTITY - (optional) Integer of a nonce. This allows to overwrite your own pending transactions that use the same nonce.
	private String nonce;
	
	public EthTransaction() {}
	public EthTransaction(BCTransaction tr) {
		from = tr.getFrom();
		to = tr.getTo();
		value = tr.getValue();
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getGas() {
		return gas;
	}
	public void setGas(String gas) {
		this.gas = gas;
	}
	public String getGasPrice() {
		return gasPrice;
	}
	public void setGasPrice(String gasPrice) {
		this.gasPrice = gasPrice;
	}
	
	@Override
	public String toString() {
		return "EthTransaction [from=" + from + ", to=" + to + ", value=" + value + ", data=" + data + ", gas=" + gas
				+ ", gasPrice=" + gasPrice + "]";
	}

}
