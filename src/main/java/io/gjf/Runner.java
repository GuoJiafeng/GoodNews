package io.gjf;

import io.gjf.utils.QuartzManager;
import io.gjf.weibo.App;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Create by GuoJF on 2019/8/31
 */
public class Runner {

    public static void main(String[] args) throws Exception {


        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        QuartzManager quartzManager = new QuartzManager();

        quartzManager.setScheduler(scheduler);

        quartzManager.addJob("weibo", "Group01", "Group01", "Group01", App.class, "0 0 * * * ? *");

    }

}
