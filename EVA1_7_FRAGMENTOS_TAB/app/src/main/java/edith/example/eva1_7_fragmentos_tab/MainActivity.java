package edith.example.eva1_7_fragmentos_tab;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        Adapter adapterFragment = new Adapter(getSupportFragmentManager());
        adapterFragment.addFragment(new TileFragment(), "Mosaicos");
        adapterFragment.addFragment(new CardFragment(), "Tarjeta");
        adapterFragment.addFragment(new ListFragment(), "Lista");
        viewPager.setAdapter(adapterFragment);
    }

    static class  Adapter extends FragmentPagerAdapter{
        private final List<Fragment> listaFragmentos = new ArrayList<>();
        private final List<String> listaTitulos = new ArrayList<>();

        public Adapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return listaFragmentos.get(position);
        }

        @Override
        public int getCount() {
            return listaFragmentos.size();
        }

        public void addFragment(Fragment fragment, String titulo){
            listaFragmentos.add(fragment);
            listaTitulos.add(titulo);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return listaTitulos.get(position);
        }
    }
}
