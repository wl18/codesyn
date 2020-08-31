package com.tuli.traffic.controller;

import com.tuli.traffic.entity.Traffic;
import com.tuli.traffic.respository.TrafficRepository;
import com.tuli.traffic.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class TrafficController {

    @Autowired
    TrafficRepository trafficRepository;

    @RequestMapping("/test")
    @ResponseBody
    public Iterable<Traffic> getArticle() {
       Iterable<Traffic> iterablelist= trafficRepository.findAll();
/*       List<Traffic> trafficList=new ArrayList<>();
        for (Traffic traffic : iterablelist) {
            trafficList.add(traffic);
        }*/

        return iterablelist;
    }


    @RequestMapping("/test2")
    @ResponseBody
    public Iterable<Traffic> getArticle2(String start,String end) {

        Iterable<Traffic> iterablelist= trafficRepository.findAllByCreatetimeBetweenOrderByCreatetimeDesc(start,end);
        return iterablelist;
       /*List<Traffic> trafficList= trafficRepository.findAllByCreatetimeBetween("2019-12-12 12:14:16.049","2019-12-12 12:16:16.049");
        System.out.println(trafficList.toString());*/
    }

    @RequestMapping("/test4")
    @ResponseBody
    public Iterable<Traffic> getArticle4(String start,String end) {

        Iterable<Traffic> iterablelist= trafficRepository.findAllByIndexBetweenOrderByIndexDesc(start,end);
        return iterablelist;
       /*List<Traffic> trafficList= trafficRepository.findAllByCreatetimeBetween("2019-12-12 12:14:16.049","2019-12-12 12:16:16.049");
        System.out.println(trafficList.toString());*/
    }

    @RequestMapping("/test3")
    @ResponseBody
    public Optional<Traffic>  getArticle3(String id) {

       Optional<Traffic> traffic=trafficRepository.findById(id);
       return traffic;
       /*List<Traffic> trafficList= trafficRepository.findAllByCreatetimeBetween("2019-12-12 12:14:16.049","2019-12-12 12:16:16.049");
        System.out.println(trafficList.toString());*/
    }




}
