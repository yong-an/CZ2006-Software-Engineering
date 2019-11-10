package com.example.chasexplorer.Entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Clinic implements Parcelable {

    // Attributes for the Clinic Object
    private String clinicCode;
    private String clinicName;
    private String licenceType;
    private String clinicTelNo;
    private int postalCode;
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
        // empty default constructor, necessary for Clinic to be able to deserialize users
    }

    /**
     * Standard constructor for Clinic Object
     * @param clinicCode
     * @param clinicName
     * @param licenceType
     * @param clinicTelNo
     * @param postalCode
     * @param addrType
     * @param blkHseNo
     * @param floorNo
     * @param unitNo
     * @param streetName
     * @param buildingName
     * @param programmeCode
     * @param XCoordinate
     * @param YCoordinate
     * @param incCrc
     * @param fmelUpdD
     */
    public Clinic(String clinicCode, String clinicName, String licenceType, String clinicTelNo, int postalCode, String addrType, String blkHseNo, String floorNo, String unitNo, String streetName, String buildingName, String programmeCode, double XCoordinate, double YCoordinate, String incCrc, String fmelUpdD) {
        this.clinicCode = clinicCode;
        this.clinicName = clinicName;
        this.licenceType = licenceType;
        this.clinicTelNo = clinicTelNo;
        this.postalCode = postalCode;
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

    /**
     * Getter method for ClinicCode
     * @return clinicCode
     */
    public String getClinicCode() {
        return clinicCode;
    }

    /**
     * Setter method for ClinicCode
     * @param clinicCode
     */
    public void setClinicCode(String clinicCode) {
        this.clinicCode = clinicCode;
    }

    /**
     * Getter method for ClinicName
     * @return clinicName
     */
    public String getClinicName() {
        return clinicName;
    }

    /**
     * Setter method for ClinicName
     * @param clinicName
     */
    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    /**
     * Getter method for LicenceType
     * @return licenceType
     */
    public String getLicenceType() {
        return licenceType;
    }

    /**
     * Setter method for LicenceType
     * @param licenceType
     */
    public void setLicenceType(String licenceType) {
        this.licenceType = licenceType;
    }

    /**
     * Getter method for ClinicTelNo
     * @return clinicTelNo
     */
    public String getClinicTelNo() {
        return clinicTelNo;
    }

    /**
     * Setter method for setClinicTelNo
     * @param clinicTelNo
     */
    public void setClinicTelNo(String clinicTelNo) {
        this.clinicTelNo = clinicTelNo;
    }

    /**
     * Getter method for PostalCode
     * @return postalCode
     */
    public int getPostalCode() {
        return postalCode;
    }

    /**
     * Setter method for PostalCode
     * @param postalCode
     */
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Getter method for AddrType
     * @return addrType
     */
    public String getAddrType() {
        return addrType;
    }

    /**
     * Setter method for AddrType
     * @param addrType
     */
    public void setAddrType(String addrType) {
        this.addrType = addrType;
    }

    /**
     * Getter method for BlkHseNo
     * @return blkHseNo
     */
    public String getBlkHseNo() {
        return blkHseNo;
    }

    /**
     * Setter method for BlkHseNo
     * @param blkHseNo
     */
    public void setBlkHseNo(String blkHseNo) {
        this.blkHseNo = blkHseNo;
    }

    /**
     * Getter method for FloorNo
     * @return floorNo
     */
    public String getFloorNo() {
        return floorNo;
    }

    /**
     * Setter method for FloorNo
     * @param floorNo
     */
    public void setFloorNo(String floorNo) {
        this.floorNo = floorNo;
    }

    /**
     * Getter method for UnitNo
     * @return unitNo
     */
    public String getUnitNo() {
        return unitNo;
    }

    /**
     * Setter method for UnitNo
     * @param unitNo
     */
    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    /**
     * Getter method for streetName
     * @return streetName
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * Setter method for streetName
     * @param streetName
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    /**
     * Getter method for buildingName
     * @return buildingName
     */
    public String getBuildingName() {
        return buildingName;
    }

    /**
     * Setter method for buildingName
     * @param buildingName
     */
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    /**
     * Getter method for programmeCode
     * @return programmeCode
     */
    public String getProgrammeCode() {
        return programmeCode;
    }

    /**
     * Setter method for programmeCode
     * @param programmeCode
     */
    public void setProgrammeCode(String programmeCode) {
        this.programmeCode = programmeCode;
    }

    /**
     * Getter method for XCoordinate
     * @return XCoordinate
     */
    public double getXCoordinate() {
        return XCoordinate;
    }

    /**
     * Setter method for XCoordinate
     * @param XCoordinate
     */
    public void setXCoordinate(double XCoordinate) {
        this.XCoordinate = XCoordinate;
    }

    /**
     * Getter method for YCoordinate
     * @return YCoordinate
     */
    public double getYCoordinate() {
        return YCoordinate;
    }

    /**
     * Setter method for YCoordinate
     * @param YCoordinate
     */
    public void setYCoordinate(double YCoordinate) {
        this.YCoordinate = YCoordinate;
    }

    /**
     * Getter method for IncCrc
     * @return incCrc
     */
    public String getIncCrc() {
        return incCrc;
    }

    /**
     * Setter method for IncCrc
     * @param incCrc
     */
    public void setIncCrc(String incCrc) {
        this.incCrc = incCrc;
    }

    /**
     * Getter method for FmelUpdD
     * @return fmelUpdD
     */
    public String getFmelUpdD() {
        return fmelUpdD;
    }

    /**
     * Setter method for FmelUpdD
     * @param fmelUpdD
     */
    public void setFmelUpdD(String fmelUpdD) {
        this.fmelUpdD = fmelUpdD;
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * Called by Firebase internal methods to save data
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeString(clinicCode);
        dest.writeString(clinicName);
        dest.writeString(licenceType);
        dest.writeString(clinicTelNo);
        dest.writeInt(postalCode);
        dest.writeString(addrType);
        dest.writeString(blkHseNo);
        dest.writeString(floorNo);
        dest.writeString(unitNo);
        dest.writeString(streetName);
        dest.writeString(buildingName);
        dest.writeString(programmeCode);
        dest.writeDouble(XCoordinate);
        dest.writeDouble(YCoordinate);
        dest.writeString(incCrc);
    }

    /**
     * Clinic Constructor called by Firebase
     * @param in
     */
    public Clinic(Parcel in) {
        clinicCode = in.readString();
        clinicName = in.readString();
        licenceType = in.readString();
        clinicTelNo = in.readString();
        postalCode = in.readInt();
        addrType = in.readString();
        blkHseNo = in.readString();
        floorNo = in.readString();
        unitNo = in.readString();
        streetName = in.readString();
        buildingName = in.readString();
        programmeCode = in.readString();
        XCoordinate = in.readDouble();
        YCoordinate = in.readDouble();
        incCrc = in.readString();
        fmelUpdD = in.readString();
    }

    /**
     * Called by Firebase internal methods
     */
    public static final Parcelable.Creator<Clinic> CREATOR = new Parcelable.Creator<Clinic>() {
        public Clinic createFromParcel(Parcel in) {
            return new Clinic(in);
        }

        public Clinic[] newArray(int size) {
            return new Clinic[size];
        }
    };

    /**
     * Called by ViewClinicDetailsActivity to display a list of the Clinic's data
     * @return clinicDetails
     */
    @Override
    public String toString(){
        String clinicDetails = new String();
        clinicDetails = getClinicName() + "\nClinic Code: " + getClinicCode();
        if(!(getClinicTelNo().equalsIgnoreCase(" ")))
            clinicDetails += "\n(+65)"  + getClinicTelNo();
        clinicDetails += "\n" + getStreetName() + "\nBlk " + getBlkHseNo() + getAddrType();
        if(!(getFloorNo().equalsIgnoreCase(" ") && getUnitNo().equalsIgnoreCase(" ")))
            clinicDetails += " #" + getFloorNo() + "-" + getUnitNo();
        else if ((getUnitNo().equalsIgnoreCase(" ")) && !(getFloorNo().equalsIgnoreCase(" ")))
            clinicDetails += " #" + getFloorNo();
        clinicDetails += "\nSingapore " + getPostalCode();
        return clinicDetails;
    }

    /**
     * Called by ViewClinicActivity to display the Clinic summary data
     * @return clinicDetails
     */
    public String toString2(){
        String clinicDetails = new String();
        clinicDetails = getClinicName();
        if(!(getClinicTelNo().equalsIgnoreCase(" ")))
            clinicDetails += "\n(+65)"  + getClinicTelNo();
        clinicDetails += "\n" + getStreetName();
        return clinicDetails;
    }

    /* How To Use Parcel
    =Store in activity 1=
    Bundle bundle = new Bundle();
    bundle.putParcelableArrayList("ACCESSKEY", DATA);
    i.putExtras(bundle);

    =Retrieve at activity 2=
    Bundle b = getIntent().getExtras();
    ArrayList<Clinic> FIREBASEDATA = b.getParcelableArrayList("ACCESSKEY");
     */
}
