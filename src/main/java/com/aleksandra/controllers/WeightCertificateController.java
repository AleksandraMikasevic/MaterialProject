/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.controllers;

import com.aleksandra.model.WeightCertificate;
import com.aleksandra.service.IGoodsReceivedNoteService;
import com.aleksandra.service.IWeightCertificateService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("weight_certificate")
public class WeightCertificateController {

    @Autowired
    private IWeightCertificateService weightCertificateService;
    @Autowired
    private IGoodsReceivedNoteService receivedNoteService;

    @ResponseBody
    @RequestMapping(value = "/json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WeightCertificate> combobox(ModelMap model) {

        List<WeightCertificate> weightCertificates = new ArrayList<>();
        try {
            weightCertificates = weightCertificateService.findAll();
        } catch (Exception ex) {
            Logger.getLogger(WeightCertificateController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return weightCertificates;
    }

    @RequestMapping(value = "/find_weight_certificate/{id}", method = RequestMethod.GET)
    public ModelAndView find_goods_received_note(@PathVariable int id) {
        WeightCertificate weightCertificate = new WeightCertificate();
        String num = receivedNoteService.existGoodsReceivedNote(id);
        try {
            weightCertificate = weightCertificateService.find(id);
        } catch (Exception ex) {
            Logger.getLogger(WeightCertificateController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ModelAndView mv = new ModelAndView("find_weight_certificate", "weightCertificate", weightCertificate);
        mv.addObject("num", String.valueOf(num));
        return mv;
    }

    @RequestMapping("/all_weight_certificate")
    public ModelAndView all_weight_certificate() {
        List<WeightCertificate> weightCertificates = new ArrayList<>();
        try {
            weightCertificates = weightCertificateService.findAll();
        } catch (Exception ex) {
            Logger.getLogger(WeightCertificateController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModelAndView mv = new ModelAndView("all_weight_certificate");
        mv.addObject("weightCertificates", weightCertificates);
        return mv;
    }

}
