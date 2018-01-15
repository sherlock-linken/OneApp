package com.witbit.sherlock.textview;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.witbit.sherlock.oneapp.R;

public class TextViewActivity extends AppCompatActivity {


	private TextView mTextView;

	private String test = "赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘" + "" + "葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳" + "酆鲍史唐费廉岑薛雷贺" +
			"倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董" + ".梁.杜.阮.蓝.闵.席.季.麻.强" + ".贾.路.娄.危.江.童.颜.郭.梅.盛" +
			".林.刁.钟.徐.邱骆高夏蔡田樊胡凌霍虞万支柯昝管卢莫经房裘缪干解应" + "宗丁宣贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄麴家封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰" +
			"秋仲伊宫宁仇栾暴甘钭厉戎祖武符刘景詹束龙叶幸司韶郜黎蓟薄印宿白怀蒲邰从鄂索咸籍赖卓蔺屠蒙池乔阴郁胥能苍双闻莘党翟谭贡劳逄姬" +
			"申扶堵冉宰郦雍舄璩桑桂濮牛寿通边扈燕冀郏浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庾终暨居衡步都耿满弘匡国文寇广禄" +
			"阙东殴殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查後荆红";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text_view);

		mTextView = (TextView) findViewById(R.id.tv_test);

		findViewById(R.id.btn_tv_test_1).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mTextView.setText(test);
			}
		});

		findViewById(R.id.btn_tv_test_2).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//				String s = mTextView.getMaxLines() + "";

				//				Log.i("sherlock", "TextViewActivity.onClick s == " + s);
				//				int end = test.length();
				//				while (isOverFlowed(test.substring(0,end)))
				//					end--;

				//				Log.i("sherlock", "TextViewActivity.onClick size is fit now setText");
				//				mTextView.setText(test.substring(0,end));
				//				isOverFlowed();

				//				if (isOverFlowed(test)) {
				//					Log.i("sherlock", "TextViewActivity.onClick isOverFlowed");
				//				}

				Log.i("sherlock", "TextViewActivity.onClick before");
				String sub = subShowText();
				Log.i("sherlock", "TextViewActivity.onClick after");

				Log.i("sherlock", "TextViewActivity.onClick subShowText == " + sub);
				mTextView.setText(sub+"...全文");

			}
		});

	}

	@SuppressLint("NewApi")
	private int getAvailableWidth() {
		return mTextView.getMaxLines() * (mTextView.getWidth() - mTextView.getPaddingLeft() - mTextView
				.getPaddingRight());
	}

	private String subShowText() {
		Paint paint = mTextView.getPaint();

		char[] cs = test.toCharArray();
		int oneLineWidth = mTextView.getWidth() - mTextView.getPaddingLeft() - mTextView.getPaddingRight();
		int maxLineCount = 6;
		int canShowIndex = 0;

		for (int i = 0; i < maxLineCount; i++) {
			float lineShown = 0;

			if(i!=0)
				canShowIndex++;

			if(i == maxLineCount-1)
				lineShown+=paint.measureText("...全文");

			while (lineShown + paint.measureText(String.valueOf(cs[canShowIndex])) <= oneLineWidth) {
				lineShown += paint.measureText(String.valueOf(cs[canShowIndex]));
				canShowIndex++;
			}
			canShowIndex--;

			Log.i("sherlock", "TextViewActivity.subShowText line : lastChar == " + i + " : " + cs[canShowIndex]);
		}

		return String.copyValueOf(cs, 0, canShowIndex+1);
	}

	private boolean isOverFlowed(String text) {
		Paint paint = mTextView.getPaint();
		float width = paint.measureText(text);

		//		Rect rect = new Rect();
		//		paint.getTextBounds(text, 0, text.length(), rect);

		Log.i("sherlock", "TextViewActivity.isOverFlowed text == " + text);
		Log.i("sherlock", "TextViewActivity.isOverFlowed width == " + width);
		Log.i("sherlock", "TextViewActivity.isOverFlowed availbale == " + getAvailableWidth());
		if (width > getAvailableWidth())
			return true;
		return false;

		//		Layout layout = mTextView.getLayout();
		//		if (layout != null) {
		//			int lines = layout.getLineCount();
		//			if (lines > 0) {
		//				int ellipsisCount = layout.getEllipsisCount(lines - 1);
		//				if (ellipsisCount > 0) {
		//					Log.d("sherlock", "Text is ellipsized");
		//					return true;
		//				}
		//				else
		//					Log.i("sherlock", "TextViewActivity.isOverFlowed not ellipsized");
		//			}
		//			else
		//				Log.i("sherlock", "TextViewActivity.isOverFlowed lines <= 0");
		//		}
		//		else
		//			Log.i("sherlock", "TextViewActivity.isOverFlowed layout is null");
		//
		//		return false;
	}
}
