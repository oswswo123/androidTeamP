package io.github.cheesecat47.socketex;

public class MemberInfo {
    private String memberID;
    private String memberPW;
    private String memberContact;
    private boolean parkTF;
    private String parkID;
    private int parkPos;

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getMemberPW() {
        return memberPW;
    }

    public void setMemberPW(String memberPW) {
        this.memberPW = memberPW;
    }

    public String getMemberContact() {
        return memberContact;
    }

    public void setMemberContact(String memberContact) {
        this.memberContact = memberContact;
    }

    public boolean isParkTF() {
        return parkTF;
    }

    public void setParkTF(boolean parkTF) {
        this.parkTF = parkTF;
    }

    public String getParkID() {
        return parkID;
    }

    public void setParkID(String parkID) {
        this.parkID = parkID;
    }

    public int getParkPos() {
        return parkPos;
    }

    public void setParkPos(int parkPos) {
        this.parkPos = parkPos;
    }
}
