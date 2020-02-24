package br.com.brainweb.interview.model.DTO;

import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import br.com.brainweb.interview.model.enums.Race;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.*;

import java.sql.Timestamp;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class HeroDTO {
    private UUID id;

    @NotBlank(message = "Name cannot be empty")
    @NotEmpty(message = "Name cannot be empty")
    @Length(min = 1, max = 255, message = "Name min 1 and max 255")
    private String name;

    @NotNull(message = "Race cannot be null")
    private Race race;

    @Min(value = 0, message = "strength min 0")
    @Max(value = 10, message = "strength max 10")
    @NotNull(message = "strength cannot be null")
    private int strength;

    @Min(value = 0, message = "agility min 0")
    @Max(value = 10, message = "agility max 10")
    @NotNull(message = "agility cannot be null")
    private int agility;

    @Min(value = 0, message = "dexterity min 0")
    @Max(value = 10, message = "dexterity max 10")
    @NotNull(message = "dexterity cannot be null")
    private int dexterity;

    @Min(value = 0, message = "intelligence min 0")
    @Max(value = 10, message = "intelligence max 10")
    @NotNull(message = "intelligence cannot be null")
    private int intelligence;

    private Timestamp created_at;
    private Timestamp updated_at;

    public HeroDTO(Hero hero, PowerStats powerStats){
        setId(hero.getId());
        setName(hero.getName());
        setRace(Race.valueOf(hero.getRace()));
        setAgility(powerStats.getAgility());
        setDexterity(powerStats.getDexterity());
        setIntelligence(powerStats.getIntelligence());
        setStrength(powerStats.getStrength());
        setCreated_at(hero.getCreated_at());
        setUpdated_at(hero.getUpdated_at());
    }
}
