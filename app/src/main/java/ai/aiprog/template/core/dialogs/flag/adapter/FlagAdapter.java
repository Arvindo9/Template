package ai.aiprog.template.core.dialogs.flag.adapter;

import android.content.Context;
import android.view.View;

import ai.aiprog.template.R;
import ai.aiprog.template.base.BaseAdapter;
import ai.aiprog.template.data.model.db.flag.Flag;
import ai.aiprog.template.databinding.FlagAdapterBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Author       : Arvindo Mondal
 * Created on   : 22-05-2019
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
public class FlagAdapter extends BaseAdapter<FlagAdapterBinding, Flag> {

    private FlagAdapterListener listener;

    public interface FlagAdapterListener {
        void onRetryClick();

        void getSelectedFlag(String code, int id, String flagUrl, Flag data);
    }

    /**
     * @param list list args require to bind adapter up to the size of array
     */
    public FlagAdapter(ArrayList<Flag> list) {
        super(list);
    }

    public void setListener(FlagAdapterListener listener) {
        this.listener = listener;
    }

    public void addItems(List<Flag> model) {
        list.addAll(model);
        notifyDataSetChanged();
    }

    public void clearItems() {
        list.clear();
        mainList.clear();
    }

    /**
     * @param position current index of ArrayList
     * @return return 0 if single layout xml else override this method for multiple xml or elements
     */
    @Override
    protected int getItemViewTypeAdapter(int position) {
        return 0;
    }

    /**
     * @return R.layout.layout_file
     */
    @Override
    protected int getLayout() {
        return R.layout.flag_adapter;
    }

    /**
     * Initialised View Holder
     *
     * @param binding DataBinding
     * @return new ViewHolder(<B, D>binding);
     */
    @Override
    public ViewHolder getViewHolder(FlagAdapterBinding binding) {
        return new ViewHolder<FlagAdapterBinding, Flag>(binding) {
            /**
             * If there is anything to do then do here otherwise leave it blank
             *
             * @param binding  layout reference for single view
             * @param data     for single view
             * @param position position of ArrayList
             */
            @Override
            protected void doSomeWorkHere(FlagAdapterBinding binding, Flag data, int position) {

            }

            /**
             * @param data binding.setData(new AdapterViewModel(data));
             */
            @Override
            protected void bindData(Flag data) {
//                binding.setData(data);
                binding.setData(new FlagAdapterViewModel(data));
            }

            /**
             * Method to set click listeners on view holder or groups
             *
             * @param thisContext set it on method : binding.layout.setOnClickListener(thisContext);
             * @param binding     DataBinding
             * @param data        data
             */
            @Override
            public void setClickListeners(ViewHolderClickListener thisContext, FlagAdapterBinding binding, Flag data) {
                binding.layout.setOnClickListener(thisContext);
            }

            /**
             * Initialised holder by new operator
             *
             * @param binding  DataBinding
             * @param data     dataList
             * @param position of adapter
             * @return new ViewHolderClickListener(binding, data, position)
             */
            @Override
            protected ViewHolderClickListener viewHolderReference(FlagAdapterBinding binding, Flag data, int position) {
                return new ViewHolderClickListener(binding, data, position) {
                    /**
                     * Called when a view has been clicked.
                     *
                     * @param view The view that was clicked.
                     *             switch (view.getId()){
                     *             case R.id.id:
                     *             break;
                     *             }
                     */
                    @Override
                    public void onClick(View view) {
                        switch (view.getId()){
                            case R.id.layout:
                                listener.getSelectedFlag(data.getMobilePhonePrefix(), data.getId(),
                                        data.getFlagBaseUrl() + data.getFlagUrl(), data);
                            break;
                        }
                    }
                };
            }
        };
    }


    /**
     * @return new FilterClass();
     */
    @Override
    protected FilterClass initialisedFilterClass() {
        return new FilterClass() {
            /**
             * @return Context, to initialise and use filter class pass activity or application context
             */
            @Override
            public Context getContext() {
                return null;
            }

            /**
             * @param constraint        JobsResponse
             *                          for (L data : list) {
             *                          if (data.getRefNo().toLowerCase().contains(constraint) ||
             *                          data.getZone().toLowerCase().contains(constraint))
             *                          filteredArrayList.add(data);
             *                          }
             * @param list              only one time, use in for loop
             * @param filteredArrayList This list will return with added data
             */
            @Override
            public ArrayList<Flag> filterData(CharSequence constraint, ArrayList<Flag> list,
                                              ArrayList<Flag> filteredArrayList) {
                for (Flag data : list) {
                    if (data.getISOCode1().toLowerCase().contains(constraint) ||
                        data.getISOCode2().toLowerCase().contains(constraint) ||
                        data.getCountry().toLowerCase().contains(constraint) ||
                        data.getMobilePhonePrefix().toLowerCase().contains(constraint) ||
                        data.getCapital().toLowerCase().contains(constraint))
                        filteredArrayList.add(data);
                    }
                return filteredArrayList;
            }
        };
    }
}
