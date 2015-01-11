package model;


public class NumeroTelephone {
	
	private String telNum;
	
	public NumeroTelephone (String telNum) {
		this.telNum = telNum;
	}
	public String getTelNum(){
		return this.telNum;
	}
	
	public String toString () {
		return this.telNum;
	}
	public boolean equals(NumeroTelephone t){
		return this.telNum.equals(t.getTelNum());
	}

}
