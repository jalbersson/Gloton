package onsite.gloton.com.co.gloton.location;

import java.util.List;

/**
 * Created by admin on 28/07/17.
 */

public interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);
}
