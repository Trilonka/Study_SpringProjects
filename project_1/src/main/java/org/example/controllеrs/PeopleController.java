package org.example.controllеrs;

import org.example.dao.PersonDAO;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String peopleMainPage(Model model) {
        model.addAttribute("people", personDAO.getPeople());
        return "people/main";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String saveNewPerson(@ModelAttribute("person") @Valid Person person,
                                BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "people/new";
        }

        personDAO.savePerson(person);

        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String getPerson(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.getPersonById(id));
        return "people/person";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.getPersonById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult,
                               @PathVariable("id") int id)
    {
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }

        personDAO.updatePerson(id, person);

        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        personDAO.deletePersonById(id);
        return "redirect:/people";
    }
}
