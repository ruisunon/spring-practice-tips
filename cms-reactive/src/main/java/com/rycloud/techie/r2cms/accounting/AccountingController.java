package com.rycloud.techie.r2cms.accounting;

import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounting")
public class AccountingController {

  private AccountingRepository repo;


  AccountingController(AccountingRepository repo) {
    this.repo = repo;
  }

  @GetMapping
  public ResponseEntity<Iterable<Accounting>> findAll() {
    return ResponseEntity.ok(repo.findAll());
  }

  @PostMapping
  public ResponseEntity<?> add(@RequestBody Accounting accounting) {
    return ResponseEntity.created(URI.create("/accountings/" + repo.save(accounting).getId()))
        .build();
  }
}