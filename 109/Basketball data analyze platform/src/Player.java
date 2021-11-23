
public class Player {
	private String name;
	private String depName;
	private String position;
	private int num;
	
	
	public Player(String name , String depName , String position , int num) {
		this.name = name;
		this.depName = depName;
		this.position = position;
		this.num = num;
	}

	public String getName() {
		return name;
	}
	
	public int getNum() {
		return num;
	}

	public String getPosition() {
		return position;
	}

	public String getDepName() {
		return depName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}
	
	public static int getDepNumber(String depname) {
		switch(depname) {
		case"Chinese":
			return 101;
		case"Education":
			return 102;
		case"History":
			return 103;	
		case"Philosophy":
			return 104;
		case"Politics":
			return 202;	
		case"Diplomacy":
			return 203;	
		case"Society":
			return 204;	
		case"Public Finance":
			return 205;	
		case"Public Administration":
			return 206;	
		case"Land Economics":
			return 207;	
		case"Economics":
			return 208;	
		case"Ethnology":
			return 209;	
		case"International Business":
			return 301;	
		case"Money and Banking":
			return 302;
		case"Accounting":
			return 303;
		case"Statistics":
			return 304;
		case"Business Administration":
			return 305;
		case"Management Information Systems":
			return 307;
		case"Finance":
			return 308;
		case"Risk Management and Insurance":
			return 309;			
		case"Communication":
			return 401;			
		case"English":
			return 501;			
		case"Arabric":
			return 502;		
		case"Slavic":
			return 504;			
		case"Japanese":
			return 506;			
		case"Korean":
			return 507;			
		case"Turkish":
			return 508;			
		case"European":
			return 509;			
		case"Southeast Asian":
			return 509;			
		case"Mathematical Sciences":
			return 509;					
		case"Psychology":
			return 509;		
		case"Computer Science":
			return 703;				
			
		}
		return 0;
		
	}


}
