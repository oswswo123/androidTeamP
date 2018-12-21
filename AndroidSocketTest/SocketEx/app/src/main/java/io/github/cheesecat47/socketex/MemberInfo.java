package io.github.cheesecat47.socketex;

public class MemberInfo {
    private String memberID;
    private String memberPW;
    private boolean parkTF;

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

    public boolean isParkTF() {
        return parkTF;
    }

    public void setParkTF(boolean parkTF) {
        this.parkTF = parkTF;
    }
}
