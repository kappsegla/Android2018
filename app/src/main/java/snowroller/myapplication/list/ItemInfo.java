package snowroller.myapplication.list;

import android.graphics.drawable.Drawable;

class ItemInfo {
    public Drawable image;
    public String title;
    public String subTitle;

    public ItemInfo(Drawable drawable, String item1, String item2) {
        image = drawable;
        title = item1;
        subTitle = item2;
    }
}
