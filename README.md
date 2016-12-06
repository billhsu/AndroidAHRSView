AndroidAHRSView [![](https://www.jitpack.io/v/billhsu/AndroidAHRSView.svg)](https://www.jitpack.io/#billhsu/AndroidAHRSView)
================

Attitude Heading Reference System(AHRS) display module for Android. 

<img src="https://raw.githubusercontent.com/billhsu/AndroidAHRSView/master/doc/androidAHRS.png" alt="screenshot" width="200" height="200"/>

## How to use it

### Add gradle dependency

#### Step 1. Add it in your root build.gradle at the end of repositories

```
  allprojects {
    repositories {
      ...
      maven { url 'https://www.jitpack.io' }
    }
  }
```

#### Step 2. Add the dependency

```
  dependencies {
          compile 'com.github.billhsu:AndroidAHRSView:0.1.0'
  }
```

### Add to the layout xml

```xml
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
![gif](https://raw.githubusercontent.com/billhsu/AndroidAHRSView/master/doc/demo.gif)

For more details please check the [SampleApp](https://github.com/billhsu/AndroidAHRSView/blob/master/SampleApp/) project.  

##Author
**Shipeng Xu**

+ http://BillHsu.me
+ http://twitter.com/1991bill
+ http://weibo.com/billhsu
