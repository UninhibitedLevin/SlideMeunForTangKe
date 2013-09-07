package com.example.nslidemenudemo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.aretha.slidemenu.SlideMenu;

public class MainActivity extends FragmentActivity implements OnItemSelectedListener{

	public SlideMenu mSlideMenu;
	private Spinner mInterpolator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slidemenu);
		mSlideMenu = (SlideMenu) findViewById(R.id.slideMenu);
		setSlideRole(R.layout.content_fragment);
		setSlideRole(R.layout.left_fragment);
		setSlideRole(R.layout.right_fragment);
		mSlideMenu.setSlideMode(SlideMenu.MODE_SLIDE_WINDOW);
		
		mInterpolator = (Spinner) findViewById(R.id.interpolator);
		
		mInterpolator.setOnItemSelectedListener(this);//���Ը��������ǵð����ע�͵�
		
		
		mInterpolator.setAdapter(ArrayAdapter.createFromResource(this,
				R.array.interpolator, android.R.layout.simple_list_item_1));
		
//		mSlideMenu.setInterpolator(new BounceInterpolator());
	}

	public void setSlideRole(int res) {
		if (null == mSlideMenu) {
			return;
		}

		getLayoutInflater().inflate(res, mSlideMenu, true);
	}

	public SlideMenu getSlideMenu() {
		return mSlideMenu;
	}

	@Override
	public void onItemSelected(AdapterView<?> adapterView, View view, int position,
			long id) {
		Interpolator interpolator;
		switch (position) {
		default:
		case 0://Ĭ�϶���
			interpolator = SlideMenu.DEFAULT_INTERPOLATOR;
			break;
		case 1://���ټ���
			interpolator = new AccelerateDecelerateInterpolator();
			break;
		case 2://����
			interpolator = new AccelerateInterpolator();
			break;
		case 3://����
			interpolator = new AnticipateInterpolator();
			break;
		case 4://��ʼ����
			interpolator = new AnticipateOvershootInterpolator();
			break;
		case 5://����
			interpolator = new BounceInterpolator();
			break;
		case 6://����
			interpolator = new DecelerateInterpolator();
			break;
		case 7://����
			interpolator = new LinearInterpolator();
			break;
		case 8://�ص�
			interpolator = new OvershootInterpolator();
			break;
		}
		mSlideMenu.setInterpolator(interpolator);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			System.out.println(mSlideMenu.getCurrentState());
			if(mSlideMenu.getCurrentState()==2){
				mSlideMenu.open(true, true);
			}else if(mSlideMenu.getCurrentState()==4){
				mSlideMenu.open(false, true);
			}else if(mSlideMenu.getCurrentState()==1||mSlideMenu.getCurrentState()==0){//�����棬�����˳�
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
