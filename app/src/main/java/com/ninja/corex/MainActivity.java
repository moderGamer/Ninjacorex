package com.ninja.corex;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import java.util.concurrent.Callable;
import ninja.corex.animation.AnimationUtils;
import ninja.corex.file.FileCounter;
import ninja.corex.file.NinjaMacroFileUtil;
import ninja.corex.threadui.TaskHelping;
import ninja.corex.threadui.ThreadCompat;
import ninja.corex.viewbind.imageviewzoomer.PhotoView;
import ninja.corex.viewbind.materialbind.FabCore;
import ninja.corex.viewbind.materialbind.InputLayoutCore;
import ninja.corex.viewbind.materialbind.dialogbind.DialogCore;
import ninja.corex.viewbind.prograssdialogcompat.PrograssDialogCompat;
import ninja.corex.viewbind.switchbind.SwitchButton;
import ninja.corex.viewbind.textviewcore.EditTextCore;
import ninja.corex.viewbind.textviewcore.TextViewCore;

public class MainActivity extends AppCompatActivity {
	///this Fake Data not Smpie app
	private TextViewCore core;
	private EditTextCore cores;
	private FabCore fabCore;
	//Read item
	private PhotoView photoView;
	private SwitchButton button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = findViewById(R.id.swi);
		button.setOnCheckedChangeListener((i, is) -> {

		});
		button.setAnimationDuration(2000L);
		button.setThumbDrawable(new ColorDrawable(Color.RED));
		button.setBackDrawable(new ColorDrawable(Color.BLUE));
		button.setThumbColor(ColorStateList.valueOf(Color.MAGENTA));
		photoView = (PhotoView) findViewById(R.id.img);
		fabCore = (FabCore) findViewById(R.id.fab);
	//	Ui();

		fabCore.setOnClickListener(c -> {

			AnimationUtils.FadeIn(fabCore);
			PrograssDialogCompat prograssDialogCompat = new PrograssDialogCompat(MainActivity.this,
					PrograssDialogCompat.StyleMobileDrak());
			prograssDialogCompat.setTitle("Hello");
			prograssDialogCompat.setShapeCut(Color.BLACK, Color.BLUE, 16);
			prograssDialogCompat.show();

		});
		fab();
		if (ContextCompat.checkSelfPermission(this,
				Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
				|| ContextCompat.checkSelfPermission(this,
						Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.READ_EXTERNAL_STORAGE,
					Manifest.permission.WRITE_EXTERNAL_STORAGE }, 1000);
		} else {
		}
		DialogCore dialogCore = new DialogCore(this);
		dialogCore.setDialogMakeFile("/storage/emulated/0/Codex/");
		dialogCore.setShapeCut(30f, Color.RED, Color.BLACK);
		dialogCore.show();
	}

	public void installPrograssDialogCompat() {
		PrograssDialogCompat compat = new PrograssDialogCompat(MainActivity.this,
				PrograssDialogCompat.StyleMobileDrak());
		compat.setTitle("Hello word");
		compat.setShapeCut(Color.BLACK, Color.RED, 20f);
		compat.show();
		final Handler handler = new Handler();
		handler.postDelayed(() -> {
			compat.dismiss();
		}, 300L);
	}

	public void TextViewCoreAndEditTextCore() {
		///TextView
		core.setAutoScroll();
		core.setTextColorString("#ff323232");
		///EditText
		cores.setAutoScroll();
		cores.setHintColorString("#ff323233");
		cores.setHighlightColorString("#424342");
		///طول تگست
		cores.setMaxSizeText(30);
		cores.setBackGroundColorsString("#ff3232");
		cores.setBackGroundColors(Color.RED);
	}

	private void FileData() {
		FileCounter fileCounter = new FileCounter(core);
		fileCounter.execute("");
		NinjaMacroFileUtil.createDirectory("YourPath", new NinjaMacroFileUtil.OnFileOperationListener() {
			@Override
			public void onSuccess(String message) {
			}

			@Override
			public void onError(String errorMessage) {
			}
		});
		NinjaMacroFileUtil.createFile("Your File", "data type", new NinjaMacroFileUtil.OnFileOperationListener() {
			@Override
			public void onSuccess(String message) {
			}

			@Override
			public void onError(String errorMessage) {
			}
		});
	}

	public void fab() {
		fabCore.setShapeCut(20f);
		//	fabCore.setShapeRounde(Color.BLACK, Color.RED, 20f);
		fabCore.setIcon(android.R.drawable.arrow_down_float);
		fabCore.setIconColorFilter(Color.BLUE, PorterDuff.Mode.MULTIPLY);
	}

	private void Ui() {
		new TaskHelping(new TaskHelping.OnStart() {
			@Override
			public void onPreExecute() {
				Show("Start");
			}

			@Override
			public void ondoInBackground(String... object) {
				Show("Data");
			}

			@Override
			public void onProgressUpdate(String... object) {
				Show("Prograss");
			}

			@Override
			public void onPost(String result) {
				Show("Post");
			}

			@Override
			public void onCancelled(String object) {
				Show("Cancelled!");
			}

		}).execute("");

		Callable<String> callable = () -> {
			for (int a = 0; a > 300000000; a++) {
				return "Hello word";
			}
			return null;
		};

		ThreadCompat.runInBackground(callable, new ThreadCompat.OnStartListener() {
			@Override
			public void onStart() {
				Show("Starts");
			}

		}, new ThreadCompat.OnProgressUpdateListener() {
			@Override
			public void onProgressUpdate(Object[] values) {
				Show("PrograssBar");
			}

		}, new ThreadCompat.OnCompleteListener() {
			@Override
			public void onComplete(Object result) {
				Show(result.toString());
			}

		}, new ThreadCompat.OnCancelledListener() {
			@Override
			public void onCancelled() {
				Show("Cansel");
			}

		});

		//ThreadCompat.runOnBackgroundThread(() -> {
		// کد خود را بنویسید
		///	});

	}

	public void Show(String m) {
		Toast.makeText(getApplicationContext(), m, 2).show();
	}

}