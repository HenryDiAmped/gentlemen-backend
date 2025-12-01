package com.barbershop.citas.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.*;
import com.mercadopago.resources.preference.Preference;
import com.barbershop.citas.models.dto.PaymentRequestDTO; 

@RestController
@RequestMapping("/api/v1/payment")
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentController {

    public PaymentController() {
        // Token temporal.
        MercadoPagoConfig.setAccessToken("TEST-ESPERANDO-VERIFICACION-TOKEN");
    }

    @PostMapping("/create_preference")
    public ResponseEntity<String> createPreference(@RequestBody PaymentRequestDTO paymentRequest) {
        try {
            List<PreferenceItemRequest> items = new ArrayList<>();

            for (PaymentRequestDTO.ItemDto item : paymentRequest.getItems()) {
                PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                        .title(item.getName())
                        .quantity(item.getQuantity())
                        .unitPrice(item.getPrice())
                        .currencyId("PEN") 
                        .build();
                items.add(itemRequest);
            }

            if (paymentRequest.getShippingCost() != null && paymentRequest.getShippingCost().compareTo(BigDecimal.ZERO) > 0) {
                 PreferenceItemRequest shippingItem = PreferenceItemRequest.builder()
                        .title("Costo de Envío")
                        .quantity(1)
                        .unitPrice(paymentRequest.getShippingCost())
                        .currencyId("PEN")
                        .build();
                items.add(shippingItem);
            }

            PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                    .success("http://localhost:4200/mi-cuenta/pedidos")
                    .failure("http://localhost:4200/checkout") 
                    .pending("http://localhost:4200/mi-cuenta/pedidos")
                    .build();

            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .backUrls(backUrls)
                    .autoReturn("approved")
                    .build();

            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);

            return ResponseEntity.ok(preference.getSandboxInitPoint());

        } catch (Exception e) {
            System.err.println("Mercado Pago falló (Esperado por token falso): " + e.getMessage());
            
            // Devolvemos la página de inicio de MP o Google como prueba
            return ResponseEntity.ok("https://www.mercadopago.com.pe"); 
        }
    }
}