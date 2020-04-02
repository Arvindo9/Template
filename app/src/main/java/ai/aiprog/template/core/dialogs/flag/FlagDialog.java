package ai.aiprog.template.core.dialogs.flag;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.databinding.ObservableList;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import ai.aiprog.template.BR;
import ai.aiprog.template.R;
import ai.aiprog.template.base.BaseDialog;
import ai.aiprog.template.core.dialogs.flag.adapter.FlagAdapter;
import ai.aiprog.template.data.model.db.flag.Flag;
import ai.aiprog.template.databinding.FlagDialogBinding;
import ai.aiprog.template.di.builder.ViewModelProviderFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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
public class FlagDialog extends BaseDialog<FlagDialogBinding, FlagViewModel> implements FlagNavigator,
        FlagAdapter.FlagAdapterListener {
    public static final String TAG_FLAG_DIALOG = FlagDialog.class.getSimpleName();
    private static final String FLAG_DATA_LIST = "FLAG_DATA_LIST";
    @Inject
    FlagAdapter adapter;
    private FlagViewModel viewModel;

    private ArrayList<Flag> flagList;
    private static FlagCallBack flagCallBack;

    private String TAG = FlagDialog.class.getSimpleName();
    private FlagDialogBinding binding;

//    public static FlagDialog newInstance(ArrayList<Flag> flagList, FlagCallBack flagCallBack) {
    public static FlagDialog newInstance(ObservableList<Flag> flagObservableList, FlagCallBack flagCallBack) {
        ArrayList<Flag> flagList = new ArrayList<>(flagObservableList);
        FlagDialog.flagCallBack = flagCallBack;
        FlagDialog fragment = new FlagDialog();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(FLAG_DATA_LIST, flagList);
        fragment.setArguments(bundle);
        return fragment;
    }

    public interface FlagCallBack {
        void flagCallBack(String code, int id, String flagUrl, Flag flagData);
    }

    public FlagDialog() {
        super();
    }

    /**
     * @return R.layout.layout_file
     */
    @Override
    protected int getLayout() {
        return R.layout.flag_dialog;
    }

    /**
     * Override for get the instance of viewModel
     * String string = getArguments() != null ? getArguments().getString(KEY) : "";
     *
     * @return viewModel = ViewModelProviders.of(this,factory).get(WelcomeViewModel.class);
     */
    @Override
    protected Class<FlagViewModel> setViewModel() {
        return FlagViewModel.class;
    }

    @Override
    protected void getViewModel(FlagViewModel viewModel) {
        this.viewModel = viewModel;
    }


    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    @Override
    public int getBindingVariable() {
        return BR.data;
    }

    /**
     * @param binding activity class data binding
     */
    @Override
    public void getActivityBinding(FlagDialogBinding binding) {
        this.binding = binding;
    }

    /**
     * Do anything on onCreateView after binding
     */
    @Override
    protected void init() {
        setUp();
        binding.setData(viewModel);
        viewModel.setNavigator(this);

        setFilter();
        setRecyclerView();
        subscribeToLiveData();
        viewModel.setFlagLiveData(flagList);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setUp(){
        flagList = getArguments() != null ? getArguments().getParcelableArrayList(FLAG_DATA_LIST) : new ArrayList<>();
    }

    private void setFilter(){
        binding.search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    if (binding.clearBtn.getVisibility() == View.GONE) {
                        binding.clearBtn.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (binding.clearBtn.getVisibility() == View.VISIBLE) {
                        binding.clearBtn.setVisibility(View.GONE);
                    }
                }
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setRecyclerView(){

        adapter.setListener(this);
//        adapter.addItems(flagList);
//        mLayoutManager.setOrientation(androidx.appcompat.widget.LinearLayoutCompatManager.VERTICAL);
//        binding.notificationListView.setLayoutManager(mLayoutManager);
        binding.listView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.listView.setAdapter(adapter);
    }

    @Override
    public void updateListView(List<Flag> model) {
        adapter.addItems(model);
    }

    private void subscribeToLiveData() {
        viewModel.getFlagLiveData().observe(this, flags ->
                viewModel.addFlagToList(flags));
    }

    @Override
    public void onBackButtonClick() {
        dismiss();
    }

    @Override
    public void onClearButtonClick() {
        binding.search.setText("");
    }

    @Override
    public void onSearchClick() {

    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void getSelectedFlag(String code, int id, String flagUrl, Flag data) {
        flagCallBack.flagCallBack(code, id, flagUrl, data);
        dismiss();
    }

    @Override
    public void onRetryClick() {
        //Adapter refresh button
    }
}
