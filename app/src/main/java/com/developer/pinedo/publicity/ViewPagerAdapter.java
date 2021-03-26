package com.developer.pinedo.publicity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.developer.pinedo.publicity.Entities.Publicity;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private List<Publicity> imageUrls;

    ViewPagerAdapter(Context context, List<Publicity> imageUrls) {
        this.context = context;
        this.imageUrls = imageUrls;
    }

    @Override
    public int getCount() {
        return imageUrls.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        Log.d("JORGE-POSI",position+"");

        /*if(imageUrls.get(position).getType_content().equals("image")){
            ImageView imageView = new ImageView(context);
            Picasso.get()
                    .load(imageUrls.get(position).getImage())
                    .fit()
                    .centerCrop()
                    .into(imageView);
            container.addView(imageView);
            return imageView;
        }else{*/

//            ((MainActivity)context).removeRunnable();

            VideoView videoView = new VideoView(context);

            //videoView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT));
            //videoView.setLayoutParams(new ViewGroup.LayoutParams(Parents));


            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.FILL_PARENT,
                            RelativeLayout.LayoutParams.FILL_PARENT);

            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.ALIGN_PARENT_TOP);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.ALIGN_PARENT_BOTTOM);

//            videoView.setLayoutParams(params);

//            container.setLayoutParams(params);

            //videoView.setLayoutParams(new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.FILL_PARENT,ViewGroup.MarginLayoutParams.FILL_PARENT));


            MediaController vidControl = new MediaController(context);

            Uri vidUri = Uri.parse(imageUrls.get(position).getImage());

            videoView.setVideoURI(vidUri);


            videoView.start();



            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    ((MainActivity)context).setCurrentItem(position + 1);
                    Log.d("JORGE","FINISIHS COMERCIAL "+position);
                }
            });

            vidControl.setAnchorView(videoView);

            videoView.setMediaController(vidControl);

            container.addView(videoView,params);

            return videoView;
        //  }



    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
