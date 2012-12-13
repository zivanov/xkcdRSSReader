import javax.swing.*;
import java.util.List;

public class Test {
  public static void main(String args[]) {
    StaxParser read = new StaxParser("http://xkcd.com/rss.xml");
    Feed feed = read.readFeed();
    List<Comic> comics = feed.getMessages();
    System.out.println(comics.get(1));

  }
} 