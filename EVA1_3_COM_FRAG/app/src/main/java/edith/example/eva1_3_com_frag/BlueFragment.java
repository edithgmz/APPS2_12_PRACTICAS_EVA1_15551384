package edith.example.eva1_3_com_frag;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlueFragment extends Fragment implements FragmentCom {
    MainActivity ma;

    public BlueFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ma = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.fragment_blue, container, false);
        Button btnSend = ll.findViewById(R.id.btnMensaje);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ma.onFromFragToMain("BLUE", "Holi");
            }
        });
        // Inflate the layout for this fragment
        return ll;
    }

    @Override
    public void onFromMainToFrag(String msg) {

    }
}
