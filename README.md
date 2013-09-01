AndroidAHRSView
================

AHRSView control for Android.  
A control for visualization of the Attitude Heading Reference System information.  

![AHRSView](https://github.com/billhsu/Android_AHRSView/raw/master/doc/android.png)  

## How to use it

**1. Link the AndroidAHRSView library to your Android project.**  

**2. Add to the layout xml.**

```xml
<com.tikoLabs.AndroidAHRS.AndroidAHRSView
        android:id="@+id/AHRSView"
        android:layout_width="250dp"
        android:layout_height="250dp" />
```

**3. Import the AndroidAHRSView package to your java file.**

```java
import com.tikoLabs.AndroidAHRS.AndroidAHRSView;
```

**4. Use it in your code!**

Define object  
```java
private AndroidAHRSView ahrsView = null;
```

Find the object in xml  
```java
ahrsView = (AndroidAHRSView)findViewById(R.id.AHRSView);
```

Set values  
```java
ahrsView.setRoll(roll);
ahrsView.setPitch(pitch);
```

For more details please check the [AHRSExample](https://github.com/billhsu/AndroidAHRSView/blob/master/AHRSExample/) project.  

##Author
**Shipeng Xu**

+ http://BillHsu.me
+ http://twitter.com/1991bill
+ http://weibo.com/billhsu
