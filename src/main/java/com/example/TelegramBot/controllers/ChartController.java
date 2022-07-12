package com.example.TelegramBot.controllers;

import com.example.TelegramBot.models.Chat;
import com.example.TelegramBot.repos.ChatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class ChartController {
    @Autowired
    ChatRepo chatRepo;

    @GetMapping("/{id}")
    public String getChart(@PathVariable(value = "id") long id, Model model) {
        Optional<Chat> data = chatRepo.findById(id);
        ArrayList<Chat> res = new ArrayList<>();
        data.ifPresent(res::add);
        model.addAttribute("chat", res);
        List<Integer> pList = new ArrayList<>();
        pList.add(res.get(0).getP1());
        pList.add(res.get(0).getP2());
        pList.add(res.get(0).getP3());
        pList.add(res.get(0).getP4());
        pList.add(res.get(0).getP5());
        pList.add(res.get(0).getP6());
        pList.add(res.get(0).getP7());
        Integer max = Collections.max(pList);
        float av_p1 = ((float) res.get(0).getP1() / max) * 100;
        float av_p2 = ((float) res.get(0).getP2() / max) * 100;
        float av_p3 = ((float) res.get(0).getP3() / max) * 100;
        float av_p4 = ((float) res.get(0).getP4() / max) * 100;
        float av_p5 = ((float) res.get(0).getP5() / max) * 100;
        float av_p6 = ((float) res.get(0).getP6() / max) * 100;
        float av_p7 = ((float) res.get(0).getP7() / max) * 100;
        model.addAttribute("av_p1", av_p1);
        model.addAttribute("av_p2", av_p2);
        model.addAttribute("av_p3", av_p3);
        model.addAttribute("av_p4", av_p4);
        model.addAttribute("av_p5", av_p5);
        model.addAttribute("av_p6", av_p6);
        model.addAttribute("av_p7", av_p7);
        return "main";
    }
}
