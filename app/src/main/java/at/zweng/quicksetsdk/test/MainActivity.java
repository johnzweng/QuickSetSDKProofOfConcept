package at.zweng.quicksetsdk.test;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import at.zweng.quicksetsdk.test.quicksetservice.IControl;
import eu.chainfire.libsuperuser.Shell;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Test QuickSet SDK";

    private static final int TEST_FREQUENCY = 38028;
    private static final int[] TEST_PATTERN = {169, 168, 21, 63, 21, 63, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 63, 21, 63, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 64, 21, 21, 21, 63, 21, 63, 21, 63, 21, 63, 21, 63, 21, 63, 21, 1794, 169, 168, 21, 21, 21, 21, 63, 21, 63, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 63, 21, 63, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 64, 21, 21, 21, 63, 21, 63, 21, 63, 21, 63, 21, 63, 21, 63, 21, 1794, 169, 168, 21, 21, 21, 21, 63, 21, 63, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 63, 21, 63, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 64, 21, 21, 21, 63, 21, 63, 21, 63, 21, 63, 21, 63, 21, 63, 21, 1794, 169, 168, 21, 21, 21, 21, 63, 21, 63, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 63, 21, 63, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 64, 21, 21, 21, 63, 21, 63, 21, 63, 21, 63, 21, 63, 21, 63, 21, 1794, 169, 168, 21, 21, 21, 21, 63, 21, 63, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 63, 21, 63, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 64, 21, 21, 21, 63, 21, 63, 21, 63, 21, 63, 21, 63, 21, 63, 21, 1794, 169, 168, 21, 21, 21, 21, 63, 21, 63, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 63, 21, 63, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 64, 21, 21, 21, 63, 21, 63, 21, 63, 21, 63, 21, 63, 21, 63, 21, 1794, 169, 168, 21, 21, 21, 21, 63, 21, 63, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 63, 21, 63, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 64, 21, 21, 21, 63, 21, 63, 21, 63, 21, 63, 21, 63, 21, 63, 21, 1794, 169, 168, 21, 21, 21, 21, 63, 21, 63, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 63, 21, 63, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 64, 21, 21, 21, 63, 21, 63, 21, 63, 21, 63, 21, 63, 21, 63, 21, 1794, 169, 168, 21, 21, 21, 21, 63, 21, 63, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 63, 21, 63, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 64, 21, 21, 21, 63, 21, 63, 21, 63, 21, 63, 21, 63, 21, 63, 21, 1794, 169, 168, 21, 21, 21, 21, 63, 21, 63, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 63, 21, 63, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 64, 21, 21, 21, 63, 21, 63, 21, 63, 21, 63, 21, 63, 21, 63, 21, 1794, 169, 168, 21, 21, 21, 21, 63, 21, 63, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 63, 21, 63, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 64, 21, 21, 21, 63, 21, 63, 21, 63, 21, 63, 21, 63, 21, 63, 21, 1794, 169, 168, 21, 21, 21, 3694};
    private static final int[] SAMSUNG_POWER_TOGGLE_COUNT = {169, 168, 21, 63, 21, 63, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 63, 21, 63, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 63, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 64, 21, 21, 21, 63, 21, 63, 21, 63, 21, 63, 21, 63, 21, 63, 21, 1794, 169, 168, 21, 21, 21, 3694};
    // is the service bound
    boolean mBound = false;
    // buttons
    private TextView textView;
    private Button btnConnectService;
    private Button btnSendIrPattern;
    // client API for the service:
    private IControl mControl;


    /**
     * Service Connection used to control the bound QuickSet SDK Service
     */
    private ServiceConnection mControlServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBound = true;
            Log.i(TAG, "QuickSet SDK Service SUCCESSFULLY CONNECTED! Yeahh! :-)");
            MainActivity.this.mControl = new IControl(service);
            MainActivity.this.btnSendIrPattern.setEnabled(true);
            setTextView("Connected to QuickSet SDK service! :-)");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
            Log.i(TAG, "QuickSet SDK Service DISCONNECTED!");
            MainActivity.this.mControl = null;
            MainActivity.this.btnSendIrPattern.setEnabled(false);
            setTextView("Disconnected from QuickSet SDK Service.");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // start background task
        (new BackgroundIrEnableTask()).execute();
        bindUiElements();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        if (mBound) {
            unbindService(mControlServiceConnection);
            mBound = false;
        }
    }

    /**
     * Try to send Infrared pattern, catch and log exceptions.
     *
     * @param carrierFrequency
     * @param pattern
     */
    private void testIrTransmit(int carrierFrequency, int[] pattern) {
        if (mControl == null) {
            Log.w(TAG, "QuickSet Service seems not to be bound. Doing nothing.");
            return;
        }
        try {
            Log.i(TAG, "Calling transmit() now...");
            mControl.transmit(carrierFrequency, pattern);
            int resultcode = mControl.getLastResultcode();
            Log.i(TAG, "resultCode: " + resultcode);
        } catch (RemoteException e) {
            Log.e(TAG, "Catched Remote Exception while trying to send command to QuickSet Service.", e);
        }
    }


    /**
     * Bind buttons in UI..
     */
    private void bindUiElements() {
        textView = (TextView) findViewById(R.id.textView);

        // Connect button:
        btnConnectService = (Button) findViewById(R.id.btnConnectService);
        btnConnectService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Click connect service button. Trying to bind service....");
                Intent controlIntent = new Intent(IControl.ACTION);
                controlIntent.setClassName(IControl.QUICKSET_UEI_PACKAGE_NAME, IControl.QUICKSET_UEI_SERVICE_CLASS);
                boolean bindResult = bindService(controlIntent, MainActivity.this.mControlServiceConnection, Context.BIND_AUTO_CREATE);
                Log.i(TAG, "Control service bind result = " + bindResult);
            }
        });

        // Send button:
        btnSendIrPattern = (Button) findViewById(R.id.btnSendPattern);
        btnSendIrPattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Click transmit button. Sending IR data....");
                testIrTransmit(TEST_FREQUENCY, TEST_PATTERN);
            }
        });

    }

    /**
     * Set text of textview..
     *
     * @param msg
     */
    private void setTextView(String msg) {
        textView.setText(msg);
    }


    /**
     * Async task, running in the background, to enable IR emitter:
     */
    private class BackgroundIrEnableTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            // this method is executed in a background thread
            // no problem calling su here
            boolean result = enableIrEmitter();
            Log.i(TAG, "Enabling IR emitter succesful: " + result);
            return null;
        }

        /**
         * Enable IR emitter by writing  '1' > /sys/remote/enable
         * First we check if the file is writable for us and if not we try as root.
         *
         * @return
         */
        private boolean enableIrEmitter() {
            try {
                File enableFile = new File("/sys/remote/enable");
                Log.i(TAG, "enableFile getCanonicalPath(): " + enableFile.getCanonicalPath());
                Log.i(TAG, "enableFile exists(): " + enableFile.exists());
                if (!enableFile.exists()) {
                    Log.w(TAG, "File '/sys/remote/enable' doesn't exist.");
                    return false;
                }
                Log.i(TAG, "enableFile isFile(): " + enableFile.isFile());
                Log.i(TAG, "enableFile canRead(): " + enableFile.canRead());
                Log.i(TAG, "enableFile canWrite(): " + enableFile.canWrite());
                if (enableFile.canWrite()) {
                    return tryToEnableNormally(enableFile);
                } else {
                    Log.w(TAG, "Sorry, don't have write permission for: file '/sys/remote/enable'. We will try to use root permission:");
                    return tryToEnableAsRoot();
                }
            } catch (Exception ioe) {
                Log.e(TAG, "Exception when opening sys file:", ioe);
                return false;
            }
        }

        /**
         * Try to enable as normal user.
         *
         * @param enableFile enable sys file
         */
        private boolean tryToEnableNormally(File enableFile) {
            Log.i(TAG, "AS NORMAL USER: Writing '1' to '/sys/remote/enable' to enable IR emitter.");
            try {
                FileWriter fileWriter = new FileWriter(enableFile);
                fileWriter.write("1");
                fileWriter.flush();
                fileWriter.close();
                Log.i(TAG, "Done. IR Emitter should be enabled.");
                return true;
            } catch (IOException e1) {
                Log.e(TAG, "Exception when opening sys file:", e1);
                return false;
            }
        }

        /**
         * Try to use SuperSU to enable IR blaster
         */
        private boolean tryToEnableAsRoot() {
            boolean suAvailable = Shell.SU.available();
            if (suAvailable) {
                Log.i(TAG, "AS ROOT: Writing '1' to '/sys/remote/enable' to enable IR emitter.");
                Shell.SU.run(new String[]{
                        "echo 1 > /sys/remote/enable"
                });
                return true;
            } else {
                Log.i(TAG, "Sorry, no root available.");
                return false;
            }
        }

    }


}
