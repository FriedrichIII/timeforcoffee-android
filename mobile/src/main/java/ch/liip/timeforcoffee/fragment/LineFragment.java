package ch.liip.timeforcoffee.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ch.liip.timeforcoffee.R;
import ch.liip.timeforcoffee.adapter.LineRecyclerViewAdapter;
import ch.liip.timeforcoffee.fragment.dummy.DummyContent;
import ch.liip.timeforcoffee.fragment.dummy.DummyContent.DummyItem;
import ch.liip.timeforcoffee.presenter.LinePresenter;
import ch.liip.timeforcoffee.presenter.StationListPresenter;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class LineFragment extends Fragment {

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface Callbacks {
//        public void onStationSelected();

        public void onRefresh(DummyItem dummyItem);
    }

    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onRefresh(DummyItem dummyItem) {}
    };

    private Callbacks mCallbacks = sDummyCallbacks;

    private LinePresenter mPresenter;

//    private OnListFragmentInteractionListener mListener;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LineFragment() {
        // Required empty public constructor
    }

//    // TODO: Customize parameter initialization
//    @SuppressWarnings("unused")
//    public static LineFragment newInstance(int columnCount) {
//        LineFragment fragment = new LineFragment();
//        Bundle args = new Bundle();
//        args.putInt(ARG_COLUMN_COUNT, columnCount);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new LinePresenter(this);
        //FIXME see if/when the recycler view has to be stored.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_line_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new LineRecyclerViewAdapter(DummyContent.ITEMS, mCallbacks));
        }

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof Callbacks)) {
            throw new IllegalStateException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
        mCallbacks = (Callbacks) context;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResumeView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = sDummyCallbacks;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
