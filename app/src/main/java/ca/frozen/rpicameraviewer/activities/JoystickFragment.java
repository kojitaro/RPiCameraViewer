package ca.frozen.rpicameraviewer.activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ca.frozen.library.classes.Log;
import ca.frozen.rpicameraviewer.R;
import ca.frozen.rpicameraviewer.classes.Camera;
import io.github.controlwear.virtual.joystick.android.JoystickView;

public class JoystickFragment extends Fragment {
    public final static String CAMERA = "camera";

    private Camera camera;
    private TextView mTextViewStrengthLeft;
    private TextView mTextViewStrengthRight;


    public static JoystickFragment newInstance(Camera camera)
    {
        JoystickFragment fragment = new JoystickFragment();

        Bundle args = new Bundle();
        args.putParcelable(CAMERA, camera);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        camera = getArguments().getParcelable(CAMERA);
        Log.info("camera: " + camera.toString());
    }

    private float verticalValue(int angle, int strength)
    {
        return (float)(strength * Math.sin(Math.PI*2*angle/360.0)/100.0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_joystick, container, false);

        mTextViewStrengthLeft = (TextView) view.findViewById(R.id.textView_strength_left);

        JoystickView joystickLeft = (JoystickView) view.findViewById(R.id.joystickView_left);
        joystickLeft.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
                mTextViewStrengthLeft.setText(String.format("%1$.3f", verticalValue(angle, strength)));
            }
        });


        mTextViewStrengthRight = (TextView) view.findViewById(R.id.textView_strength_right);

        JoystickView joystickRight = (JoystickView) view.findViewById(R.id.joystickView_right);
        joystickRight.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
                mTextViewStrengthRight.setText(String.format("%1$.3f", verticalValue(angle, strength)));
            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
