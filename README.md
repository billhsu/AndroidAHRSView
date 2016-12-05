AndroidAHRSView
================

Attitude Heading Reference System(AHRS) display module for Android.

## How to use it

**1. Link the AndroidAHRSView library to your Android project.**  

**2. Add to the layout xml.**

```xml
<me.billhsu.ahrsview.AHRSView
        android:id="@+id/AHRSView"
        android:layout_width="300dp"
        android:layout_height="300dp"/>
```

**3. Use it in your code!**

Define object  
```java
private AndroidAHRSView ahrsView = null;
```

Find the object in xml  
```java
AHRSView ahrsView = (AHRSView) findViewById(R.id.AHRSView);
ahrsView.setRoll(roll);
ahrsView.setPitch(pitch);
```

For more details please check the [SampleApp](https://github.com/billhsu/AndroidAHRSView/blob/master/SampleApp/) project.  

##Author
**Shipeng Xu**

+ http://BillHsu.me
+ http://twitter.com/1991bill
+ http://weibo.com/billhsu
