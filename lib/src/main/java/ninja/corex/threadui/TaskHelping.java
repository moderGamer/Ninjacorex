package ninja.corex.threadui;

import android.os.AsyncTask;

public class TaskHelping extends AsyncTask<String, String, String> {
	protected OnStart onStart;

	/**
	 * Making Class To Helping AsyncTask
	 * code by ninja coder
	 */

	public interface OnStart {
		void onPreExecute();

		void ondoInBackground(String... object);

		void onProgressUpdate(String... object);

		void onPost(String result);

		void onCancelled(String object);
	}

	public TaskHelping(OnStart onStart) {
		this.onStart = onStart;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		onStart.onPreExecute();
	}

	@Override
	protected String doInBackground(String... object) {
		onStart.ondoInBackground(object);
		return "";
	}

	@Override
	protected void onProgressUpdate(String... arg0) {
		super.onProgressUpdate(arg0);
		onStart.onProgressUpdate(arg0);
	}

	@Override
	protected void onPostExecute(String arg0) {
		super.onPostExecute(arg0);
		onStart.onPost(arg0);
	}

	@Override
	protected void onCancelled(String arg0) {
		super.onCancelled(arg0);
		onStart.onCancelled(arg0);
	}

}