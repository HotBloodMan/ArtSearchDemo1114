package com.ljt.artsearchdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;


/*
* 结合内部类 ListVieEx
 * 内部拦截法，同向
* */
public class ListViewEx extends ListView {

        private RefreshLayoutBase2 outter;

        public ListViewEx(Context context, RefreshLayoutBase2 outter) {
            super(context);
            this.outter = outter;
        }

        public ListViewEx(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public ListViewEx(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        /**
         * 使用 outter.requestDisallowInterceptTouchEvent();
         * 来决定父控件是否对事件进行拦截
         * @param ev
         * @return
         */
        @Override
        public boolean dispatchTouchEvent(MotionEvent ev) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    outter.requestDisallowInterceptTouchEvent(true);
                    break;
                case MotionEvent.ACTION_MOVE:

                    if ( isTop() && outter.getScrollY() <= outter.mInitScrollY) {
                        outter.requestDisallowInterceptTouchEvent(false);
                    }
                    break;

            }
            return super.dispatchTouchEvent(ev);
        }

        public boolean isTop() {
            return getFirstVisiblePosition() ==0;
        }
    }
