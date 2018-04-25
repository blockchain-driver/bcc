package bcc.blockchain.bc;

public interface Blockchain {

	String saveTransaction(BCTransaction tr);

	BCTransaction readTransaction(String trid);

}
