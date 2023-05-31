package ninja.corex.file;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import android.os.Handler;
import android.os.Looper;
import java.io.IOException;

public class NinjaMacroFileUtil {
  /* Using java 8 to Later*/
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

  /* using in code to java 7*/
  protected static void createNewFile(String path, OnFileOperationListener listener) {
    new Thread(
            () -> {
              int lastPath = path.lastIndexOf(File.separator);
              if (lastPath > 0) {
                String past = path.substring(0, lastPath);
                makeDir(past);
              }
              File file = new File(path);
              try {
                if (!file.exists()) file.createNewFile();
                notifyListener(listener, true, path);
              } catch (IOException e) {
                notifyListener(listener, false, e.getMessage());
              }
            })
        .start();
  }

  public static void makeDir(String path) {
    if (!isExistFile(path)) {
      File file = new File(path);
      file.mkdirs();
    }
  }

  public static boolean isExistFile(String path) {
    File file = new File(path);
    return file.exists();
  }

  public static void deleteFile(String path, OnFileOperationListener listener) {
    new Thread(
            () -> {
              File file = new File(path);
              if (file.exists()) {
                if (file.delete()) {
                  notifyListener(listener, true, "File deleted successfully.");
                } else {
                  notifyListener(listener, false, "Failed to delete file.");
                }
              } else {
                notifyListener(listener, false, "File does not exist!");
              }
            })
        .start();
  }

  public static void moveFile(String srcPath, String destPath, OnFileOperationListener listener) {
    new Thread(
            () -> {
              File srcFile = new File(srcPath);
              if (!srcFile.exists()) {
                notifyListener(listener, false, "File does not exist!");
                return;
              }

              File destFile = new File(destPath);
              if (destFile.exists()) {
                notifyListener(listener, false, "Destination file already exists!");
                return;
              }

              if (!srcFile.renameTo(destFile)) {
                try {
                  copyFile(srcFile, destFile);
                  srcFile.delete();
                  notifyListener(listener, true, "File moved successfully.");
                } catch (IOException e) {
                  notifyListener(listener, false, e.getMessage());
                }
              }
            })
        .start();
  }

  public static void copyFile(File src, File dst) throws IOException {
    Files.copy(src.toPath(), dst.toPath());
  }
    public static String readFiles(String path,OnFileOperationListener getFiles) {
        createNewFile(path,getFiles);

        StringBuilder sb = new StringBuilder();
        FileReader fr = null;
        try {
            fr = new FileReader(new File(path));

            char[] buff = new char[1024];
            int length = 0;

            while ((length = fr.read(buff)) > 0) {
                sb.append(new String(buff, 0, length));
            }
            notifyListener(getFiles, true, "File read successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            notifyListener(getFiles, true, e.getMessage());
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (Exception e) {
                    notifyListener(getFiles, true, e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }
  public interface OnFileOperationListener {
    void onSuccess(String message);

    void onError(String errorMessage);
  }
    
    
    
}
