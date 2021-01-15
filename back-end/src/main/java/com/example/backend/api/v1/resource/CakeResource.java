package com.example.backend.api.v1.resource;

import com.example.backend.domain.dto.CakeDto;
import com.example.backend.service.CakeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/cakes")
public class CakeResource {


    private final CakeServiceImpl cakeService;

    public CakeResource(CakeServiceImpl cakeService) {
        this.cakeService = cakeService;
    }



    @GetMapping
    public ResponseEntity<List<CakeDto>> getCakes() {
        List<CakeDto> cakeDtos = cakeService.getAll().stream().map(cake -> new CakeDto(
                cake.getTitle(),
                cake.getDesc(),
                cake.getImage())).collect(Collectors.toList());

        return new ResponseEntity<>(cakeDtos, HttpStatus.OK);
    }

    @PostMapping()
    @Operation(
            summary = "Saves new cake resource",
            description = "Endpoint takes in a CakeDto and saves it to DB",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "cakeDto", description = "Cake Dto Object",
                            content = @Content(schema = @Schema(implementation = CakeDto.class)))
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cake saved successfully"),
            }
    )
    public ResponseEntity<Object> saveCake(@RequestBody CakeDto cakeDto){
        cakeService.save(cakeDto);
        return ResponseEntity.ok().build();

    }
}
