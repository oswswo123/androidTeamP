package io.github.cheesecat47.ucantfindp;

public class ParkListInfo {

    /*
    ParkInfo의 원형
    해당 class에서 ParkListActivity, ParkListAdater, ParkListItem에 제공할 객체를 만들어줍니다.
    형태는 이름 빼고 다 똑같아요1
     */
    private String parkName;
    private int parkCntAll;
    private int parkCntLeft;

    public ParkListInfo(String parkName, int parkCntAll, int parkCntLeft) {
        //Constructor
        this.parkName = parkName;
        this.parkCntAll = parkCntAll;
        this.parkCntLeft = parkCntLeft;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public int getParkCntAll() {
        return parkCntAll;
    }

    public void setParkCntAll(int parkCntAll) {
        this.parkCntAll = parkCntAll;
    }

    public int getParkCntLeft() {
        return parkCntLeft;
    }

    public void setParkCntLeft(int parkCntLeft) {
        this.parkCntLeft = parkCntLeft;
    }
}
