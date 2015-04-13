/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Omada Health, Inc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.omadahealth.slidepager.lib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.daimajia.easing.Glider;
import com.daimajia.easing.Skill;
import com.github.omadahealth.slidepager.lib.R;
import com.github.omadahealth.slidepager.lib.interfaces.OnWeekListener;
import com.github.omadahealth.slidepager.lib.interfaces.PageChildInterface;
import com.github.omadahealth.typefaceview.TypefaceTextView;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by stoyan on 4/7/15.
 */
public class WeekSlideView extends LinearLayout implements PageChildInterface {
    /**
     * The tag for logging
     */
    private static final String TAG = "WeekSlideView";

    /**
     * An array that holds all the {@link DayProgressView} for this layout
     */
    private List<DayProgressView> mDays = new ArrayList<>(7);

    /**
     * The left textview
     */
    private TypefaceTextView mLeftTextView;

    /**
     * The right textview
     */
    private TypefaceTextView mRightTextView;

    /**
     * True of we want to show {@link #mLeftTextView}
     */
    private boolean mShowLeftText;

    /**
     * True of we want to show {@link #mRightTextView}
     */
    private boolean mShowRightText;

    /**
     * The current day sliding {@link android.widget.ImageView} we display
     * below the currently selected {@link DayProgressView} from {@link #mDays}
     */
    private SelectedImageView mSelectedImageView;

    /**
     * The callback listener for when views are clicked
     */
    private OnWeekListener mCallback;


    public WeekSlideView(Context context) {
        this(context, null);
    }

    public WeekSlideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    public void loadStyledAttributes(TypedArray attributes) {
        if (attributes != null) {
            mShowLeftText = attributes.getBoolean(R.styleable.SlidePager_slide_show_week, true);
            mShowRightText = attributes.getBoolean(R.styleable.SlidePager_slide_show_date, true);

            mLeftTextView.setVisibility(mShowLeftText ? VISIBLE : GONE);
            mRightTextView.setVisibility(mShowRightText ? VISIBLE : GONE);
        }
    }

    /**
     * Initiate the view and start butterknife injection
     *
     * @param context
     */
    private void init(Context context) {
        if (!isInEditMode()) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.view_week_slide, this);
            ButterKnife.inject(this, view);

            injectDays();
            setListeners();
        }
    }

    /**
     * Inject the views into {@link #mDays}
     */
    private void injectDays() {
        mDays.add(ButterKnife.<DayProgressView>findById(this, R.id.day_progress_1));
        mDays.add(ButterKnife.<DayProgressView>findById(this, R.id.day_progress_2));
        mDays.add(ButterKnife.<DayProgressView>findById(this, R.id.day_progress_3));
        mDays.add(ButterKnife.<DayProgressView>findById(this, R.id.day_progress_4));
        mDays.add(ButterKnife.<DayProgressView>findById(this, R.id.day_progress_5));
        mDays.add(ButterKnife.<DayProgressView>findById(this, R.id.day_progress_6));
        mDays.add(ButterKnife.<DayProgressView>findById(this, R.id.day_progress_7));

        mLeftTextView = ButterKnife.findById(this, R.id.left_textview);
        mRightTextView = ButterKnife.findById(this, R.id.right_textview);

        mSelectedImageView = ButterKnife.findById(this, R.id.selected_day_image_view);
        mSelectedImageView.setSelectedViewId(mDays.get(3).getId());

    }

    public void animateSelectedTranslation(View view){
        AnimatorSet set = new AnimatorSet();
        final Float offset =  -1 * this.getWidth() + view.getWidth()/2 + view.getX();
        mSelectedImageView.setTag(R.id.selected_day_image_view, offset);
        mSelectedImageView.setSelectedViewId(view.getId());
        set.playTogether(Glider.glide(Skill.QuadEaseInOut, 1000, ObjectAnimator.ofFloat(mSelectedImageView, "x", mSelectedImageView.getX(), offset)));
        set.setDuration(1000);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();
    }

    /**
     * Set up listeners for all the views in {@link #mDays}
     */
    private void setListeners() {
        for (final DayProgressView dayProgressView : mDays) {
            if (dayProgressView != null) {
                dayProgressView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int index = dayProgressView.getIntTag();
                        animateSelectedTranslation(view);
                        if (mCallback != null) {
                            mCallback.onDaySelected(index);
                        }
                    }
                });
            }
        }
    }

    /**
     * Sets the listener for click events in this view
     * @param listener
     */
    public void setListener(OnWeekListener listener) {
        this.mCallback = listener;
    }

    public SelectedImageView getSelectedImageView() {
        return mSelectedImageView;
    }
}