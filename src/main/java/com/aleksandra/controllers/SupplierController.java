/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.controllers;

import com.aleksandra.model.GoodsReceivedNote;
import com.aleksandra.model.Supplier;
import com.aleksandra.service.IGoodsReceivedNoteService;
import com.aleksandra.service.ISupplierService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private ISupplierService supplierService;
    @Autowired
    private IGoodsReceivedNoteService receivedNoteService;

    @RequestMapping("/all_suppliers")
    public ModelAndView all_suppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        try {
            suppliers = supplierService.findAll();
        } catch (Exception ex) {
            Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ModelAndView mv = new ModelAndView("all_suppliers");
        mv.addObject("suppliers", suppliers);
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/all_suppliers_json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Supplier> suppliers_json() {
        List<Supplier> suppliers = new ArrayList<>();
        try {
            suppliers = supplierService.findAll();
        } catch (Exception ex) {
            Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return suppliers;
    }

    @RequestMapping(value = "/add_supplier", method = RequestMethod.GET)
    public ModelAndView add_supplier_get() {
        ModelAndView mv = new ModelAndView("add_supplier", "supplier", new Supplier());
        return mv;
    }

    @RequestMapping(value = "/add_supplier", method = RequestMethod.POST)
    public String add_supplier_post(@ModelAttribute("supplier") Supplier supplier, RedirectAttributes redirectAttributes) {
        try {
            supplierService.add(supplier);
            return "redirect:/supplier/all_suppliers";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", "Error during creation of supplier.");
            return "redirect:/supplier/add_supplier";
        }
    }

    @RequestMapping(value = "/remove_supplier/{id}", method = RequestMethod.GET)
    public ModelAndView remove_supplier(@PathVariable String id) {
        Supplier supplier = new Supplier();
        try {
            supplier = supplierService.find(id);
        } catch (Exception ex) {
            Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModelAndView mv = new ModelAndView("remove_supplier", "supplier", supplier);

        return mv;
    }

    @RequestMapping(value = "/remove_supplier/{id}", method = RequestMethod.POST)
    public String remove_supplier_post(@PathVariable String id, @ModelAttribute("supplier") Supplier supplier, RedirectAttributes redirectAttributes) {
        try {
            supplierService.delete(id);
            return "redirect:/supplier/all_suppliers";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", "Error during deletion of supplier.");
            return "redirect:/supplier/remove_supplier";
        }
    }

    @RequestMapping(value = "/find_supplier/{id}", method = RequestMethod.GET)
    public ModelAndView find_supplier(@PathVariable String id) {
        Supplier supplier = new Supplier();
        try {
            supplier = supplierService.find(id);
        } catch (Exception ex) {
            Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModelAndView mv = new ModelAndView("find_supplier");
        mv.addObject("supplier", supplier);
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/all_suppliers_json_graph", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> suppliers_json_graph() {
        List<GoodsReceivedNote> grns = new ArrayList<>();
        List<Supplier> suppliers = new ArrayList<>();
        try {
            suppliers = supplierService.findAll();
            grns = receivedNoteService.findAll();
        } catch (Exception ex) {
            Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
        }

        JSONArray jsonArray1 = new JSONArray();
        JSONArray jsonArray2 = new JSONArray();
        List<String> listSuppliers = new ArrayList<>();
        List<Integer> listOrders = new ArrayList<>();
        for (Supplier supplier1 : suppliers) {
            int count = 0;
            for (GoodsReceivedNote grn1 : grns) {
                if (grn1.getSupplier().getId().equals(supplier1.getId())) {
                    count++;
                }
            }
            //ovde dodas materijal i broj ukupno za njega
            listSuppliers.add(supplier1.getName());
            listOrders.add(count);
        }
        Map<String, Object> json = new HashMap();
        json.put("suppliers", listSuppliers);
        json.put("orders", listOrders);
        return json;
    }

    @RequestMapping(value = "/update_supplier/{id}", method = RequestMethod.GET)
    public ModelAndView update_supplier_get(@PathVariable String id) {
        Supplier supplier = new Supplier();
        try {
            supplier = supplierService.find(id);
        } catch (Exception ex) {
            Logger.getLogger(SupplierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModelAndView mv = new ModelAndView("update_supplier", "supplier", supplier);
        return mv;
    }

    @RequestMapping(value = "/update_supplier/{id}", method = RequestMethod.POST)
    public String update_supplier_post(@ModelAttribute("supplier") Supplier supplier, @PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            supplier.setId(id);
            supplierService.save(supplier);
            return "redirect:/supplier/all_suppliers";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", "Error during update of supplier.");
            return "redirect:/supplier/update_supplier";
        }
    }

}
