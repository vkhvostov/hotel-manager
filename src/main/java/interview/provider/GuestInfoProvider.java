package interview.provider;

import interview.model.GuestInfo;

import java.util.List;

/**
 * Guest info provider interface
 */
public interface GuestInfoProvider {

    List<GuestInfo> retrieveGuestInfo();
}
