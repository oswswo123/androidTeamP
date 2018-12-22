package io.github.cheesecat47.ucantfindp;

public class MemberInfo {
    private String memberID;
    private String memberPW;
    private String parkTF;

    public MemberInfo(String memberID, String memberPW, String parkTF) {
        //Constructor
        this.memberID = memberID;
        this.memberPW = memberPW;
        this.parkTF = parkTF;
    }

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

    public String isParkTF() {
        return parkTF;
    }

    public void setParkTF(String parkTF) {
        this.parkTF = parkTF;
    }
}