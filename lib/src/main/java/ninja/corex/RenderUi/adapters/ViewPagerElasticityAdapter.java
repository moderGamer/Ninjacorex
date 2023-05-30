package ninja.corex.RenderUi.adapters;


import android.view.View;

import androidx.viewpager.widget.ViewPager;
import ninja.corex.RenderUi.HorizontalElasticityBounceEffect;

/**
 * Created by Bruce too
 * Enhance by amit
 * On 2016/6/16
 * At 14:51
 * An adapter to enable over-scrolling over object of {@link ViewPager}
 *
 * @see HorizontalElasticityBounceEffect
 */
public class ViewPagerElasticityAdapter implements IElasticityAdapter, ViewPager.OnPageChangeListener {

    protected final ViewPager mViewPager;

    protected int mLastPagerPosition = 0;
    protected float mLastPagerScrollOffset;

    public ViewPagerElasticityAdapter(ViewPager viewPager) {
        this.mViewPager = viewPager;

        mViewPager.addOnPageChangeListener(this);

        mLastPagerPosition = mViewPager.getCurrentItem();
        mLastPagerScrollOffset = 0f;
    }

    @Override
    public View getView() {
        return mViewPager;
    }

    @Override
    public boolean isInAbsoluteStart() {

        return mLastPagerPosition == 0 &&
                mLastPagerScrollOffset == 0f;
    }

    @Override
    public boolean isInAbsoluteEnd() {

        return mLastPagerPosition == mViewPager.getAdapter().getCount()-1 &&
                mLastPagerScrollOffset == 0f;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mLastPagerPosition = position;
        mLastPagerScrollOffset = positionOffset;
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
