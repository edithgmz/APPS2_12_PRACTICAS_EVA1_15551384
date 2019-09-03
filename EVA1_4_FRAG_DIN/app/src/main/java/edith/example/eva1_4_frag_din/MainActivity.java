package edith.example.eva1_4_frag_din;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    FragmentManager fmManager;
    FragmentTransaction ftTrans;
    GreenFragment gfVerde;
    BlueFragment bfAzul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fmManager = getSupportFragmentManager();
    }

    public void onClickGreen(View view) {
        ftTrans = fmManager.beginTransaction();
        gfVerde = new GreenFragment();
        //ftTrans.replace(R.id.frmlyFragmentos, gfVerde);
        //ftTrans.commit();
        ftTrans.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        ftTrans.addToBackStack(null); //Se le puede poner nombre para especificar un stack particular
        ftTrans.add(R.id.frmlyFragmentos, gfVerde, "BLANK_FRAGMENT");
        ftTrans.commit();
    }

    public void onClickBlue(View view) {
        ftTrans = fmManager.beginTransaction();
        bfAzul = new BlueFragment();
        //ftTrans.replace(R.id.frmlyFragmentos, bfAzul);
        //ftTrans.commit();
        ftTrans.setCustomAnimations(R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right);
        ftTrans.addToBackStack(null); //Se le puede poner nombre para especificar un stack particular
        ftTrans.add(R.id.frmlyFragmentos, bfAzul, "BLANK_FRAGMENT");
        ftTrans.commit();
    }
}
