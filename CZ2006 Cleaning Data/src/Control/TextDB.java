package Control;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Entity.Clinic;

/**
 * The {@code TextDB} is a control class that manages the reading and writing of
 * ALL {@code Entity} objects. The respective {@code Entity} managers will upon
 * the methods when required.
 * 
 * @author Joseph Fung King Yiu
 * @version 1.0
 * @since 2019-04-17
 */

public class TextDB {
	private static final String SEPARATOR = "\n";
	private static final String patternEnd = "</SchemaData>";
	private static final String patternClinicName = "<SimpleData name=\"HCI_NAME\">";
	private static final String patternLicenceType = "<SimpleData name=\"LICENCE_TYPE\">";
	private static final String patternClinicTel = "<SimpleData name=\"HCI_TEL\">";
	private static final String patternPostalCode = "<SimpleData name=\"POSTAL_CD\">";
	private static final String patternAddrType = "<SimpleData name=\"ADDR_TYPE\">";
	private static final String patternBlkNo = "<SimpleData name=\"BLK_HSE_NO\">";
	private static final String patternFloorNo = "<SimpleData name=\"FLOOR_NO\">";
	private static final String patternUnitNo = "<SimpleData name=\"UNIT_NO\">";
	private static final String patternStreetName = "<SimpleData name=\"STREET_NAME\">";
	private static final String patternBuildingName = "<SimpleData name=\"BUILDING_NAME\">";
	private static final String patternClinicProgrammeCode = "<SimpleData name=\"CLINIC_PROGRAMME_CODE\">";
	private static final String patternXCoord = "<SimpleData name=\"X_COORDINATE\">";
	private static final String patternYCoord = "<SimpleData name=\"Y_COORDINATE\">";
	private static final String patternIncCrc = "<SimpleData name=\"INC_CRC\">";
	private static final String patternFMEL = "<SimpleData name=\"FMEL_UPD_D\">";

