/*
 * Copyright 2016 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yanzhenjie.alertdialog.sample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yanzhenjie.alertdialog.AlertDialog;


/**
 * Created by Yan Zhenjie on 2016/12/28.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.btn_show_common).setOnClickListener(mOnClickListener);
        findViewById(R.id.btn_show_special).setOnClickListener(mOnClickListener);
    }

    // 这里注意包别导错了。
    // X 不是：android.support.v7.app.AlertDialog
    // X 不是：android.app.AlertDialog
    // √ 是：com.yanzhenjie.alertdialog.AlertDialog

    /**
     * 显示正常用法的按钮。
     */
    private void onCommonClick() {
        AlertDialog.build(this)
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
        AlertDialog alertDialog = AlertDialog.build(this)
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

    /**
     * Show dialog btn click.
     */
    private View.OnClickListener mOnClickListener = v -> {
        int id = v.getId();
        switch (id) {
            case R.id.btn_show_common: {
                onCommonClick();
                break;
            }
            case R.id.btn_show_special: {
                onSpecialClick();
                break;
            }
        }
    };
}
