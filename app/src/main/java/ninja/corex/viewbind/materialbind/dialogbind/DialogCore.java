package ninja.corex.viewbind.materialbind.dialogbind;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import ninja.corex.R;
import ninja.corex.file.NinjaMacroFileUtil;
import ninja.corex.viewbind.materialbind.InputLayoutCore;
import ninja.corex.viewbind.textviewcore.EditTextCore;

public class DialogCore extends MaterialAlertDialogBuilder {
	private Activity activity;

	public DialogCore(Activity activity) {
		super(activity);
		this.activity = activity;
	}

	public DialogCore setDialogMakeFolder(String Folder) {
		ViewGroup viewGroup = activity.findViewById(android.R.id.content);
		View view = activity.getLayoutInflater().inflate(R.layout.ninjalayoutmakefolder, viewGroup, false);
		InputLayoutCore inputLayoutCore = view.findViewById(R.id.input);
		EditTextCore core = view.findViewById(R.id.code);
		setTitle("MakeFolder");
		core.setHint("Type name folder");
		core.setHintTextColor(ColorStateList.valueOf(Color.RED));
		setMessage("Can you make new Folder??");
		inputLayoutCore.setTag("inputLayoutCore");
		core.setTag("core");
		inputLayoutCore.setBoxBackgroundMode(InputLayoutCore.BOX_BACKGROUND_OUTLINE);

		setPositiveButton("Make folder", (__, ___) -> {
			NinjaMacroFileUtil.createDirectory(Folder + "/" + core.getText().toString(),
					new NinjaMacroFileUtil.OnFileOperationListener() {
						@Override
						public void onError(String errorMessage) {
							Toast.makeText(getContext(), errorMessage, 2).show();
						}

						@Override
						public void onSuccess(String message) {
							Toast.makeText(getContext(), message, 2).show();
						}
					});
		});
		setView(view);
		return this;
	}

	public DialogCore setDialogMakeFile(String Folder) {
		ViewGroup viewGroup = activity.findViewById(android.R.id.content);
		View view = activity.getLayoutInflater().inflate(R.layout.ninjalayoutmakefolder, viewGroup, false);
		InputLayoutCore inputLayoutCore = view.findViewById(R.id.input);
		EditTextCore core = view.findViewById(R.id.code);
		setTitle("MakeFile");
		core.setHint("Type name File");
		core.setHintTextColor(ColorStateList.valueOf(Color.RED));
		setMessage("Can you make new Folder??");
		inputLayoutCore.setTag("inputLayoutCore");
		inputLayoutCore.setBoxBackgroundMode(InputLayoutCore.BOX_BACKGROUND_OUTLINE);
		core.setTag("core");

		setPositiveButton("Make file", (__, ___) -> {
			NinjaMacroFileUtil.createFile(Folder + "/" + core.getText().toString(), "\n\n s",
					new NinjaMacroFileUtil.OnFileOperationListener() {
						@Override
						public void onError(String errorMessage) {
							Toast.makeText(getContext(), errorMessage, 2).show();
						}

						@Override
						public void onSuccess(String message) {
							Toast.makeText(getContext(), message, 2).show();
						}
					});
		});
		setView(view);
		return this;
	}

	public void setShapeCut(float size, int strokerColor, int color) {
		MaterialShapeDrawable shapeDrawable = new MaterialShapeDrawable(
				ShapeAppearanceModel.builder().setAllCorners(CornerFamily.CUT, size).build());
		shapeDrawable.setFillColor(ColorStateList.valueOf(color));
		shapeDrawable.setStroke(1f, strokerColor);
		setBackground(shapeDrawable);
	}

	public void setShapeRounded(float size, int strokerColor, int color) {
		MaterialShapeDrawable shapeDrawable = new MaterialShapeDrawable(
				ShapeAppearanceModel.builder().setAllCorners(CornerFamily.ROUNDED, size).build());
		shapeDrawable.setFillColor(ColorStateList.valueOf(color));
		shapeDrawable.setStroke(1f, strokerColor);
		setBackground(shapeDrawable);
	}
}