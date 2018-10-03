package interview.provider;

import interview.model.GuestInfo;

import java.util.List;

/**
 * Created on 03.10.18
 * TODO: Add comment
 */
public interface GuestInfoProvider {

    List<GuestInfo> retrieveGuestInfo();
}
