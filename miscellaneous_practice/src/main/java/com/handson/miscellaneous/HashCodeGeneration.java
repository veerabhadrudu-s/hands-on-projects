package com.handson.miscellaneous;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class HashCodeGeneration {

	private static final Logger logger = LoggerFactory.getLogger(HashCodeGeneration.class.getClass());

	public static void main(final String[] args) throws GeneralSecurityException {
		logger.debug(readXML("<Bundle> dd dd          </Bundle>"));
	}

	/**
	 * Function to parse the xml response
	 *
	 * @param inputValue
	 */
	public static String readXML(final String inputValue) {
		String originalResponse = null;
		final List<String> list = new ArrayList<String>();
		try {
			String substringId = null;
			final int flag = 0;
			String myHash = null;
			String myHash1 = null;
			Document doc = null;
			final String dispenseData = inputValue.replaceAll(">\\s+<", "><");
			// logger.debug("1");
			try {
				doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
						.parse(new InputSource(new StringReader(dispenseData)));
			} catch (final Exception e) {
				logger.debug(e.getMessage());
			}
			doc.getDocumentElement().normalize();
			final NodeList nList = doc.getElementsByTagName("fullUrl");
			final NodeList nList1 = doc.getElementsByTagName("authorizingPrescription");
			final NodeList nList2 = doc.getElementsByTagName("entry");
			// logger.debug(doc);
			// logger.debug("2");
			/**
			 * Loop for authorizingPrescription
			 */

			for (int temp = 0; temp < nList1.getLength(); temp++) {
				final Node nNode = nList1.item(temp);
				// logger.debug(nList1.getLength()+"before for AP");
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					final Element eElement = (Element) nNode;
					final String reference = eElement.getElementsByTagName("reference").item(0).getAttributes()
							.getNamedItem("value").getNodeValue();
					// String reference1 =
					// eElement.getElementsByTagName("reference").item(0).getAttributes().getNamedItem("value")

					myHash = hashXslt(reference);
					list.add(myHash);
					eElement.getElementsByTagName("reference").item(0).getAttributes().getNamedItem("value")
							.setNodeValue("MedicationOrder/" + myHash);
				}
			}
			// logger.debug(nList1.getLength()+"after for AP");
			/**
			 * Loop for MEdicationOrder and Dispense
			 */

			final List<Integer> invalidIndexList = new ArrayList<>();

			for (int temp = 0; temp < nList.getLength(); temp++) {
				final Node nNode = nList.item(temp);
				// logger.debug(nList1.getLength()+"before for fullurl");
				final Element eElement = (Element) nNode;
				final String medDispense = eElement.getAttribute("value");
				// logger.debug("4");
				if (medDispense.contains("MedicationDispense")) {
					// logger.debug("5");
					substringId = (medDispense.substring(medDispense.lastIndexOf('/') + 1));
					myHash = hashXslt(substringId);
					final int y1 = medDispense.indexOf("MedicationDispense/");
					final String hashDispense = medDispense.substring(0, y1) + "MedicationDispense/" + myHash;
					eElement.setAttribute("value", hashDispense);
				}
				if (medDispense.contains("MedicationOrder")) {
					// logger.debug("6");
					substringId = (medDispense.substring(medDispense.lastIndexOf('/') + 1));
					myHash1 = hashXslt(substringId);
					// logger.debug(myHash);
					final int y1 = medDispense.indexOf("MedicationOrder/");
					for (int x = 0; x < list.size(); x++) {
						// logger.debug(list.size());
						logger.debug("hahs value is" + myHash1);
						logger.debug("list.get value" + list.get(x));
						if (myHash1.equals(list.get(x))) {
							logger.debug("inside my iff");
							final String hashPrescription = medDispense.substring(0, y1) + "MedicationOrder/" + myHash;
							eElement.setAttribute("value", hashPrescription);
						} else {
							invalidIndexList.add(temp);
						}

					}

				}
			}

			logger.debug(nList1.getLength() + "before for fullurl");
			logger.debug("block out1");
			originalResponse = convertDocumentToString(doc);
			logger.debug("block out2");
			// logger.debug(originalResponse);
		} catch (final Exception e) {
			logger.debug(e.getMessage());
		}
		return originalResponse;
	}

	/**
	 * Function to generate Hash Value
	 *
	 * @param text
	 * @return
	 * @throws NoSuchAlgorithmException
	 */

	public static String hashXslt(final String text) throws NoSuchAlgorithmException {
		final MessageDigest md = MessageDigest.getInstance("SHA-256");
		try {
			md.update(text.getBytes("UTF-8"));
		} catch (final UnsupportedEncodingException e) {
			e.getMessage();
		}
		final byte[] digest = md.digest();
		final String result = String.format("%064x", new BigInteger(1, digest));
		return result;
	}

	/**
	 * Function to Convert Document To String
	 *
	 * @param doc
	 * @return
	 */

	private static String convertDocumentToString(final Document doc) {
		logger.debug("block out3");
		final TransformerFactory tf = TransformerFactory.newInstance();
		logger.debug("block out4");
		Transformer transformer;
		logger.debug("block out5");
		logger.debug("inside conv" + doc);
		try {
			logger.debug("1");
			transformer = tf.newTransformer();
			logger.debug("2");
			// below code to remove XML declaration
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			logger.debug("3");
			final StringWriter writer = new StringWriter();
			logger.debug("4");
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			logger.debug("5");
			final String output = writer.getBuffer().toString();
			logger.debug("6");
			logger.debug("here we go" + output);
			return output;
		} catch (final Exception e) {
			logger.debug(e.getMessage());
		}
		return null;
	}
}