	/**
	 * Reads in Clinic from text file. Returns ArrayList[Clinic].
	 * 
	 * @param filename FilePath
	 */
	public static ArrayList<Clinic> readClinic(String filename) throws IOException {
		// read String from text file
		String patternStart = "<SimpleData name=\"HCI_CODE\">";
	    // Create a Pattern object
	    Pattern r = Pattern.compile(patternStart);
	    // Now create matcher object.
		ArrayList stringArray = (ArrayList) read(filename);
		ArrayList<Clinic> clinicAl = new ArrayList<Clinic>();
		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
																		// using delimiter ","
			String line = star.nextToken().trim(); // first token
			Matcher m = r.matcher(line);  
			if (m.find()) {
				String hciCode =  line.replaceAll("\\<.*?>","");
				i++;
				Clinic c = saveClinic(hciCode,i,stringArray);
				clinicAl.add(c);
			}
		}
		return clinicAl;
	}

	/**
	 * Reads in the remaining information of a Single clinic(e.g Clinic name, Licence Type, Clinic telephone,etc after Caller identified the Clinic Code.) 
	 * The loop stops when it detected </SchemaData> which signifies that the data acquisition for a particular Clinic is complete.
	 * Returns Clinic object.
	 * 
	 * @param filename FilePath
	 */
	private static Clinic saveClinic(String HCICode,int i, ArrayList stringArray) {
		Clinic c = new Clinic();
		c.setHCICode(HCICode);
		Pattern r = Pattern.compile(patternEnd);
		Pattern r2 = Pattern.compile(patternClinicName);
		Pattern r3 = Pattern.compile(patternLicenceType);
		Pattern r4 = Pattern.compile(patternClinicTel);
		Pattern r5 = Pattern.compile(patternPostalCode);
		Pattern r6 = Pattern.compile(patternAddrType);
		Pattern r7 = Pattern.compile(patternBlkNo);
		Pattern r8 = Pattern.compile(patternFloorNo);
		Pattern r9 = Pattern.compile(patternUnitNo);
		Pattern r10 = Pattern.compile(patternStreetName);
		Pattern r11 = Pattern.compile(patternBuildingName);
		Pattern r12 = Pattern.compile(patternClinicProgrammeCode);
		Pattern r13 = Pattern.compile(patternXCoord);
		Pattern r14 = Pattern.compile(patternYCoord);
		Pattern r15 = Pattern.compile(patternIncCrc);
		Pattern r16 = Pattern.compile(patternFMEL);
		String st = (String) stringArray.get(i);
		StringTokenizer star = new StringTokenizer(st, SEPARATOR); 
		String line = star.nextToken().trim(); 
		Matcher m20 = r.matcher(line);
		Matcher m,m2, m3,m4,m5,m6,m7,m8,m9,m10,m11,m12,m13,m14,m15;
		while(m20.matches() != true) {
			m = r2.matcher(line);
			m2 = r3.matcher(line);
			m3 = r4.matcher(line);
			m4 = r5.matcher(line);
			m5 = r6.matcher(line);
			m6 = r7.matcher(line);
			m7 = r8.matcher(line);
			m8 = r9.matcher(line);
			m9 = r10.matcher(line);
			m10 = r11.matcher(line);
			m11 = r12.matcher(line);
			m12 = r13.matcher(line);
			m13 = r14.matcher(line);
			m14 = r15.matcher(line);
			m15 = r16.matcher(line);
			if(m.find()) {
				c.setHCIName(cleanData(line));
			} else if(m2.find()) {
				c.setLicenceType(cleanData(line));
			} else if(m3.find()) {
				c.setHCITel(cleanData(line));
			} else if(m4.find()) {
				c.setPostalCD(cleanData(line));
			} else if(m5.find()) {
				c.setAddrType(cleanData(line));
			} else if(m6.find()) {
				c.setBlkHseNo(cleanData(line));
			} else if(m7.find()) {
				c.setFloorNo(cleanData(line));
			} else if(m8.find()) {
				c.setUnitNo(cleanData(line));
			} else if(m9.find()) {
				c.setStreetName(cleanData(line));
			} else if(m10.find()) {
				c.setBuildingName(cleanData(line));
			} else if(m11.find()) {
				c.setClinicProgrammeCode(cleanData(line));
			} else if(m12.find()) {
				c.setXCoordinate(Double.parseDouble(cleanData(line)));
			} else if(m13.find()) {
				c.setYCoordinate(Double.parseDouble(cleanData(line)));	
			} else if(m14.find()) {
				c.setIncCrc(cleanData(line));
			} else if(m15.find()) {
				c.setFmelUpdD(cleanData(line));
			} 
			i++;
			st = (String) stringArray.get(i);
			star = new StringTokenizer(st, SEPARATOR); 
			line = star.nextToken().trim();
			m20 = r.matcher(line);
		}
		c = convertXYCoord(c,String.valueOf(c.getXCoordinate()),String.valueOf(c.getYCoordinate()));
		return c;
	}

	/**
	 * Strips each line of the kml tags
	 * 
	 * @param line DataLineByLine
	 */
	private static String cleanData(String line) {
		return line.replaceAll("\\<.*?>","");
	}
	
	/**
	 * Reads the content of the given file.
	 * 
	 * @param filename FilePath
	 */
	private static List<String> read(String fileName) throws IOException {
		List<String> data = new ArrayList<String>();
		Scanner scanner = new Scanner(new FileInputStream(fileName));
		try {
			while (scanner.hasNextLine()) {
				data.add(scanner.nextLine());
			}
		} finally {
			scanner.close();
		}
		return data;
	}
	
	
	//https://developers.onemap.sg/commonapi/convert/3414to4326?X=28983.788791079794&Y=33554.5098132845
	
	private static Clinic convertXYCoord(Clinic c,String x, String y) {
		String USER_AGENT = "Mozilla/5.0";
		String url = "https://developers.onemap.sg/commonapi/convert/3414to4326?X=" +x+ "&Y=" +y+ "\r\n";
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			// optional default is GET
			con.setRequestMethod("GET");
			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT);
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			StringTokenizer st = new StringTokenizer(response.toString(),",");
			while(st.hasMoreTokens()) {
				String s = st.nextToken();
				if(s.contains("latitude")) {
					s = s.replaceAll("^\\{\"latitude\":","");
					c.setXCoordinate(Double.parseDouble(s));
				} else if (s.contains("longitude")) {
					s = s.replaceAll("^\"longitude\":","");
					s = s.replaceAll("\\}", "");
					c.setYCoordinate(Double.parseDouble(s));
				}
			}
			//print result
			System.out.println(response.toString());
			in.close();
		} catch (Exception e) {e.printStackTrace();}		
		return c;
	}
}
