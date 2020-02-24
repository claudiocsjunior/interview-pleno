package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.DTO.HeroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/heroes", produces = APPLICATION_JSON_VALUE)
public class HeroController {

    @Autowired
    private HeroService heroService;

    /**
     * EndPoint de criação de heróis
     * @param heroDTO
     * @return ResponseEntity
     */
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity create(@Validated
                                 @RequestBody HeroDTO heroDTO) {
        try {
            HeroDTO heroDTOResponse = this.heroService.create(heroDTO);
            return heroDTOResponse != null ? ResponseEntity.created(getURI(heroDTOResponse.getId())).build() : ResponseEntity.badRequest().build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * EndPoint de busca de heróis por id
     * @param id
     * @return ResponseEntity
     */
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") UUID id){
        try{
            HeroDTO heroDTO = this.heroService.get(id);
            return heroDTO != null ?
                ResponseEntity.ok(heroDTO) :
                ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Endpoint de busca de heróis por nome
     * @param name
     * @return ResponseEntity
     */
    @GetMapping("/name/{name}")
    public ResponseEntity get(@PathVariable("name") String name){
        try{
            HeroDTO heroDTO = this.heroService.get(name);
            return heroDTO != null ?
                    ResponseEntity.ok(heroDTO) :
                    ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    /**
     * Endpoint de alteração de informações de heróis
     * @param id
     * @param heroDTO
     * @return ResponseEntity
     */
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity update(@PathVariable("id") UUID id,
                                 @Validated
                                 @RequestBody HeroDTO heroDTO){
        try{
            HeroDTO heroDTOResponse = this.heroService.update(id, heroDTO);
            return heroDTOResponse != null ?
                    ResponseEntity.ok(heroDTOResponse) :
                    ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * EndPoint de remoção de heróis
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable("id") UUID id){
        try{
            boolean deleted = this.heroService.delete(id);
            return deleted ?
                    ResponseEntity.ok().build() :
                    ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }


    private URI getURI(UUID id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}
