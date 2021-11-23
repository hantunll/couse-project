public class PlayerGame {
	private int totalPoint;
	
	//
	private int twoPA , twoPM ,  threePA ,threePM ,  fairPA , fairPM ,  offReb , defReb , assist , steal , block , turnover , foul;
	private int gameID,playerNumber;
	
	
	/*
	 * a new constructor for the data record
	 */
	public PlayerGame(int gameID , int playerNumber , int twoPA , int twoPM , int threePA ,int threePM , int fairPA , int fairPM , int offReb , int defReb , int assist , int steal , int block , int turnover , int foul) {
		this.gameID = gameID;
		this.playerNumber = playerNumber;
		this.twoPA = twoPA;
		this.twoPM = twoPM;
		this.threePA = threePA;
		this.threePM = threePM;
		this.fairPA = fairPA;
		this.fairPM = fairPM;
		this.offReb = offReb;
		this.defReb = defReb;
		this.assist = assist;
		this.steal = steal;
		this.block = block;
		this.turnover = turnover;
		this.foul = foul;

	}
	/*
	 * get the game ID and get playerNUmber
	 */
	public int getGameID() {
		return this.gameID; 
	}
	
	public int getPlayernum() {
		return this.playerNumber;
	}
	
	
	/*
	 *  get data about a game
	 */
	public int getTwoPA() {
		return this.twoPA;
	}
	public int gettwoPM() {
		return this.twoPM;
	}
	public int getthreePA() {
		return this.threePA;
	}
	public int getthreePM() {
		return this.threePM;
	}
	public int getfairPA() {
		return this.fairPA;
	}
	public int getfairPM() {
		return this.fairPM;
	}
	public int getoffReb() {
		return this.offReb;
	}
	public int getdefReb() {
		return this.defReb;
	}
	public int getassist() {
		return this.assist;
	}
	public int getsteal() {
		return this.steal;
	}
	public int getblock() {
		return this.block;
	}
	public int getturnover() {
		return this.turnover;
	}
	public int getfoul() {
		return this.foul;
	}
	public int getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(int totalPoint) {
		this.totalPoint = totalPoint;
	}
}
