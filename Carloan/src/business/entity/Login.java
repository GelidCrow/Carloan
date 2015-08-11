package business.entity;



public class Login extends Entity{
	private String username;
	private String password; 
	private String operatore;
	
	
	
	public Login(String username, String password, String operatore) {
		this.username = username;
		this.password = password;
		this.operatore = operatore;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getOperatore() {
		return operatore;
	}

	public void setOperatore(String operatore) {
		this.operatore = operatore;
	}
}
