package tech.antoniosgarbi.gestorpeixaria.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.antoniosgarbi.gestorpeixaria.dto.specification.StockEntries;
import tech.antoniosgarbi.gestorpeixaria.dto.stock.ExpirationLotSpecBody;
import tech.antoniosgarbi.gestorpeixaria.dto.stock.ProductEntryRequest;
import tech.antoniosgarbi.gestorpeixaria.service.contract.StockService;

@RestController
@RequestMapping("/stock")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity<Long> productEntry(@RequestBody ProductEntryRequest request, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(stockService.productEntry(request, token));
    }

    @PutMapping("/query")
    public ResponseEntity<Page<StockEntries>> query(@RequestBody ExpirationLotSpecBody body, Pageable pageable) {
        return ResponseEntity.ok(stockService.query(body, pageable));
    }

}