package crud_app.controllers;

import crud_app.dao.CartoonCharacterDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import crud_app.models.CartoonCharacter;
import javax.validation.Valid;

@Controller
@RequestMapping("/characters")
public class CharactersController {

    private final CartoonCharacterDAO characterDAO;

    @Autowired
    public CharactersController(CartoonCharacterDAO characterDAO) {
        this.characterDAO = characterDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("character", characterDAO.index());
        return "characters/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("character", characterDAO.show(id));
        return "characters/show";
    }

    @GetMapping("/new")
    public String newCharacter(@ModelAttribute("character") CartoonCharacter character) {
        return "characters/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("character") @Valid CartoonCharacter character,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "characters/new";

        characterDAO.save(character);
        return "redirect:/characters";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("character", characterDAO.show(id));
        return "characters/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("character") @Valid CartoonCharacter character,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        if(bindingResult.hasErrors())
            return "characters/edit";

        characterDAO.update(id, character);
        return "redirect:/characters";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        characterDAO.delete(id);
        return "redirect:/characters";
    }
}