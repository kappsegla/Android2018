package snowroller.myapplication.list;

import android.graphics.drawable.Drawable;

import com.google.firebase.firestore.Exclude;

class ItemInfo {
    @Exclude
    public Drawable image;
    public String title;
    public String subTitle;
    @Exclude
    public String id;

    public ItemInfo(){

    }


    public ItemInfo(Drawable drawable, String item1, String item2) {
        image = drawable;
        title = item1;
        subTitle = item2;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }
}
