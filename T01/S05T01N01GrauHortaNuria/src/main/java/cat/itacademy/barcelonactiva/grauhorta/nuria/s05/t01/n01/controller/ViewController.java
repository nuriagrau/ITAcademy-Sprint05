package cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n01.controller;


import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n01.model.dto.Mapper;
import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n01.model.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n01.model.services.SucursalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sucursals")
public class ViewController {
    private final SucursalService sucursalService;

    private final Mapper mapper;

    public ViewController(SucursalService sucursalService, Mapper mapper) {
        this.sucursalService = sucursalService;
        this.mapper = mapper;
    }

    @GetMapping(path="/")
    public String viewHomePage(Model model) {
        model.addAttribute( "sucursalsList", sucursalService.getAllSucursals() );

        return "index";
    }

    @GetMapping(path="/addView")
    public String showNewSucursalForm(Model model) {
        SucursalDTO sucursalDto = new SucursalDTO();
        model.addAttribute("sucursalDto", sucursalDto);

        return "add";
    }

    @PostMapping(path="/saveView")
    public String saveSucursalBySucursal(@ModelAttribute("sucursal") SucursalDTO sucursalDto) {
        sucursalService.createSucursal(sucursalDto);

        return "redirect:/sucursals/";
    }

    @GetMapping(path="/updateView/{id}")
    public String showFormForUpdate(@PathVariable int id, Model model) {
        SucursalDTO sucursalDto = sucursalService.getOneSucursal(id);
        model.addAttribute("sucursalDto", sucursalDto);

        return "update";
    }

    @PostMapping(path="/updateViewSucursal")
    public String updateSucursalByView(@ModelAttribute("sucursalDto") SucursalDTO sucursalDto) {
        sucursalService.updateSucursal(sucursalDto);

        return "redirect:/sucursals/";
    }


    @GetMapping(path="/deleteView/{id}")
    public String deleteSucursalByView(@PathVariable int id) {
        sucursalService.deleteSucursal(id);

        return "redirect:/sucursals/";
    }


    @GetMapping(path="/getOneView/{id}")
    public String getOneSucursal(@PathVariable int id, Model model) {
        model.addAttribute("sucursalsList", sucursalService.getOneSucursal(id));

        return "index";
    }

}
