package com.developer.pinedo.publicity;


import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.developer.pinedo.publicity.Entities.AccessToken;
import com.developer.pinedo.publicity.Entities.Publicity;
import com.developer.pinedo.publicity.Network.ApiError;
import com.developer.pinedo.publicity.Network.ApiService;
import com.developer.pinedo.publicity.Network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private List<Publicity> imageUrls;

    private int secs = 0;
    private boolean isActive = false;
    public int position = 1;


    private Handler handler=new Handler();

    ApiService service,serviceAuth;
    Call<AccessToken> call;
    Call<List<Publicity>> callPublicity;

    TokenManager tokenManager;
    ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();



        viewPager = findViewById(R.id.pager);

        service = RetrofitBuilder.createService(ApiService.class);

        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE));

       // call = service.login("tablet@fiaro.com","123456");

        /*call.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                if (response.isSuccessful()) {
                    tokenManager.saveToken(response.body());
                    Log.d("JORGE",response.body().toString());


                    serviceAuth = RetrofitBuilder.createServiceWithAuth(ApiService.class,tokenManager);

                    callPublicity = serviceAuth.listPublicity();

                    callPublicity.enqueue(new Callback<List<Publicity>>() {
                        @Override
                        public void onResponse(Call<List<Publicity>> call, Response<List<Publicity>> response) {

                            Log.d("JORGE-img",response.body().toString());

                            adapter = new ViewPagerAdapter(MainActivity.this, LoadImages(response.body()));
                            viewPager.setAdapter(adapter);

                            position = viewPager.getCurrentItem();
                            Log.d("JORGE-post",""+position);

                        }

                        @Override
                        public void onFailure(Call<List<Publicity>> call, Throwable t) {
                            Log.d("JORGE", "onFailure" + t.getMessage());
                        }
                    });



                } else {
                    handleErrors(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                Log.d("JORGE", "onFailure" + t.getMessage());
            }
        });*/

        adapter = new ViewPagerAdapter(MainActivity.this, LoadImages());

        viewPager.setAdapter(adapter);

        position = viewPager.getCurrentItem();

    }


    private void handleErrors(ResponseBody response) {
        ApiError apiError=Utils.convertErrors(response);

        Log.d("JORKE11","llego");

        //Log.d("JORKE11",response.toString());

       /* for(Map.Entry<String,List<String>> error:apiError.getErrors().entrySet()){

            if(error.getKey().equals("username")){
                ed_username.setError(error.getValue().get(0));
            }

            if(error.getKey().equals("password")){
                ed_password.setError(error.getValue().get(0));
            }
        }*/
    }


    private List<Publicity> LoadImages(){
        imageUrls = new ArrayList<>();
        //imageUrls = listPublicity;

        imageUrls.add(new Publicity(1,"https://spad-media.s3.us-east-2.amazonaws.com/publicity/Lanzamiento_Cheetos_Popcorn_Chocolate_Spot_15_segundos.mp4","video",67000));
        imageUrls.add(new Publicity(2,"https://spad-media.s3.us-east-2.amazonaws.com/publicity/Nueva_campaa_de_publicidad_de_Royal_Prestige_15_segundos.mp4","video",67000));
        imageUrls.add(new Publicity(3,"https://spad-media.s3.us-east-2.amazonaws.com/publicity/Publicidad_Pecho_Fro_30_segundos.mp4","video",67000));
        //imageUrls.add(new Publicity(2,"https://ia800201.us.archive.org/22/items/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4","video",67000));


        /*imageUrls.add(new Publicity(1,"https://spad-media.s3.us-east-2.amazonaws.com/media/1080.mp4","video",7000));
        https://spad-media.s3.us-east-2.amazonaws.com/publicity/7f0066456af0451d41ab46e5e1a5ba9c58fe4cf4.jpg
        imageUrls.add(new Publicity(2,"https://cdn.pixabay.com/photo/2017/12/21/12/26/glowworm-3031704_960_720.jpg","image",4000));
        imageUrls.add(new Publicity(3,"https://cdn.pixabay.com/photo/2016/11/11/23/34/cat-1817970_960_720.jpg","image",4000));*/


       /* imageUrls.add(new Publicity(4,"https://cdn.pixabay.com/photo/2017/11/07/00/07/fantasy-2925250_960_720.jpg","image",4000));
        imageUrls.add(new Publicity(5,"https://cdn.pixabay.com/photo/2017/10/10/15/28/butterfly-2837589_960_720.jpg","image",4000));*/

       /*if(imageUrls.get(0).getType_content()=="video"){
           Log.d("JORGE","Starting Video");
           isActive = false;
           viewPager.setCurrentItem(position);
       }else{
           isActive = true;
           handler.postDelayed(changeItem,listPublicity.get(0).getDuration());
       }*/

       /* isActive = false;
        viewPager.setCurrentItem(position);*/


        return imageUrls;
    }

    public void setCurrentItem(int item){
        position = item;
        isActive = true;
        Log.d("eJORGE","setCurrentItem "+item);
        Log.d("eJORGE","large "+imageUrls.size());
        if(item == imageUrls.size()){
            Log.d("eJORGE","restart");
            //position = 0;
            //adapter.notifyDataSetChanged();
        }
        viewPager.setCurrentItem(position);
        //handler.postDelayed(changeItem,imageUrls.get(position).getDuration());
    }

    private Runnable changeItem = new Runnable() {
        @Override
        public void run() {
            if(isActive) {
                if (position == imageUrls.size()) {
                    position = 0;
                    Log.d("JORGE", "RESET");
                    viewPager.setCurrentItem(position);
                    isActive = false;
                    handler.removeCallbacks(changeItem);
                    handler.removeCallbacksAndMessages(changeItem);
                }

                if (position > 0) {
                    Log.d("JORGE", "SECOND");
                    viewPager.setCurrentItem(position);
                }

                Log.d("JORGE", "position " + position);

                handler.postDelayed(changeItem, imageUrls.get(position).getDuration());

                position++;
            }else{
                Log.d("JORGE", "Not active ");
            }
        }
    };

}
