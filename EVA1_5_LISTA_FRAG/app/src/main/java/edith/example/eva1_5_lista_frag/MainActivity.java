package edith.example.eva1_5_lista_frag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import edith.example.eva1_5_lista_frag.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {
    ItemFragment ifFragLista;
    DetalleFragment dfDetalle;
    FragmentManager fmManager;
    FragmentTransaction ftTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fmManager = getSupportFragmentManager();
        ifFragLista = new ItemFragment();
        ftTrans = fmManager.beginTransaction();
        ftTrans.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        ftTrans.replace(R.id.fmlFrame, ifFragLista);
        ftTrans.commit();
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        //Toast.makeText(this, item.content, Toast.LENGTH_LONG).show();
        dfDetalle = new DetalleFragment();
        dfDetalle.onMainToFrag(item);
        ftTrans = fmManager.beginTransaction();
        ftTrans.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        //ftTrans.replace(R.id.fmlFrame, ifFragLista);
        ftTrans.add(R.id.fmlFrame, dfDetalle);
        ftTrans.addToBackStack(null);
        ftTrans.commit();
    }
}
