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
package com.yanzhenjie.alertdialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ArrayRes;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by Yan Zhenjie on 2016/12/12.
 */
public abstract class AlertDialog {

    public static Builder build(Context context) {
        if (Build.VERSION.SDK_INT >= 21) {
            return new APi21Builder(context);
        }
        return new Api20Builder(context);
    }

    public static Builder build(Context context, int themeResId) {
        if (Build.VERSION.SDK_INT >= 21) {
            return new APi21Builder(context, themeResId);
        }
        return new Api20Builder(context, themeResId);
    }

    public abstract void show();

    public abstract void dismiss();

    public abstract boolean isShowing();

    public abstract void cancel();

    public abstract Button getButton(int whichButton);

    public abstract
    @Nullable
    ListView getListView();

    public abstract
    @NonNull
    Context getContext();

    public abstract
    @Nullable
    View getCurrentFocus();

    public abstract
    @NonNull
    LayoutInflater getLayoutInflater();

    public abstract
    @Nullable
    Activity getOwnerActivity();

    public abstract int getVolumeControlStream();

    public abstract
    @Nullable
    Window getWindow();


    private static class Api21Dialog extends AlertDialog {

        private android.app.AlertDialog alertDialogApp;

        private Api21Dialog(android.app.AlertDialog alertDialog) {
            this.alertDialogApp = alertDialog;
        }

        @Override
        public void show() {
            alertDialogApp.show();
        }

        @Override
        public void dismiss() {
            if (alertDialogApp.isShowing())
                alertDialogApp.dismiss();
        }

        @Override
        public boolean isShowing() {
            return alertDialogApp.isShowing();
        }

        @Override
        public void cancel() {
            if (alertDialogApp.isShowing())
                alertDialogApp.cancel();
        }

        @Override
        public Button getButton(int whichButton) {
            return alertDialogApp.getButton(whichButton);
        }

        @Nullable
        @Override
        public ListView getListView() {
            return alertDialogApp.getListView();
        }

        @NonNull
        @Override
        public Context getContext() {
            return alertDialogApp.getContext();
        }

        @Nullable
        @Override
        public View getCurrentFocus() {
            return alertDialogApp.getCurrentFocus();
        }

        @NonNull
        @Override
        public LayoutInflater getLayoutInflater() {
            return alertDialogApp.getLayoutInflater();
        }

        @Nullable
        @Override
        public Activity getOwnerActivity() {
            return alertDialogApp.getOwnerActivity();
        }

        @Override
        public int getVolumeControlStream() {
            return alertDialogApp.getVolumeControlStream();
        }

        @Nullable
        @Override
        public Window getWindow() {
            return alertDialogApp.getWindow();
        }
    }

    private static class Api20Dialog extends AlertDialog {

        private android.support.v7.app.AlertDialog alertDialogSupport;

        private Api20Dialog(android.support.v7.app.AlertDialog alertDialog) {
            this.alertDialogSupport = alertDialog;
        }

        @Override
        public void show() {
            alertDialogSupport.show();
        }

        @Override
        public void dismiss() {
            if (alertDialogSupport.isShowing())
                alertDialogSupport.dismiss();
        }

        @Override
        public boolean isShowing() {
            return alertDialogSupport.isShowing();
        }

        @Override
        public void cancel() {
            if (alertDialogSupport.isShowing())
                alertDialogSupport.cancel();
        }

        @Override
        public Button getButton(int whichButton) {
            return alertDialogSupport.getButton(whichButton);
        }

        @Nullable
        @Override
        public ListView getListView() {
            return alertDialogSupport.getListView();
        }

        @NonNull
        @Override
        public Context getContext() {
            return alertDialogSupport.getContext();
        }

        @Nullable
        @Override
        public View getCurrentFocus() {
            return alertDialogSupport.getCurrentFocus();
        }

        @NonNull
        @Override
        public LayoutInflater getLayoutInflater() {
            return alertDialogSupport.getLayoutInflater();
        }

