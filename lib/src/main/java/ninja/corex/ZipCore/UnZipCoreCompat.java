package ninja.corex.ZipCore;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.*;
import java.io.*;
import java.util.zip.*;
import ninja.corex.viewbind.materialbind.dialogbind.DialogCore;
import ninja.corex.viewbind.prograssdialogcompat.*;

/** Code by ninja coder how to using? see MainActivity */
public class UnZipCoreCompat extends AsyncTask<String, Object, Boolean> {

  private Context setContext;
  private ProgressDialogCompat progressDialog;
  private String destDirectory = "";
  private static int ColorBackground = Color.parseColor("#FF281D1B");
  private static int ColorStoker = Color.parseColor("#FFFCB07D");
  private OnResult mresult;

  public UnZipCoreCompat(Context setContext, OnResult mresult) {
    this.setContext = setContext;
    this.mresult = mresult;
  }

  @Override
  protected void onPreExecute() {
    super.onPreExecute();
    progressDialog = new ProgressDialogCompat(setContext, ProgressDialogCompat.StyleMobileDrak());
    progressDialog.setTitle("UnZip");
    progressDialog.setMessage("UnZiping...");
    progressDialog.setIndeterminate(false);
    progressDialog.setMax(100);
    progressDialog.setShapeCut(ColorBackground, ColorStoker, 20f);
    progressDialog.setProgressStyle(ProgressDialogCompat.STYLE_HORIZONTAL);
    progressDialog.setCancelable(false);
    progressDialog.show();
  }

  @Override
  protected Boolean doInBackground(String... params) {
    String zipFilePath = params[0];
    destDirectory = params[1];

    // ساخت شیء ZipInputStream
    ZipInputStream zipIn = null;
    try {
      zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
      long totalBytes = new File(zipFilePath).length();
      long extractedBytes = 0;
      byte[] buffer = new byte[4096];
      int readBytes;

      // خواندن و استخراج فایل‌ها از ZIP
      ZipEntry entry = zipIn.getNextEntry();
      while (entry != null) {
        String filePath = destDirectory + File.separator + entry.getName();
        if (!entry.isDirectory()) {
          extractFile(zipIn, filePath, buffer);
          extractedBytes += entry.getCompressedSize();
          int progress = (int) ((extractedBytes * 100) / totalBytes);
          publishProgress(progress, filePath);
        } else {
          File dir = new File(filePath);
          dir.mkdirs();
        }
        zipIn.closeEntry();
        entry = zipIn.getNextEntry();
      }
      zipIn.close();
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  // استخراج فایل‌ها
  private void extractFile(ZipInputStream zipIn, String filePath, byte[] buffer)
      throws IOException {
    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
    int readBytes;
    while ((readBytes = zipIn.read(buffer)) != -1) {
      bos.write(buffer, 0, readBytes);
    }
    bos.flush();
    bos.close();
  }

  @Override
  protected void onProgressUpdate(Object... values) {
    int progress = (int) values[0];
    progressDialog.setProgress(progress);
    String filePath = (String) values[1];
    progressDialog.setMessage("UnZip from \n " + filePath.trim());
  }

  @Override
  protected void onPostExecute(Boolean result) {
    progressDialog.dismiss();
    if (result) {
      // نمایش پیغام موفقیت
      Dialoginit("Done", "saved for " + destDirectory.toLowerCase().trim());
      mresult.EndTask();
    } else {
      // نمایش پیغام خطا
      Dialoginit("Error", "");
      mresult.EndTask();
    }
  }

  private void Dialoginit(CharSequence title, CharSequence msg) {
    DialogCore dialog = new DialogCore(setContext);
    dialog.setTitle(title);
    dialog.setMessage(msg);
    dialog.setShapeCut(20f, ColorStoker, ColorBackground);
    dialog.setPositiveButton("ok", null);
    dialog.show();
  }

  public interface OnResult {
    void EndTask();
  }
}
