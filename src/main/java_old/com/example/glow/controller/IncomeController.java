package com.example.glow.controller;

import com.example.glow.controller.dto.BaseData;
import com.example.glow.controller.dto.IncomeData;
import com.example.glow.entity.postgreSql.ApplicationsResource;
import com.example.glow.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class IncomeController {

    private IncomeService incomeService;

    @Autowired
    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @GetMapping("/income")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<IncomeData> getIncome(@RequestParam(name = "fio") String fio) {
        String customPredicate = fio.toLowerCase().trim();
        List<IncomeData> res = incomeService.getIncomeData().stream().filter(val -> val.getCustFio().toLowerCase().trim().equals(customPredicate)).collect(Collectors.toList());
        if (res.size() > 0) {
            return ResponseEntity.ok(res.get(0));
        }
        return ResponseEntity.ok(null);
    }
}
