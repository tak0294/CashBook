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
		//canvas.drawBitmap(m_trayBitmap, 0, 0, null);
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
		int coinColCount = 0;
		int paperColCount = 0;
		
		for(int ii=0;ii<m_caches.size();ii++)
		{
			Cache tmpCache = m_caches.get(ii);
			m.reset();
			int x = 0;
			int y = 0;
			
			
			BitmapFactory.Options option = new BitmapFactory.Options();
			
			//if(tmpBitmap != null)
			{
				if(tmpCache.getType() == Cache.TYPE.PAPER)
				{
					//m.postScale(0.2f, 0.2f);
					option.inSampleSize = 2;
					x = 10 + paperColCount * 3;
					y = paperCount * 10 + 10;
					
					paperCount++;
					if(paperCount == 7)
					{
						paperCount = 0;
						paperColCount++;
					}
				}
				else
				{
					//m.postScale(0.5f, 0.5f);
					option.inSampleSize = 4;
					if(tmpCache.getAmount() == 500)
						x = 90;
					else if(tmpCache.getAmount() == 100)
						x = 100;
					else if(tmpCache.getAmount() == 50)
						x = 110;
					else if(tmpCache.getAmount() == 10)
						x = 120;
					else if(tmpCache.getAmount() == 5)
						x = 130;
					else
						x = 140;
					
					x += coinColCount*3;
					y = coinCount * 10 + 10;
					coinCount++;
					if(coinCount == 7)
					{
						coinCount = 0;
						coinColCount++;
					}
					
				}
				
				Bitmap tmpBitmap = BitmapUtil.getBitmapById(getResources(), tmpCache.getResId(), option);

				m.postTranslate(x, y);
				canvas.drawBitmap(tmpBitmap, m, null);
				//tmpBitmap.recycle();
				//tmpBitmap = null;
			}
		}
	}
}
