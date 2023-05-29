package ninja.corex.file;

import android.os.*;
import android.view.View;
import java.util.stream.Collectors;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.nio.file.Paths;
import java.nio.file.Path;
import ninja.corex.viewbind.textviewcore.TextViewCore;

public class FileCounter extends AsyncTask<String, Void, String> {
	private TextViewCore core;

	public FileCounter(TextViewCore core) {
		this.core = core;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		core.setVisibility(View.GONE);
	}

	@Override
	protected String doInBackground(String... params) {
		try {
			Path path = Paths.get(params[0]);

			// Get list of all files and directories in the directory
			List<Path> fileList = Files.list(path).collect(Collectors.toList());

			if (fileList.isEmpty()) {
				// If no files or directories found, return "Folder : 0 Files: 0"
				return "پوشه  : 0 فایل : 0";
			} else {
				// Get the number of folders and files in the directory
				long folderCount = fileList.stream().parallel().filter(Files::isDirectory).count();

				long fileCount = fileList.stream().parallel().filter(Files::isRegularFile).count();

				return "پوشه : " + folderCount + " فایل: " + fileCount;
			}
		} catch (IOException e) {
			core.setText(e.getMessage());
		}

		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		if (result != null) {
			core.setVisibility(View.VISIBLE);
			core.setText(result);
		} else {
			throw new RuntimeException("Error");
		}

	}

}