package ss.finalproject.twentyfivehourday.mvc.model.domain;


import javax.persistence.Id;


public class Activity {
	
	@Id
	private Long id; 
	private String title;
	private String content;
	private String founder; // the id of person who do this activity first
	private int[] characteristic;// five characteristic of an activity
	private int scoredTimes ;
	private double score;
	public long stamp;
	//private String[] whoDislike = {null};
	
	public Activity() {
		super();
	}
		
	public Activity(String title, String content, String founder, int[] characteristic){
		this.title = title;
		this.content = content;
		this.founder = founder;
		this.characteristic = characteristic;
	}
	
	public Long getId() {
			return id;
		}

	public void setId(Long id) {
			this.id = id;
		}
	
	public long getStamp() {
		return stamp;
	}

	public void setStamp(long stamp) {
		this.stamp = stamp;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent(){
		return content;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getFounder(){
		return founder;
	}
	public void setScoredTimes(int scoredtimes){
		this.scoredTimes = scoredtimes;
	}
	public int getScoredTimes(){
		return this.scoredTimes;
	}
	
	public void setFounder(String founder){
		this.founder = founder;
	}
	

	public void setScore(double score){
		this.score = score;
		
	}
	
	public double getScore(){
		return this.score;
	}
	
	public int getCharacteristicValueByIndex(int index){
		return characteristic[index];
	}
	
	public int[] getCharacteristic(){
		return this.characteristic;
	}
	
	public void setCharacteristic(int[] characteristic){
	 /*for(int i = 0; i < 5 ; i++ ){
			this.characteristic[i] += characteristic[i];
			this.characteristic[i] = this.characteristic[i]/scoredTimes;
		}*/
		this.characteristic=characteristic;
	}	
	public void setCharacteristicByIndex(int index , int value){
		this.characteristic[index] = value;
	}
}
