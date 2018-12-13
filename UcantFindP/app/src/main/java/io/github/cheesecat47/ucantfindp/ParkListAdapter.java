package io.github.cheesecat47.ucantfindp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class ParkListAdapter extends BaseAdapter {
    Context context;
    ArrayList<ParkInfo> total;

    ParkListAdapter(Context context, ArrayList<ParkInfo> total) {
        this.context = context;
        this.total = total;
    }

    @Override
    public int getCount() {
        return total.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ParkListItem parkListItem = new ParkListItem(context, total.get(position));
        return parkListItem;
    }
}
