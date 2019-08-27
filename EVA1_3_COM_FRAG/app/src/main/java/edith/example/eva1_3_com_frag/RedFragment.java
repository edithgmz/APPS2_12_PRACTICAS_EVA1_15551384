package edith.example.eva1_3_com_frag;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class RedFragment extends Fragment implements FragmentCom {
    TextView txtMensaje;

    public RedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.fragment_red, container, false);
        txtMensaje = ll.findViewById(R.id.txtVwMensaje);
        // Inflate the layout for this fragment
        return ll;
    }

    @Override
    public void onFromMainToFrag(String msg) {
        txtMensaje.setText(msg);
    }
}
