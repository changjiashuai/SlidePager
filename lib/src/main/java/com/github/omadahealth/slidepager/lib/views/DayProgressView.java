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
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.daimajia.easing.Glider;
import com.daimajia.easing.Skill;
import com.github.OrangeGangsters.circularbarpager.library.CircularBar;
import com.github.omadahealth.slidepager.lib.R;
import com.github.omadahealth.slidepager.lib.utils.DayProgress;
import com.github.omadahealth.typefaceview.TypefaceTextView;
import com.github.omadahealth.typefaceview.TypefaceType;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by stoyan on 4/2/15.
 */
public class DayProgressView extends RelativeLayout {
    /**
     * The tag for logging
     */
    private static final String TAG = "DayProgressView";

    /**
     * The left streak
     */
    private ImageView mLeftStreak;

    /**
     * The right streak
     */
    private ImageView mRightStreak;

    /**
     * The completed check mark that goes inside {@link #mCircularBar}
     */
    private ImageView mCheckMark;

    /**
     * The circular progress bar
     */
    private CircularBar mCircularBar;

    /**
     * The textview that shows today's day name
     */
    private TypefaceTextView mDayOfWeek;

    /**
     * The default {@link #mDayOfWeek} is initialized in its attrs
     */
    private TypefaceType mDefaultDayTypeface;

    /**
     * The duration of the easing in of {@link #mLeftStreak} and {@link #mRightStreak}
     */
    private static final int EASE_IN_DURATION = 350;

    /**
     * For save and restore instance of progressbar
     */
    private static final String INSTANCE_STATE = "saved_instance";

    /**
     * The color of the progress already achieved for {@link #mCircularBar}
     */
    private int mReachColor;

    /**
     * The fill color for {@link #mCircularBar}
     */
    private int mFillColor;

    /**
     * The default outline color for {@link #mCircularBar}
     */
    private int mOutlineColor;

    /**
     * The default fill color for {@link #mCircularBar} when progress is 100
     */
    private int mCompletedFillColor;

    /**
     * The default fill color for {@link #mCircularBar} when progress is below 100
     */
    private int mNotCompletedFillColor;

    /**
     * The progress color for {@link #mCircularBar} when progress is 100
     */
    private int mCompletedColor;

    /**
     * The progress reached color for {@link #mCircularBar} when progress is below 100
     */
    private int mNotCompletedReachColor;

    /**
     * The progress color outline for {@link #mCircularBar} when progress is below 100
     */
    private int mNotCompletedOutlineColor;

    /**
     * The progress reached color for {@link #mCircularBar} when it is today's date
     */
    private int mTodayReachColor;

    /**
     * The progress color outline for {@link #mCircularBar} when it is today's date
     */
    private int mTodayOutlineColor;

    /**
     * The progress fill color for today's date
     */
    private int mTodayFillColor;

    /**
     * The days in a week
     */
    private static String[] mWeekDays;

    /**
     * The sibling {@link DayProgressView} of this class
     */
    private List<DayProgressView> mSiblings;

    /**
     * Boolean that controls if the {@link #mLeftStreak} and  {@link #mRightStreak} showed
     * be shown
     */
    private boolean mShowStreaks;

    /**
     * Boolean that controls if we should use the special today colors for this view
     */
    private boolean isSpecial;

    /**
     * The left and right streaks
     */
    public enum STREAK {
        LEFT_STREAK,
        RIGHT_STREAK
    }

    public DayProgressView(Context context) {
        this(context, null);
    }

    public DayProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        injectViews(context);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        final Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATE, super.onSaveInstanceState());
