package com.example.chasexplorer;

import android.os.Parcel;
import android.os.Parcelable;

class Firebase implements Parcelable {

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

    public Firebase() {
        // empty default constructor, necessary for Firebase to be able to deserialize users
    }

    public Firebase(String clinicCode, String clinicName, String licenceType, String clinicTelNo, int postalCode, String addrType, String blkHseNo, String floorNo, String unitNo, String streetName, String buildingName, String programmeCode, double XCoordinate, double YCoordinate, String incCrc, String fmelUpdD) {
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

    public String getClinicCode() {
        return clinicCode;
    }

    public void setClinicCode(String HCICode) {
        this.clinicCode = clinicCode;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getLicenceType() {
        return licenceType;
    }

    public void setLicenceType(String licenceType) {
        this.licenceType = licenceType;
    }

    public String getClinicTelNo() {
        return clinicTelNo;
    }

    public void setClinicTelNo(String clinicTelNo) {
        this.clinicTelNo = clinicTelNo;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCD(String postalCD) {
        this.postalCode = postalCode;
    }

    public String getAddrType() {
        return addrType;
    }

    public void setAddrType(String addrType) {
        this.addrType = addrType;
    }

    public String getBlkHseNo() {
        return blkHseNo;
    }

    public void setBlkHseNo(String blkHseNo) {
        this.blkHseNo = blkHseNo;
    }

    public String getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(String floorNo) {
        this.floorNo = floorNo;
    }

    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getProgrammeCode() {
        return programmeCode;
    }

    public void setProgrammeCode(String programmeCode) {
        this.programmeCode = programmeCode;
    }

    public double getXCoordinate() {
        return XCoordinate;
    }

    public void setXCoordinate(double XCoordinate) {
        this.XCoordinate = XCoordinate;
    }

    public double getYCoordinate() {
        return YCoordinate;
    }

    public void setYCoordinate(double YCoordinate) {
        this.YCoordinate = YCoordinate;
    }

    public String getIncCrc() {
        return incCrc;
    }

    public void setIncCrc(String incCrc) {
        this.incCrc = incCrc;
    }

    public String getFmelUpdD() {
        return fmelUpdD;
    }

    public void setFmelUpdD(String fmelUpdD) {
        this.fmelUpdD = fmelUpdD;
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

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
        dest.writeString(fmelUpdD);
    }

    public Firebase(Parcel in) {
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

    public static final Parcelable.Creator<Firebase> CREATOR = new Parcelable.Creator<Firebase>() {
        public Firebase createFromParcel(Parcel in) {
            return new Firebase(in);
        }

        public Firebase[] newArray(int size) {
            return new Firebase[size];
        }
    };

    /* How To Use Parcel
    =Store in activity 1=
    Bundle bundle = new Bundle();
    bundle.putParcelableArrayList("ACCESSKEY", DATA);
    i.putExtras(bundle);

    =Retrieve at activity 2=
    Bundle b = getIntent().getExtras();
    ArrayList<Firebase> FIREBASEDATA = b.getParcelableArrayList("ACCESSKEY");
     */
}
