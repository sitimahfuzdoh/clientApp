/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MCC53.client.clientapp.services;

import java.util.List;
import com.MCC53.client.clientapp.models.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author user
 */
@Service
public class DepartmentService {
    
    @Autowired
    RestTemplate restTemplate;
    
    @Value ("${api.baseUrl}/department")
    private String baseUrl;
//    ==============================================================================
    //getAll
    public List <Department> getAll(){
        ResponseEntity <List<Department>> respon = restTemplate
                .exchange(baseUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Department>>(){});
     
        return respon.getBody();
    } 
    
    //getbyid
    public Department getById (Long id){
        ResponseEntity<Department> respon = restTemplate
                .getForEntity(baseUrl +"/"+id,Department.class);
        
        return respon.getBody();
    }
//    ============================================================================
    //create
    public Department createDepartment (Department department){
        ResponseEntity <Department> respon = restTemplate
                .postForEntity(baseUrl,department,Department.class);
        
        return respon.getBody();
    }
//    =============================================================================

    public String deleteDepartment (Long id){
        
        restTemplate.delete(baseUrl+"/"+id,Department.class);
        
        return "delete sukses";
    }
    
    public String updateDepartment (Long id, Department department){
        
        restTemplate.put(baseUrl+"/"+id,department,Department.class);
        
        return "update sukses";
    }
}
