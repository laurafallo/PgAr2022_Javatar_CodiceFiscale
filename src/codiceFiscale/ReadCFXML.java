package codiceFiscale;



import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ReadCFXML {
	private static final String FILENAME = "codiciFiscali.xml";
	private ArrayList<String> CFs = new ArrayList<>();


	public ArrayList<String> readCFs(){
	
		// Instantiate the Factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			// optional, but recommended
			// process XML securely, avoid attacks like XML External Entities (XXE)
			dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

			// parse XML file
			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.parse(new File(FILENAME));

			doc.getDocumentElement().normalize();
			
			System.out.println("Scanning CFs");

			NodeList list = doc.getElementsByTagName("codice");

			for (int temp = 0; temp < list.getLength(); temp++) {
				Node node = list.item(temp);
				CFs.add(node.getTextContent());
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

		return CFs;
	}
}