package io.jotech.classicmodels.rest;


import io.jotech.classicmodels.entity.Office;
import io.jotech.classicmodels.service.OfficeService;
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
@Path("/offices")
public class Offices {
    @Inject
    private OfficeService officeService;

    @GET
    public Response getAllOffices(@DefaultValue("0") @QueryParam("start") Integer start,
                                  @DefaultValue ("10") @QueryParam("limit") Integer limit) {

        var allOffices = officeService.getAllOffices(start, limit);
        var res = GridResponse.<Office>builder()
                .rows(allOffices)
                .success(true)
                .totalCount(allOffices.size())
                .build();
        return Response.ok().entity(res).build();
    }

    @GET
    @Path("/{officeCode}")
    public Response getOffice(@PathParam("officeCode") String officeCode) {
        var office = officeService.getOffice(officeCode);
        var res = FormResponse.<Office>builder()
                .data(office)
                .success(true).build();
        return Response.ok().entity(res).build();
    }

    @POST
    public Response createOffice(@Valid Office office, @Context UriInfo uriInfo) {
        var savedOffice = officeService.createOffice(office);
        var  res = FormResponse.<Office>builder()
                .data(savedOffice)
                .success(true).build();
        return Response
                .created(uriInfo.getAbsolutePathBuilder()
                        .path(savedOffice.getOfficeCode().toString())
                        .build()
                )
                .entity(res)
                .build();

    }

    @PUT
    public Response updateOffice(@Valid Office office) {
        var updatedOfficeAccount = officeService.updateOffice(office);
        var res = FormResponse.<Office>builder()
                .data(updatedOfficeAccount)
                .success(true).build();
        return Response
                .ok()
                .entity(res)
                .build();

    }

    @DELETE
    @Path("/{officeCode}")
    public Response deleteOffice(@PathParam("officeCode") String officeCode) {
        boolean deleted = officeService.deleteOffice(officeCode);
        return Response
                .ok()
                .entity(FormResponse.<Boolean>builder()
                        .success(deleted)
                        .build()
                )
                .build();

    }
}
