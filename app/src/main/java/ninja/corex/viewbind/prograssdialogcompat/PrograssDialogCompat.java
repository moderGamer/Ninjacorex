package ninja.corex.viewbind.prograssdialogcompat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.ColorStateList;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import ninja.corex.privateutil.PrivateColorUtil;

public class PrograssDialogCompat extends ProgressDialog {
	public PrograssDialogCompat(Context context) {
		super(context);

	}
	public PrograssDialogCompat(Context context,int def) {
		super(context,def);

	}

	public static int StyleMobileDrak() {
		return PrograssDialogCompat.THEME_DEVICE_DEFAULT_DARK;

	}

	public int StyleMoblieLight() {
		return PrograssDialogCompat.THEME_DEVICE_DEFAULT_LIGHT;
	}

	public void setShapeCut(int color, int stokerColor, float sizeShape) {
		MaterialShapeDrawable shape = new MaterialShapeDrawable(
				ShapeAppearanceModel.builder().setAllCorners(PrivateColorUtil.CornerCut, sizeShape).build());
		shape.setFillColor(ColorStateList.valueOf(color));
		shape.setStroke(1f, stokerColor);
		getWindow().getDecorView().setBackground(shape);
	}

	public void setShapeRounde(int color, int stoker, float sizeShape) {
		MaterialShapeDrawable shape = new MaterialShapeDrawable(
				ShapeAppearanceModel.builder().setAllCorners(PrivateColorUtil.CornerNormal, sizeShape).build());
		shape.setFillColor(ColorStateList.valueOf(color));
		shape.setStroke(1f, stoker);
		getWindow().getDecorView().setBackground(shape);
	}
	
	
}