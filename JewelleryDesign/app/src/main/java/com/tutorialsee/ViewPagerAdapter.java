package com.tutorialsee;

import java.util.List;

import android.os.Parcelable;

import android.view.View;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ViewPagerAdapter extends PagerAdapter {
    
   
    private List<View> views;
    
    public ViewPagerAdapter (List<View> views){
        this.views = views;
    }

  
    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView(views.get(arg1));
    }

    @Override
    public void finishUpdate(View arg0) {
        // TODO Auto-generated method stub
        
    }

  
    @Override
    public int getCount() {
        if (views != null)
        {
            return views.size();
        }
        
        return 0;
    }
    

  
    @Override
    public Object instantiateItem(View arg0, int arg1) {
        
        ((ViewPager) arg0).addView(views.get(arg1), 0);
        
        return views.get(arg1);
    }

  
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return (arg0 == arg1);
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Parcelable saveState() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void startUpdate(View arg0) {
        // TODO Auto-generated method stub
        
    }

}