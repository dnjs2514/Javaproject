package model;


public class  Review{

	private String reviewid;
	private String score;
	private String reviewcomment;
	public Review(String reviewid, String score, String reviewcomment) {
		
		this.reviewid = reviewid;
		this.score = score;
		this.reviewcomment = reviewcomment;
	}
	
	public String getReviewid() {
		return reviewid;
	}
	public void setReviewid(String reviewid) {
		this.reviewid = reviewid;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getReviewcomment() {
		return reviewcomment;
	}
	public void setReviewcomment(String reviewcomment) {
		this.reviewcomment = reviewcomment;
	}
	




}


