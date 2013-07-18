package fam.tak0294.cashbook;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.view.View;


public class CacheTray extends View {
	
	ArrayList<Cache> m_caches;
	Bitmap m_trayBitmap;
	
	
	public CacheTray(Context context)
	{
		super(context);
		m_caches = new ArrayList<Cache>();
		m_trayBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gra_dai);
		
	}
	
	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		setMeasuredDimension(m_trayBitmap.getWidth(), m_trayBitmap.getHeight());
	}
	
	
	public void addCache(Cache cache)
	{
		m_caches.add(cache);
	}
	
	public int getSum()
	{
		int sum = 0;
		for(int ii=0;ii<m_caches.size();ii++)
		{
			sum += m_caches.get(ii).getAmount();
		}
		return sum;
	}
	
	@Override
	public void onDraw(Canvas canvas)
	{
		canvas.drawBitmap(m_trayBitmap, 0, 0, null);
		drawCaches(canvas);
	}
	
	//------------------------------
	//	private methods.
	//------------------------------
	private void drawCaches(Canvas canvas)
	{
		Matrix m = new Matrix();
		Random rand = new Random(123456);
		
		int coinCount  = 0;
		int paperCount = 0;
		
		for(int ii=0;ii<m_caches.size();ii++)
		{
			Cache tmpCache = m_caches.get(ii);
			m.reset();
			int x = 0;
			int y = 0;
			
			Bitmap tmpBitmap = BitmapFactory.decodeResource(getResources(), tmpCache.getResId());
			if(tmpBitmap != null)
			{
				if(tmpCache.getType() == Cache.TYPE.PAPER)
				{
					m.preScale(0.75f, 0.75f);
					x = 10;
					y = paperCount * 10;
					paperCount++;
				}
				else
				{
					m.preScale(0.5f, 0.5f);
					if(tmpCache.getAmount() == 500)
						x = 160;
					else if(tmpCache.getAmount() == 100)
						x = 180;
					else if(tmpCache.getAmount() == 50)
						x = 200;
					else if(tmpCache.getAmount() == 10)
						x = 220;
					else if(tmpCache.getAmount() == 5)
						x = 240;
					else
						x = 260;
					y = coinCount * 10;
					coinCount++;
				}

				m.preTranslate(x, y);
				canvas.drawBitmap(tmpBitmap, m, null);
				tmpBitmap.recycle();
				tmpBitmap = null;
			}
		}
	}
}
