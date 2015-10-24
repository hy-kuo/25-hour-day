package ss.finalproject.twentyfivehourday.mvc.model.domain;

import javax.persistence.Id;

public class Comment {
	@Id
	private Long id;
	private Long activity;
	private String text;
	private long stamp;
	private String founder;
	
	public Comment() {
		super();
	}

	public Comment(Long activity, String text) {
		this.activity = activity;
		this.text = text;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getActivity() {
		return activity;
	}

	public void setActivity(Long activity) {
		this.activity = activity;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public long getStamp() {
		return stamp;
	}

	public void setStamp(long stamp) {
		this.stamp = stamp;
	}
	public void setFounder(String founder){
		this.founder = founder;
	}
	
	public String getFounder(){
		return this.founder;
		
	}
}
