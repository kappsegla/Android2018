package snowroller.myapplication.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import snowroller.myapplication.R;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<ListItemViewHolder> {

    private List<ItemInfo> list;

    public MyRecyclerViewAdapter(@NonNull List<ItemInfo> list){
        this.list = list;
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewHolderType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup,false);
        return new ListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder listItemViewHolder, int position) {
        ItemInfo item = list.get(position);
        listItemViewHolder.setData(item);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(ItemInfo info){
        list.add(info);
        this.notifyItemInserted(list.size()-1);
    }

    public void removeItem(int index){
        if( index >= 0 && index < list.size()) {
            list.remove(index);
            this.notifyItemRemoved(index);
        }
    }


    public void removeItem(String id) {
        for (int i = 0; i < list.size(); i++) {
            if( list.get(i).id.equals(id) ) {
                removeItem(i);
                return;
            }
        }
    }

    public ItemInfo getItem(int adapterPosition) {
        return list.get(adapterPosition);
    }
}
