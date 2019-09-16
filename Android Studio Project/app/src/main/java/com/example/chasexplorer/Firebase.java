package com.example.chasexplorer;

import android.os.Parcel;
import android.os.Parcelable;

class Firebase implements Parcelable {

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

    public Firebase() {
        // empty default constructor, necessary for Firebase to be able to deserialize users
    }

    public Firebase(String HCICode, String HCIName, String licenceType, String HCITel, String postalCD, String addrType, String blkHseNo, String floorNo, String unitNo, String streetName, String buildingName, String clinicProgrammeCode, double XCoordinate, double YCoordinate, String incCrc, String fmelUpdD) {
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

    public void setHCICode(String HCICode) {
        this.HCICode = HCICode;
    }

    public String getHCIName() {
        return HCIName;
    }

    public void setHCIName(String HCIName) {
        this.HCIName = HCIName;
    }

    public String getLicenceType() {
        return licenceType;
    }

    public void setLicenceType(String licenceType) {
        this.licenceType = licenceType;
    }

    public String getHCITel() {
        return HCITel;
    }

    public void setHCITel(String HCITel) {
        this.HCITel = HCITel;
    }

    public String getPostalCD() {
        return postalCD;
    }

    public void setPostalCD(String postalCD) {
        this.postalCD = postalCD;
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

    public String getClinicProgrammeCode() {
        return clinicProgrammeCode;
    }

    public void setClinicProgrammeCode(String clinicProgrammeCode) {
        this.clinicProgrammeCode = clinicProgrammeCode;
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
        dest.writeString(HCICode);
        dest.writeString(HCIName);
        dest.writeString(licenceType);
        dest.writeString(HCITel);
        dest.writeString(postalCD);
        dest.writeString(addrType);
        dest.writeString(blkHseNo);
        dest.writeString(floorNo);
        dest.writeString(unitNo);
        dest.writeString(streetName);
        dest.writeString(buildingName);
        dest.writeString(clinicProgrammeCode);
        dest.writeDouble(XCoordinate);
        dest.writeDouble(YCoordinate);
        dest.writeString(incCrc);
        dest.writeString(fmelUpdD);
    }

    public Firebase(Parcel in) {
        HCICode = in.readString();
        HCIName = in.readString();
        licenceType = in.readString();
        HCITel = in.readString();
        postalCD = in.readString();
        addrType = in.readString();
        blkHseNo = in.readString();
        floorNo = in.readString();
        unitNo = in.readString();
        streetName = in.readString();
        buildingName = in.readString();
        clinicProgrammeCode = in.readString();
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
