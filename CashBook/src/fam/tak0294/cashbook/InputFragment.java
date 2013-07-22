package fam.tak0294.cashbook;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class InputFragment extends Fragment implements OnClickListener,OnTouchListener,OnCheckedChangeListener
{
	final static String TAG = "InputFragment";
	ImageView m_img10000yen;
	ImageView m_img5000yen;
	ImageView m_img1000yen;
	ImageView m_img500yen;
	ImageView m_img100yen;
	ImageView m_img50yen;
	ImageView m_img10yen;
	ImageView m_img5yen;
	ImageView m_img1yen;
	FrameLayout m_layoutTray;
	TextView m_sumText;
	ToggleButton m_payButton;
	ToggleButton m_incomeButton;
	
	Button m_clearButton;
	Button m_okButton;
	Button m_undoButton;
	
	CacheTray m_cacheTray;
	
	SharedPreferences m_pref;
	
	
	public InputFragment() {
		this.setRetainInstance(true);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Log.i(TAG, "onResume");
		m_cacheTray.restoreData(m_pref);
		m_cacheTray.invalidate();
		updateSumText();
	}
	
	@Override
	public void onPause()
	{
		super.onPause();
		Log.i(TAG, "onPause");
		m_cacheTray.saveData(m_pref);
	}

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG,"InputFragment onCreate");
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		Log.i(TAG,"InputFragment onSaveInstanceState");
		
	}

	
	@Override
	public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);
        
        //設定情報.
        m_pref = getActivity().getSharedPreferences(TAG, Activity.MODE_PRIVATE);
        
        
        View view = getView();

        //お金画像へのイベント割付.
        m_img10000yen = (ImageView)view.findViewById(R.id.img_10000yen);
        m_img5000yen  = (ImageView)view.findViewById(R.id.img_5000yen);
        m_img1000yen  = (ImageView)view.findViewById(R.id.img_1000yen);
        m_img500yen   = (ImageView)view.findViewById(R.id.img_500yen);
        m_img100yen   = (ImageView)view.findViewById(R.id.img_100yen);
        m_img50yen    = (ImageView)view.findViewById(R.id.img_50yen);
        m_img10yen    = (ImageView)view.findViewById(R.id.img_10yen);
        m_img5yen     = (ImageView)view.findViewById(R.id.img_5yen);
        m_img1yen     = (ImageView)view.findViewById(R.id.img_1yen);
        
        m_img10000yen.setOnTouchListener(this);
        m_img5000yen.setOnTouchListener(this);
        m_img1000yen.setOnTouchListener(this);
        m_img500yen.setOnTouchListener(this);
        m_img100yen.setOnTouchListener(this);
        m_img50yen.setOnTouchListener(this);
        m_img10yen.setOnTouchListener(this);
        m_img5yen.setOnTouchListener(this);
        m_img1yen.setOnTouchListener(this);
        
        //トグルボタンへのイベント割付.
        m_payButton    = (ToggleButton)view.findViewById(R.id.input_button_sisyutu);
        m_incomeButton = (ToggleButton)view.findViewById(R.id.input_button_syunyu);
        m_payButton.setOnCheckedChangeListener(this);
        m_incomeButton.setOnCheckedChangeListener(this);
        
        m_payButton.setChecked(true);
        
        //キャッシュトレー.
        m_layoutTray = (FrameLayout)view.findViewById(R.id.layout_cacheTray);
        m_cacheTray = new CacheTray(view.getContext());
        m_layoutTray.addView(m_cacheTray, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        
        //合計金額.
        m_sumText = (TextView)view.findViewById(R.id.txt_sum);
        
        //ボタン.
        m_clearButton = (Button)view.findViewById(R.id.clear_button);
        m_okButton    = (Button)view.findViewById(R.id.ok_button);
        m_undoButton  = (Button)view.findViewById(R.id.undo_button);
        
        m_clearButton.setOnClickListener(this);
        m_okButton.setOnClickListener(this);
        m_undoButton.setOnClickListener(this);
        
        Log.i(TAG,"InputFragment onActivityCreated");
    }
	
	@Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{  
        // 第３引数のbooleanは"container"にreturnするViewを追加するかどうか  
        //trueにすると最終的なlayoutに再度、同じView groupが表示されてしまうのでfalseでOKらしい
		View view = inflater.inflate(R.layout.input_fragment, container, false);
		Log.i(TAG,"InputFragment onCreateView");
		
        return view;  
    }

	@Override
	public void onClick(View view) {
		switch(view.getId())
		{
		case R.id.ok_button:
			break;
			
		case R.id.clear_button:
			m_cacheTray.clearData();
			m_cacheTray.invalidate();
			updateSumText();
			break;
			
		case R.id.undo_button:
			m_cacheTray.undo();
			m_cacheTray.invalidate();
			updateSumText();
			break;
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO 自動生成されたメソッド・スタブ
		switch(buttonView.getId())
		{
		case R.id.input_button_sisyutu:
			if(isChecked)
				m_incomeButton.setChecked(false);
			break;
		
		case R.id.input_button_syunyu:
			if(isChecked)
				m_payButton.setChecked(false);
			break;
		}
	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		Log.i(TAG, "onTouch !!");
		// TODO 自動生成されたメソッド・スタブ
		if(event.getAction() == MotionEvent.ACTION_DOWN)
		{
			switch(view.getId())
			{
			case R.id.img_10000yen:
				m_cacheTray.addCache(new Cache(10000, R.drawable.gra_10000yen));
				break;
	
			case R.id.img_5000yen:
				m_cacheTray.addCache(new Cache(5000, R.drawable.gra_5000yen));
				break;
	
			case R.id.img_1000yen:
				m_cacheTray.addCache(new Cache(1000, R.drawable.gra_1000yen));
				break;
	
			case R.id.img_500yen:
				m_cacheTray.addCache(new Cache(500, R.drawable.gra_500yen));
				break;
	
			case R.id.img_100yen:
				m_cacheTray.addCache(new Cache(100, R.drawable.gra_100yen));
				break;
	
			case R.id.img_50yen:
				m_cacheTray.addCache(new Cache(50, R.drawable.gra_50yen));
				break;
	
			case R.id.img_10yen:
				m_cacheTray.addCache(new Cache(10, R.drawable.gra_10yen));
				break;
	
			case R.id.img_5yen:
				m_cacheTray.addCache(new Cache(5, R.drawable.gra_5yen));
				break;
	
			case R.id.img_1yen:
				m_cacheTray.addCache(new Cache(1, R.drawable.gra_1yen));
				break;
			}
			
			m_cacheTray.invalidate();
			updateSumText();
			Log.i(TAG, "合計金額：" + m_cacheTray.getSum());
		}
		return false;
	} 
	
	private void updateSumText() {
		m_sumText.setText(m_cacheTray.getSum() + "円");
	}
}
