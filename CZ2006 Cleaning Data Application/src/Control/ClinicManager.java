package Control;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.*;

import Entity.Clinic;

/**
 * The {@code ClinicManager} is a control class used to model all control behavior
 * specific to the {@link Clinic} 
 * 
 * @author Joseph Fung King Yiu
 * @version 1.0
 * @since 2019-08-31
 */

public class ClinicManager {
	private ArrayList<Clinic> clinicAl;
	
	public ClinicManager() {
		try {
			clinicAl = TextDB.readClinic("C:\\Users\\Joseph\\Desktop\\chas-clinics-kml.kml");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String gsonStr = gson.toJson(clinicAl);  
			BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Joseph\\Desktop\\ClinicData.json", false));
		    writer.append(gsonStr);
		    writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printClinicAl() {
		for (Clinic c : clinicAl) {
			System.out.println(c.toString());
		}
		System.out.println("Total AL length = " + clinicAl.size());
	}
	
	public void findClinic(String clinicName) {
		for (Clinic c : clinicAl) {
			if (c.getClinicName().equalsIgnoreCase(clinicName)) {
				System.out.println("Found Clinic!\n" + c.toString());
				return;
			}
		}
	}
}
