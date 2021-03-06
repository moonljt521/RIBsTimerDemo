package com.gogovan.test.app;

import android.view.ViewGroup;

import com.gogovan.test.app.common.data.TimeTasksRepository;
import com.gogovan.test.app.root.RootBuilder;
import com.uber.rib.core.RibActivity;
import com.uber.rib.core.ViewRouter;

public class RootActivity extends RibActivity {




    @Override
    protected ViewRouter<?, ?, ?> createRouter(ViewGroup parentViewGroup) {

        SampleApplication sampleApplication = (SampleApplication)getApplication();



        RootBuilder rootBuilder = new RootBuilder(new RootBuilder.ParentComponent() {

            @Override
            public TimeTasksRepository getTimeTasksRepository() {
                return  sampleApplication.getAppComponent().getTimeTasksRepository();
            }
        });

        return rootBuilder.build(parentViewGroup);

    }
}
