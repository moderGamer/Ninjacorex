package ninja.corex.file;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import android.os.Handler;
import android.os.Looper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NinjaMacroFileUtil {

  public static void createDirectory(
      final String folderName, final OnFileOperationListener listener) {

    new Thread(
            () -> {
              try {
                Path dirPath = Paths.get(folderName);
                if (!Files.exists(dirPath)) {
                  Files.createDirectory(dirPath);
                }

                notifyListener(listener, true, "Directory created successfully.");
              } catch (IOException e) {
                notifyListener(listener, false, e.getMessage());
              }
            })
        .start();
  }

  public static void createFile(
      final String fileName, final String content, final OnFileOperationListener listener) {
    new Thread(
            () -> {
              try {
                Path filePath = Paths.get(fileName);
                if (!Files.exists(filePath)) {
                  Files.createFile(filePath);
                }
                Files.write(filePath, content.getBytes(), StandardOpenOption.WRITE);
                notifyListener(listener, true, "File created successfully.");
              } catch (IOException e) {
                notifyListener(listener, false, e.getMessage());
              }
            })
        .start();
  }

  public static void readFile(final String fileName, final OnFileOperationListener listener) {
    new Thread(
            () -> {
              try {
                Path filePath = Paths.get(fileName);
                if (!Files.exists(filePath)) {
                  notifyListener(listener, false, "File does not exist!");
                  return;
                }
                byte[] fileContent = Files.readAllBytes(filePath);
                notifyListener(listener, true, new String(fileContent));
              } catch (IOException e) {
                notifyListener(listener, false, e.getMessage());
              }
            })
        .start();
  }

  private static void notifyListener(
      final OnFileOperationListener listener, final boolean success, final String message) {
    if (listener != null) {
      new Handler(Looper.getMainLooper())
          .post(
              () -> {
                if (success) {
                  listener.onSuccess(message);
                } else {
                  listener.onError(message);
                }
              });
    }
  }

  public interface OnFileOperationListener {
    void onSuccess(String message);

    void onError(String errorMessage);
  }
}
