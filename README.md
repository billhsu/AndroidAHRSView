AndroidAHRSView
================

Attitude Heading Reference System(AHRS) display module for Android. 

<img src="https://raw.githubusercontent.com/billhsu/AndroidAHRSView/master/doc/androidAHRS.png" alt="screenshot" width="200" height="200"/>

## How to use it

### Add gradle dependency

```
  dependencies {
          compile 'me.billhsu.ahrsview:AHRSView:1.0.3'
  }
```

### Add to the layout xml

```
<me.billhsu.ahrsview.AHRSView
        android:id="@+id/AHRSView"
        android:layout_width="300dp"
        android:layout_height="300dp"/>
```

### Use it in your code

```
AHRSView ahrsView = (AHRSView) findViewById(R.id.AHRSView);
ahrsView.setRoll(roll);
ahrsView.setPitch(pitch);
ahrsView.setYaw(yaw);
```

#### Demo gif
![gif1](https://raw.githubusercontent.com/billhsu/AndroidAHRSView/master/doc/demo1.gif)
![gif2](https://raw.githubusercontent.com/billhsu/AndroidAHRSView/master/doc/demo2.gif)

For more details please check the [SampleApp](https://github.com/billhsu/AndroidAHRSView/blob/master/SampleApp/) project.  

## Author
**Shipeng Xu**

+ http://BillHsu.me
+ http://twitter.com/1991bill
+ http://weibo.com/billhsu
