package com.digiexpress.partner.web.controllers;

import com.digiexpress.partner.services.PartnerService;
import com.digiexpress.partner.services.dto.PartnerDTO;
import com.digiexpress.partner.services.dto.SearchPartnerRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/partners")
@RequiredArgsConstructor
@RestController
public class PartnerController {
    private final PartnerService partnerService;

    @PostMapping("")
    public void savePartner(@RequestBody @Validated PartnerDTO partnerDTO) {
        partnerService.savePartner(partnerDTO);
    }

    @GetMapping("/{id}")
    public PartnerDTO getPartnerById(@PathVariable @NonNull Long id) {
        return partnerService.getPartnerById(id);
    }

    @GetMapping("/search")
    public PartnerDTO searchPartner(SearchPartnerRequest searchPartnerRequest) {
        return partnerService.searchPartnerByLocation(searchPartnerRequest.getLng(), searchPartnerRequest.getLat());
    }
}
