package com.allen.qrcode;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.allen.myqrcode.R;
import com.qq.e.appwall.GdtAppwall;

public class SetingActivity extends BaseActivity {
	ListView ListView;

	// չʾ������
	private String[] texts = new String[] { "�������", "��Ʒ�Ƽ�", "����������", "�������ݾ����ڴ�" };
	// չʾ��ͼƬ
	private int[] images = new int[] { R.drawable.xa, R.drawable.xa,
			R.drawable.xa, R.drawable.xa };

	ImageView imageView;
	TextView title;

	LeftMenuAdapter leftMenuAdapter;
	LeftMenuOnClick leftMenuOnClick;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seting);
		imageView = (ImageView) findViewById(R.id.btn_back);
		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SetingActivity.this.finish();

			}
		});
		leftMenuOnClick = new LeftMenuOnClick();
		ListView = (ListView) findViewById(R.id.listview);
		leftMenuAdapter = new LeftMenuAdapter();
		ListView.setAdapter(leftMenuAdapter);
		ListView.setOnItemClickListener(leftMenuOnClick);

	}

	public class LeftMenuOnClick implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub

			switch (arg2) {

			case 0:

				sendEmail(SetingActivity.this);
				break;
			case 1:
				/*
				 * ����Ӧ��ǽ��� ��appid�� ָ�� http://e.qq.com/dev/ �ܿ�����appΨһ�ַ��� ��posid��
				 * ָ�� http://e.qq.com/dev/ ���ɵ����ִ��� ���� appid ���� appkey testad
				 * �������Ϊtrue���������Թ��ģʽ���ù��ģʽ�²��۷ѡ� �����ڵ�ʽʱ����Ϊtrue������ǰ����Ϊfalse��
				 */
				final GdtAppwall appwall = new GdtAppwall(SetingActivity.this,
						Constants.APPId, Constants.YYQPosId, false);
				appwall.doShowAppWall();

				break;
			case 2:
				Uri weatheruri = Uri
						.parse("http://a.app.qq.com/o/simple.jsp?pkgname=com.allen.weather");
				Intent intent1 = new Intent(Intent.ACTION_VIEW, weatheruri);
				startActivity(intent1);
				break;
			case 3:
				Toast.makeText(SetingActivity.this, "�����������ڼӰ࿪���С�����", 0).show();
				break;

			}

		}

	}

	public class LeftMenuAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return texts.length;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View view, ViewGroup arg2) {
			if (view == null) {
				view = LayoutInflater.from(getApplicationContext()).inflate(
						R.layout.item_seting, null);

				imageView = (ImageView) view
						.findViewById(R.id.imageView_left_menu);
				title = (TextView) view.findViewById(R.id.left_menu_title);

			}

			imageView.setImageResource(images[position]);
			title.setText(texts[position]);
			return view;
		}

	}

	/**
	 * ����һ���򵥵Ĳ��ԣ���֧�ִ����������ˣ����ͷ��͵ȡ�
	 * 
	 * @param context
	 */
	public void sendEmail(Context context) {
		// Intent intent = new Intent();
		// intent.setData(Uri.parse("mailto:"));
		Intent intent = new Intent(Intent.ACTION_SEND);

		String[] tos = { "lygttpod@163.com" };

		intent.putExtra(Intent.EXTRA_EMAIL, tos); // �ռ���
		intent.setType("message/rfc822"); // �����ʼ���ʽ

		/* �����ʼ��ı��� */
		intent.putExtra(Intent.EXTRA_SUBJECT, "�������");
		/* �����ʼ������� */
		intent.putExtra(Intent.EXTRA_TEXT, "����ϸ����ʹ�ñ�������������������⣬���ǻ��һʱ����������");
		// ��ʼ����
		context.startActivity(intent);
	}

}
