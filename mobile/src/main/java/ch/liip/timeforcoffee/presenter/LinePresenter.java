package ch.liip.timeforcoffee.presenter;

import ch.liip.timeforcoffee.TimeForCoffeeApplication;
import ch.liip.timeforcoffee.api.Station;
import ch.liip.timeforcoffee.api.events.OpenDataStationboardFetchedEvent;
import ch.liip.timeforcoffee.common.presenter.Presenter;
import ch.liip.timeforcoffee.fragment.LineFragment;
import ch.liip.timeforcoffee.opendata.Checkpoint;
import ch.liip.timeforcoffee.opendata.Journey;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by boubi on 09.05.2017.
 */
public class LinePresenter implements Presenter {

    private LineFragment mFragment;

    @Inject
    EventBus mEventBus;

    public LinePresenter(LineFragment lineFragment) {
        mFragment = lineFragment;

        ((TimeForCoffeeApplication) mFragment.getActivity().getApplication()).inject(this);
        mEventBus.register(this);

    }

    @Override
    public void onResumeView() {

    }

    @Override
    public void onRefreshView() {

    }

    @Override
    public void onPauseView() {

    }

    @Override
    public void onDestroy() {
        mEventBus.unregister(this);
        mFragment = null;
    }

    @Subscribe
    public void onStationBoardFetched(OpenDataStationboardFetchedEvent event) {
        updateLine(event.getStationboards());
    }

    private void updateLine(List<Journey> journey) {
        // TODO Check result.
        List<Checkpoint> list = journey.iterator().next().getPassList();


    }
}
