package tech.fnplus.enrollme;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import tech.fnplus.enrollme.data.Event;
import tech.fnplus.enrollme.data.source.remote.APIServices;
import tech.fnplus.enrollme.data.source.remote.RetrofitClient;


public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Retrofit retrofit= RetrofitClient.INSTANCE.getClient("https://api.meetup.com/");
        APIServices apiServices =retrofit.create(APIServices.class);
        Call<List<Event>> call = apiServices.loadEvents();

        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(
                    Call<List<Event>> call,
                    Response<List<Event>> response
            )
            {
                List<Event> result=response.body();
                if(result!=null){
                    Log.i("Result",result.toString());
                }
                else{
                    Log.i("Result","Null");
                }
            }
            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                t.printStackTrace();
                Log.i("Result","Fail");
            }
        });

        return view;
    }

}
