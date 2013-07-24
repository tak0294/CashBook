package fam.tak0294.cashbook.fragment;

import fam.tak0294.cashbook.MainActivity;
import fam.tak0294.cashbook.R;
import fam.tak0294.cashbook.R.id;
import fam.tak0294.cashbook.R.layout;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class TabFragment extends Fragment
{
	RadioGroup tabRadioGroup;
	RadioButton tab1RadioButton;
	RadioButton tab2RadioButton;
	RadioButton tab3RadioButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{  
        // ��R������boolean��"container"��return����View��ǉ����邩�ǂ���  
        //true�ɂ���ƍŏI�I��layout�ɍēx�A����View group���\������Ă��܂��̂�false��OK�炵��  
        return inflater.inflate(R.layout.tab_fragment, container, false);  
    }
	
	@Override
	public void onStart()
	{
		super.onStart();
		findViews();
		
		MyOnClickListener listener = new MyOnClickListener();
		tab1RadioButton.setOnClickListener(listener);
		tab2RadioButton.setOnClickListener(listener);
		tab3RadioButton.setOnClickListener(listener);
	}
	
	protected void findViews()
	{
		tabRadioGroup 	= (RadioGroup)  getActivity().findViewById(R.id.radioGroup1);
		tab1RadioButton = (RadioButton) getActivity().findViewById(R.id.radio0);
		tab2RadioButton = (RadioButton) getActivity().findViewById(R.id.radio1);
		tab3RadioButton = (RadioButton) getActivity().findViewById(R.id.radio2);
	}
	
	public class MyOnClickListener implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO �����������ꂽ���\�b�h�E�X�^�u
			if(v == tab1RadioButton)
			{
				((MainActivity) getActivity()).addFragment(1);
			}
			else if(v == tab2RadioButton)
			{
				((MainActivity) getActivity()).addFragment(2);
			}
		}
	}
}
