package edith.example.eva1_5_lista_frag;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edith.example.eva1_5_lista_frag.dummy.DummyContent;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleFragment extends Fragment {
    TextView txtVwDetalle;
    String sMsg;

    public DetalleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vs = inflater.inflate(R.layout.fragment_detalle, container, false);
        txtVwDetalle = vs.findViewById(R.id.txtVwDetalle);
        txtVwDetalle.setText(sMsg);
        return vs;
    }

    public void onMainToFrag(DummyContent.DummyItem di){
        sMsg = di.content;
    }
}
