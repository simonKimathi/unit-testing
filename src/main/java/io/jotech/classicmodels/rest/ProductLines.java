package io.jotech.classicmodels.rest;


import io.jotech.classicmodels.entity.ProductLine;
import io.jotech.classicmodels.service.ProductLineService;
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
@Path("/productlines")
public class ProductLines {
    @Inject
    private ProductLineService productLineService;

    @GET
    public Response getAllProductLines(@DefaultValue("0") @QueryParam("start") Integer start,
                                       @DefaultValue ("10") @QueryParam("limit") Integer limit) {
        var allProductLines = productLineService.getAllProductLines(start,limit);
        var res = GridResponse.<ProductLine>builder()
                .rows(allProductLines)
                .success(true)
                .totalCount(allProductLines.size())
                .build();
        return Response.ok().entity(res).build();

    }
}
