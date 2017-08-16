package ch.liip.timeforcoffee.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ch.liip.timeforcoffee.fragment.LineFragment;
import ch.liip.timeforcoffee.presenter.LinePresenter;
import ch.liip.timeforcoffee.fragment.dummy.DummyContent.DummyItem;
import ch.liip.timeforcoffee.R;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link ch.liip.timeforcoffee.fragment.LineFragment.Callbacks}.
 * TODO: Replace the implementation with code for your data type.
 */
public class LineRecyclerViewAdapter extends RecyclerView.Adapter<LineRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;
    private final LineFragment.Callbacks mListener;

    public LineRecyclerViewAdapter(List<DummyItem> items, LineFragment.Callbacks listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_line_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    // TODO decide if stops are interactive, and what it does.
                    mListener.onRefresh(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
