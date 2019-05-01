/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.controllers;

import com.aleksandra.model.GoodsReceivedNote;
import com.aleksandra.model.GoodsReceivedNoteItem;
import com.aleksandra.model.Material;
import com.aleksandra.model.Unit;
import com.aleksandra.service.IGoodsReceivedNoteService;
import com.aleksandra.service.IMaterialService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/material")
@Scope("session")
public class MaterialController {
        @Autowired
    private IMaterialService materialService;
    @Autowired
    private IGoodsReceivedNoteService receivedNoteService;

    @RequestMapping("/all_materials")
    public ModelAndView all_materials() {
        List<Material> materijali = new ArrayList<>();
        try {
            materijali = materialService.findAll();
        } catch (Exception ex) {
            Logger.getLogger(MaterialController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModelAndView mv = new ModelAndView("all_materials");
        mv.addObject("materials", materijali);
        return mv;
    }

    @RequestMapping("/all_materials_graph")
    public ModelAndView all_materials_graph() {
        ModelAndView mv = new ModelAndView("all_materials_graph");
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/all_materials_json_graph", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> mat_json_graph() {
        List<GoodsReceivedNote> grns = new ArrayList<>();
        List<Material> materials = new ArrayList<>();
        try {
            materials = materialService.findAll();
            grns = receivedNoteService.findAll();
        } catch (Exception ex) {
            Logger.getLogger(MaterialController.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<GoodsReceivedNoteItem> items = new ArrayList<>();
        for (GoodsReceivedNote grn : grns) {
            items.addAll(grn.getGoodsReceivedNoteItems());
        }
        JSONArray jsonArray1 = new JSONArray();
        JSONArray jsonArray2 = new JSONArray();
        List<String> listMaterials = new ArrayList<>();
        List<Double> listQuantities = new ArrayList<>();
        for (Material material : materials) {
            double quantity = 0;
            for (GoodsReceivedNoteItem item : items) {
                if (item.getMaterial().getId().equals(material.getId())) {
                    quantity = quantity + item.getQuantity();
                }
            }
            //ovde dodas materijal i broj ukupno za njega
            listMaterials.add(material.getName());
            listQuantities.add(quantity);
        }
        Map<String, Object> json = new HashMap();
        json.put("materials", listMaterials);
        json.put("quantities", listQuantities);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/all_materials_json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Material> mat_json() {
        List<Material> materijali = new ArrayList<>();
        try {
            materijali = materialService.findAll();
        } catch (Exception ex) {
            Logger.getLogger(MaterialController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return materijali;
    }

    @RequestMapping(value = "/add_material", method = RequestMethod.GET)
    public ModelAndView add_material_get() {
        ModelAndView mv = new ModelAndView("add_material", "material", new Material());
        mv.addObject("units", Unit.values());
        return mv;
    }

    @RequestMapping(value = "/add_material", method = RequestMethod.POST)
    public String add_material_post(@ModelAttribute("material") Material material, RedirectAttributes redirectAttributes) {
        try {
            material.setUnit(material.getUnitEn().toString());
            materialService.add(material);
            return "redirect:/material/all_materials";
        } catch (Exception ex) {
            System.out.println("greska: "+ex.getMessage());
            redirectAttributes.addFlashAttribute("error", "Error during creation of material.");
            return "redirect:/material/add_material";
        }
    }

    @RequestMapping(value = "/remove_material/{id}", method = RequestMethod.GET)
    public ModelAndView remove_material(@PathVariable String id) {
        Material material = new Material();
        try {
            material = materialService.find(id);
        } catch (Exception ex) {
            Logger.getLogger(MaterialController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModelAndView mv = new ModelAndView("remove_material", "material", material);

        return mv;
    }

    @RequestMapping(value = "/remove_material/{id}", method = RequestMethod.POST)
    public String remove_material_post(@PathVariable String id, @ModelAttribute("material") Material material, RedirectAttributes redirectAttributes) {
        try {
            System.out.println("remov_materiaaal");
            materialService.delete(id);
            return "redirect:/material/all_materials";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", "Error during deletion of material.");
            return "redirect:/material/remove_material";
        }
    }

    @RequestMapping(value = "/find_material/{id}", method = RequestMethod.GET)
    public ModelAndView find_material(@PathVariable String id) {
        Material material = new Material();
        try {
            material = materialService.find(id);
        } catch (Exception ex) {
            Logger.getLogger(MaterialController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModelAndView mv = new ModelAndView("find_material");
        mv.addObject("material", material);
        return mv;
    }

    @RequestMapping(value = "/update_material/{id}", method = RequestMethod.GET)
    public ModelAndView update_material_get(@PathVariable String id) {
        Material material = new Material();
        try {
            material = materialService.find(id);
        } catch (Exception ex) {
            Logger.getLogger(MaterialController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModelAndView mv = new ModelAndView("update_material", "material", material);
        mv.addObject("units", Unit.values());
        return mv;
    }

    @RequestMapping(value = "/update_material/{id}", method = RequestMethod.POST)
    public String update_material_post(@ModelAttribute("material") Material material, @PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            material.setUnit(material.getUnitEn().toString());
            material.setId(id);
            materialService.save(material);
            return "redirect:/material/all_materials";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", "Error during update of material.");
            return "redirect:/material/update_material";
        }

    }

}
