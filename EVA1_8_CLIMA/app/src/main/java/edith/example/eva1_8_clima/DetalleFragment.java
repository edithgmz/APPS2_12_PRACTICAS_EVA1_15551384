package edith.example.eva1_8_clima;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleFragment extends Fragment {
    ImageView imgVwClima2;
    TextView txtVwTemp2, txtVwClima2, txtVwDesc2, txtVwCd2;
    Bundle bMsg;

    public DetalleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vs = inflater.inflate(R.layout.fragment_detalle, container, false);
        //
        imgVwClima2 = vs.findViewById(R.id.imgVwClima2);
        txtVwTemp2 = vs.findViewById(R.id.txtVwTemp2);
        txtVwClima2 = vs.findViewById(R.id.txtVwClima2);
        txtVwDesc2 = vs.findViewById(R.id.txtVwDesc2);
        txtVwCd2 = vs.findViewById(R.id.txtVwCd2);
        //
        imgVwClima2.setImageResource(bMsg.getInt("IMG"));
        txtVwTemp2.setText(bMsg.getDouble("TEMP") + "°F");
        txtVwClima2.setText(bMsg.getString("CLIMA"));
        txtVwDesc2.setText(bMsg.getString("DESC"));
        txtVwCd2.setText(bMsg.getString("CD"));
        // Inflate the layout for this fragment
        return vs;
    }

    public void onMainToFrag(Bundle bundle) {
        bMsg = bundle;
    }
}
