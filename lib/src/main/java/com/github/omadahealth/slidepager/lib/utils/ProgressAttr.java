package com.github.omadahealth.slidepager.lib.utils;

/**
 * Created by stoyan on 4/15/15.
 */
public class ProgressAttr {

    /**
     * Progress from 0-100
     */
    private int mProgress;

    /**
     * Whether this is a special day, and is colored in differently
     * when the page translates. Check :
     * {@link com.github.omadahealth.slidepager.lib.views.ProgressView#mSpecialFillColor}
     * {@link com.github.omadahealth.slidepager.lib.views.ProgressView#mSpecialOutlineColor}
     * {@link com.github.omadahealth.slidepager.lib.views.ProgressView#mSpecialReachColor}
     */
    private boolean mSpecial;

    /**
     * Determines if this {@link ProgressAttr} is in the future or not, specifying different thickness for the {@link com.github.OrangeGangsters.circularbarpager.library.CircularBar}
     */
    private boolean mIsFuture;

    /**
     * Determines the color for this specific {@link com.github.omadahealth.slidepager.lib.views.ProgressView} {@link com.github.OrangeGangsters.circularbarpager.library.CircularBar}
     * reachedColor. Uses the default style color if set to null.
     */
    private Integer mReachedColor;

    /**
     * Determines the color for this specific {@link com.github.omadahealth.slidepager.lib.views.ProgressView} {@link com.github.OrangeGangsters.circularbarpager.library.CircularBar}
     * completedColor. Uses the default style color if set to null.
     */
    private Integer mCompletedColor;

    /**
     * Determines the color filled for this specific {@link com.github.omadahealth.slidepager.lib.views.ProgressView} {@link com.github.OrangeGangsters.circularbarpager.library.CircularBar}
     * completedColor. Uses the default style color if set to null.
     */
    private Integer mCompletedFillColor;

    /**
     * Determines the drawable for this specific {@link com.github.omadahealth.slidepager.lib.views.ProgressView} {@link com.github.OrangeGangsters.circularbarpager.library.CircularBar}
     * when progress is 100%. Uses the default drawable if set to null.
     */
    private Integer mCompletedDrawable;

    /**
     * Determine if a {@link com.github.omadahealth.slidepager.lib.views.ProgressView} should display a streak leftwards off the current page.
     * True it should, false it shouldn't
     */
    private boolean mStreakLeftOffScreen = false;

    /**
     * Determine if a {@link com.github.omadahealth.slidepager.lib.views.ProgressView} should display a streak leftwards off the current page
     * True it should, false it shouldn't
     */
    private boolean mStreakRightOffScreen = false;

    public ProgressAttr(int progress, boolean special, boolean isFuture) {
        this.mProgress = progress;
        this.mSpecial = special;
        this.mIsFuture = isFuture;
        this.mReachedColor = null;
        this.mCompletedColor = null;
        this.mCompletedDrawable = null;
    }

    public ProgressAttr(int progress, boolean special, boolean isFuture, Integer reachedColor, Integer completedColor, Integer completedDrawable) {
        this.mProgress = progress;
        this.mSpecial = special;
        this.mIsFuture = isFuture;
        this.mReachedColor = reachedColor;
        this.mCompletedColor = completedColor;
        this.mCompletedDrawable = completedDrawable;
    }

    public ProgressAttr(int progress, boolean special, boolean isFuture, Integer reachedColor, Integer completedColor, Integer completedDrawable, boolean streakLeftOffScreen, boolean streakRightOffScreen) {
        this.mProgress = progress;
        this.mSpecial = special;
        this.mIsFuture = isFuture;
        this.mReachedColor = reachedColor;
        this.mCompletedColor = completedColor;
        this.mCompletedDrawable = completedDrawable;
        this.mStreakLeftOffScreen = streakLeftOffScreen;
        this.mStreakRightOffScreen = streakRightOffScreen;
    }

    public ProgressAttr(int progress, boolean special, boolean isFuture, Integer reachedColor, Integer completedColor, Integer completedFillColor, Integer completedDrawable, boolean streakLeftOffScreen, boolean streakRightOffScreen) {
        this.mProgress = progress;
        this.mSpecial = special;
        this.mIsFuture = isFuture;
        this.mReachedColor = reachedColor;
        this.mCompletedColor = completedColor;
        this.mCompletedFillColor = completedFillColor;
        this.mCompletedDrawable = completedDrawable;
        this.mStreakLeftOffScreen = streakLeftOffScreen;
        this.mStreakRightOffScreen = streakRightOffScreen;
    }

    public boolean isSpecial() {
        return mSpecial;
    }

    public void setSpecial(boolean special) {
        this.mSpecial = special;
    }

    public int getProgress() {
        return mProgress;
    }

    public void setProgress(int progress) {
        this.mProgress = progress;
    }

    public boolean isFuture() {
        return mIsFuture;
    }

    public void setIsFuture(boolean isFuture) {
        this.mIsFuture = isFuture;
    }

    public Integer getReachedColor() {
        return mReachedColor;
    }

    public void setReachedColor(Integer reachedColor) {
        this.mReachedColor = reachedColor;
    }

    public Integer getCompletedColor() {
        return mCompletedColor;
    }

    public void setCompletedColor(Integer completedColor) {
        this.mCompletedColor = completedColor;
    }

    public Integer getCompletedFillColor() {
        return mCompletedFillColor;
    }

    public void setCompletedFillColor(Integer completedFillColor) {
        this.mCompletedFillColor = completedFillColor;
    }

    public Integer getCompletedDrawable() {
        return mCompletedDrawable;
    }

    public void setCompletedDrawable(Integer completedDrawable) {
        this.mCompletedDrawable = completedDrawable;
    }

    public boolean isStreakLeftOffScreen() {
        return mStreakLeftOffScreen;
    }

    public boolean isStreakRightOffScreen() {
        return mStreakRightOffScreen;
    }
}
