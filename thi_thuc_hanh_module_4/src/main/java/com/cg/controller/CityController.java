package com.cg.controller;

import com.cg.model.City;
import com.cg.model.Country;
import com.cg.service.city.ICityService;
import com.cg.service.country.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/city")
public class CityController {

    @Autowired
    private ICityService cityService;

    @Autowired
    private ICountryService countryService;

    @GetMapping("")
    public ModelAndView listCity() {
        ModelAndView modelAndView = new ModelAndView("/city/listCities");
        List<City> cities = cityService.findAll();
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }

    @GetMapping("/getCity/{id}")
    public ModelAndView getCity(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/city/showInfoCity");
        Optional<City> city = cityService.findById(id);
        if (city.isPresent()) {
            modelAndView.addObject("city", city.get());
            return modelAndView;
        }
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("/city/createCity");
        List<Country> countries = countryService.findAll();
        modelAndView.addObject("countries", countries);
        modelAndView.addObject("city", new City());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView save(@Validated @ModelAttribute City city,
                             BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("/city/createCity");
        List<Country> countries = countryService.findAll();
        if (result.hasFieldErrors()) {

            modelAndView.addObject("countries", countries);
            return modelAndView;
        }
        cityService.save(city);
        modelAndView.addObject("countries", countries);
        modelAndView.addObject("city", new City());
        modelAndView.addObject("message", "Create new create successful!!!");
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView showFormUpdate(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/city/updateCity");
        Optional<City> city = cityService.findById(id);
        List<Country> countries = countryService.findAll();
        if (city.isPresent()) {
            modelAndView.addObject("city", city.get());
            modelAndView.addObject("countries", countries);
            return modelAndView;
        }
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView update(@Validated @ModelAttribute City city,
                               BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("/city/updateCity");
        List<Country> countries = countryService.findAll();
        if (result.hasFieldErrors()) {
            modelAndView.addObject("countries", countries);
            return modelAndView;
        }
        City updateCity = cityService.save(city);
        modelAndView.addObject("city", updateCity);
        modelAndView.addObject("countries", countries);
        modelAndView.addObject("message", "Update successful");
        return modelAndView;
    }

    @GetMapping("/suspension/{id}")
    public ModelAndView suspension(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/city/");
        cityService.remove(id);
        return modelAndView;
    }
}
