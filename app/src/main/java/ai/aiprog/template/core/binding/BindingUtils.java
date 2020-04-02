/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package ai.aiprog.template.core.binding;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.RecyclerView;

import ai.aiprog.template.R;
import ai.aiprog.template.core.dialogs.flag.adapter.FlagAdapter;
import ai.aiprog.template.data.model.db.flag.Flag;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * Author       : Arvindo Mondal
 * Created on   : 09-05-2019
 * Email        : arvindo@aiprog.in
 * Company      : AIPROG
 * Designation  : Programmer
 * About        : I am a human can only think, I can't be a person like machine which have lots of memory and knowledge.
 * Quote        : No one can measure limit of stupidity but stupid things bring revolutions
 * Strength     : Never give up
 * Motto        : To be known as great Mathematician
 * Skills       : Algorithms and logic
 * Website      : www.aiprog.in
 */
public final class BindingUtils {
    //ObservableField must not have final
    public static ObservableField<Boolean> progressBarVisibility = new ObservableField<>();

    private BindingUtils() {
        // This class is not publicly instantiable
    }

    @BindingAdapter({"ImageViewUrl"})
    public static void setImageUrl(ImageView imageView, String url) {
/*
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.status_loading)
                .error(R.drawable.status_error)
                .fit()
                .centerCrop()
                .into(imageView);
*/
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.status_loading)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .fit()
                .centerCrop()
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        //Try again online if cache failed
                        Picasso.get()
                                .load(url)
                                .placeholder(R.drawable.status_loading)
                                .error(R.drawable.status_error)
                                .fit()
                                .centerCrop()
                                .into(imageView, new Callback() {
                                    @Override
                                    public void onSuccess() {

                                    }

                                    @Override
                                    public void onError(Exception e) {
//                                        e.printStackTrace();
//                                        Log.v("Picasso","Could not fetch image");
                                    }
                                });
                    }
                });
    }

    @BindingAdapter({"ImageView"})
    public static void setImageView(ImageView imageView, File file) {
        Picasso.get()
                .load(file)
                .placeholder(R.drawable.status_loading)
                .error(R.drawable.status_error)
                .fit()
                .centerCrop()
                .into(imageView);
    }

    @BindingAdapter({"ImageViewDrawable"})
    public static void setImageViewDrawable(ImageView imageView, int index) {
        Picasso.get()
                .load(index)
                .placeholder(R.drawable.status_loading)
                .error(R.drawable.status_error)
                .fit()
                .centerCrop()
                .into(imageView);
    }

    //List view-----------------------------------

    @BindingAdapter({"FlagAdapter"})
    public static void addFlagAdapter(RecyclerView recyclerView, List<Flag> list) {
        FlagAdapter adapter = (FlagAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(list);
        }
    }
}
