package com.tienphuckx.swifthelperv1.controller;

import com.tienphuckx.swifthelperv1.builder.MT103ProwideCoreBuilder;
import com.tienphuckx.swifthelperv1.model.TransactionRequest;
import com.tienphuckx.swifthelperv1.service.SwiftMt103Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mt103")
public class SwiftMt103Controller {

    private final SwiftMt103Service mt103Service;

    public SwiftMt103Controller(SwiftMt103Service mt103Service) {
        this.mt103Service = mt103Service;
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generateMT103(@RequestBody TransactionRequest request) {
        return ResponseEntity.ok(mt103Service.generateMT103(request));
    }

    @PostMapping("/generate_v1")
    public ResponseEntity<String> generateMT103ProwideCore(@RequestBody TransactionRequest request) {
        return ResponseEntity.ok(mt103Service.generateMT103ProwideCore(request));
    }
}
