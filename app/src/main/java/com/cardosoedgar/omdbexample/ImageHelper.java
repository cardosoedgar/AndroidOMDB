package com.cardosoedgar.omdbexample;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by edgarcardoso on 2/4/16.
 */
public class ImageHelper {

    Context context;

    public ImageHelper(Context context) {
        this.context = context;
    }

    public String saveImage(Bitmap image, String name) {
        if(image == null) {
            return "";
        }

        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir("images", Context.MODE_PRIVATE);
        File mypath = new File(directory,name);
        FileOutputStream fos;

        try {
            fos = new FileOutputStream(mypath);
            image.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return directory.getAbsolutePath() + "/" + name;
    }
}
