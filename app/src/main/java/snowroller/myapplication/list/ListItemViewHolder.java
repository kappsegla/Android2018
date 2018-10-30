package snowroller.myapplication.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import snowroller.myapplication.R;

class ListItemViewHolder extends RecyclerView.ViewHolder {

    public View itemView;
    public ImageView imageView;
    public TextView textTitle;
    public TextView textSubTitle;


    public ListItemViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        imageView = itemView.findViewById(R.id.imageView);
        textTitle = itemView.findViewById(R.id.textTitle);
        textSubTitle = itemView.findViewById(R.id.textSubTitle);
    }
}
