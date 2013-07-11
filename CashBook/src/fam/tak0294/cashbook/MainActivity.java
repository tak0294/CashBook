package fam.tak0294.cashbook;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

public class MainActivity extends SherlockFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(R.style.Theme_Sherlock_Light_DarkActionBar);
		setContentView(R.layout.activity_main);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
		
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		
		transaction.add(R.id.content_fragment, new InputFragment());
		transaction.add(R.id.tab_fragment, new TabFragment());
		
		transaction.commit();
		
	}
	
	
	public void addFragment(int id)
	{
		System.out.println("ADD FRAGMENT CALL" + id);
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		
		if(id == 1)
		{
			transaction.replace(R.id.content_fragment, new InputFragment());
		}
		else if(id == 2)
		{
			transaction.replace(R.id.content_fragment, new GraphFragment());
		}
		
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		transaction.addToBackStack(null);
		transaction.commit();
	}
	
	
	
	@Override
	public void onPause()
	{
		super.onPause();
		System.out.println("[MainActivity] onPause");
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		System.out.println("[MainActivity] onResume");
	}
}
