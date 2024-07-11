package cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n01.controller;


import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n01.model.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n01.model.services.SucursalService;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/sucursals")
public class SucursalController {

    private final SucursalService sucursalService;

    public SucursalController(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }



    @PostMapping(path="/add")
    public ResponseEntity<SucursalDTO> saveSucursal(@RequestBody SucursalDTO sucursalDto) {
        sucursalService.createSucursal(sucursalDto);
        return new ResponseEntity<>(sucursalDto, HttpStatus.CREATED);

    }

    @PutMapping(path="/update")
    public ResponseEntity<SucursalDTO> updateSucursal(@RequestBody SucursalDTO sucursalDto) {

        return new ResponseEntity<>(sucursalService.updateSucursal(sucursalDto),HttpStatus.OK);
    }


    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<String> deleteSucursal(@PathVariable int id) {
        sucursalService.deleteSucursal(id);
        return new ResponseEntity<>(("Sucursal id " + id + " deleted succesfully"), HttpStatus.OK);
    }


    @GetMapping(path="/getOne/{id}")
    public ResponseEntity<SucursalDTO> getOneSucursal(@PathVariable int id) {

        return new ResponseEntity<>(sucursalService.getOneSucursal(id), HttpStatus.OK);
    }


    @GetMapping(path="/getAll")
    public ResponseEntity<List<SucursalDTO>> getAllSucursals() {

        return new ResponseEntity<>(sucursalService.getAllSucursals(), HttpStatus.OK);
    }


}
