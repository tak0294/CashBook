package fam.tak0294.cashbook;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class GraphFragment extends Fragment {

	@Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{  
        // ��R������boolean��"container"��return����View��ǉ����邩�ǂ���  
        //true�ɂ���ƍŏI�I��layout�ɍēx�A����View group���\������Ă��܂��̂�false��OK�炵��  
        return inflater.inflate(R.layout.graph_fragment, container, false);  
    }  
}
