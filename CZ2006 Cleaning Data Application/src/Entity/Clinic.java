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
	
	private String HCICode;
	private String HCIName;
	private String licenceType;
	private String HCITel;
	private String postalCD;
	private String addrType;
	private String blkHseNo;
	private String floorNo;
	private String unitNo;
	private String streetName;
	private String buildingName;
	private String clinicProgrammeCode;
	private double XCoordinate;
	private double YCoordinate;
	private String incCrc;
	private String fmelUpdD;

	public Clinic() {
		this.HCICode = "null";
		this.HCIName = "null";
		this.licenceType = "null";
		this.HCITel = "null";
		this.postalCD = "null";
		this.addrType = "null";
		this.blkHseNo = "null";
		this.floorNo = "null";
		this.unitNo = "null";
		this.streetName = "null";
		this.buildingName = "null";
		this.clinicProgrammeCode = "null";
		this.XCoordinate = 0;
		this.YCoordinate = 0;
		this.incCrc = "null";
		this.fmelUpdD = "null";
	}
	
	public Clinic(String HCICode,String HCIName, String licenceType,String HCITel,String postalCD,String addrType, String blkHseNo, String floorNo, String unitNo,
			String streetName, String buildingName, String clinicProgrammeCode, double XCoordinate, double YCoordinate,String incCrc, String fmelUpdD) {
		this.HCICode = HCICode;
		this.HCIName = HCIName;
		this.licenceType = licenceType;
		this.HCITel = HCITel;
		this.postalCD = postalCD;
		this.addrType = addrType;
		this.blkHseNo = blkHseNo;
		this.floorNo = floorNo;
		this.unitNo = unitNo;
		this.streetName = streetName;
		this.buildingName = buildingName;
		this.clinicProgrammeCode = clinicProgrammeCode;
		this.XCoordinate = XCoordinate;
		this.YCoordinate = YCoordinate;
		this.incCrc = incCrc;
		this.fmelUpdD = fmelUpdD;
	}
	
	public String getHCICode() {
		return HCICode;
	}

	public String getHCIName() {
		return HCIName;
	}

	public String getLicenceType() {
		return licenceType;
	}

	public String getHCITel() {
		return HCITel;
	}

	public String getPostalCD() {
		return postalCD;
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

	public String getClinicProgrammeCode() {
		return clinicProgrammeCode;
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

	public void setHCICode(String hCICode) {
		HCICode = hCICode;
	}

	public void setHCIName(String hCIName) {
		if(hCIName.contains("'")) {
			hCIName = hCIName.replaceAll("\'","");
		}
		HCIName = hCIName;
	}

	public void setLicenceType(String licenceType) {
		this.licenceType = licenceType;
	}

	public void setHCITel(String hCITel) {
		HCITel = hCITel;
	}

	public void setPostalCD(String postalCD) {
		this.postalCD = postalCD;
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

	public void setClinicProgrammeCode(String clinicProgrammeCode) {
		this.clinicProgrammeCode = clinicProgrammeCode;
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
		clinicString = "HCI Code: " + getHCICode() + "\nClinic Name: " + getHCIName() + "\nLicence Type: " + 
		getLicenceType() + "\nTelephone Number: " + getHCITel() + "\nPostal Code: " + getPostalCD() + "\nAddress Type: " + getAddrType() + "\nBlk Number: " + getBlkHseNo() 
		+ "\nFloor No: " + getFloorNo() + "\nUnit No: " + getUnitNo() + "\nStreet Name: " + getStreetName() + "\nBuilding Name: " + getBuildingName() + "\nClinic Programme Name: " 
		+ getClinicProgrammeCode() + "\nX Coordinate: " + getXCoordinate() + "\nY Coordinate: " + getYCoordinate() + "\nINC CRC: " + getIncCrc() + "\nFMEL Upd D: " + getFmelUpdD() + "\n";
		return clinicString;
	}
}