        @Nullable
        @Override
        public Activity getOwnerActivity() {
            return alertDialogSupport.getOwnerActivity();
        }

        @Override
        public int getVolumeControlStream() {
            return alertDialogSupport.getVolumeControlStream();
        }

        @Nullable
        @Override
        public Window getWindow() {
            return alertDialogSupport.getWindow();
        }
    }

    public interface Builder {

        @NonNull
        Context getContext();

        Builder setTitle(@StringRes int titleId);

        Builder setTitle(CharSequence title);

        Builder setCustomTitle(View customTitleView);

        Builder setMessage(@StringRes int messageId);

        Builder setMessage(CharSequence message);

        Builder setIcon(@DrawableRes int iconId);

        Builder setIcon(Drawable icon);

        Builder setIconAttribute(@AttrRes int attrId);

        Builder setPositiveButton(@StringRes int textId, final DialogInterface.OnClickListener listener);

        Builder setPositiveButton(CharSequence text, final DialogInterface.OnClickListener listener);

        Builder setNegativeButton(@StringRes int textId, final DialogInterface.OnClickListener listener);

        Builder setNegativeButton(CharSequence text, final DialogInterface.OnClickListener listener);

        Builder setNeutralButton(@StringRes int textId, final DialogInterface.OnClickListener listener);

        Builder setNeutralButton(CharSequence text, final DialogInterface.OnClickListener listener);

        Builder setCancelable(boolean cancelable);

        Builder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener);

        Builder setOnDismissListener(DialogInterface.OnDismissListener onDismissListener);

        Builder setOnKeyListener(DialogInterface.OnKeyListener onKeyListener);

        Builder setItems(@ArrayRes int itemsId, final DialogInterface.OnClickListener listener);

        Builder setItems(CharSequence[] items, final DialogInterface.OnClickListener listener);

        Builder setAdapter(final ListAdapter adapter, final DialogInterface.OnClickListener listener);

        Builder setCursor(final Cursor cursor, final DialogInterface.OnClickListener listener, String
                labelColumn);

        Builder setMultiChoiceItems(@ArrayRes int itemsId, boolean[] checkedItems, final DialogInterface
                .OnMultiChoiceClickListener listener);

        Builder setMultiChoiceItems(CharSequence[] items, boolean[] checkedItems, final DialogInterface
                .OnMultiChoiceClickListener listener);

        Builder setMultiChoiceItems(Cursor cursor, String isCheckedColumn, String labelColumn, final
        DialogInterface.OnMultiChoiceClickListener listener);

        Builder setSingleChoiceItems(@ArrayRes int itemsId, int checkedItem, final DialogInterface
                .OnClickListener listener);

        Builder setSingleChoiceItems(Cursor cursor, int checkedItem, String labelColumn, final
        DialogInterface.OnClickListener listener);

        Builder setSingleChoiceItems(CharSequence[] items, int checkedItem, final DialogInterface
                .OnClickListener listener);

        Builder setSingleChoiceItems(ListAdapter adapter, int checkedItem, final DialogInterface
                .OnClickListener listener);


        Builder setOnItemSelectedListener(final AdapterView.OnItemSelectedListener listener);

        Builder setView(int layoutResId);

        Builder setView(View view);

        AlertDialog create();

