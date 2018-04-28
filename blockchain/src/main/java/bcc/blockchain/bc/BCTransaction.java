package bcc.blockchain.bc;

/**
 * General blockchain transaction.
 * 
 * Transaction in any blockchain is a line in ledger:
 * <code>from</code> some account, <code>to</code> some account, and what <code>value</code>
 *
 */
public class BCTransaction {
	
	private String from;
	private String to;
	private String value;
	
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
	@Override
	public String toString() {
		return "BCTransaction [from=" + from + ", to=" + to + ", value=" + value + "]";
	}
	
	

}
