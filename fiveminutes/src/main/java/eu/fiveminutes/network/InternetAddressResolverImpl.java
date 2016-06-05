package eu.fiveminutes.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public final class InternetAddressResolverImpl implements InternetAddressResolver {

    private static final String EMPTY = "";

    private final String url;

    public InternetAddressResolverImpl(final String url) {
        this.url = url;
    }

    @Override
    public boolean canResolveAddress() {
        try {
            final InetAddress address = InetAddress.getByName(url);

            return address != null && !EMPTY.equals(address.getHostAddress());

        } catch (final UnknownHostException e) {
            throw new IllegalArgumentException("Unknown host: " + url);
        }
    }
}
