package io.jotech.classicmodels.rest;


import io.jotech.classicmodels.entity.Employee;
import io.jotech.classicmodels.service.EmployeeService;
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
@Path("/employees")
public class Employees {
    @Inject
    private EmployeeService employeeService;

    @GET
    public Response getEmployees( @DefaultValue("0") @QueryParam("start") Integer start,
                                  @DefaultValue ("10") @QueryParam("limit") Integer limit){
        var allEmployees = employeeService.getAllEmployees(start,limit);
        var res = GridResponse.<Employee>builder()
                .rows(allEmployees)
                .success(true)
                .totalCount(allEmployees.size())
                .build();
        return Response.ok().entity(res).build();
    }
}
