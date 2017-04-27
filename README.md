**欢迎加入QQ技术交流群：[46523908](http://jq.qq.com/?_wv=1027&k=40hvC7E)**  

Material Design风格的AlertDialog，内部在API Version <= 20时使用Appcomat项目的AlertDialog，API Version > 20时，使用系统原生的Alertdialog。

目前是我个人使用，开源的话意义不是特别大，但是我的几个开源项目demo都都要用到，所以整合出来。

## 引入项目
Gradle
```
compile 'com.yanzhenjie.alertdialog:alertdialog:1.0.1'
```

Or Maven:
```
<dependency>
  <groupId>com.yanzhenjie.alertdialog</groupId>
  <artifactId>AlertDialog</artifactId>
  <version>1.0.1</version>
  <type>pom</type>
</dependency>
```

lvy：
```
<dependency org='com.yanzhenjie.alertdialog' name='AlertDialog' rev='1.0.1'>
  <artifact name='AlertDialog' ext='pom' ></artifact>
</dependency>
```

## 用法
和系统原生用法一模一样，特点也一模一样的。但是为了照顾小白同学，我还是来个sample。
```java
// 这里注意包别导错了。
// X 不是：android.support.v7.app.AlertDialog
// X 不是：android.app.AlertDialog
// √ 是：com.yanzhenjie.alertdialog.AlertDialog

/**
 * 显示正常用法的按钮。
 */
private void onCommonClick() {
    AlertDialog.newBuilder(this)
        .setTitle("标题")
        .setMessage("提示信息")
        .setNeutralButton("忽略", (dialog, which) -> {
            Toast.makeText(MainActivity.this, "点击了忽略", Toast.LENGTH_SHORT).show();
        })
        .setNegativeButton("取消", (dialog, which) -> {
            Toast.makeText(MainActivity.this, "点击了取消", Toast.LENGTH_SHORT).show();
        })
        .setPositiveButton("好的", (dialog, which) -> {
            Toast.makeText(MainActivity.this, "点击了确定", Toast.LENGTH_SHORT).show();
        })
        .show();

    //  Button被点击后，不调用dialog.dismiss()，因为AlertDialog会自动调用。
}

/**
 * 特殊用法被点击。
 */
private void onSpecialClick() {
    AlertDialog alertDialog = AlertDialog.newBuilder(this)
        .setTitle("标题")
        .setMessage("提示信息")
        // Listener 写null，Button被点击时，Alertdialog就不会自动dismiss了。
        .setNeutralButton("忽略", null)
        .setNegativeButton("取消", null)
        .setPositiveButton("好的", null)
        .show(); // 这里是直接show()，不是create()，否则不能getButton()。

    // 获取中性按钮。
    Button btnNeutral = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
    btnNeutral.setOnClickListener(v -> {
        Toast.makeText(MainActivity.this, "我们拦截了忽略点击关闭dialog操作",
                Toast.LENGTH_SHORT).show();
    });
    // 获取取消按钮。
    Button btnNegative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
    btnNegative.setOnClickListener(v -> {
        alertDialog.dismiss(); // 拦截了btn的默认操作，需要调用dialog.dismiss()。
        Toast.makeText(MainActivity.this, "点击了取消",
                Toast.LENGTH_SHORT).show();
    });
    // 获取确定按钮。
    Button btnPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
    btnPositive.setOnClickListener(v -> {
        alertDialog.dismiss(); // 拦截了btn的默认操作，需要调用dialog.dismiss()。
        Toast.makeText(MainActivity.this, "点击了确定",
                Toast.LENGTH_SHORT).show();
    });
}
```

## License
```text
Copyright 2017 Yan Zhenjie

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
