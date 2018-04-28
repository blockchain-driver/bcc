package bcc.blockchain.bc;

public interface Blockchain {

	void setTraceHttpRequestResponce(boolean traceSetting);

	String saveTransaction(BCTransaction tr);

	BCTransaction readTransaction(String trid);


}
