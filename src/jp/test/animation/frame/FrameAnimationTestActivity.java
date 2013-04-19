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

	// �{�^��
	Button mBtnAnimation;
	Button mBtnAnimFromXML;

	// �r���[
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
			// �A�j���[�V�������Ȃ�A��~
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
    
    
	// �t���[���A�j���[�V�����̃e�X�g
    void frameAnimationTest( Context con, View v ){
    	AnimationDrawable anim = new AnimationDrawable();
    	
    	// �摜�̓ǂݍ��� 
    	Drawable frame1 = con.getResources().getDrawable( R.drawable.frame1 );
    	Drawable frame2 = con.getResources().getDrawable( R.drawable.frame2 );
    	Drawable frame3 = con.getResources().getDrawable( R.drawable.frame3 );
    	Drawable frame4 = con.getResources().getDrawable( R.drawable.frame4 );
    	Drawable frame5 = con.getResources().getDrawable( R.drawable.frame5 );
    	
    	// �摜���A�j���[�V�����̃R�}�Ƃ��Ēǉ����Ă���
    	anim.addFrame( frame1,  60 );
    	anim.addFrame( frame2,  60 );
    	anim.addFrame( frame3,  70 );
    	anim.addFrame( frame4,  80 );
    	anim.addFrame( frame5, 120 );
    	anim.addFrame( frame4,  80 );
    	anim.addFrame( frame3,  70 );
    	anim.addFrame( frame2,  60 );
    	anim.addFrame( frame1,  60 );
    	
    	// �J��Ԃ��ݒ�
    	anim.setOneShot(false);
    	
    	// �r���[�̔w�i�摜�ɃA�j���[�V������ݒ�
    	v.setBackgroundDrawable( anim );
    	
    	// �A�j���[�V�����J�n
    	anim.start();
    }
    
    
    // �t���[���A�j���[�V������ XML ����ǂݍ���
    void frameAnimationFromXMLTest( View v ){
    	
    	// ���\�[�X����A�j���[�V������ǂݍ��݁A�r���[�ɐݒ�
    	v.setBackgroundResource( R.drawable.droid_jump );
    	
    	// �r���[����A�j���[�V���������o��
    	AnimationDrawable anim = (AnimationDrawable)v.getBackground();
    	
    	// �A�j���[�V�����J�n
    	anim.start();
    }
}