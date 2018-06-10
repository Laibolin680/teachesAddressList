package com.addressList;

public class teachersHNU {
    private int num;
    private  String teachersName;
    private  String poSition;
    //private  String telNumber;
    //private  String qqNumber;
    private  String offNumber;
    private  String instituteHNU;
    private  String offAdd;

    public String getOffAdd() {
        return offAdd;
    }

    public void setOffAdd(String offAdd) {
        this.offAdd = offAdd;
    }

    public String getinstituteHNU() {
        return instituteHNU;
    }

    public void setinstituteHNU(String instituteHNU) {
        this.instituteHNU = instituteHNU;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTeachersName() {
        return teachersName;
    }

    public void setTeachersName(String teachersName) {
        this.teachersName = teachersName;
    }

    public String getpoSition() {
        return poSition;
    }

    public void setpoSition(String poSition) {
        this.poSition = poSition;
    }

    /*public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }*/
   /*public String getQqNumber() {
        return qqNumber;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }*/

    public String getOffNumber() {
        return offNumber;
    }

    public void setOffNumber(String offNumber) {
        this.offNumber = offNumber;
    }

    public teachersHNU(int num, String teachersName, String poSition, String offNumber, String instituteHNU) {
        this.num = num;
        this.teachersName = teachersName;
        this.poSition = poSition;
        //this.telNumber = telNumber;
        //this.qqNumber = qqNumber;
        this.offNumber = offNumber;
        this.instituteHNU = instituteHNU;
    }

    public teachersHNU() {
        this.num = 0;
        this.teachersName = null;
        this.poSition = null;
        //this.telNumber = null;
        //this.qqNumber = null;
        this.offNumber = null;
        this.instituteHNU = null;
    }

    @Override
    public String toString() {
        return "teachersHNU{" +
                "num=" + num +
                ", teachersName='" + teachersName + '\'' +
                ", poSition='" + poSition + '\'' +
               // ", telNumber='" + telNumber + '\'' +
               // ", qqNumber='" + qqNumber + '\'' +
                ", offNumber='" + offNumber + '\'' +
                ", instituteHNU='" + instituteHNU + '\'' +
                '}';
    }
}
