package snowroller.myapplication.list;

import android.graphics.drawable.Drawable;
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

    public void setData(Drawable d, String title, String subTitle){
        imageView.setImageDrawable(d);
        textTitle.setText(title);
        textSubTitle.setText(subTitle);
    }

    public void setData(ItemInfo info){
        imageView.setImageDrawable(info.image);
        textTitle.setText(info.title);
        textSubTitle.setText(info.subTitle);
    }
}
