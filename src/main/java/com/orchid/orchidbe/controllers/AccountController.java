package com.orchid.orchidbe.controllers;

import com.orchid.orchidbe.dto.AccountDTO;
import com.orchid.orchidbe.pojos.Account;
import com.orchid.orchidbe.services.IService.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/accounts")
@RequiredArgsConstructor
@Tag(name = "Account Api", description = "Operation related to Account")
@Slf4j
public class AccountController {

    private final AccountService accountService;

    @GetMapping("")
    @Operation(summary = "Get all accounts", description = "Returns a list of all accounts")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all accounts")
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.ok(accountService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get account by ID", description = "Returns an account by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account found"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    public ResponseEntity<Account> getAccountById(@PathVariable int id) {
        Account account = accountService.getById(id);
        if (account == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(account);
    }

    @PostMapping("/register")
    @Operation(summary = "Create new account", description = "Creates a new account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input or email already exists")
    })
    public ResponseEntity<Void> createAccount(@RequestBody @Valid AccountDTO.CreateAccountReq accountReq) {
        accountService.add(accountReq);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update account", description = "Updates an existing account by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input or email already exists"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    public ResponseEntity<Void> updateAccount(@PathVariable int id, @RequestBody AccountDTO.UpdateAccountReq accountReq) {
        accountService.update(id, accountReq);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete account", description = "Deletes an account by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Account deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    public ResponseEntity<Void> deleteAccount(@PathVariable int id) {
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }
}