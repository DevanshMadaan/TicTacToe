package tictactoe;

public class Player {
	private String name;
	private char sym;
	
	public Player(String name1,char symbol) {
		setname(name1);
		setsymbol(symbol);
	}
	
	public void setname(String name1) {
		this.name=name1;
	}
	public void setsymbol(char symbol) {
		this.sym=symbol;
	}
	public String getname() {
		return name;
	}
	public char getsymbol() {
		return sym;
	}
}
