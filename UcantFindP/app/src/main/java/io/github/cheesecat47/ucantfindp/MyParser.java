package io.github.cheesecat47.ucantfindp;

import java.util.ArrayList;

public class MyParser {
    ArrayList<ParkInfo> parkInfoArr = new ArrayList<ParkInfo>();
    int parkNum;
    String parkTF;
    String carID;

    ArrayList<MemberInfo> memberInfoArr = new ArrayList<MemberInfo>();
    String memberID;
    String memberPW;

    public MyParser(boolean isMember, String inputdata) {
        String str = inputdata;

        String str1 = str.replace(")", "");
        String str2 = str1.replace("(", "");
        String str3 = str2.replace(" ", "");
        String str4 = str3.replace("\'","");
        String str5 = str4.replace("[", "");
        String str6 = str5.replace("]", "");

        String[] buffer = str6.split(",");
        if(isMember){
            for (int i = 0; i < buffer.length; i += 3) {
                memberID = buffer[i];
                memberPW = buffer[i + 1];
                parkTF = buffer[i + 2];
                memberInfoArr.add(new MemberInfo(memberID, memberPW, parkTF));
            }
        }
        else {
            for (int i = 0; i < buffer.length; i += 3) {
                parkNum = Integer.parseInt(buffer[i]);
                parkTF = buffer[i + 1];
                carID = buffer[i + 2];
                parkInfoArr.add(new ParkInfo(parkNum, parkTF, carID));
            }
        }
    }

    public ArrayList<ParkInfo> getParkInfoArr() { return parkInfoArr;  }

    public ArrayList<MemberInfo> getMemberInfoArr() {
        return memberInfoArr;
    }
}