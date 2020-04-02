package ai.aiprog.template.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Author       : Arvindo Mondal
 * Created on   : 09-05-2019
 * Email        : arvindo@aiprog.ai
 * Company      : AIPROG
 * Designation  : Programmer
 * About        : I am a human can only think, I can't be a person like machine which have lots of memory and knowledge.
 * Quote        : No one can measure limit of stupidity but stupid things bring revolutions
 * Strength     : Never give up
 * Motto        : To be known as great Mathematician
 * Skills       : Algorithms and logic
 * Website      : www.aiprog.ai
 */
public abstract class BaseAdapter<B extends ViewDataBinding, D> extends
        RecyclerView.Adapter<BaseAdapter.ViewHolder> implements Filterable {

    protected String filterText = "";
    protected ArrayList<D> list;
    protected ArrayList<D> mainList;
    private Listener listener;
    private FilterClass filter = null;

    /**
     * @param adapterList list args require to bind adapter up to the size of array
     */
    public BaseAdapter(ArrayList<D> adapterList) {
        this.list = adapterList;
        this.mainList = adapterList;
    }

    public void addItems(List<D> model) {
        list.addAll(model);
        notifyDataSetChanged();
    }

    public void clearItems() {
        list.clear();
        mainList.clear();
        notifyDataSetChanged();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        B binding = DataBindingUtil.inflate(inflater,
                getLayout(), parent, false);

        showHideView();

        doJobInOnCreate(parent, viewType, binding);

        // set the view's size, margins, paddings and layout parameters
        return getViewHolder(binding);
    }

    protected <VB extends ViewDataBinding> ViewDataBinding createBinding(ViewGroup parent, int
            viewType, @LayoutRes int layoutId) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        VB binding = DataBindingUtil.inflate(inflater, layoutId, parent, false);
//        return DataBindingUtil.<VB>inflate(inflater,
//                layoutId, parent, false);
        return binding;
    }

    /**
     * @param viewGroup view group
     * @param viewType  view type
     * @param binding   adapter binding
     */
    protected void doJobInOnCreate(ViewGroup viewGroup, int viewType, B binding) {
    }

    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    public void onBindViewHolder(@NonNull BaseAdapter.ViewHolder holder, int position) {
        holder.bind(list.get(position), position);
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        else
            return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        int itemType = getItemViewTypeAdapter(position);
        if (itemType != 0) {
            return itemType;
        }
        return position;
    }

    /**
     * @param position current index of ArrayList
     * @return return 0 if single layout xml else override this method for multiple xml or elements
     */
    protected abstract int getItemViewTypeAdapter(int position);

    /**
     * This will hide the view on first load
     * This is use to make the hidden view on first load
     * left blank if noting is hidden
     */
    protected void showHideView() {
    }

    /**
     * @return R.layout.layout_file
     */
    @LayoutRes
    protected abstract int getLayout();

    /**
     * Initialised View Holder
     *
     * @param binding DataBinding
     * @return new ViewHolder<B, D>(binding);
     */
    public abstract ViewHolder getViewHolder(B binding);

    /**
     * @return new FilterClass();
     */
    protected abstract FilterClass initialisedFilterClass();

    //-----------------filter/search in adapter---------------------

    /**
     * <p>Returns a filter that can be used to constrain data with a filtering
     * pattern.</p>
     * <p>
     * <p>This method is usually implemented by {@link Adapter}
     * classes.</p>
     *
     * @return a filter used to constrain data
     */
    @Override
    public Filter getFilter() {
        if (filter == null)
            filter = initialisedFilterClass();
        return filter;
    }

    public interface Listener {
        void onRetryClick();
    }

    /**
     * class use for the filter of adapter view
     */
    public abstract class FilterClass extends Filter {
        //        private AdapterFilterCalls adapterFilterCalls;
        private ArrayList<D> filteredArrayList;

        protected FilterClass() {
//            this.adapterFilterCalls = (AdapterFilterCalls) getContext();
        }

        /**
         * @return Context, to initialise and use filter class pass activity or application context
         */
        public abstract Context getContext();

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            constraint = constraint.toString().toLowerCase();

            if (constraint.length() <= 0) {
                filteredArrayList = mainList;
            } else {
                filteredArrayList = new ArrayList<>();

                filteredArrayList = filterData(constraint, mainList, filteredArrayList);

                filterResults.count = filteredArrayList.size();
                filterResults.values = filteredArrayList;
            }

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            //noinspection unchecked
            list = (ArrayList<D>) results.values;
            filterText = constraint + "";

            if (constraint == null || constraint.length() <= 0) {
                list = mainList;
                filterText = "";
            }

//            adapterFilterCalls.callBackToActivity(String.valueOf(filteredArrayList.size()));

            notifyDataSetChanged();
        }

        /**
         * @param filteredArrayList JobsResponse
         *                          for (L data : list) {
         *                          if (data.getRefNo().toLowerCase().contains(constraint) ||
         *                          data.getZone().toLowerCase().contains(constraint))
         *                          filteredArrayList.add(data);
         *                          }
         * @param list              only one time, use in for loop
         * @param filteredArrayList This list will return with added data
         */
        public abstract ArrayList<D> filterData(CharSequence constraint, ArrayList<D> list,
                                                ArrayList<D> filteredArrayList);
    }

    //------------------view Holder---------------------------------

    public abstract class ViewHolder<B extends ViewDataBinding, D> extends RecyclerView.ViewHolder {
        protected int position;
        protected B binding;

        /**
         * @param binding layout reference
         */
        protected ViewHolder(B binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        protected ViewHolder(View view) {
            super(view);
        }

        void bind(D data, int position) {
            this.position = position;
            bindData(data);
            setClickListeners(data, position);
            doSomeWorkHere(binding, data, position);
        }

        /**
         * If there is anything to do then do here otherwise leave it blank
         *
         * @param binding  layout reference for single view
         * @param data     for single view
         * @param position position of ArrayList
         */
        protected abstract void doSomeWorkHere(B binding, D data, int position);

        /**
         * @param data binding.setData(new AdapterViewModel(data));
         */
        protected abstract void bindData(D data);

        protected void setClickListeners(D data, int position) {
            ViewHolderClickListener viewHolderClickListener = viewHolderReference(binding, data,
                    position);
            setClickListeners(viewHolderClickListener, binding, data);
        }

        /**
         * Method to set click listeners on view holder or groups
         *
         * @param thisContext set it on method : binding.layout.setOnClickListener(thisContext);
         * @param binding     DataBinding
         * @param data        data
         */
        public abstract void setClickListeners(ViewHolderClickListener thisContext,
                                               B binding, D data);


        /**
         * Initialised holder by new operator
         *
         * @param binding  DataBinding
         * @param data     dataList
         * @param position of adapter
         * @return new ViewHolderClickListener<B, D>(binding, data, position)
         */
        protected abstract ViewHolderClickListener viewHolderReference(
                B binding, D data, int position);
    }

    //----------------------------ViewHolder Click Listener---------

    public abstract class ViewHolderClickListener<B extends ViewDataBinding, D> implements View.OnClickListener {
        protected final int position;
        private final D data;
        protected B binding;

        /**
         * @param position of a adapter in current view
         */
        public ViewHolderClickListener(B binding, D data, int position) {
            this.position = position;
            this.binding = binding;
            this.data = data;
        }

        /**
         * Called when a view has been clicked.
         *
         * @param view The view that was clicked.
         *             switch (view.getId()){
         *             case R.id.id:
         *             // itemView.getContext().startActivity();
         *             break;
         *             }
         */
        @Override
        public abstract void onClick(View view);
    }

    //--------------------------------------------------------------
}
