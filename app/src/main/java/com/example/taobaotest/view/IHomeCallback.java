package com.example.taobaotest.view;

import com.example.taobaotest.base.IBaseCallback;
import com.example.taobaotest.model.domain.Categories;

public interface IHomeCallback extends IBaseCallback {

    void onCategoriesLoaded(Categories categories);



}


