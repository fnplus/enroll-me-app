package tech.fnplus.enrollme;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager = getSupportFragmentManager();
    HomeFragment homeFragment = new HomeFragment();
    DashboardFragment dashboardFragment = new DashboardFragment();
    DiscoverFragment discoverFragment = new DiscoverFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentManager.beginTransaction()
                            .replace(R.id.containerMainFragment, homeFragment)
                            .addToBackStack(null)
                            .commit();
                    return true;
                case R.id.navigation_discover:
                    fragmentManager.beginTransaction()
                            .replace(R.id.containerMainFragment, discoverFragment)
                            .addToBackStack(null)
                            .commit();
                    return true;
                case R.id.navigation_dashboard:

                    fragmentManager.beginTransaction()
                            .replace(R.id.containerMainFragment, dashboardFragment)
                            .addToBackStack(null)
                            .commit();
                    return true;
                case R.id.navigation_profile:

                    fragmentManager.beginTransaction()
                            .replace(R.id.containerMainFragment, profileFragment)
                            .addToBackStack(null)
                            .commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentManager.beginTransaction()
                .replace(R.id.containerMainFragment, homeFragment)
                .addToBackStack(null)
                .commit();
    }

}
