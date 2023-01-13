package io.jotech.classicmodels.rest;


import io.jotech.classicmodels.entity.Payment;
import io.jotech.classicmodels.service.PaymentService;
import io.jotech.classicmodels.vm.GridResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/payments")
public class Payments {
    @Inject
    private PaymentService paymentService;

    @GET
    public Response getAllPayments(@DefaultValue("0") @QueryParam("start") Integer start,
                                   @DefaultValue ("10") @QueryParam("limit") Integer limit) {
        var allPayments = paymentService.getAllPayments(start,limit);
        var res = GridResponse.<Payment>builder()
                .rows(allPayments)
                .success(true)
                .totalCount(allPayments.size())
                .build();
        return Response.ok().entity(res).build();

    }
}

