package br.com.alelo.api.resource;

import br.com.alelo.api.dto.ClientDTO;
import br.com.alelo.api.service.IClientService;
import br.com.alelo.api.serviceImpl.ClientServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientResource {
    @Autowired
    private ClientServiceImpl iClientService;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients(){
        return ResponseEntity.ok(iClientService.findAll());
    }

    @PostMapping
    public ResponseEntity<ClientDTO> saveClient(@RequestBody ClientDTO request) throws ParseException {
        ClientDTO response = (ClientDTO) iClientService.save(request);
        if(response == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<ClientDTO> updateClient(@RequestBody ClientDTO request) throws ParseException {
        ClientDTO response = (ClientDTO) iClientService.update(request);
        if(response == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findClient(@PathVariable(value="id") String id) throws ParseException {
        ClientDTO response = (ClientDTO) iClientService.findById(Long.parseLong(id));
        if(response == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable(value="id") String id) throws ParseException {
        iClientService.delete(Long.parseLong(id));

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
