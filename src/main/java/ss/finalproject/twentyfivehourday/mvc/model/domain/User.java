package ss.finalproject.twentyfivehourday.mvc.model.domain;

import javax.persistence.Id;

public class User {
	
    @Id
	private String id;
	private int[] preference;
	private String password;
	private String username;
	
	public User() {
		super();
	}
	
	public User(String id , String password , String username, int[] preference){
		this.id = id;
		this.password = password;
		this.username = username;
		this.preference = preference;
	}
	
	public String getId(){
		return this.id;
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public int[] getPreference(){
		return this.preference;
	}
	

	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setPassword(String password){
		this.password = password;	
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public void setPreference(int[] preference){
		this.preference = preference;
	}
	

	
	
	
}
