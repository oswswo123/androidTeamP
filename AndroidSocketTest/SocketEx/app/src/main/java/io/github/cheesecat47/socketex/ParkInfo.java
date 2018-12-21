package io.github.cheesecat47.socketex;

/*
    주차장 정보를 저장하는 객체입니다.
    String parkName - 주차장 이름
    int parkCntAll - 주차장 총 자리 수
    int parkCntLeft - 주차장 빈칸 수
 */

public class ParkInfo {
    // 원래 있던 거
    private String parkName;
    private int parkCntAll;
    private int parkCntLeft;


    // 새로 만든 거 ex.(1, 'N', None) or (3, 'Y', 'id');
    private int parkNum;
    private String parkTF;
    private String carID;


    public ParkInfo(int parkNum, String parkTF, String carID) {
        //Constructor
        this.parkNum = parkNum;
        this.parkTF = parkTF;
        this.carID = carID;
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
