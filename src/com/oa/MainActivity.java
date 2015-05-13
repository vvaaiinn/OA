package com.oa;

import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.oa.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.sax.Element;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private String address = "http://218.60.65.71:9001/xtbg/WebService.asmx?op=";
	private EditText Name;
	private EditText Password;
	private static final String TAG = "MainActivity";
	private SharedPreferences sp;
	private SharedPreferences theme_choice;
	private SharedPreferences webURL;
	private SharedPreferences info;
	private String urladdress;
	private CheckBox remember;
	private Drawable drawable = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		webURL = getSharedPreferences("url", 0);
		SharedPreferences.Editor editor = webURL.edit();
		editor.putString("address", address);
		editor.commit();

		urladdress = webURL.getString("address", "");

		// this.setTheme(R.style.blue);
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		// 使用布局文件来定义标题栏
		setContentView(R.layout.main);

		theme_choice = getSharedPreferences("theme", 0);
		String style_rs = theme_choice.getString("style", "1");

		Resources res = getResources();

		if (style_rs.equals("1")) {
			drawable = res.getDrawable(R.drawable.bluebegin);
			this.getWindow().setBackgroundDrawable(drawable);
		} else {
			drawable = res.getDrawable(R.drawable.redbegin);
			this.getWindow().setBackgroundDrawable(drawable);
		}

		Name = (EditText) this.findViewById(R.id.name);
		Name.setPadding(15, 0, 0, 0);
		Password = (EditText) this.findViewById(R.id.password);
		Password.setPadding(15, 0, 0, 0);
		Button btnSearch = (Button) this.findViewById(R.id.btnSearch);
		remember = (CheckBox) findViewById(R.id.remember);
		sp = getSharedPreferences("userInfo", 0);
		String name = sp.getString("USER_NAME", "");
		String pass = sp.getString("PASSWORD", "");
		boolean choseRemember = sp.getBoolean("remember", false);
		// 如果上次选了记住密码，那进入登录页面也自动勾选记住密码，并填上用户名和密码
		if (choseRemember) {
			Name.setText(name);
			Password.setText(pass);
			remember.setChecked(true);
		}

		btnSearch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String name = Name.getText().toString();
				String password = Password.getText().toString();
				//
				// SharedPreferences.Editor ed = theme_choice.edit();
				// ed.putString("style","2");
				// ed.commit();

				SharedPreferences.Editor editor = sp.edit();

				// 读取xml文件
				InputStream inStream = this.getClass().getClassLoader()
						.getResourceAsStream("sign.xml");
				try {
					URL url = new URL(urladdress + "shenfen");
					Getinfo g = new Getinfo();
					String result = g.getResult(inStream, name, password, url)
							.toString();
					if (result.equals("Error")) {
						Toast.makeText(MainActivity.this, "帐号或密码错误，请重新输入",
								Toast.LENGTH_SHORT).show();
						Name.setText("");
						Password.setText("");
					} else {
						// System.out.println(result);
						info = getSharedPreferences("info", 0);
						SharedPreferences.Editor ed = info.edit();
						ed.putString("info", result);
						ed.commit();

						editor.putString("USER_NAME", name);
						editor.putString("PASSWORD", password);
						// 是否记住密码
						if (remember.isChecked()) {
							editor.putBoolean("remember", true);
						} else {
							editor.putBoolean("remember", false);
						}
						editor.commit();

						Bundle data = new Bundle();
						data.putSerializable("result", result);
						Intent intent = new Intent(MainActivity.this,
								Menu.class);
						intent.putExtras(data);
						Toast.makeText(MainActivity.this, "登陆成功", 1).show();
						startActivity(intent);
						finish();
					}

				} catch (Exception e) {
					// Log.e(TAG, e.toString());
					Toast.makeText(MainActivity.this, "Error",
							Toast.LENGTH_SHORT).show();
				}

			}
		});

	}
}