        AlertDialog show();
    }

    private static class APi21Builder implements Builder {

        private android.app.AlertDialog.Builder builderApp;

        private APi21Builder(@NonNull Context context) {
            this(context, 0);
        }

        private APi21Builder(@NonNull Context context, @StyleRes int themeResId) {
            builderApp = new android.app.AlertDialog.Builder(context, themeResId);
        }

        @NonNull
        @Override
        public Context getContext() {
            return builderApp.getContext();
        }

        @Override
        public Builder setTitle(@StringRes int titleId) {
            builderApp.setTitle(titleId);
            return this;
        }

        @Override
        public Builder setTitle(CharSequence title) {
            builderApp.setTitle(title);
            return this;
        }

        @Override
        public Builder setCustomTitle(View customTitleView) {
            builderApp.setCustomTitle(customTitleView);
            return this;
        }

        @Override
        public Builder setMessage(@StringRes int messageId) {
            builderApp.setMessage(messageId);
            return this;
        }

        @Override
        public Builder setMessage(CharSequence message) {
            builderApp.setMessage(message);
            return this;
        }

        @Override
        public Builder setIcon(@DrawableRes int iconId) {
            builderApp.setIcon(iconId);
            return this;
        }

        @Override
        public Builder setIcon(Drawable icon) {
            builderApp.setIcon(icon);
            return this;
        }

        @Override
        public Builder setIconAttribute(@AttrRes int attrId) {
            builderApp.setIconAttribute(attrId);
            return this;
        }

        @Override
        public Builder setPositiveButton(@StringRes int textId, final DialogInterface.OnClickListener
                listener) {
            builderApp.setPositiveButton(textId, listener);
            return this;
        }

        @Override
        public Builder setPositiveButton(CharSequence text, final DialogInterface.OnClickListener listener) {
            builderApp.setPositiveButton(text, listener);
            return this;
        }

        @Override
        public Builder setNegativeButton(@StringRes int textId, final DialogInterface.OnClickListener
                listener) {
            builderApp.setNegativeButton(textId, listener);
            return this;
        }

        @Override
        public Builder setNegativeButton(CharSequence text, final DialogInterface.OnClickListener listener) {
            builderApp.setNegativeButton(text, listener);
            return this;
        }

        @Override
        public Builder setNeutralButton(@StringRes int textId, final DialogInterface.OnClickListener
                listener) {
            builderApp.setNeutralButton(textId, listener);
            return this;
        }

        @Override
        public Builder setNeutralButton(CharSequence text, final DialogInterface.OnClickListener listener) {
            builderApp.setNeutralButton(text, listener);
            return this;
        }

        @Override
        public Builder setCancelable(boolean cancelable) {
            builderApp.setCancelable(cancelable);
            return this;
        }

        @Override
        public Builder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
            builderApp.setOnCancelListener(onCancelListener);
            return this;
        }

        @Override
        public Builder setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                builderApp.setOnDismissListener(onDismissListener);
            }
            return this;
        }

        @Override
        public Builder setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
            builderApp.setOnKeyListener(onKeyListener);
            return this;
        }

        @Override
        public Builder setItems(@ArrayRes int itemsId, final DialogInterface.OnClickListener listener) {
            builderApp.setItems(itemsId, listener);
            return this;
        }

        @Override
        public Builder setItems(CharSequence[] items, final DialogInterface.OnClickListener listener) {
            builderApp.setItems(items, listener);
            return this;
        }

        @Override
        public Builder setAdapter(final ListAdapter adapter, final DialogInterface.OnClickListener listener) {
            builderApp.setAdapter(adapter, listener);
            return this;
        }

        @Override
        public Builder setCursor(final Cursor cursor, final DialogInterface.OnClickListener listener,
                                 String labelColumn) {
            builderApp.setCursor(cursor, listener, labelColumn);
            return this;
        }

        @Override
        public Builder setMultiChoiceItems(@ArrayRes int itemsId, boolean[] checkedItems, final
        DialogInterface.OnMultiChoiceClickListener listener) {
            builderApp.setMultiChoiceItems(itemsId, checkedItems, listener);
            return this;
        }

        @Override
        public Builder setMultiChoiceItems(CharSequence[] items, boolean[] checkedItems, final
        DialogInterface.OnMultiChoiceClickListener listener) {
            builderApp.setMultiChoiceItems(items, checkedItems, listener);
            return this;
        }

        @Override
        public Builder setMultiChoiceItems(Cursor cursor, String isCheckedColumn, String labelColumn, final
        DialogInterface.OnMultiChoiceClickListener listener) {
            builderApp.setMultiChoiceItems(cursor, isCheckedColumn, labelColumn, listener);
            return this;
        }

        @Override
        public Builder setSingleChoiceItems(@ArrayRes int itemsId, int checkedItem, final DialogInterface
                .OnClickListener listener) {
            builderApp.setSingleChoiceItems(itemsId, checkedItem, listener);
            return this;
        }

        @Override
        public Builder setSingleChoiceItems(Cursor cursor, int checkedItem, String labelColumn, final
        DialogInterface.OnClickListener listener) {
            builderApp.setSingleChoiceItems(cursor, checkedItem, labelColumn, listener);
            return this;
        }

        @Override
        public Builder setSingleChoiceItems(CharSequence[] items, int checkedItem, final DialogInterface
                .OnClickListener listener) {
            builderApp.setSingleChoiceItems(items, checkedItem, listener);
            return this;
        }

        @Override
        public Builder setSingleChoiceItems(ListAdapter adapter, int checkedItem, final DialogInterface
                .OnClickListener listener) {
            builderApp.setSingleChoiceItems(adapter, checkedItem, listener);
            return this;
        }

        @Override
        public Builder setOnItemSelectedListener(final AdapterView.OnItemSelectedListener listener) {
            builderApp.setOnItemSelectedListener(listener);
            return this;
        }

        @Override
        public Builder setView(int layoutResId) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builderApp.setView(layoutResId);
            }
            return this;
        }

        @Override
        public Builder setView(View view) {
            builderApp.setView(view);
            return this;
        }

        @Override
        public AlertDialog create() {
            return new Api21Dialog(builderApp.create());
        }

        @Override
        public AlertDialog show() {
            final AlertDialog dialog = create();
            dialog.show();
            return dialog;
        }

    }

    private static class Api20Builder implements Builder {

        private android.support.v7.app.AlertDialog.Builder builderSupport;

        private Api20Builder(@NonNull Context context) {
            this(context, 0);
        }

        private Api20Builder(@NonNull Context context, @StyleRes int themeResId) {
            builderSupport = new android.support.v7.app.AlertDialog.Builder(context, themeResId);
        }

        @NonNull
        public Context getContext() {
            return builderSupport.getContext();
        }

        public Builder setTitle(@StringRes int titleId) {
            builderSupport.setTitle(titleId);
            return this;
        }

        public Builder setTitle(CharSequence title) {
            builderSupport.setTitle(title);
            return this;
        }

        public Builder setCustomTitle(View customTitleView) {
            builderSupport.setCustomTitle(customTitleView);
            return this;
        }

        public Builder setMessage(@StringRes int messageId) {
            builderSupport.setMessage(messageId);
            return this;
        }

        public Builder setMessage(CharSequence message) {
            builderSupport.setMessage(message);
            return this;
        }

        public Builder setIcon(@DrawableRes int iconId) {
            builderSupport.setIcon(iconId);
            return this;
        }

        public Builder setIcon(Drawable icon) {
            builderSupport.setIcon(icon);
            return this;
        }

        public Builder setIconAttribute(@AttrRes int attrId) {
            builderSupport.setIconAttribute(attrId);
            return this;
        }

        public Builder setPositiveButton(@StringRes int textId, final DialogInterface.OnClickListener
                listener) {
            builderSupport.setPositiveButton(textId, listener);
            return this;
        }

        public Builder setPositiveButton(CharSequence text, final DialogInterface.OnClickListener listener) {
            builderSupport.setPositiveButton(text, listener);
            return this;
        }

        public Builder setNegativeButton(@StringRes int textId, final DialogInterface.OnClickListener
                listener) {
            builderSupport.setNegativeButton(textId, listener);
            return this;
        }

        public Builder setNegativeButton(CharSequence text, final DialogInterface.OnClickListener listener) {
            builderSupport.setNegativeButton(text, listener);
            return this;
        }

        public Builder setNeutralButton(@StringRes int textId, final DialogInterface.OnClickListener
                listener) {
            builderSupport.setNeutralButton(textId, listener);
            return this;
        }

        public Builder setNeutralButton(CharSequence text, final DialogInterface.OnClickListener listener) {
            builderSupport.setNeutralButton(text, listener);
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            builderSupport.setCancelable(cancelable);
            return this;
        }

        public Builder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
            builderSupport.setOnCancelListener(onCancelListener);
            return this;
        }

        public Builder setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
            builderSupport.setOnDismissListener(onDismissListener);
            return this;
        }

        public Builder setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
            builderSupport.setOnKeyListener(onKeyListener);
            return this;
        }

        public Builder setItems(@ArrayRes int itemsId, final DialogInterface.OnClickListener listener) {
            builderSupport.setItems(itemsId, listener);
            return this;
        }

        public Builder setItems(CharSequence[] items, final DialogInterface.OnClickListener listener) {
            builderSupport.setItems(items, listener);
            return this;
        }

        public Builder setAdapter(final ListAdapter adapter, final DialogInterface.OnClickListener listener) {
            builderSupport.setAdapter(adapter, listener);
            return this;
        }

        public Builder setCursor(final Cursor cursor, final DialogInterface.OnClickListener listener,
                                 String labelColumn) {
            builderSupport.setCursor(cursor, listener, labelColumn);
            return this;
        }

        public Builder setMultiChoiceItems(@ArrayRes int itemsId, boolean[] checkedItems, final
        DialogInterface.OnMultiChoiceClickListener listener) {
            builderSupport.setMultiChoiceItems(itemsId, checkedItems, listener);
            return this;
        }

        public Builder setMultiChoiceItems(CharSequence[] items, boolean[] checkedItems, final
        DialogInterface.OnMultiChoiceClickListener listener) {
            builderSupport.setMultiChoiceItems(items, checkedItems, listener);
            return this;
        }

        public Builder setMultiChoiceItems(Cursor cursor, String isCheckedColumn, String labelColumn, final
        DialogInterface.OnMultiChoiceClickListener listener) {
            builderSupport.setMultiChoiceItems(cursor, isCheckedColumn, labelColumn, listener);
            return this;
        }

        public Builder setSingleChoiceItems(@ArrayRes int itemsId, int checkedItem, final DialogInterface
                .OnClickListener listener) {
            builderSupport.setSingleChoiceItems(itemsId, checkedItem, listener);
            return this;
        }

        public Builder setSingleChoiceItems(Cursor cursor, int checkedItem, String labelColumn, final
        DialogInterface.OnClickListener listener) {
            builderSupport.setSingleChoiceItems(cursor, checkedItem, labelColumn, listener);
            return this;
        }

        public Builder setSingleChoiceItems(CharSequence[] items, int checkedItem, final DialogInterface
                .OnClickListener listener) {
            builderSupport.setSingleChoiceItems(items, checkedItem, listener);
            return this;
        }

        public Builder setSingleChoiceItems(ListAdapter adapter, int checkedItem, final DialogInterface
                .OnClickListener listener) {
            builderSupport.setSingleChoiceItems(adapter, checkedItem, listener);
            return this;
        }

        public Builder setOnItemSelectedListener(final AdapterView.OnItemSelectedListener listener) {
            builderSupport.setOnItemSelectedListener(listener);
            return this;
        }

        public Builder setView(int layoutResId) {
            builderSupport.setView(layoutResId);
            return this;
        }

        public Builder setView(View view) {
            builderSupport.setView(view);
            return this;
        }

        public AlertDialog create() {
            return new Api20Dialog(builderSupport.create());
        }

        public AlertDialog show() {
            final AlertDialog dialog = create();
            dialog.show();
            return dialog;
        }
    }
}
