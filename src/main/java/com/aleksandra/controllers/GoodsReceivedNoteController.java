/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.controllers;

import com.aleksandra.model.Employee;
import com.aleksandra.model.GoodsReceivedNote;
import com.aleksandra.model.GoodsReceivedNoteItem;
import com.aleksandra.model.GoodsReceivedNoteItemPK;
import com.aleksandra.model.Material;
import com.aleksandra.model.Supplier;
import com.aleksandra.model.WeightCertificate;
import com.aleksandra.service.IEmployeeService;
import com.aleksandra.service.IGoodsReceivedNoteService;
import com.aleksandra.service.IMaterialService;
import com.aleksandra.service.ISupplierService;
import com.aleksandra.service.IWeightCertificateService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
@RequestMapping("/goods_received_note")
@Scope("session")
public class GoodsReceivedNoteController {

    GoodsReceivedNote goodsReceivedNote;

    @Autowired
    private IGoodsReceivedNoteService goodsReceivedNoteService;
    @Autowired
    private IMaterialService materialService;
    @Autowired
    private IWeightCertificateService weightCertificateService;
    @Autowired
    private ISupplierService supplierService;
    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("/all_goods_received_notes")
    public ModelAndView all_goods_received_notes() {

        goodsReceivedNote = new GoodsReceivedNote();
        List<GoodsReceivedNote> goodsReceivedNotes = new ArrayList<>();
        try {
            goodsReceivedNotes = goodsReceivedNoteService.findAll();
        } catch (Exception ex) {
            Logger.getLogger(GoodsReceivedNoteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModelAndView mv = new ModelAndView("all_goods_received_notes");
        mv.addObject("goodsReceivedNotes", goodsReceivedNotes);
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/all_goods_received_note_json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GoodsReceivedNote> all_goods_received_notes_json() {
        goodsReceivedNote = new GoodsReceivedNote();
        List<GoodsReceivedNote> goodsReceivedNotes = new ArrayList<>();
        try {
            goodsReceivedNotes = goodsReceivedNoteService.findAll();
        } catch (Exception ex) {
            Logger.getLogger(GoodsReceivedNoteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return goodsReceivedNotes;
    }

    @ResponseBody
    @RequestMapping(value = "/items_json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GoodsReceivedNoteItem> items_json() {
        return goodsReceivedNote.getGoodsReceivedNoteItems();
    }

    @ResponseBody
    @RequestMapping(value = "/json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GoodsReceivedNote> mat_json() {
        List<GoodsReceivedNote> goodsReceivedNotes = new ArrayList<>();
        try {
            goodsReceivedNotes = goodsReceivedNoteService.findAll();
        } catch (Exception ex) {
            Logger.getLogger(GoodsReceivedNoteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return goodsReceivedNotes;
    }

    @RequestMapping(value = "/add_goods_received_note/{id}", method = RequestMethod.GET)
    public ModelAndView add_goods_received_note_get(@PathVariable int id) {
        goodsReceivedNote = new GoodsReceivedNote();
        ModelAndView mv = new ModelAndView("add_goods_received_note");
        try {
            int num = goodsReceivedNoteService.getNumber();
            goodsReceivedNote.setId(num);
            WeightCertificate certificate = weightCertificateService.find(id);
            goodsReceivedNote.setWeightCertificate(certificate);
        } catch (Exception ex) {
            Logger.getLogger(GoodsReceivedNoteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mv.addObject("grcn", goodsReceivedNote);
        List<WeightCertificate> certificates = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        List<Supplier> suppliers = new ArrayList<>();
        try {
            certificates = weightCertificateService.findPossibilities();
            employees = employeeService.findAll();
            suppliers = supplierService.findAll();
        } catch (Exception ex) {
            Logger.getLogger(GoodsReceivedNoteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mv.addObject("certificates", certificates);
        mv.addObject("employees", employees);
        mv.addObject("suppliers", suppliers);
        return mv;
    }

    @RequestMapping(value = "/add_goods_received_note", method = RequestMethod.POST)
    public String add_goods_received_note(RedirectAttributes redirectAttributes) {
        sum();
        try {
            goodsReceivedNoteService.add(goodsReceivedNote);
            return "redirect:/goods_received_note/all_goods_received_notes";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", "Error during creation of goods received note.");
            return "redirect:/goods_received_note/add_goods_received_note";
        }
    }

    @RequestMapping(value = "/add_goods_received_note_info", method = RequestMethod.POST)
    public String add_goods_received_note_info(@ModelAttribute("grcn") GoodsReceivedNote grn) {
        goodsReceivedNote.setDate(grn.getDate());
        goodsReceivedNote.setWeightCertificate(grn.getWeightCertificate());
        goodsReceivedNote.setEmployee(grn.getEmployee());
        goodsReceivedNote.setSupplier(grn.getSupplier());
        return "redirect:/goods_received_note/add_goods_received_items";
    }

    @RequestMapping(value = "/change_goods_received_note_info", method = RequestMethod.POST)
    public String change_goods_received_note_info(@ModelAttribute("grcn") GoodsReceivedNote grn) {
        goodsReceivedNote.setDate(grn.getDate());
        System.out.println("change goods received note info");
        goodsReceivedNote.setWeightCertificate(grn.getWeightCertificate());
        goodsReceivedNote.setEmployee(grn.getEmployee());
        goodsReceivedNote.setSupplier(grn.getSupplier());
                System.out.println(goodsReceivedNote.getDate());
        System.out.println(goodsReceivedNote.getEmployee().getId());
        System.out.println(goodsReceivedNote.getSupplier());

        return "redirect:/goods_received_note/change_goods_received_items";
    }

    @RequestMapping(value = "/change_goods_received_items", method = RequestMethod.GET)
    public ModelAndView change_goods_received_items() {
        ModelAndView mv = new ModelAndView("change_goods_received_items");
        mv.addObject("grcn", goodsReceivedNote);
        List<Material> materials = new ArrayList<>();
        try {
            materials = materialService.findAll();
        } catch (Exception ex) {
            Logger.getLogger(GoodsReceivedNoteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mv.addObject("materials", materials);
        System.out.println(goodsReceivedNote.getEmployee().getId());
        System.out.println("change grcn");
        return mv;
    }

    @RequestMapping(value = "/add_goods_received_items", method = RequestMethod.GET)
    public ModelAndView add_goods_received_items() {
        ModelAndView mv = new ModelAndView("add_goods_received_items");
        mv.addObject("grcn", goodsReceivedNote);
        goodsReceivedNote.setGoodsReceivedNoteItems(new ArrayList<GoodsReceivedNoteItem>());
        List<Material> materials = new ArrayList<>();
        try {
            materials = materialService.findAll();
        } catch (Exception ex) {
            Logger.getLogger(GoodsReceivedNoteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mv.addObject("materials", materials);
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/add_item/{materialID}/{quantity}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public GoodsReceivedNoteItem add_item(@PathVariable String materialID, @PathVariable double quantity) {
        GoodsReceivedNoteItem item = new GoodsReceivedNoteItem();
                System.out.println(goodsReceivedNote.getEmployee().getId());
        System.out.println("add item");
        try {
            if (goodsReceivedNote.getGoodsReceivedNoteItems().isEmpty()) {
                item = new GoodsReceivedNoteItem(new GoodsReceivedNoteItemPK(goodsReceivedNote.getId(), 1));
                item.setSerNum(1);
                                System.out.println(goodsReceivedNote.getEmployee().getId());

            } else {
                item = new GoodsReceivedNoteItem(new GoodsReceivedNoteItemPK(goodsReceivedNote.getId(), goodsReceivedNote.getGoodsReceivedNoteItems().get(goodsReceivedNote.getGoodsReceivedNoteItems().size() - 1).getGoodsReceivedNoteItemPK().getItemNum() + 1));
                item.setSerNum(goodsReceivedNote.getGoodsReceivedNoteItems().size() + 1);
                                System.out.println(goodsReceivedNote.getEmployee().getId());

            }
            item.setMaterial(materialService.find(materialID));
            item.setQuantity(quantity);
            goodsReceivedNote.getGoodsReceivedNoteItems().add(item);
                            System.out.println(goodsReceivedNote.getEmployee().getId());

        } catch (Exception ex) {
            Logger.getLogger(GoodsReceivedNoteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return item;
    }

    @ResponseBody
    @RequestMapping(value = "/update_item/{num}/{materialID}/{quantity}", method = RequestMethod.GET)
    public String update_item(@PathVariable int num, @PathVariable String materialID, @PathVariable double quantity) {
        System.out.println(goodsReceivedNote.getEmployee().getFullName());
        System.out.println("Update item");
        for (GoodsReceivedNoteItem item1 : goodsReceivedNote.getGoodsReceivedNoteItems()) {
            if (item1.getGoodsReceivedNoteItemPK().getItemNum() == num) {
                System.out.println("");
                item1.setQuantity(quantity);
                Material material = new Material();
                material.setId(materialID);
                item1.setMaterial(material);
                break;
            }
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/remove_item/{itemNum}", method = RequestMethod.GET)
    public GoodsReceivedNoteItem remove_item(@PathVariable int itemNum) {
        GoodsReceivedNoteItem item = new GoodsReceivedNoteItem();
        for (GoodsReceivedNoteItem item1 : goodsReceivedNote.getGoodsReceivedNoteItems()) {
            if (item1.getGoodsReceivedNoteItemPK().getItemNum() == itemNum) {
                item = item1;
            }
        }
        goodsReceivedNote.getGoodsReceivedNoteItems().remove(item);
        changeNumbers();
        return null;
    }

    @RequestMapping(value = "/remove_goods_received_note/{id}", method = RequestMethod.GET)
    public ModelAndView remove_goods_received_note(@PathVariable int id) {
        try {
            goodsReceivedNote = goodsReceivedNoteService.find(id);
        } catch (Exception ex) {
            Logger.getLogger(GoodsReceivedNoteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ModelAndView mv = new ModelAndView("remove_goods_received_note", "grcn", goodsReceivedNote);

        return mv;
    }

    @RequestMapping(value = "/remove_goods_received_note/{id}", method = RequestMethod.POST)
    public String remove_goods_received_note_post(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            goodsReceivedNoteService.delete(id);
            return "redirect:/goods_received_note/all_goods_received_notes";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", "Error during deletion of goods received note.");
            return "redirect:/goods_received_note/remove_goods_received_note";

        }

    }

    @RequestMapping(value = "/find_goods_received_note/{id}", method = RequestMethod.GET)
    public ModelAndView find_goods_received_note(@PathVariable int id) {
        try {
            goodsReceivedNote = goodsReceivedNoteService.find(id);
        } catch (Exception ex) {
            Logger.getLogger(GoodsReceivedNoteController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ModelAndView mv = new ModelAndView("find_goods_received_note", "grcn", goodsReceivedNote);

        return mv;
    }

    @RequestMapping(value = "/update_goods_received_note/{id}", method = RequestMethod.GET)
    public ModelAndView update_goods_received_note(@PathVariable int id) {
        goodsReceivedNote = new GoodsReceivedNote();
        try {
            goodsReceivedNote = goodsReceivedNoteService.find(id);
        } catch (Exception ex) {
            Logger.getLogger(GoodsReceivedNoteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<WeightCertificate> certificates = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        List<Supplier> suppliers = new ArrayList<>();
        try {
            suppliers = supplierService.findAll();
            certificates = weightCertificateService.findPossibilities();
            certificates.add(goodsReceivedNote.getWeightCertificate());
            employees = employeeService.findAll();
        } catch (Exception ex) {
            Logger.getLogger(GoodsReceivedNoteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModelAndView mv = new ModelAndView("update_goods_received_note", "grcn", goodsReceivedNote);
        mv.addObject("certificates", certificates);
        mv.addObject("employees", employees);
        mv.addObject("suppliers", suppliers);
        return mv;
    }

    @RequestMapping(value = "/update_goods_received_note", method = RequestMethod.POST)
    public String update_goods_received_note_post(@ModelAttribute("goodsReceivedNote") GoodsReceivedNote grcn, RedirectAttributes redirectAttributes) {
        System.out.println("pocetak update");          
        System.out.println(goodsReceivedNote.getEmployee().getId());

        try {
            sum();
            System.out.println(goodsReceivedNote.getEmployee().getId());
            System.out.println("Update grcn//");
            goodsReceivedNoteService.save(goodsReceivedNote);
            return "redirect:/goods_received_note/all_goods_received_notes";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", "Error during update of goods received note.");
            return "redirect:/goods_received_note/update_goods_received_note";
        }
    }

    private void changeNumbers() {
        for (int i = 0; i < goodsReceivedNote.getGoodsReceivedNoteItems().size(); i++) {
            goodsReceivedNote.getGoodsReceivedNoteItems().get(i).setSerNum(i + 1);
        }
    }

    private void sum() {
       
        double sum = 0;
        double tax = 0;
        for (GoodsReceivedNoteItem item : goodsReceivedNote.getGoodsReceivedNoteItems()) {
            try {
                sum = sum + (item.getQuantity() * item.getMaterial().getPrice());
                tax = tax + (item.getQuantity() * item.getMaterial().getPrice() * item.getMaterial().getTax() * 0.01);
            } catch (Exception ex) {
                Logger.getLogger(GoodsReceivedNoteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("tax sum: " + tax);
        goodsReceivedNote.setTaxSum(tax);
        goodsReceivedNote.setSum(sum);
        goodsReceivedNote.setSumWithTax(tax + sum);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    private GoodsReceivedNoteItem findItem(int serNum) {
        for (GoodsReceivedNoteItem item : goodsReceivedNote.getGoodsReceivedNoteItems()) {
            if (item.getGoodsReceivedNoteItemPK().getGrcnID() == serNum) {
                return item;
            }
        }
        return null;
    }

}
