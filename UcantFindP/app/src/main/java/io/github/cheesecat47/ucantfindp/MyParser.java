package io.github.cheesecat47.ucantfindp;

import java.util.ArrayList;

public class MyParser {
    ArrayList<ParkInfo> parkInfoArr = new ArrayList<ParkInfo>();
    int parkNum;
    String parkTF;
    String carID;

    public MyParser(String inputdata) {
        String str = "(1, 'N', None)(2, 'Y', None)(3, 'Y', None)(4, 'Y', None)(5, 'N', None)(6, 'Y', None)";

        String str1 = str.replace(")", ",");
        String str2 = str1.replace("(", "");
        String str3 = str2.replace(" ", "");
        String str4 = str3.replace("\'","");

        String[] buffer = str4.split(",");

        for (int i = 0; i < buffer.length; i+=3) {
            parkNum = Integer.parseInt(buffer[i]);
            parkTF = buffer[i+1];
            carID = buffer[i+2];
            parkInfoArr.add(new ParkInfo(parkNum, parkTF, carID));
        }
    }

    public ArrayList<ParkInfo> getParkInfoArr() { return parkInfoArr;  }
}
