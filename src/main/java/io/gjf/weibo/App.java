package io.gjf.weibo;

import cn.hutool.extra.mail.MailUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.ArrayList;

/**
 * Create by GuoJF on 2019/8/31
 */
public class App implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        ArrayList<TopSearchBean> topSearch = null;
        try {
            topSearch = TopSearch.getTopSearch("https://s.weibo.com/top/summary");
        } catch (Exception e) {
            e.printStackTrace();
        }


        String htmlbnody = "<body><html>";


        for (TopSearchBean topSearchBean : topSearch) {

            htmlbnody += "<a href=\"" + topSearchBean.getUrl() + " \">" + topSearchBean.getName() + "</a> </br>";

        }
        htmlbnody += "</html></body>";


        System.out.println(htmlbnody);


        MailUtil.send("1281615836@qq.com", "微博实时热搜", htmlbnody, true);



    }
}
