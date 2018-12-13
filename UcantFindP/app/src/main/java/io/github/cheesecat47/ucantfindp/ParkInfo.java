package io.github.cheesecat47.ucantfindp;

/*
    주차장 정보를 저장하는 객체입니다.
    String parkName - 주차장 이름
    int parkCntAll - 주차장 총 자리 수
    int parkCntLeft - 주차장 빈칸 수
 */

public class ParkInfo {
    String parkName;
    int parkCntAll;
    int parkCntLeft;

    public ParkInfo(String parkName, int parkCntAll, int parkCntLeft) {
        //Constructor
        this.parkName = parkName;
        this.parkCntAll = parkCntAll;
        this.parkCntLeft = parkCntLeft;
    }
}
