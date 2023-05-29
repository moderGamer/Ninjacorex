package ninja.corex.glidehelper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfRenderer;
import android.os.ParcelFileDescriptor;
import android.media.MediaMetadataRetriever;
import android.widget.ImageView;
import android.widget.TextView;
import ninja.corex.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.Priority;
import java.io.IOException;
import java.io.FileInputStream;
import com.bumptech.glide.request.target.Target;
import java.io.File;

public class GlideCompat {
  public static void GlideNormal(ImageView imageView, String path) {
    Glide.with(imageView.getContext())
        .load(new File(path))
        .thumbnail(0.1f)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)
        .transform(new RoundedCornersTransformation(RenderSize()))
        .into(imageView);
  }

  public static void GlideNormal(ImageView imageView, File path) {
    Glide.with(imageView.getContext())
        .load(path)
        .thumbnail(0.1f)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)
        .transform(new RoundedCornersTransformation(RenderSize()))
        .into(imageView);
  }

  public static void LoadMp4(ImageView imageView, String string) {
    Glide.with(imageView.getContext())
        .load(new File(string))
        .thumbnail(0.1f)
        .transform(new RoundedCornersTransformation(RenderSize()))
        .priority(Priority.HIGH)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(imageView);
  }

  public static void LoadImage(ImageView imageView, String string, TextView textView) {
    Glide.with(imageView.getContext())
        .load(new File(string))
        .thumbnail(0.1f)
        .transform(new RoundedCornersTransformation(RenderSize()))
        .priority(Priority.HIGH)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(imageView);
    getSizeImage(string, textView);
  }

  public static void LoadVideo(ImageView imageView, String string, TextView textView) {
    Glide.with(imageView.getContext())
        .load(string)
        .thumbnail(0.1f)
        .transform(new RoundedCornersTransformation(RenderSize()))
        .error(android.R.drawable.gallery_thumb)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)
        .into(imageView);
    textView.setText(getVideoWhich(string).concat(",".concat(getVideohighlights(string))));
  }

  protected static String getVideoWhich(String d) {
    MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
    mediaMetadataRetriever.setDataSource(d);
    return mediaMetadataRetriever.extractMetadata(18);
  }

  protected static String getVideohighlights(String dataBase) {
    MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
    mediaMetadataRetriever.setDataSource(dataBase);
    return mediaMetadataRetriever.extractMetadata(19);
  }

  private static final void getSizeImage(String string, TextView textView) {
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(string, options);
    int imageHeight = options.outHeight;
    int imageWidth = options.outWidth;
    textView.setText(String.valueOf(imageWidth).concat("x".concat(String.valueOf(imageHeight))));
  }

  public static void loadImgPdf(File file, ImageView imageView) throws IOException {
    FileInputStream inputStream = new FileInputStream(file);
    try {
      ParcelFileDescriptor fileDescriptor = ParcelFileDescriptor.dup(inputStream.getFD());
      Bitmap bitmap = pdfPageToBitmap(fileDescriptor);
      Glide.with(imageView.getContext())
          .load(bitmap)
          .transform(new RoundedCornersTransformation(RenderSize()))
          .diskCacheStrategy(DiskCacheStrategy.ALL)
          .priority(Priority.HIGH)
          .error(android.R.drawable.gallery_thumb)
          .into(imageView);
    } finally {
      inputStream.close();
    }
  }

  private static Bitmap pdfPageToBitmap(ParcelFileDescriptor fileDescriptor) throws IOException {
    PdfRenderer renderer = new PdfRenderer(fileDescriptor);
    PdfRenderer.Page page = renderer.openPage(0);
    // create a new bitmap with the same dimensions as the page
    Bitmap bitmap = Bitmap.createBitmap(page.getWidth(), page.getHeight(), Bitmap.Config.ARGB_8888);
    page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
    page.close();
    renderer.close();
    return bitmap;
  }

  protected static int RenderSize() {
    return 21;
  }
}
