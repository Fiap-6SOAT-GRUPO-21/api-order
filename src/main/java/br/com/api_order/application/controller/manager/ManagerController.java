package br.com.api_order.application.controller.manager;


import br.com.api_order.application.dtos.manager.ManagerDTO;
import br.com.api_order.application.dtos.manager.ManagerInputDTO;
import br.com.api_order.domain.entity.manager.ManagerDomain;
import br.com.api_order.domain.useCases.manager.CreateNewManager;
import br.com.api_order.domain.useCases.manager.DeleteManagerById;
import br.com.api_order.domain.useCases.manager.FindManagerByCPF;
import br.com.api_order.domain.useCases.manager.UpdateManager;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/manager")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ManagerController {

    private final FindManagerByCPF findManager;
    private final CreateNewManager createNewManager;
    private final UpdateManager updateManager;
    private final DeleteManagerById deleteManager;
    final ModelMapper modelMapper;

    @Operation(summary = "Get a manager by CPF")
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ManagerDTO> findManagerByCpf(@PathVariable String cpf) {
        ManagerDomain managerDomain = findManager.execute(cpf);
        if (managerDomain != null) {
            ManagerDTO managerDTO = modelMapper.map(managerDomain, ManagerDTO.class);
            return ResponseEntity.ok(managerDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Create a new manager")
    @PostMapping
    public ResponseEntity<ManagerDTO> saveNewManager(@RequestBody @Valid ManagerInputDTO managerInputDTO) {
        ManagerDomain domain = modelMapper.map(managerInputDTO, ManagerDomain.class);
        ManagerDomain savedManager = createNewManager.execute(domain);
        ManagerDTO dto = modelMapper.map(savedManager, ManagerDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Operation(summary = "Update a manager by id")
    @PutMapping("/{id}")
    public ResponseEntity<ManagerDTO> updateManager(@PathVariable UUID id, @RequestBody ManagerInputDTO managerInputDTO) {
        ManagerDomain domain = modelMapper.map(managerInputDTO, ManagerDomain.class);
        domain.setId(id);

        ManagerDomain updatedPessoa = updateManager.execute(domain);
        ManagerDTO dto = modelMapper.map(updatedPessoa, ManagerDTO.class);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Operation(summary = "Delete a manager by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManagerById(@PathVariable UUID id) {
        deleteManager.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
