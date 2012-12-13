import javax.imageio.ImageIO;
import javax.swing.*;
import java.net.URL;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ShowComic extends JFrame{
	private static int currentComic = 0;
	private static String url ;
	static JFrame frame = new JFrame("xkcd");
	static comicPanel panel;
	
	public  void setUpGui()	{
		panel = new comicPanel();
		
		
		JButton next = new JButton("Next");
		next.addActionListener(new NextListener());
		
		JButton previous = new JButton("Previous");
		previous.addActionListener(new PreviousListener());
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.getContentPane().add(BorderLayout.SOUTH, previous);
		frame.getContentPane().add(BorderLayout.NORTH, next);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(30, 30, 500, 500);
		frame.setVisible(true);
	}
	
	public String getCurrentComicUrl()	{
		StaxParser read = new StaxParser("http://xkcd.com/rss.xml");
		Feed feed = read.readFeed();
		List<Comic> comics = feed.getMessages();
		url = comics.get(currentComic).getImageUrl();
		return url;
	}
	
	public static void main(String args[]) {
		ShowComic asd = new ShowComic();
		asd.go();
	}
	public void go()	{
		getCurrentComicUrl();
		setUpGui();
	}
	
	class NextListener implements ActionListener	{
		public void actionPerformed(ActionEvent event)	{
			currentComic--;
			panel.removeAll();
			panel.updateUI();
		}
	}
	
	class PreviousListener implements ActionListener	{
		public void actionPerformed(ActionEvent event)	{
			currentComic++;
			panel.removeAll();
			panel.updateUI();
		}
	}

	class comicPanel extends JPanel {
		public void paintComponent(Graphics g) {
			try	{
				URL comicUrl = new URL(getCurrentComicUrl());
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				Image comic =  ImageIO.read(comicUrl);
				g.drawImage(comic,3,4,this);
			} catch (Exception e){}
		}
	}
}