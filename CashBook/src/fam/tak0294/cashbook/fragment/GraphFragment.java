package fam.tak0294.cashbook.fragment;

import fam.tak0294.cashbook.CalendarView;
import fam.tak0294.cashbook.R;
import fam.tak0294.cashbook.R.id;
import fam.tak0294.cashbook.R.layout;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class GraphFragment extends Fragment {

	@Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{  
        // 第３引数のbooleanは"container"にreturnするViewを追加するかどうか  
        //trueにすると最終的なlayoutに再度、同じView groupが表示されてしまうのでfalseでOKらしい  
		View view = inflater.inflate(R.layout.graph_fragment, container, false);
		
		CalendarView calendarView = new CalendarView(getActivity());
		calendarView.set(2013, 7-1);
		LinearLayout layout = (LinearLayout)view.findViewById(R.id.calendarViewLayout);
		layout.addView(calendarView);
		
		return view;
    }  
}
