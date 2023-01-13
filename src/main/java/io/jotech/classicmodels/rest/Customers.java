package io.jotech.classicmodels.rest;


import io.jotech.classicmodels.entity.Customer;
import io.jotech.classicmodels.service.CustomerService;
import io.jotech.classicmodels.vm.FormResponse;
import io.jotech.classicmodels.vm.GridResponse;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/customers")
public class Customers {
    @Inject
    private CustomerService customerService;


    @GET
    public Response getAllCustomers(
            @DefaultValue ("0") @QueryParam("start") Integer start,
            @DefaultValue ("10") @QueryParam("limit") Integer limit

    ) {

        var allCustomers = customerService.getAllCustomers(start,limit);

        var res = GridResponse.<Customer>builder()
                .rows(allCustomers)
                .success(true)
                .totalCount(allCustomers.size())
                .build();
        return Response.ok().entity(res).build();
    }

    @GET
    @Path("/{customerNumber}")
    public Response getCustomer(@PathParam("customerNumber") Integer customerNumber) {
        var customer = customerService.getCustomer(customerNumber);
        var res = FormResponse.<Customer>builder()
                .data(customer)
                .success(true).build();
        return Response.ok().entity(res).build();
    }

    @POST
    public Response createCustomer(@Valid Customer customer, @Context UriInfo uriInfo) {
        var savedCustomer = customerService.createCustomer(customer);
       var  res = FormResponse.<Customer>builder()
                .data(savedCustomer)
                .success(true).build();
        return Response
                .created(uriInfo.getAbsolutePathBuilder()
                        .path(savedCustomer.getCustomerNumber().toString())
                        .build()
                )
                .entity(res)
                .build();

    }

    @PUT
    public Response updateCustomer(@Valid Customer customer) {
        var updatedCustomerAccount = customerService.updateCustomer(customer);
        var res = FormResponse.<Customer>builder()
                .data(updatedCustomerAccount)
                .success(true).build();
        return Response
                .ok()
                .entity(res)
                .build();

    }

    @DELETE
    @Path("/{customerNumber}")
    public Response deleteCustomer(@PathParam("customerNumber") Integer customerNumber) {
        boolean deleted = customerService.deleteCustomer(customerNumber);
        return Response
                .ok()
                .entity(FormResponse.<Boolean>builder()
                        .success(deleted)
                        .build()
                )
                .build();

    }

}
