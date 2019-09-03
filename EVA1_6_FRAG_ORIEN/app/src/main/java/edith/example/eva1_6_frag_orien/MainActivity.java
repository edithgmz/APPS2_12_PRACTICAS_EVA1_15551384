package edith.example.eva1_6_frag_orien;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    DetailFragment df;
    MainFragment mf;
    FragmentManager fmManager;
    FragmentTransaction ftTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            fmManager = getSupportFragmentManager();
            mf = new MainFragment();
            df = new DetailFragment();
            ftTrans = fmManager.beginTransaction();
            ftTrans.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
            ftTrans.replace(R.id.fmlPpal, mf);
            ftTrans.add(R.id.fmlDet, df);
            ftTrans.addToBackStack(null);
            ftTrans.commit();
        } else {
            fmManager = getSupportFragmentManager();
            mf = new MainFragment();
            ftTrans = fmManager.beginTransaction();
            ftTrans.replace(R.id.fmlPpal, mf);
            ftTrans.commit();
        }
    }
}
