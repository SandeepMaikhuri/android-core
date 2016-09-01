package eu.fiveminutes.util;

import android.telephony.TelephonyManager;

public final class GeolocationUtilsImpl implements GeolocationUtils {

    private final TelephonyManager telephonyManager;

    public GeolocationUtilsImpl(TelephonyManager telephonyManager) {
        this.telephonyManager = telephonyManager;
    }

    @Override
    public boolean checkIsSimFromSpecifiedCountry(final String simISOcode) {
        final String simCountryISO = telephonyManager.getSimCountryIso();
        return simCountryISO != null && simCountryISO.equals(simISOcode);
    }
}
