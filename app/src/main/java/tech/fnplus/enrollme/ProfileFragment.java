package tech.fnplus.enrollme;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
public class ProfileFragment extends Fragment {
    FirebaseAuth mAuth;
    ImageView qrcode;

    public ProfileFragment() {
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        qrcode = view.findViewById(R.id.barcode);
        mAuth= FirebaseAuth.getInstance();
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try{
        BitMatrix bitMatrix = multiFormatWriter.encode(mAuth.getCurrentUser().getUid(), BarcodeFormat.QR_CODE, 200, 200);
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
        qrcode.setImageBitmap(bitmap);}
        catch (WriterException e) {
            e.printStackTrace();
        }
        return view;
    }

}
