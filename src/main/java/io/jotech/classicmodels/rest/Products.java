package io.jotech.classicmodels.rest;


import io.jotech.classicmodels.entity.Product;
import io.jotech.classicmodels.service.ProductService;
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
@Path("/products")
public class Products {
    @Inject
    private ProductService productService;

    @GET
    public Response getAllProducts(@DefaultValue("0") @QueryParam("start") Integer start,
                                   @DefaultValue ("10") @QueryParam("limit") Integer limit) {
        var allProducts = productService.getAllProducts(start,limit);
        var res = GridResponse.<Product>builder()
                .rows(allProducts)
                .success(true)
                .totalCount(allProducts.size())
                .build();
        return Response.ok().entity(res).build();

    }
}
