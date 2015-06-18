package com.allen.qrcode;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.allen.myqrcode.R;
import com.qq.e.ads.AdListener;
import com.qq.e.ads.AdRequest;
import com.qq.e.ads.AdSize;
import com.qq.e.ads.AdView;
import com.qq.e.ads.InterstitialAd;
import com.qq.e.ads.InterstitialAdListener;

public class ShowActivity extends BaseActivity {
	private TextView txt1, TV_show_type, iamgeTV;
	private String message;
	private Button btn_URL, btnMore, btn_TEXT;
	private Bundle bundle;
	private AdView bannerAD;
	private InterstitialAd iad;

	private LinearLayout miniLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show);

		initView();
		initIntent();

	}

	private void initView() {
		txt1 = (TextView) findViewById(R.id.txt1);
		TV_show_type = (TextView) findViewById(R.id.show_type);
		btn_URL = (Button) findViewById(R.id.url_button);
		btn_TEXT = (Button) findViewById(R.id.text_button);
		btnMore = (Button) findViewById(R.id.btn_set);
		iamgeTV = (TextView) findViewById(R.id.image_title);

		miniLayout = (LinearLayout) findViewById(R.id.miniAdLinearLayout);

		btn_URL.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				playVibrate();
				Intent urlIntent = new Intent("android.intent.action.VIEW", Uri
						.parse(message));
				startActivity(urlIntent);

			}
		});
		btn_TEXT.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				playVibrate();
				copy(message, ShowActivity.this);
			}
		});
		btnMore.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				playVibrate();
				Toast.makeText(ShowActivity.this, "���������Ѷ���ͨƽ̨����֧�֣�", 1).show();
				showInterstitialAd(ShowActivity.this);

				Intent intent = new Intent();
				intent.setClass(ShowActivity.this, SetingActivity.class);
				startActivity(intent);
			}
		});
	}

	private void initIntent() {
		bundle = getIntent().getExtras();
		message = bundle.getString("msg");
		if (isURL(message)) {
			this.showBannerAD();
			TV_show_type.setText("��ַ:");
			iamgeTV.setBackgroundResource(R.drawable.url72);
			btn_URL.setVisibility(View.VISIBLE);
		} else {
			btn_TEXT.setVisibility(View.VISIBLE);
		}
		if (bundle != null) {
			txt1.setText(message);

		}
	}

	private boolean isURL(String msg) {
		// String regex =
		// "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
		// Pattern patt = Pattern.compile(regex);
		// Matcher matcher = patt.matcher(msg);
		// boolean isMatch = matcher.matches();
		// if (!isMatch) {
		// System.out.println("�������URL��ַ����ȷ");
		// return false;
		// } else {
		// return true;
		// }
		if (msg.startsWith("HTTP://") || msg.startsWith("HTTPS://")
				|| msg.startsWith("http://") || msg.startsWith("https://")) {
			return true;
		} else {
			return false;
		}

	}

	private static final long VIBRATE_DURATION = 50;

	private void playVibrate() {

		Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
		vibrator.vibrate(VIBRATE_DURATION);

	}

	public static void copy(String str, Context context) {
		// �õ������������
		ClipboardManager cmb = (ClipboardManager) context
				.getSystemService(Context.CLIPBOARD_SERVICE);
		cmb.setText(str);
		Toast.makeText(context, "���Ƴɹ�", 0).show();
	}

	public void showInterstitialAd(Activity activity) {

		iad = new InterstitialAd(activity, Constants.APPId,
				Constants.InterteristalPosId);
		iad.setAdListener(new InterstitialAdListener() {
			@Override
			public void onBack() {
				// iad.loadAd();
				Log.i("admsg:", "Intertistial AD Closed");
			}

			@Override
			public void onFail() {
				Log.i("admsg:", "Intertistial AD Load Fail");
			}

			@Override
			public void onAdReceive() {
				Log.i("admsg:", "Intertistial AD  ReadyToShow");

				iad.show(ShowActivity.this);
			}

			@Override
			public void onClicked() {
				// ������淢�����ʱ�ص������ڵ��ȥ�ص����ز��ܱ�֤�ص���������������ͳ������һ��
				Log.i("admsg:", "Intertistial AD Clicked");
			}

			@Override
			public void onExposure() {
				// ��������ع�ʱ�Ļص�
				Log.i("admsg:", "Intertistial AD Exposured");
			}

			@Override
			public void onFail(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		iad.loadAd();

	}

	public void showBannerAD() {
		this.bannerAD = new AdView(this, AdSize.BANNER, Constants.APPId,
				Constants.BannerPosId);
		AdRequest adRequest = new AdRequest();
		adRequest.setShowCloseBtn(true);
		this.bannerAD.setAdListener(new AdListener() {

			@Override
			public void onNoAd() {
				Log.i("admsg:", "Banner AD LoadFail");
			}

			@Override
			public void onBannerClosed() {
				// ���ڿ������ͨ���رհ�ťʱ��Ч
				Log.i("admsg:", "Banner AD Closed");
			}

			@Override
			public void onAdReceiv() {
				Log.i("admsg:", "Banner AD Ready to show");
			}

			@Override
			public void onAdExposure() {
				Log.i("admsg:", "Banner AD Exposured");
			}

			@Override
			public void onAdClicked() {
				// Banner��淢�����ʱ�ص������ڵ��ȥ�ص����ز��ܱ�֤�ص���������������ͳ������һ��
				Log.i("admsg:", "Banner AD Clicked");
			}

			@Override
			public void onNoAd(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		this.miniLayout.removeAllViews();
		this.miniLayout.addView(bannerAD);

		bannerAD.fetchAd(adRequest);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
