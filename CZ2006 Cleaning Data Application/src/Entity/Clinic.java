package Entity;

/**
 * The {@code Clinic} entity class is an object wrapper. 
 * <p>
 * Contains primitive information related to a Clinic (e.g Clinic Name, Clinic Code, Clinic Postal Code, Address,etc).
 * </p>
 * 
 * @author Joseph Fung King Yiu
 * @version 1.0
 * @since 2019-08-31
 */

public class Clinic {
	
	private String clinicCode;
	private String clinicName;
	private String licenceType;
	private String clinicTelNo;
	private int postalCode;
	private String placeID;
	private String addrType;
	private String blkHseNo;
	private String floorNo;
	private String unitNo;
	private String streetName;
	private String buildingName;
	private String programmeCode;
	private double XCoordinate;
	private double YCoordinate;
	private String incCrc;
	private String fmelUpdD;

	public Clinic() {
		this.clinicCode = "null";
		this.clinicName = "null";
		this.licenceType = "null";
		this.clinicTelNo = "null";
		this.postalCode = 0;
		this.placeID = "null";
		this.addrType = "a";
		this.blkHseNo = "null";
		this.floorNo = "null";
		this.unitNo = "null";
		this.streetName = "null";
		this.buildingName = "null";
		this.programmeCode = "null";
		this.XCoordinate = 0;
		this.YCoordinate = 0;
		this.incCrc = "null";
		this.fmelUpdD = "null";
	}
	
	public Clinic(String clinicCode,String clinicName, String licenceType,String clinicTelNo,int postalCode,String placeID,String addrType, String blkHseNo,String floorNo, String unitNo,
			String streetName, String buildingName, String programmeCode, double XCoordinate, double YCoordinate,String incCrc, String fmelUpdD) {
		this.clinicCode = clinicCode;
		this.clinicName = clinicName;
		this.licenceType = licenceType;
		this.clinicTelNo = clinicTelNo;
		this.postalCode = postalCode;
		this.placeID = placeID;
		this.addrType = addrType;
		this.blkHseNo = blkHseNo;
		this.floorNo = floorNo;
		this.unitNo = unitNo;
		this.streetName = streetName;
		this.buildingName = buildingName;
		this.programmeCode = programmeCode;
		this.XCoordinate = XCoordinate;
		this.YCoordinate = YCoordinate;
		this.incCrc = incCrc;
		this.fmelUpdD = fmelUpdD;
	}
	
	public String getClinicCode() {
		return clinicCode;
	}

	public String getClinicName() {
		return clinicName;
	}

	public String getLicenceType() {
		return licenceType;
	}

	public String getClinicTelNo() {
		return clinicTelNo;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public String getAddrType() {
		return addrType;
	}

	public String getBlkHseNo() {
		return blkHseNo;
	}

	public String getFloorNo() {
		return floorNo;
	}

	public String getUnitNo() {
		return unitNo;
	}

	public String getStreetName() {
		return streetName;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public String getPlaceID() {
		return placeID;
	}

	public void setPlaceID(String placeID) {
		this.placeID = placeID;
	}

	public String getProgrammeCode() {
		return programmeCode;
	}

	public double getXCoordinate() {
		return XCoordinate;
	}

	public double getYCoordinate() {
		return YCoordinate;
	}

	public String getIncCrc() {
		return incCrc;
	}

	public String getFmelUpdD() {
		return fmelUpdD;
	}

	public void setClinicCode(String clinicCode) {
		this.clinicCode = clinicCode;
	}

	public void setClinicName(String clinicName) {
		if(clinicName.contains("'")) {
			clinicName = clinicName.replaceAll("\'","");
		}
		this.clinicName = clinicName;
	}

	public void setLicenceType(String licenceType) {
		this.licenceType = licenceType;
	}

	public void setClinicTelNo(String clinicTelNo) {
		this.clinicTelNo = clinicTelNo;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public void setAddrType(String addrType) {
		this.addrType = addrType;
	}

	public void setBlkHseNo(String blkHseNo) {
		this.blkHseNo = blkHseNo;
	}

	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}

	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public void setProgrammeCode(String programmeCode) {
		this.programmeCode = programmeCode;
	}

	public void setXCoordinate(double xCoordinate) {
		XCoordinate = xCoordinate;
	}

	public void setYCoordinate(double yCoordinate) {
		YCoordinate = yCoordinate;
	}

	public void setIncCrc(String incCrc) {
		this.incCrc = incCrc;
	}

	public void setFmelUpdD(String fmelUpdD) {
		this.fmelUpdD = fmelUpdD;
	}

	@Override
	public String toString() {
		String clinicString = null;
		clinicString = "Clinic Code: " + getClinicCode() + "\nClinic Name: " + getClinicName() + "\nLicence Type: " + 
		getLicenceType() + "\nTelephone Number: " + getClinicTelNo() + "\nPostal Code: " + getPostalCode() + "\nPlace ID: " + getPlaceID() +  "\nAddress Type: " + getAddrType() + "\nBlk Number: " + getBlkHseNo() 
		+ "\nFloor No: " + getFloorNo() + "\nUnit No: " + getUnitNo() + "\nStreet Name: " + getStreetName() + "\nBuilding Name: " + getBuildingName() + "\nClinic Programme Name: " 
		+ getProgrammeCode() + "\nX Coordinate: " + getXCoordinate() + "\nY Coordinate: " + getYCoordinate() + "\nINC CRC: " + getIncCrc() + "\nFMEL Upd D: " + getFmelUpdD() + "\n";
		return clinicString;
	}
}
