
public class Comic {
	private String date;
	private String description;
	private String title;
	private String imageUrl;
	
	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}
	
	  @Override
	  public String toString() {
	    return "Item [date=" + date + ", title=" + title + ", description=" + description + ", image=" + imageUrl + "]";
	  }
}
