import java.util.ArrayList;
import java.util.List;

/*
 * Stores an RSS feed
 */
public class Feed {

  final String title;
  final String description;
  final String pubDate;
  
  final List<Comic> entries = new ArrayList<Comic>();

  public Feed(String title,  String description, String pubDate) {
    this.title = title;
    this.description = description;
    this.pubDate = pubDate;
  }

  public List<Comic> getMessages() {
    return entries;
  }

  public String getTitle() {
    return title;
  }


  public String getDescription() {
    return description;
  }



  @Override
  public String toString() {
    return "Feed [description=" + description + ", title=" + title +"]";
  }

} 