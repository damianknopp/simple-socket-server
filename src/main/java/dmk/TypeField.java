package dmk;

public enum TypeField {
	TEXT(1), INT(2), UNK(0);
	private int type;
	private TypeField(int type){
		this.type = type;
	}
	
	public int getType(){
		return this.type;
	}
}
