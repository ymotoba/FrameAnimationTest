package jp.test.animation.frame;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FrameAnimationTestActivity extends Activity {

	// ボタン
	Button mBtnAnimation;
	Button mBtnAnimFromXML;

	// ビュー
	ImageView mImageAnimation;

	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mBtnAnimation   = (Button) findViewById( R.id.button_animation     );
        mBtnAnimFromXML = (Button) findViewById( R.id.button_anim_from_xml );
        mImageAnimation = (ImageView) findViewById( R.id.image_animation );

        mBtnAnimation.setOnClickListener(mClickListener);
        mBtnAnimFromXML.setOnClickListener(mClickListener);
    }
    
    
    View.OnClickListener mClickListener = new View.OnClickListener() {
		public void onClick(View v) {
			// アニメーション中なら、停止
			Drawable d = mImageAnimation.getBackground();
			if( d != null ){
				try{ 
					if( ((AnimationDrawable) d).isRunning() ){
						((AnimationDrawable) d).stop();
						return;
					}
				}
				catch( RuntimeException e ){
					e.printStackTrace();
				}
			}
			
			if( v == mBtnAnimation   ){ 
				frameAnimationTest( 
					FrameAnimationTestActivity.this, mImageAnimation );
			}
			else if( v == mBtnAnimFromXML ){
				frameAnimationFromXMLTest( mImageAnimation );
			}
		}
	};
    
    
	// フレームアニメーションのテスト
    void frameAnimationTest( Context con, View v ){
    	AnimationDrawable anim = new AnimationDrawable();
    	
    	// 画像の読み込み 
    	Drawable frame1 = con.getResources().getDrawable( R.drawable.frame1 );
    	Drawable frame2 = con.getResources().getDrawable( R.drawable.frame2 );
    	Drawable frame3 = con.getResources().getDrawable( R.drawable.frame3 );
    	Drawable frame4 = con.getResources().getDrawable( R.drawable.frame4 );
    	Drawable frame5 = con.getResources().getDrawable( R.drawable.frame5 );
    	
    	// 画像をアニメーションのコマとして追加していく
    	anim.addFrame( frame1,  60 );
    	anim.addFrame( frame2,  60 );
    	anim.addFrame( frame3,  70 );
    	anim.addFrame( frame4,  80 );
    	anim.addFrame( frame5, 120 );
    	anim.addFrame( frame4,  80 );
    	anim.addFrame( frame3,  70 );
    	anim.addFrame( frame2,  60 );
    	anim.addFrame( frame1,  60 );
    	
    	// 繰り返し設定
    	anim.setOneShot(false);
    	
    	// ビューの背景画像にアニメーションを設定
    	v.setBackgroundDrawable( anim );
    	
    	// アニメーション開始
    	anim.start();
    }
    
    
    // フレームアニメーションを XML から読み込む
    void frameAnimationFromXMLTest( View v ){
    	
    	// リソースからアニメーションを読み込み、ビューに設定
    	v.setBackgroundResource( R.drawable.droid_jump );
    	
    	// ビューからアニメーションを取り出し
    	AnimationDrawable anim = (AnimationDrawable)v.getBackground();
    	
    	// アニメーション開始
    	anim.start();
    }
}