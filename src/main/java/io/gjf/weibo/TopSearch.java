package io.gjf.weibo;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Create by GuoJF on 2019/8/31
 */
public class TopSearch {
    public static ArrayList<TopSearchBean> getTopSearch(String urltext) throws Exception {

        //获取主页
        String url = urltext;
        Connection connection = Jsoup.connect(url);

        String header = "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3\n" +
                "Accept-Encoding: gzip, deflate, br\n" +
                "Accept-Language: zh-CN,zh;q=0.9,en;q=0.8,zh-TW;q=0.7,zh-HK;q=0.6\n" +
                "Cache-Control: no-cache\n" +
                "Connection: keep-alive\n" +
                "Cookie: SINAGLOBAL=6179870341432.93.1533800092867; ALF=1589373543; SCF=AhxMlkEVRFl8jS_WUE3nWlro9IFd3MKb6bfqkTes2fn-lCrj4kqFcM7Bx0iS43uSKRmXmauSy55e8DVvFBbPWZo.; SUHB=0rrLqIyjpfCVoz; SUB=_2AkMqX9GOf8PxqwJRmP0RzmnlaYlzyQ_EieKcAyBVJRMxHRl-yT83qmETtRB6Ad__YRmvBlgXDZk3I_qoRN1aLYMz16nd; SUBP=0033WrSXqPxfM72-Ws9jqgMF55529P9D9WhDyHZjJmn_QafehlGZWhcK; UM_distinctid=16cbca0d65c3bc-0ba1924bf5cebd-e353165-1fa400-16cbca0d65da96; UOR=,,www.baidu.com; _s_tentry=www.baidu.com; Apache=9951297836890.924.1567250222150; ULV=1567250222159:23:3:2:9951297836890.924.1567250222150:1567247824657; WBStorage=f54cf4e4362237da|undefined\n" +
                "Host: s.weibo.com\n" +
                "Pragma: no-cache\n" +
                "Referer: https://www.baidu.com/s?wd=%E5%BE%AE%E5%8D%9A%E7%83%AD%E6%90%9C&rsv_spt=1&rsv_iqid=0x9a79e95b000de2ea&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&rqlang=&tn=baiduhome_pg&ch=&rsv_enter=1&rsv_dl=ib&inputT=11935\n" +
                "Upgrade-Insecure-Requests: 1\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36";


        for (String s : header.split("\n")) {

            String[] value = s.split(":");
            connection.header(value[0], value[1]);


        }

        Connection.Response response = connection.ignoreContentType(true).method(Connection.Method.GET).execute();
        Document document = response.parse();
        //System.out.println(document.body().html());


        Elements topSearchNameList = document.getElementsByClass("td-02");


        ArrayList<TopSearchBean> searchBeans = new ArrayList<>();
        topSearchNameList.forEach((name) -> {

            System.out.println(name);

        });


        topSearchNameList.forEach((name) -> {

            System.out.println(name);

            // System.out.println(name.getElementsByAttributeValueContaining("href","/").html());

            if (!name.toString().contains("href_to")) {

                if (!name.toString().contains("javascript:void(0)")) {

                    searchBeans.add(new TopSearchBean(name.text().split(" ")[0], "https://s.weibo.com" + name.toString().split("<a href=\"")[1].split("\" target=\"_blank\">")[0]));

                }else {

                    searchBeans.add(new TopSearchBean(name.text().split(" ")[0], "https://s.weibo.com" + name.toString().split("<a href=\"")[1].split("\" href=\"javascript:void")[0]));


                }

            } else {

                String s1 = name.toString().split("<a href_to=\"")[1];

                System.out.println(s1);


                String s2 = s1.split("\" href=\"javascript:void")[0];

                System.out.println(s2);


                searchBeans.add(new TopSearchBean(name.text().split(" ")[0], "https://s.weibo.com" + name.toString().split("<a href_to=\"")[1].split("\" href=\"javascript:void")[0]));


            }

        });


        searchBeans.forEach(topSearchBean -> {
            //System.out.println(topSearchBean.toString());
        });


        return searchBeans;
    }
}