//        bundle.putBoolean(INSTANCE_START_LINE_ENABLED, isStartLineEnabled());
//        bundle.putFloat(INSTANCE_CLOCKWISE_REACHED_BAR_HEIGHT, getClockwiseReachedArcWidth());
//        bundle.putFloat(INSTANCE_CLOCKWISE_OUTLINE_BAR_HEIGHT, getClockwiseOutlineArcWidth());
//        bundle.putInt(INSTANCE_CLOCKWISE_REACHED_BAR_COLOR, getClockwiseReachedArcColor());
//        bundle.putInt(INSTANCE_CLOCKWISE_OUTLINE_BAR_COLOR, getClockwiseOutlineArcColor());
//        bundle.putFloat(INSTANCE_COUNTER_CLOCKWISE_REACHED_BAR_HEIGHT, getCounterClockwiseReachedArcWidth());
//        bundle.putFloat(INSTANCE_COUNTER_CLOCKWISE_OUTLINE_BAR_HEIGHT, getCounterClockwiseOutlineArcWidth());
//        bundle.putInt(INSTANCE_COUNTER_CLOCKWISE_REACHED_BAR_COLOR, getCounterClockwiseReachedArcColor());
//        bundle.putInt(INSTANCE_COUNTER_CLOCKWISE_OUTLINE_BAR_COLOR, getCounterClockwiseOutlineArcColor());
//        bundle.putBoolean(INSTANCE_CIRCLE_FILL_ENABLED, isCircleFillEnabled());
//        bundle.putInt(INSTANCE_CIRCLE_FILL_COLOR, getCircleFillColor());
//        bundle.putInt(INSTANCE_MAX, getMax());
//        bundle.putFloat(INSTANCE_PROGRESS, getProgress());
//        bundle.putString(INSTANCE_SUFFIX, getSuffix());
//        bundle.putString(INSTANCE_PREFIX, getPrefix());
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            final Bundle bundle = (Bundle) state;
//            mStartLineEnabled = bundle.getBoolean(INSTANCE_START_LINE_ENABLED);
//            mClockwiseReachedArcWidth = bundle.getFloat(INSTANCE_CLOCKWISE_REACHED_BAR_HEIGHT);
//            mClockwiseOutlineArcWidth = bundle.getFloat(INSTANCE_CLOCKWISE_OUTLINE_BAR_HEIGHT);
//            mClockwiseArcColor = bundle.getInt(INSTANCE_CLOCKWISE_REACHED_BAR_COLOR);
//            mClockwiseOutlineArcColor = bundle.getInt(INSTANCE_CLOCKWISE_OUTLINE_BAR_COLOR);
//            mCounterClockwiseReachedArcWidth = bundle.getFloat(INSTANCE_COUNTER_CLOCKWISE_REACHED_BAR_HEIGHT);
//            mCounterClockwiseOutlineArcWidth = bundle.getFloat(INSTANCE_COUNTER_CLOCKWISE_OUTLINE_BAR_HEIGHT);
//            mCounterClockwiseArcColor = bundle.getInt(INSTANCE_COUNTER_CLOCKWISE_REACHED_BAR_COLOR);
//            mCounterClockwiseOutlineArcColor = bundle.getInt(INSTANCE_COUNTER_CLOCKWISE_OUTLINE_BAR_COLOR);
//            mCircleFillEnabled = bundle.getBoolean(INSTANCE_CIRCLE_FILL_ENABLED);
//            mCircleFillColor = bundle.getInt(INSTANCE_CIRCLE_FILL_COLOR);
//            mCircleFillMode = bundle.getInt(INSTANCE_CIRCLE_FILL_MODE);
//            initializePainters();
//            setMax(bundle.getInt(INSTANCE_MAX));
//            setProgress(bundle.getFloat(INSTANCE_PROGRESS));
//            setPrefix(bundle.getString(INSTANCE_PREFIX));
//            setSuffix(bundle.getString(INSTANCE_SUFFIX));
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATE));
            return;
        }
        super.onRestoreInstanceState(state);
    }

    /**
     * Initiate the view and start butterknife injection
     *
     * @param context
     */
    private void injectViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_day_progress, this);
        ButterKnife.inject(this, view);

        mLeftStreak = ButterKnife.findById(this, R.id.day_progress_streak_left);
        mRightStreak = ButterKnife.findById(this, R.id.day_progress_streak_right);
        mCheckMark = ButterKnife.findById(this, R.id.check_mark);
        mCircularBar = ButterKnife.findById(this, R.id.circularbar);
        mDayOfWeek = ButterKnife.findById(this, R.id.day_of_week);

        mDefaultDayTypeface = mDayOfWeek.getCurrentTypeface();

        loadStyledAttributes(null, null);
    }

    /**
     * Loads the styles and attributes defined in the xml tag of this class
     *
     * @param attributes The attributes to read from, do not pass {@link AttributeSet} as inflation needs the context of the {@link android.support.v4.view.PagerAdapter}
     */
    public DayProgressView loadStyledAttributes(TypedArray attributes, DayProgress progress) {
        isSpecial = progress == null ? false : progress.isSpecial();

        Resources res = getContext().getResources();
        mWeekDays = res.getStringArray(R.array.week_days);
        if (attributes != null) {
            mShowStreaks = attributes.getBoolean(R.styleable.SlidePager_slide_show_streaks, true);

            mCompletedColor = attributes.getColor(R.styleable.SlidePager_slide_progress_completed_reach_color, res.getColor(R.color.default_progress_completed_reach_color));
            mCompletedFillColor = attributes.getColor(R.styleable.SlidePager_slide_progress_completed_fill_color, res.getColor(R.color.default_progress_completed_fill_color));

            mNotCompletedReachColor = attributes.getColor(R.styleable.SlidePager_slide_progress_not_completed_reach_color, res.getColor(R.color.default_progress_not_completed_reach_color));
            mNotCompletedOutlineColor = attributes.getColor(R.styleable.SlidePager_slide_progress_not_completed_outline_color, res.getColor(R.color.default_progress_not_completed_outline_color));
            mNotCompletedFillColor = attributes.getColor(R.styleable.SlidePager_slide_progress_not_completed_fill_color, res.getColor(R.color.default_progress_not_completed_fill_color));

            mTodayReachColor = attributes.getColor(R.styleable.SlidePager_slide_progress_today_reach_color, res.getColor(R.color.default_progress_today_reach_color));
            mTodayOutlineColor = attributes.getColor(R.styleable.SlidePager_slide_progress_today_outline_color, res.getColor(R.color.default_progress_today_outline_color));
            mTodayFillColor = attributes.getColor(R.styleable.SlidePager_slide_progress_today_fill_color, res.getColor(R.color.default_progress_today_fill_color));

            //Do not recycle attributes, we need them for the future views
        } else {
            mShowStreaks = true;

            mCompletedColor = res.getColor(R.color.default_progress_completed_reach_color);
            mCompletedFillColor = res.getColor(R.color.default_progress_completed_fill_color);

            mNotCompletedReachColor = res.getColor(R.color.default_progress_not_completed_reach_color);
            mNotCompletedOutlineColor = res.getColor(R.color.default_progress_not_completed_outline_color);
            mNotCompletedFillColor = res.getColor(R.color.default_progress_not_completed_fill_color);

            mTodayReachColor = res.getColor(R.color.default_progress_today_reach_color);
            mTodayOutlineColor = res.getColor(R.color.default_progress_today_outline_color);
            mTodayFillColor = res.getColor(R.color.default_progress_today_fill_color);
        }

        setCircleColors();

        initAnimations();

        return this;
    }

    /**
     * Initiates the animation listener for the {@link CircularBar} so we can animate the streaks in
     * on animation end
     */
    private void initAnimations() {
        final int index = getIntTag();
        addAnimationListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (getCircularBar().getProgress() >= 99.95f) {
                    showCheckMark(true);

                    if (mShowStreaks && mSiblings != null && mSiblings.size() > 0) {
                        //Previous exists
                        if (getIntTag() - 1 >= 0) {
                            DayProgressView previousDay = mSiblings.get(index - 1);
                            //Previous is complete
                            if (previousDay.getCircularBar().getProgress() >= 99.95f) {
                                showStreak(true, DayProgressView.STREAK.LEFT_STREAK);
                            }

                        }

                        //Next exists
                        if (index + 1 < mSiblings.size()) {
                            DayProgressView nextDay = mSiblings.get(index + 1);
                            //Next is complete
                            if (nextDay.getCircularBar().getProgress() >= 99.95f) {
                                showStreak(true, DayProgressView.STREAK.RIGHT_STREAK);
                            }
                        }
                    }

                    //Set Color
                    mCircularBar.setCircleFillColor(mCompletedFillColor);
                    mCircularBar.setClockwiseReachedArcColor(mCompletedColor);
                } else {
                    //Set Color
                    mCircularBar.setCircleFillColor(mFillColor);
                    mCircularBar.setClockwiseReachedArcColor(mReachColor);

                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }

    /**
     * Sets the colors of {@link #mCircularBar}
     */
    public void setCircleColors() {
        mFillColor = isSpecial ? mTodayFillColor : mNotCompletedFillColor;
        mReachColor =  isSpecial ? mTodayReachColor : mNotCompletedReachColor;
        mOutlineColor = isSpecial ? mTodayOutlineColor : mNotCompletedOutlineColor;

        mCircularBar.setCircleFillColor(mFillColor);
        mCircularBar.setClockwiseReachedArcColor(mReachColor);
        mCircularBar.setClockwiseOutlineArcColor(mOutlineColor);
        setDayText();

    }

    /**
     * Sets the text for the {@link #mDayOfWeek}
     */
    private void setDayText() {
        getDayOfWeek().setText(mWeekDays[getIntTag()]);
        getDayOfWeek().setTextColor(mNotCompletedReachColor);
    }

    /**
     * Sets the font type of {@link #mDayOfWeek}
     *
     * @param selected True for {@link TypefaceType#ROBOTO_BOLD}, false for {@link TypefaceType#ROBOTO_THIN}
     */
    public void isSelected(boolean selected) {
        Typeface typeface = TypefaceTextView.getFont(getContext(),
                selected ? TypefaceType.ROBOTO_BOLD.getAssetFileName() : mDefaultDayTypeface.getAssetFileName());
        mDayOfWeek.setTypeface(typeface);
    }

    /**
     * Animate the progress from start to end for the {@link CircularBar} and the rest of the views in
     * this container
     *
     * @param start    0-100
     * @param progress A {@link DayProgress} object, containing the progress end (0-100) and the boolean to know if the day is special
     * @param duration The duration in milliseconds of the animation
     * @param siblings The sibling views we use to evaluate streaks showing
     */
    public void animateProgress(int start, DayProgress progress, int duration, List<View> siblings) {
        if(progress == null) {
            return;
        }
        isSpecial = progress.isSpecial();
        setCircleColors();
        mReachColor = isSpecial ? mTodayReachColor : mReachColor;

        mSiblings = setSiblings(siblings);
        if (mReachColor != mNotCompletedReachColor) {
            mCircularBar.setClockwiseReachedArcColor(mReachColor);
        } else {
            mCircularBar.setClockwiseReachedArcColor(progress.getProgress() == 100 ? mCompletedColor : mReachColor);
        }
        mCircularBar.animateProgress(start, progress.getProgress(), duration);
    }

    /**
     * Resets the progress and animations that have occurred on the
     * {@link #mCircularBar} and {@link #mShowStreaks}
     */
    public void reset() {
        mShowStreaks = false;
        setDayText();
        mCircularBar.setClockwiseReachedArcColor(mReachColor);
        mCircularBar.setCircleFillColor(mFillColor);
        mCircularBar.setClockwiseOutlineArcColor(mOutlineColor);
        mCircularBar.setProgress(0);
    }

    /**
     * Animates the display and hiding of {@link #mCheckMark}
     * @param show True to show, false to hide
     */
    public void showCheckMark(boolean show) {
        AnimatorSet set = new AnimatorSet();
        //Immediately remove them
        if (!show) {
            mCheckMark.setAlpha(0f);
            return;
        }
        float start = 0;
        float end = 1f;
        set.playTogether(Glider.glide(Skill.QuadEaseInOut, EASE_IN_DURATION, ObjectAnimator.ofFloat(mCheckMark, "alpha", start, end)));
        set.setDuration(EASE_IN_DURATION);
        set.start();
    }


    /**
     * Show or hide the streaks between the view
     *
     * @param show True if to show, false otherwise
     * @param side The side to animate and change visibility
     */
    public void showStreak(final boolean show, STREAK side) {
        AnimatorSet set = new AnimatorSet();
        View sideView = null;
        switch (side) {
            case LEFT_STREAK:
                sideView = mLeftStreak;
                break;
            case RIGHT_STREAK:
                sideView = mRightStreak;
                break;
            default:
                return;
        }
        //Immediately remove them
        if (!show) {
            sideView.setAlpha(0f);
            return;
        }
        float start = 0;
        float end = 1f;
        set.playTogether(Glider.glide(Skill.QuadEaseInOut, EASE_IN_DURATION, ObjectAnimator.ofFloat(sideView, "alpha", start, end)));
        set.setDuration(EASE_IN_DURATION);
        final View finalSideView = sideView;
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (show) {
                    finalSideView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (!show) {
                    finalSideView.setVisibility(View.INVISIBLE);
                }
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
     * Sets the animation listener on {@link #mCircularBar}
     * @param listener
     */
    public void addAnimationListener(Animator.AnimatorListener listener) {
        mCircularBar.removeAllListeners();
        mCircularBar.addListener(listener);
    }

    public TypefaceTextView getDayOfWeek() {
        return mDayOfWeek;
    }

    public CircularBar getCircularBar() {
        return mCircularBar;
    }


    /**
     * Sets the {@link #mSiblings} after removing any non {@link DayProgressView}
     * from the list supplied
     *
     * @param views The views in the layout
     */
    public static List<DayProgressView> setSiblings(List<View> views) {
        List<DayProgressView> siblings = new ArrayList<>();
        if (views != null) {
            for (View view : views) {
                if (view instanceof DayProgressView) {
                    siblings.add((DayProgressView) view);
                }
            }
        }
        return siblings;
    }

    public Integer getIntTag() {
        return Integer.parseInt((String) getTag());
    }

}
