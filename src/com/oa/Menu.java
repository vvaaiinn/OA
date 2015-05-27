package com.oa;

import java.io.InputStream;
import java.net.URL;

import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Menu extends ActivityGroup {

	// private ImageButton btn ;
	// private TextView news;
	// private ImageButton sp;
	// private ImageButton em;
	// private ImageButton rw;
	// private TextView dwgg;
	// private TextView wdsp;
	// private TextView wdrw;
	// private TextView wdyj;
	// private static final String TAG = "XML";

	private LinearLayout bodyView, headview;
	private LinearLayout one, two, three, four, five;
	private int flag = 12;
	TextView ts = null;
	InputStream inStream = null;
	String result = null;
	Resources res = null;
	Drawable top_drDrawable = null;
	Drawable bg_drawable = null;
	Drawable bt_drawable = null;
	private SharedPreferences theme_choice;
	private final String arrayem[] = new String[] { "写邮件", "收件箱" };
	private final String arraygg[] = new String[] { "发公告", "公告查看" };
	private SharedPreferences webURL;
	private String urladdress;

	public void onCreate(Bundle savedInstanceState) {
		webURL = getSharedPreferences("url", 0);
		urladdress = webURL.getString("address", "");

		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		// 使用布局文件来定义标题栏
		setContentView(R.layout.mainactivity);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);
		ts = (TextView) findViewById(R.id.titleset);
		ts.setText("菜单");

		RelativeLayout tit = (RelativeLayout) findViewById(R.id.title);

		theme_choice = getSharedPreferences("theme", 0);
		String style_rs = theme_choice.getString("style", "1");

		res = getResources();
		bt_drawable = res.getDrawable(R.drawable.frame_button_background_white);
		if (style_rs.equals("1")) {
			top_drDrawable = res.getDrawable(R.drawable.top_blue);
			tit.setBackgroundDrawable(top_drDrawable);
			// bg_drawable = res.getDrawable(R.drawable.default_bg);
			// this.getWindow().setBackgroundDrawable(bg_drawable);

		} else {
			top_drDrawable = res.getDrawable(R.drawable.top_red_1);
			tit.setBackgroundDrawable(top_drDrawable);
			// bg_drawable = res.getDrawable(R.drawable.red_bg);
			// this.getWindow().setBackgroundDrawable(bg_drawable);
			// bt_drawable =
			// res.getDrawable(R.drawable.frame_button_background_red);
		}

		inStream = this.getClass().getClassLoader()
				.getResourceAsStream("getnews.xml");
		try {
			URL url = new URL(urladdress + "Getnews");
			Getnews g = new Getnews();
			result = g.getResult(inStream, url).toString();
			System.out.println(result);
		} catch (Exception e) {
			Toast.makeText(Menu.this, "查询失败", 1).show();
		}

		initMainView();
		Intent intent = getIntent();
		flag = intent.getIntExtra("flag", 12);
		System.out.println(flag);
		showView(flag);
		// ts.setText("新闻公告");
		two.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				flag = 0;
				ts.setText("我的审批");
				showView(flag);
			}
		});

		one.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub

				new AlertDialog.Builder(Menu.this)
						.setTitle("新闻公告")
						.setItems(arraygg,
								new DialogInterface.OnClickListener() {// Items
																		// to
																		// choose
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										Toast.makeText(Menu.this,
												arraygg[which],
												Toast.LENGTH_LONG).show();
										if (which == 1) {
											flag = 12;
											ts.setText("单位公告");
											showView(flag);
										} else {
											flag = 11;
											ts.setText("发公告");
											showView(flag);
										}
									}
								})
						.setNegativeButton("取消选择",
								new DialogInterface.OnClickListener() {// cancel

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// cancel to do
									}
								}).show();

			}
		});

		three.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub

				new AlertDialog.Builder(Menu.this)
						.setTitle("内部邮件")
						.setItems(arrayem,
								new DialogInterface.OnClickListener() {// Items
																		// to
																		// choose
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										Toast.makeText(Menu.this,
												arrayem[which],
												Toast.LENGTH_LONG).show();
										if (which == 1) {
											flag = 22;
											ts.setText("收件箱");
											showView(flag);
										} else {
											flag = 21;
											ts.setText("写邮件");
											showView(flag);
										}
									}
								})
						.setNegativeButton("取消选择",
								new DialogInterface.OnClickListener() {// cancel

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// cancel to do
									}
								}).show();

			}
		});
		four.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				flag = 3;
				ts.setText("我的会签");
				showView(flag);
			}
		});
		five.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				flag = 4;
				ts.setText("设置");
				showView(flag);
			}
		});

		// news = (TextView) this.findViewById(R.id.shownews);
		// btn = (ImageButton)findViewById(R.id.news);
		// sp = (ImageButton)findViewById(R.id.sp);
		// em = (ImageButton)findViewById(R.id.mail);
		// rw = (ImageButton)findViewById(R.id.job);
		// wdsp = (TextView)findViewById(R.id.wdsp);
		// wdrw = (TextView)findViewById(R.id.wdrw);
		// dwgg = (TextView)findViewById(R.id.dwgg);
		// wdyj = (TextView)findViewById(R.id.wdyj);
		// dwgg.setOnClickListener(wgg);
		// wdsp.setOnClickListener(dsp);
		// wdyj.setOnClickListener(yj);
		// wdrw.setOnClickListener(drw);
		//
		// btn.setOnClickListener(wgg);
		// sp.setOnClickListener(dsp);
		// em.setOnClickListener(yj);
		// rw.setOnClickListener(drw);
	}

	public void initMainView() {
		// headview=(LinearLayout) findViewById(R.id.head);
		bodyView = (LinearLayout) findViewById(R.id.body);
		one = (LinearLayout) findViewById(R.id.one);
		two = (LinearLayout) findViewById(R.id.two);
		three = (LinearLayout) findViewById(R.id.three);
		four = (LinearLayout) findViewById(R.id.four);
		five = (LinearLayout) findViewById(R.id.five);
	}

	public void showView(int flag) {
		switch (flag) {
		case 0:
			bodyView.removeAllViews();
			Intent intent1 = new Intent(Menu.this, ShShow.class);
			intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			// intent1.putExtra("result","1");
			bodyView.addView(getLocalActivityManager().startActivity("one",
					intent1).getDecorView());
			ts.setText("我的审批");
			two.setBackgroundDrawable(bt_drawable);
			one.setBackgroundResource(R.drawable.frame_button_nopressbg);
			three.setBackgroundResource(R.drawable.frame_button_nopressbg);
			four.setBackgroundResource(R.drawable.frame_button_nopressbg);
			five.setBackgroundResource(R.drawable.frame_button_nopressbg);
			break;
		case 12:
			bodyView.removeAllViews();
			if (result != null) {
				Intent intent11 = new Intent(Menu.this, NewsShow.class);
				intent11.putExtra("result", result);
				bodyView.addView(getLocalActivityManager().startActivity("two",
						intent11).getDecorView());
			}
			ts.setText("新闻公告");

			one.setBackgroundDrawable(bt_drawable);
			two.setBackgroundResource(R.drawable.frame_button_nopressbg);
			three.setBackgroundResource(R.drawable.frame_button_nopressbg);
			four.setBackgroundResource(R.drawable.frame_button_nopressbg);
			five.setBackgroundResource(R.drawable.frame_button_nopressbg);
			break;
		case 11:
			bodyView.removeAllViews();
			Intent intent12 = new Intent(Menu.this, SendGg.class);
			bodyView.addView(getLocalActivityManager().startActivity("two",
					intent12).getDecorView());
			ts.setText("发公告");
			two.setBackgroundResource(R.drawable.frame_button_nopressbg);
			one.setBackgroundDrawable(bt_drawable);
			three.setBackgroundResource(R.drawable.frame_button_nopressbg);
			four.setBackgroundResource(R.drawable.frame_button_nopressbg);
			five.setBackgroundResource(R.drawable.frame_button_nopressbg);
			break;

		case 21:
			bodyView.removeAllViews();
			Intent intent21 = new Intent(Menu.this, SendEm.class);
			bodyView.addView(getLocalActivityManager().startActivity("three",
					intent21).getDecorView());
			ts.setText("发邮件");
			one.setBackgroundResource(R.drawable.frame_button_nopressbg);
			two.setBackgroundResource(R.drawable.frame_button_nopressbg);
			three.setBackgroundDrawable(bt_drawable);
			four.setBackgroundResource(R.drawable.frame_button_nopressbg);
			five.setBackgroundResource(R.drawable.frame_button_nopressbg);
			break;
		case 22:
			bodyView.removeAllViews();
			Intent intent22 = new Intent(Menu.this, EmShow.class);
			bodyView.addView(getLocalActivityManager().startActivity("three",
					intent22).getDecorView());
			ts.setText("收件箱");
			one.setBackgroundResource(R.drawable.frame_button_nopressbg);
			two.setBackgroundResource(R.drawable.frame_button_nopressbg);
			three.setBackgroundDrawable(bt_drawable);
			four.setBackgroundResource(R.drawable.frame_button_nopressbg);
			five.setBackgroundResource(R.drawable.frame_button_nopressbg);
			break;
		case 3:
			bodyView.removeAllViews();
			Intent intent2 = new Intent(Menu.this, HqShow.class);
			// intent2.putExtra("result","2");
			bodyView.addView(getLocalActivityManager().startActivity("four",
					intent2).getDecorView());
			ts.setText("我的会签");
			one.setBackgroundResource(R.drawable.frame_button_nopressbg);
			two.setBackgroundResource(R.drawable.frame_button_nopressbg);
			three.setBackgroundResource(R.drawable.frame_button_nopressbg);
			four.setBackgroundDrawable(bt_drawable);
			five.setBackgroundResource(R.drawable.frame_button_nopressbg);
			break;
		case 4:
			bodyView.removeAllViews();
			Intent intent3 = new Intent(Menu.this, Config.class);
			bodyView.addView(getLocalActivityManager().startActivity("five",
					intent3).getDecorView());
			ts.setText("设置");
			one.setBackgroundResource(R.drawable.frame_button_nopressbg);
			two.setBackgroundResource(R.drawable.frame_button_nopressbg);
			three.setBackgroundResource(R.drawable.frame_button_nopressbg);
			four.setBackgroundResource(R.drawable.frame_button_nopressbg);
			five.setBackgroundDrawable(bt_drawable);
			break;

		default:
			break;
		}
	}

	// public OnClickListener drw = new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// // TODO 自动生成的方法存根
	// Intent intent = new Intent(Menu.this,SpShow.class);
	// intent.putExtra("result","2");
	// startActivity(intent);
	//
	// }
	// };
	//
	// public OnClickListener yj = new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// // TODO 自动生成的方法存根
	// Intent intent = new Intent(Menu.this,EmShow.class);
	// startActivity(intent);
	//
	// }
	// };
	// public OnClickListener dsp = new OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// // TODO 自动生成的方法存根
	// Intent intent = new Intent(Menu.this,SpShow.class);
	// intent.putExtra("result","1");
	// startActivity(intent);
	//
	// }
	// };
	// public OnClickListener wgg = new OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// // TODO 自动生成的方法存根
	//
	// InputStream inStream =
	// this.getClass().getClassLoader().getResourceAsStream("getnews.xml");
	// try
	// {
	// URL url = new URL("http://10.34.11.224:88/WebService.asmx?op=Getnews");
	// Getnews g = new Getnews();
	// String result = g.getResult(inStream, url).toString();
	// //news.setText(result);
	// if(result!=null)
	// {
	// Intent intent = new Intent(Menu.this,NewsShow.class);
	// intent.putExtra(```"result",result);
	// startActivity(intent);
	// }
	// }
	// catch (Exception e)
	// {
	// Toast.makeText(Menu.this, "查询失败", 1).show();
	// }
	// }
	// };

}
