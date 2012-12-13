import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import org.apache.commons.lang3.*;


public class StaxParser {
  static final String DATE = "pubDate";
  static final String DESCRIPTION = "description";	
  static final String TITLE = "title";
  static final String ITEM = "item";
  static final String CHANNEL = "channel";
  private String rawDescription;
  
  
  
  final URL url;
  
  public  StaxParser(String feedUrl)	{
	  try	{
		  this.url = new URL(feedUrl);
	  } catch (MalformedURLException ex)	{
		  throw new RuntimeException(ex);
	  }
  }
  
  public Feed readFeed()	{
	  Feed feed = null;
	  try	{
		  String description = "";
		  String title = "";
		  String date = "";
		  String image = "";
		  boolean isFeedHeader = true;
		  
		  XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		  InputStream in = read();
	      XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
	      while (eventReader.hasNext()) {
	          XMLEvent event = eventReader.nextEvent();
	          
	          if (event.isStartElement()) {
	            if (event.asStartElement().getName().getLocalPart() == (CHANNEL)) {
	              if (isFeedHeader) {
	                isFeedHeader = false;
	                feed = new Feed(title, description, date);
	              }
	              event = eventReader.nextEvent();
	              continue;
	            }
	
	            if (event.asStartElement().getName().getLocalPart() == (TITLE)) {
	              event = eventReader.nextEvent();
	              title = event.asCharacters().getData();
	              continue;
	            }
	            if (event.asStartElement().getName().getLocalPart() == (DESCRIPTION) ) {
	              event = eventReader.nextEvent();
	              rawDescription  = eventReader.peek().toString();
	              image = StringUtils.substringBetween(rawDescription, "\"");
	              description = StringUtils.substringBetween(StringUtils.substringAfter(rawDescription, "title="), "\"");
	              continue;
	            }
	            
	            if (event.asStartElement().getName().getLocalPart() == (DATE)) {
	                event = eventReader.nextEvent();
	                date = StringUtils.substringBefore(event.asCharacters().getData(), "-");
	                continue;
	              }
	
	
	          } else if (event.isEndElement()) {
	            if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
	              Comic comic = new Comic();
	              comic.setDescription(description);
	              comic.setTitle(title);
	              comic.setDate(date);
	              comic.setImageUrl(image);
	              feed.getMessages().add(comic);
	              event = eventReader.nextEvent();
	              continue;
	            }
	          }
	        }
      	} catch (XMLStreamException ex)	{}
  
      return feed;
  }
  
  private InputStream read()	{
	  try	{
		  return url.openStream();
	  } catch (IOException ex)	{
		  throw new RuntimeException(ex);
	  }
  }
}
