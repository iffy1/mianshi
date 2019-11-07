package com.iffy.mianshi.hotfix;

import android.content.Context;
import android.widget.Toast;

//public class BugClassJava {
//    static public int dairYouTryThis(Context c) {
//        Toast.makeText(c, "我修好了", Toast.LENGTH_LONG).show();
//        return 0;
//    }
//}

public class BugClassJava {
    static public int dairYouTryThis(Context c) {
        return 1/0;
    }
}
