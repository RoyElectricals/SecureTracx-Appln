

import java.awt.Image;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class About extends JPanel
{
	JLabel l1;	
	 BufferedReader in;
	public About()
	{

		
		Image img=null;
		try
		{
			File logo=new File("C:/Users/Lenovo/Documents/Java_Project/SecureTracx/src/com/SecureTracx/logo.png");
			img=ImageIO.read(logo);
		}
		catch(Exception e)
		{
			
		}
		
		l1=new JLabel(new ImageIcon(img));
		add(l1);
		l1.setBounds(0, 0, 200, 50);
		l1.setVisible(true);
		JPanel middlePanel = new JPanel();
		middlePanel.setBounds(0, 100, 200, 100);
	    middlePanel.setBorder ( (Border) new TitledBorder ( new EtchedBorder (), "Application Details" ) );

	    // create the middle panel components
	   
		try {
			in = new BufferedReader(new FileReader("C:/Users/Lenovo/Documents/Java_Project/SecureTracx/src/com/SecureTracx/readme.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    String text="";
	    JTextArea display = new JTextArea(10, 50);
	    display.setText(text);
	    
	    try{
	    while((text=in.readLine() )!= null){
	    	display.append("\n"+text);
	    }
	    }catch (IOException e) {
			// TODO: handle exception
		}
	    
	    display.setEditable ( false ); // set textArea non-editable
	    JScrollPane scroll = new JScrollPane ( display );
	    scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
	    	   public void run() { 
	    	       scroll.getVerticalScrollBar().setValue(0);
	    	   }
	    	});


	    //Add Textarea in to middle panel
	    middlePanel.add ( scroll );

	    // My code
	    //JFrame frame = new JFrame ();
	   add ( middlePanel );
	   
	    setVisible ( true );
	    }
	
	}

