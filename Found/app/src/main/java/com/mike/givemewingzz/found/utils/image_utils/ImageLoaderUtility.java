package com.mike.givemewingzz.found.utils.image_utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;
import android.widget.ImageView;

import com.mike.givemewingzz.found.FoundApplication;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by GiveMeWingzz on 12/15/2015.
 */
public class ImageLoaderUtility {

    private Context context;

    private static final DisplayImageOptions blurOptions = new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .preProcessor(new BlurBitmapProcessor())
            .build();

    private static Bitmap createBlurredImage(Bitmap bitmap) {
        Bitmap.Config config = bitmap.getConfig();
        if (config == null) {
            return bitmap;
        }

        Bitmap outBitmap = bitmap.copy(config, true);

        final RenderScript rs = RenderScript.create(FoundApplication.context.getApplicationContext());
        final ScriptIntrinsicBlur script =
                ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        final Allocation input = Allocation.createFromBitmap(rs, outBitmap, Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT | Allocation.USAGE_SHARED);
        final Allocation output = Allocation.createFromBitmap(rs, outBitmap);

        script.setRadius(25.f);
        script.setInput(input);
        script.forEach(output);
        output.copyTo(outBitmap);

        rs.destroy();
        bitmap.recycle();

       /* // Darken Bitmap //
        Paint paint = new Paint();
        paint.setARGB(200, 26, 26, 26);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        Canvas canvas = new Canvas(outBitmap);
        canvas.drawBitmap(outBitmap, 0, 0, paint);*/

        return outBitmap;
    }

    private static final DisplayImageOptions options = new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .build();

    public static void blurAndLoadImage(String imageURI, ImageView viewToPopulate) {
        ImageLoader.getInstance().displayImage(imageURI, viewToPopulate, blurOptions);
    }

    public static Bitmap blurBitmap(Bitmap bitmap, float scale, int radius) {
        return BlurBitmapProcessor.fastblur(bitmap, scale, radius);
    }

}
