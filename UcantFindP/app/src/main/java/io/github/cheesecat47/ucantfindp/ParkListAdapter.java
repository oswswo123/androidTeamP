package io.github.cheesecat47.ucantfindp;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/*
    ParkListActivity에서 ListView와 ArrayList를 연결해주는 어댑터입니다.
    Context context - 어디에 표시할건지.
    ArrayList<ParkInfo> total - ParkListActivity에서 받아온 배열. 이름 다르게 해놔서 확인 잘 해주세요.
 */

public class ParkListAdapter extends BaseAdapter {
    Context context;
    ArrayList<ParkInfo> total;

    ParkListAdapter(Context context, ArrayList<ParkInfo> total) {   //Constructor
        this.context = context;
        this.total = total;
    }

    @Override
    public int getCount() {     //총 크기. 이거 꼭 있어야됨
        return total.size();
    }

    @Override
    public Object getItem(int position) {
        return total.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {     //이것도 꼭.
        //이게 액티비티에서 주차장 한 칸으로 보일 내용.
        ParkListItem parkListItem = new ParkListItem(context, total.get(position));
        //한 칸 아이템 만들어서                   context 그대로, 배열 값 하나 넘깁니다.
        Log.i("TAG", "ParkListAdatper: make new ParkListItem and return");
        return parkListItem;    //그래서 만든거 리턴.
    }
}
