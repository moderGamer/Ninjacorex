package ninja.corex.viewbind.materialbind.sheet;

import android.content.Context;
import com.google.android.material.card.MaterialCardView;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.hardware.camera2.params.ColorSpaceTransform;
import android.text.TextShaper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import ninja.corex.R;
import ninja.corex.exceptionbind.ColorNotFoundException;

public class SheetCompat extends BottomSheetDialog {

  private OnCallBackSheet back;

  public SheetCompat(Context context) {
    super(context);
    inits();
  }

  public void inits() {
    getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
  }

  public void setDialogSheetView(String title, String Massges, OnCallBackSheet back) {

    View view;
    view = getLayoutInflater().inflate(R.layout.layoutinfo, null);
    Button btn = view.findViewById(R.id.miser);
    LinearLayout mlayout = view.findViewById(R.id.corner);
    mlayout.setBackground(db(Color.BLACK, 99f));
    TextView titles = view.findViewById(R.id.title);
    titles.setText(title);
    TextView msg = view.findViewById(R.id.massges);
    MaterialCardView card = view.findViewById(R.id.card);
    ShapeAppearanceModel.Builder app = new ShapeAppearanceModel.Builder();
    app.setTopLeftCorner(CornerFamily.CUT, 20f);
    app.setTopRightCorner(CornerFamily.CUT, 20f);
    card.setShapeAppearanceModel(app.build());
    titles.setTextColor(Color.BLACK);
        setColorText(msg,Color.RED);
        setColorText(titles,Color.RED);
        
    btn.setOnClickListener(
        c -> {
          back.onDismiss();
          dismiss();
        });
    setContentView(view);
  }

  public interface OnCallBackSheet {
    void onDismiss();
  }

  public GradientDrawable db(int colors, float size) {
    GradientDrawable ds = new GradientDrawable();
    ds.setColor(colors);
        ds.setStroke(1,Color.RED);
    ds.setCornerRadius(size);
    return ds;
  }
    public void setColorText(TextView tv,int colors){
        if(colors >0){
            throw new ColorNotFoundException("dont using zero");
        }else{
            tv.setTextColor(colors);
        }
    }
}
