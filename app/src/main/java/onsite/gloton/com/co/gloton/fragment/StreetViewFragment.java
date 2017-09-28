package onsite.gloton.com.co.gloton.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;

import onsite.gloton.com.co.gloton.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class StreetViewFragment extends Fragment {

    private static View view;

    // TODO: Rename and change types of parameters
    private double latitud;
    private double longitud;

    public StreetViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {

            if (getArguments() != null) {
                latitud = getArguments().getDouble("latitud");
                longitud = getArguments().getDouble("longitud");
                Log.d("Longitud",String.valueOf(longitud));
                Log.d("latitud",String.valueOf(latitud));
            }
            view = inflater.inflate(R.layout.fragment_street_view, container, false);



            StreetViewPanoramaFragment streetViewPanoramaFragment =
                    (StreetViewPanoramaFragment) getActivity().getFragmentManager()
                            .findFragmentById(R.id.streetviewpanorama);
            streetViewPanoramaFragment.getStreetViewPanoramaAsync(new OnStreetViewPanoramaReadyCallback() {
                @Override
                public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
                    streetViewPanorama.setPosition(new LatLng(latitud, longitud));
                }
            });
        } catch (InflateException e) {
        /* map is already there, just return view as it is */
        }
        return view;
    }


}
