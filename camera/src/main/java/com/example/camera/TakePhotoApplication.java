package com.example.camera;

import android.app.Application;

/**
 * @author MyApplication
 * @date 2019/6/20 17:18
 */
public class TakePhotoApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
//        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
//            /**
//             * @param activity
//             * @param savedInstanceState
//             */
//            @Override
//            public void onActivityCreated(final Activity activity, Bundle savedInstanceState) {
//                //这里全局给Activity设置toolbar和title,你想象力有多丰富,这里就有多强大,以前放到BaseActivity的操作都可以放到这里
////                if (activity.findViewById(R.id.toolbar) != null) {
////                    //找到 Toolbar 并且替换 Actionbar
////                    if (activity instanceof AppCompatActivity) {
////                        ((AppCompatActivity) activity).setSupportActionBar((Toolbar) activity.findViewById(R.id.toolbar));
////                        ((AppCompatActivity) activity).getSupportActionBar().setDisplayShowTitleEnabled(false);
////                        ((AppCompatActivity) activity).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
////                        ((Toolbar) activity.findViewById(R.id.toolbar)).setNavigationOnClickListener(new View.OnClickListener() {
////                            @Override
////                            public void onClick(View v) {
////                                activity.finish();
////                            }
////                        });
////
////                    } else {
////                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
////                            activity.setActionBar((android.widget.Toolbar) activity.findViewById(R.id.toolbar));
////                            activity.getActionBar().setDisplayShowTitleEnabled(false);
////                        }
////                    }
////                }
////                if (activity.findViewById(R.id.toolbar_title) != null) {
////                    //找到 Toolbar 的标题栏并设置标题名
////                    ((TextView) activity.findViewById(R.id.toolbar_title)).setText(activity.getTitle());
////
////
////                }
//            }
//
//            @Override
//            public void onActivityStarted(Activity activity) {
//
//            }
//
//            @Override
//            public void onActivityResumed(Activity activity) {
//
//            }
//
//            @Override
//            public void onActivityPaused(Activity activity) {
//
//            }
//
//            @Override
//            public void onActivityStopped(Activity activity) {
//
//            }
//
//            @Override
//            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
//
//            }
//
//            @Override
//            public void onActivityDestroyed(Activity activity) {
//
//            }
//        });

    }
}
