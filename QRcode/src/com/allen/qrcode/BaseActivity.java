package com.allen.qrcode;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.allen.myqrcode.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class BaseActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// // 创建状态栏的管理实例
		// SystemBarTintManager tintManager = new SystemBarTintManager(this);
		// // 激活状态栏设置
		// tintManager.setStatusBarTintEnabled(true);
		// // 激活导航栏设置
		// tintManager.setNavigationBarTintEnabled(true);
		// tintManager.setStatusBarTintColor(getResources().getColor(R.color.red));
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			setTranslucentStatus(true);
		}

		SystemBarTintManager tintManager = new SystemBarTintManager(this);
		tintManager.setStatusBarTintEnabled(true);
		tintManager.setStatusBarTintResource(R.color.hailan);

	}

	@TargetApi(19)
	private void setTranslucentStatus(boolean on) {
		Window win = getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		if (on) {
			winParams.flags |= bits;
		} else {
			winParams.flags &= ~bits;
		}
		win.setAttributes(winParams);
	}
}
