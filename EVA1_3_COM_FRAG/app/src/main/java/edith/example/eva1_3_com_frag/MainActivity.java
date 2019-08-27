package edith.example.eva1_3_com_frag;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {
    RedFragment rfRojo;
    BlueFragment bfAzul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment.getClass() == RedFragment.class) {
            rfRojo = (RedFragment) fragment;
        } else if (fragment.getClass() == BlueFragment.class) {
            bfAzul = (BlueFragment) fragment;
        }
    }

    public void onFromFragToMain(String sender, String msg) {
        if (sender.equals("RED")) {

        } else if (sender.equals("BLUE")) { //Fragmento Azul
            rfRojo.onFromMainToFrag(msg);
        }
    }
}
