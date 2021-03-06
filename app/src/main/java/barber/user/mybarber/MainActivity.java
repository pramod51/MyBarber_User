package barber.user.mybarber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import barber.user.mybarber.Fragments.HistoryFragment;
import barber.user.mybarber.Fragments.ProfileFragment;
import barber.user.mybarber.Fragments.ShopFragment;
import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity extends AppCompatActivity {
    SmoothBottomBar bottomBar;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bannerAd();
        bottomBar=findViewById(R.id.bottomBar);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ShopFragment()).commit();
        bottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {
                Fragment fragment=null;
                switch (i){
                    case 0:
                        fragment=new ShopFragment();
                        break;
                    case 1:
                        fragment=new HistoryFragment();
                        break;
                    case 2:
                        fragment=new ProfileFragment();
                        break;
                }
                if (fragment!=null)
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();

                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        finishAffinity();
    }
    private void bannerAd() {
        mAdView=findViewById(R.id.adView);
        MobileAds.initialize(MainActivity.this);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }


}