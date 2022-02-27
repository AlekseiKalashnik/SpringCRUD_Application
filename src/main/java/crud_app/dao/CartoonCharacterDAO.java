package crud_app.dao;

import org.springframework.stereotype.Component;
import crud_app.models.CartoonCharacter;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartoonCharacterDAO {
    private static int CHARACTERS_COUNT;
    private List<CartoonCharacter> characters;

    {
        characters = new ArrayList<>();

        characters.add(new CartoonCharacter(++CHARACTERS_COUNT, "Bugs Bunny"));
        characters.add(new CartoonCharacter(++CHARACTERS_COUNT, "Homer Simpson"));
        characters.add(new CartoonCharacter(++CHARACTERS_COUNT, "Mickey Mouse"));
        characters.add(new CartoonCharacter(++CHARACTERS_COUNT, "Winnie the Pooh"));
        characters.add(new CartoonCharacter(++CHARACTERS_COUNT, "Scooby-Doo"));
    }

    public List<CartoonCharacter> index() {
        return characters;
    }

    public CartoonCharacter show(int id) {
        return characters.stream()
                .filter(characters -> characters.getId() == id)
                .findAny()
                .orElse(null);
    }

    public void save(CartoonCharacter character) {
        character.setId(++CHARACTERS_COUNT);
        characters.add(character);
    }

    public void update(int id, CartoonCharacter updatedCharacter) {
        CartoonCharacter characterToBeUpdated = show(id);

        characterToBeUpdated.setName(updatedCharacter.getName());
    }

    public void delete(int id) {
        characters.removeIf(p -> p.getId() == id);
    }
}