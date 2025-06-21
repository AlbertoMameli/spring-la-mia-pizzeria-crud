package org.lessons.wdpt6.pizzeria.la_mia_pizzeria.controller;

import org.lessons.wdpt6.pizzeria.la_mia_pizzeria.model.Pizza;
import org.lessons.wdpt6.pizzeria.la_mia_pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("pizzas", pizzaRepository.findAll());// prendo la lista delle pizze chiamando la
                                                                // pizzarepository
        return "pizzas/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable(name = "id") Integer id, Model model) {
        Pizza pizza = pizzaRepository.findById(id).get();
        model.addAttribute("pizza", pizza);
        return "pizzas/show";
    }

    @GetMapping("/create") // sto chiamando la rotta per andare sul create
    public String create(Model model){
        model.addAttribute("pizza", new Pizza());
        return "pizzas/create";
    } 


    @PostMapping("/create")
    public String store(@ModelAttribute("pizza")Pizza formPizza,BindingResult bindingResult,Model model){

        //validazioni

   if (bindingResult.hasErrors()){
           
        //salvare la pizza inserita nel form 
        pizzaRepository.save(formPizza); 
        return "/pizzas/create";
    }

//ritorna alla pagina pizzas
        return "redirect/pizzas";

    }
}
