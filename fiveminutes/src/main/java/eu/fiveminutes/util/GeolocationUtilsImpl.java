package eu.fiveminutes.util;

import android.content.Context;
import android.telephony.TelephonyManager;

public final class GeolocationUtilsImpl implements GeolocationUtils {

    private final TelephonyManager telephonyManager;

    public GeolocationUtilsImpl(Context context) {
        telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    }

    @Override
    public boolean checkIsSimFromSpecifiedCountry(String simISOcode) {
        final String simCountryISO = telephonyManager.getSimCountryIso();

        if (simCountryISO != null) {
            return simCountryISO.equals(simISOcode);
        } else {
            return false;
        }
    }
}
