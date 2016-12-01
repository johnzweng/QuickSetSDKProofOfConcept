package at.zweng.quicksetsdk.test.quicksetservice;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Service client class. Used to interact with the bound UEI QuickSet service.
 */
public class IControl {
    public static final String ACTION = "com.uei.control.IControl";

    /**
     * This is the package name of the QuickSet service on the LeEco LePro 3.
     * This might be different on other phones (like LG ot Honor).
     * <p>
     * Check this on the phone via "adb shell" with:
     * > pm list packages | grep quickset
     */
    public static final String QUICKSET_UEI_PACKAGE_NAME = "com.uei.quicksetsdk.letv";

    /**
     * This is the service class name within the Quickset Package.
     */
    public static final String QUICKSET_UEI_SERVICE_CLASS = "com.uei.control.Service";


    private IBinder _controlService = null;

    public IControl(IBinder service) {
        this._controlService = service;
    }

    /**
     * Get result code for last action from the QuickSet service.
     *
     * @return result code, 0 means OK
     * @throws RemoteException
     */
    public int getLastResultcode() throws RemoteException {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        try {
            _data.writeInterfaceToken(ACTION);
            this._controlService.transact(13, _data, _reply, 0);
            _reply.readException();
            int _result = _reply.readInt();
            return _result;
        } finally {
            _reply.recycle();
            _data.recycle();
        }
    }


    /**
     * Transmit custom infrared pattern. Seems to work like the official Android API
     * in ConsumerIrManager.transmit().<br>
     * <br>
     * See also:
     * https://developer.android.com/reference/android/hardware/ConsumerIrManager.html#transmit(int, int[])
     *
     * @param carrierFrequency
     * @param pattern
     * @return
     * @throws RemoteException
     */
    public int transmit(int carrierFrequency, int[] pattern) throws RemoteException {
        Parcel _data = Parcel.obtain();
        Parcel _reply = Parcel.obtain();
        int _result = 1;
        if (this._controlService != null) {
            try {
                _data.writeInterfaceToken(ACTION);
                _data.writeInt(carrierFrequency);
                _data.writeIntArray(pattern);
                this._controlService.transact(18, _data, _reply, 0);
                _reply.readException();
                _result = _reply.readInt();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                _reply.recycle();
                _data.recycle();
            }
        }
        return _result;
    }
}
