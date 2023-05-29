package ninja.corex;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyView extends LinearLayout {

	private Context context;
	private LinearLayout linear3;
	private TextView textview1;
	private ImageView imageview1;

	public MyView(Context context) {
		super(context);
		init();
	}

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		this.context = getContext();
		LinearLayout root = new LinearLayout(context);
		LinearLayout.LayoutParams rootLayoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		root.setOrientation(LinearLayout.VERTICAL);
		root.setLayoutParams(rootLayoutParams);
		linear3 = new LinearLayout(context);
		LinearLayout.LayoutParams linear3LayoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		linear3.setTag(/*ID*/"linear3");
		linear3.setPadding(8, 8, 8, 8);
		linear3.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
		linear3.setOrientation(LinearLayout.HORIZONTAL);
		root.addView(linear3);
		linear3.setLayoutParams(linear3LayoutParams);

		textview1 = new TextView(context);
		LinearLayout.LayoutParams textview1LayoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);

		textview1.setTag(/*ID*/"textview1");
		textview1.setFocusable(false);
		textview1.setPadding(8, 8, 8, 8);
		textview1.setText("Hello in ToolBar");
		textview1.setTextSize(16);
		textview1.setTextColor(Color.WHITE);
		linear3.addView(textview1);
		textview1.setLayoutParams(textview1LayoutParams);

		imageview1 = new ImageView(context);
		LinearLayout.LayoutParams imageview1LayoutParams = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

		imageview1.setTag(/*ID*/"imageview1");
		imageview1.setFocusable(false);
		imageview1.setImageResource(R.drawable.ic_launcher_foreground);
		imageview1.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
		linear3.addView(imageview1);
		imageview1.setLayoutParams(imageview1LayoutParams);

		addView(root);
	}

	//Getter for linear3
	private LinearLayout getLinear3() {
		return this.linear3;
	}

	//Getter and setter for textview1
	private TextView getTextview1() {
		return this.textview1;
	}

	private void setTextview1(String text) {
		this.textview1.setText(text);
	}

	//Getter and setter for imageview1
	private ImageView getImageview1() {
		return this.imageview1;
	}

	private void setImageview1(int resId) {
		this.imageview1.setImageResource(resId);
	}
}
